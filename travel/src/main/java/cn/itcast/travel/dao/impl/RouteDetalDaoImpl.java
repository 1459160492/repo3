package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDetalDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.MyStaticForJDBC;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDetalDaoImpl implements RouteDetalDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(MyStaticForJDBC.getDataSource());

    @Override
    public List<RouteImg> findDetalImg(String rid) {
        String sql = "SELECT * FROM TAB_ROUTE_IMG WHERE RID = ?";
        List<RouteImg> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
        return query;
    }

    @Override
    public Seller findSeller(Integer sid) {
        String sql = "SELECT * FROM TAB_SELLER WHERE SID = ?";
        List<Seller> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
        return query.get(0);
    }

    @Override
    public Route findByRid(String rid) {
        String sql = "SELECT * FROM TAB_ROUTE WHERE RID = ?";
        List<Route> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        return query.get(0);
    }
}
