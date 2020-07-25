package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.MyStaticForJDBC;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(MyStaticForJDBC.getDataSource());
    @Override
    public List<User> findByUsername(User user) {
        String sql = "select * from tab_user where username=?";
        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername());
        return  query;
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into tab_user(uid,username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, null, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(),user.getStatus(),user.getCode());
    }

    @Override
    public List<User> findByCode(String string) {
        String sql = "select * from tab_user where code= ?";
        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), string);
        return query;
    }

    @Override
    public boolean updateUserCode(String string) {
        String sql = "update tab_user set status='Y' where code=?";
        int update = jdbcTemplate.update(sql,string);
        if(update!=0){
            return true;
        }else{
            return false;
        }
    }
}
