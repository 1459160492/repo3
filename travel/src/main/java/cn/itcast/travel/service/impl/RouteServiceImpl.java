package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteForPage;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDaoImpl routeDao = new RouteDaoImpl();

    @Override
    public RouteForPage findPage(RouteForPage routeForPage) {
        Integer allRouteNumber = routeDao.findAllRouteNumber(routeForPage);
        List<Route> allRoute = routeDao.findAllRoute(routeForPage);

        Integer pagesMode = (allRouteNumber)%(routeForPage.getNumberOfOnePage());
        Integer page = allRouteNumber/routeForPage.getNumberOfOnePage();
        Integer pages = (pagesMode==0 ? page:(page+1));
        RouteForPage routeForPage1 = new RouteForPage(routeForPage.getNowPage(),routeForPage.getCidForPage(),pages,routeForPage.getNumberOfOnePage(),allRouteNumber,allRoute,routeForPage.getSearchKey());
        return routeForPage1;
    }
}
