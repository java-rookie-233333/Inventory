package com.jorden.eshop.service.impl;

import com.jorden.eshop.dao.UserMapper;
import com.jorden.eshop.model.User;
import com.jorden.eshop.service.UserService;
import com.jorden.eshop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by jorden.li on 2018/05/01.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
