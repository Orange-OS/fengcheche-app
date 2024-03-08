package com.zsy.moudle.period.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zsy.common.request.PageInfo;
import com.zsy.common.response.PageModel;
import com.zsy.framework.exception.BusinessException;
import com.zsy.framework.model.UserInfo;
import com.zsy.moudle.admin.entity.BaseDept;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractBusService<M extends BaseMapper<T>, T extends BaseDept> extends AbstractService<M, T> implements IBusService<T> {

    public T getById(Serializable id, UserInfo userInfo) {
        return permissionFilter(super.getById(id), userInfo);
    }

    public T getOne(Wrapper<T> queryWrapper, UserInfo userInfo) {
        return permissionFilter(super.getOne(queryWrapper), userInfo);
    }

    public List<T> listByIds(Collection<? extends Serializable> idList, UserInfo userInfo) {
        return permissionListFilter(super.listByIds(idList), userInfo);
    }

    public List<T> list(Wrapper<T> queryWrapper, UserInfo userInfo) {
        return permissionListFilter(super.list(queryWrapper), userInfo);
    }

    public List<T> list(UserInfo userInfo) {
        return permissionListFilter(super.list(), userInfo);
    }

    /**
     * 分页查询
     *
     * @return
     */
    public PageModel<T> page(PageInfo<T> pageInfo, UserInfo userInfo) {
        IPage page = toPage(pageInfo);
        T entity = pageInfo.getCondition();
        return toPageModel(baseMapper.selectPage(page, Wrappers.<T>lambdaQuery().setEntity(entity).eq(T::getDeptId, userInfo.getDeptId())));
    }

    /**
     * 分页查询
     *
     * @return
     */
    public PageModel<T> page(PageInfo pageInfo, LambdaQueryWrapper<T> wrapper, UserInfo userInfo) {
        IPage page = toPage(pageInfo);
        return toPageModel(baseMapper.selectPage(page, wrapper.eq(T::getDeptId, userInfo.getDeptId())));
    }

    public boolean removeById(Serializable id, UserInfo userInfo) {
        T entity = getById(id, userInfo);
        if (entity == null) {
            throw new BusinessException("删除的数据不存在");
        }
        checkRemove(entity, userInfo);
        return removeById(id);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean removeByIdsBus(Collection<? extends Serializable> idList, UserInfo userInfo) {
        for (Serializable id : idList) {
            removeById(id, userInfo);
        }
        return true;
    }

    public boolean save(T entity, UserInfo userInfo) {
        checkSave(entity, userInfo);
        return save(entity);
    }

    public boolean updateById(T entity, UserInfo userInfo) {
        T exist = getById(entity.getId(), userInfo);
        if (exist == null) {
            throw new BusinessException("更新的数据不存在");
        }
        checkUpdate(entity, userInfo);
        return updateById(entity);
    }

    public boolean update(T entity, LambdaQueryWrapper<T> updateWrapper, UserInfo userInfo) {
        return super.update(entity, updateWrapper.eq(T::getDeptId, userInfo.getDeptId()));
    }

    protected void checkSave(T entity, UserInfo userInfo) {
    }

    protected void checkUpdate(T entity, UserInfo userInfo) {
    }

    protected void checkRemove(T entity, UserInfo userInfo) {
    }

    public boolean saveOrUpdate(T entity, UserInfo userInfo) {
        if (null == entity.getId()) {
            return save(entity, userInfo);
        } else {
            return updateById(entity, userInfo);
        }
    }

    protected T permissionFilter(T entity, UserInfo userInfo) {
        if (entity == null) {
            return null;
        }
        if (userInfo == null || userInfo.getDeptId() == null) {
            return null;
        }
        if (!userInfo.getDeptId().equals(entity.getDeptId())) {
            return null;
        }
        return entity;
    }

    protected List<T> permissionListFilter(List<T> entityList, UserInfo userInfo) {
        List<T> list = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(entityList)) {
            for (T entity : entityList) {
                T result = permissionFilter(entity, userInfo);
                if (result != null) {
                    list.add(result);
                }
            }
        }
        return list;
    }


}
