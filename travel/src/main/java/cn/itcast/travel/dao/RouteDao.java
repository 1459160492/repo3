package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteForPage;

import java.util.List;

public interface RouteDao {
    public List<Route> findAllRoute(RouteForPage routeForPage);
    public Integer findAllRouteNumber(RouteForPage routeForPage);
}
