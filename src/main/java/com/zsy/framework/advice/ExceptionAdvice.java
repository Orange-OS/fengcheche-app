package com.zsy.framework.advice;

import com.zsy.common.response.ResponseCodeEnum;
import com.zsy.common.response.ResponseModel;
import com.zsy.framework.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 统一异常处理
 *
 * @author Mayday
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseModel errorHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseModel.fail("系统异常");
    }

    /**
     * 参数检验异常
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseModel argumentValidHandler(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            return ResponseModel.fail(ResponseCodeEnum.BUS_EXCEPTION, list.get(0).getDefaultMessage());
        }
        return ResponseModel.fail("参数错误");
    }

    /**
     * 捕捉UnauthorizedException
     *
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseModel<String> handleUnauthorized(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseModel.fail(ResponseCodeEnum.AUTH_EXCEPTION);
    }

    /**
     * 捕捉shiro的异常
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ResponseModel<String> handleShiroException(ShiroException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseModel.fail(ResponseCodeEnum.AUTH_EXCEPTION);
    }

    /**
     * 捕捉BusinessException自定义抛出的异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseModel handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseModel.fail(ResponseCodeEnum.BUS_EXCEPTION, ex.getMessage());
    }
}
