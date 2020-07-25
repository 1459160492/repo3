package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.MyStaticForJDBC;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(MyStaticForJDBC.getDataSource());


    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        List<Category> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return  query;
    }
}
