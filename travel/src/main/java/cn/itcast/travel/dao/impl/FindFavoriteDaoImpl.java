package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FindFavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.MyStaticForJDBC;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FindFavoriteDaoImpl implements FindFavoriteDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(MyStaticForJDBC.getDataSource());

    @Override
    public boolean findFavorite(String rid, String uid) {
        String sql = "SELECT * FROM TAB_FAVORITE WHERE RID=? AND UID=? ";
        List<Favorite> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        if (query.size() != 0) {
            return true;
        } else {
            return false;
        }
    }
}
