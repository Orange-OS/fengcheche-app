package com.zsy.moudle.admin.service.impl;

import com.zsy.moudle.admin.entity.Menu;
import com.zsy.moudle.admin.mapper.MenuMapper;
import com.zsy.moudle.admin.service.AbstractService;
import com.zsy.moudle.admin.service.IMenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends AbstractService<MenuMapper, Menu> implements IMenuService {
}
