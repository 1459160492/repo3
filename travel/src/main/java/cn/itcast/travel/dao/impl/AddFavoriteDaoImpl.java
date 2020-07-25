package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.AddFavoriteDao;
import cn.itcast.travel.util.MyStaticForJDBC;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFavoriteDaoImpl implements AddFavoriteDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(MyStaticForJDBC.getDataSource());
    @Override
    public void addFavorite(String rid, String uid) {
        String sql = "INSERT INTO TAB_FAVORITE(RID,DATE,UID) VALUES(?,?,?)";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        jdbcTemplate.update(sql,rid,date,uid);
    }
}
