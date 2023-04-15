package com.kaikeba.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.User;
import com.kaikeba.dao.UserDao;
import com.kaikeba.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<User> findPage(User condition, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            userDao.selectList(Wrappers.<User>query());
        });
    }

    @Override
    public List<User> find(User condition) {
        return userDao.selectList(Wrappers.query());
    }

    @Override
    public int add(User user) {
        return userDao.insert(user);
    }

    @Override
    public User findById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public int update(User user) {
        return userDao.updateById(user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.deleteById(id);
    }
}
