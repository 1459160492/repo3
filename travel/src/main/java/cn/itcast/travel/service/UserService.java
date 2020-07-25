package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

import java.util.List;

public interface UserService {
//    注册用户
    public boolean regist(User user);
    public boolean acctive(String string);
    public List<User> login(User user);
}
