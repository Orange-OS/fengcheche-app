package com.zsy.moudle.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsy.common.request.PageInfo;
import com.zsy.common.response.PageModel;
import com.zsy.framework.exception.BusinessException;
import com.zsy.framework.validation.InsertAction;
import com.zsy.framework.validation.UpdateAction;
import org.hibernate.validator.HibernateValidator;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AbstractService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IService<T> {

    public boolean save(T entity) {
        checkSave(entity);
        return super.save(entity);
    }

    public boolean updateById(T entity) {
        checkUpdate(entity);
        return super.updateById(entity);
    }

    public boolean removeById(Serializable id) {
        T entity = getById(id);
        checkRemove(entity);
        return super.removeById(id);
    }

    /**
     * 分页查询
     *
     * @return
     */
    public PageModel<T> page(PageInfo<T> pageInfo) {
        return page(pageInfo, pageInfo.getCondition());
    }

    /**
     * 分页查询
     *
     * @return
     */
    public PageModel<T> page(PageInfo pageInfo, T entity) {
        return page(pageInfo, new QueryWrapper(entity));
    }

    /**
     * 分页查询
     *
     * @return
     */
    public PageModel<T> page(PageInfo pageInfo, Wrapper<T> wrapper) {
        IPage page = toPage(pageInfo);
        IPage resultPage = baseMapper.selectPage(page, wrapper);
        List<T> list = resultPage.getRecords();
        for (T entity : list) {
            fillback(entity);
        }
        return toPageModel(resultPage);
    }

    /**
     * 分页对象转换
     *
     * @param pageInfo
     * @return
     */
    protected IPage<T> toPage(PageInfo pageInfo) {
        if (pageInfo == null) {
            pageInfo = new PageInfo();
        }
        Page<T> page = new Page(pageInfo.getCurrent(), pageInfo.getSize());
        page.setOrders(pageInfo.getOrders());
        return page;
    }

    protected PageModel<T> toPageModel(IPage<T> page) {
        PageModel<T> pageModel = new PageModel<>();
        pageModel.setList(page.getRecords());
        pageModel.setCurrent(page.getCurrent());
        pageModel.setSize(page.getSize());
        pageModel.setPages(page.getPages());
        pageModel.setTotal(page.getTotal());
        return pageModel;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdsBus(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            removeById(id);
        }
        return true;
    }

    protected void checkSave(T entity) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(entity, InsertAction.class);
        for (ConstraintViolation<Object> validateObj : set) {
            throw new BusinessException(validateObj.getMessage());
        }
    }

    protected void checkUpdate(T entity) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(entity, UpdateAction.class);
        for (ConstraintViolation<Object> validateObj : set) {
            throw new BusinessException(validateObj.getMessage());
        }
    }

    protected void fillback(T entity) {

    }

    protected void checkRemove(T entity) {
    }
}
