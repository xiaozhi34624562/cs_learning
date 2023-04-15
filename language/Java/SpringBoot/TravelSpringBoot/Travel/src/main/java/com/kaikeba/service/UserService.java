package com.kaikeba.service;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.User;

import java.util.List;

public interface UserService {

    public PageInfo<User> findPage(User condition, int pageNum, int pageSize);

    public List<User> find(User condition);

    public int add(User user);

    public User findById(Integer id);

    public int update(User user);

    public int delete(Integer id);
}
