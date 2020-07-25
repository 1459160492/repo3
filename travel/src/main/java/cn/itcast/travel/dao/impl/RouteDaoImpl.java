package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteForPage;
import cn.itcast.travel.util.MyStaticForJDBC;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(MyStaticForJDBC.getDataSource());

    @Override
    public List<Route> findAllRoute(RouteForPage routeForPage) {
        String sql = "SELECT * FROM TAB_ROUTE WHERE 1=1 ";
        List list = new ArrayList();
        Integer nowPage = routeForPage.getNowPage();
        Integer numberOfOnePage = routeForPage.getNumberOfOnePage();
        Integer startNumber = (nowPage-1)*numberOfOnePage;
        if(routeForPage.getCidForPage()!=0){
            sql+=" and  CID=? ";
            list.add(routeForPage.getCidForPage());
        }
        if(routeForPage.getSearchKey()!=""){
            sql+=" and rname like ? ";
            String rname = "%"+routeForPage.getSearchKey()+"%";
            list.add(rname);
        }
        sql+=" limit ?,? ";
        list.add(startNumber);
        list.add(numberOfOnePage);
        List<Route> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class),list.toArray());
        return query;
    }

    @Override
    public Integer findAllRouteNumber(RouteForPage routeForPage) {
        String sql = "SELECT COUNT(*) FROM TAB_ROUTE WHERE 1=1 ";
        ArrayList<Object> list = new ArrayList<>();
        if(routeForPage.getCidForPage()!=0){
            sql+=" and CID=? ";
            list.add(routeForPage.getCidForPage());
        }
        if(routeForPage.getSearchKey()!=""){
            sql+=" and rname like ? ";
            String rname = "%"+routeForPage.getSearchKey()+"%";
            list.add(rname);
        }
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, list.toArray());
        return integer;
    }
}
