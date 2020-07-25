package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findByUsername(User user);

    public void saveUser(User user);

    public List<User> findByCode(String string);

    public boolean updateUserCode(String string);
}
