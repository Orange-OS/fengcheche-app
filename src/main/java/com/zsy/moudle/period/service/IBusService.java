package com.zsy.moudle.period.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsy.common.request.PageInfo;
import com.zsy.common.response.PageModel;
import com.zsy.framework.model.UserInfo;
import com.zsy.moudle.admin.entity.BaseDept;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 业务类service接口
 */
public interface IBusService<T extends BaseDept> extends IService<T> {

    T getById(Serializable id, UserInfo userInfo);

    List<T> listByIds(Collection<? extends Serializable> idList, UserInfo userInfo);

    T getOne(Wrapper<T> queryWrapper, UserInfo userInfo);

    List<T> list(Wrapper<T> queryWrapper, UserInfo userInfo);

    List<T> list(UserInfo userInfo);

    /**
     * 分页查询
     *
     * @return
     */
    PageModel<T> page(PageInfo<T> pageInfo, UserInfo userInfo);

    /**
     * 分页查询
     *
     * @return
     */
    PageModel<T> page(PageInfo pageInfo, LambdaQueryWrapper<T> wrapper, UserInfo userInfo);


    boolean saveOrUpdate(T entity, UserInfo userInfo);

    boolean removeById(Serializable id, UserInfo userInfo);

    boolean removeByIdsBus(Collection<? extends Serializable> idList, UserInfo userInfo);

    boolean save(T entity, UserInfo userInfo);

    boolean updateById(T entity, UserInfo userInfo);

    boolean update(T entity, LambdaQueryWrapper<T> updateWrapper, UserInfo userInfo);


}
