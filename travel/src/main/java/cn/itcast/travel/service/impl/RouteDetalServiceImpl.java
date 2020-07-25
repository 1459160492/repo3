package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.RouteDetalDaoImpl;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteDetalService;

import java.util.List;

public class RouteDetalServiceImpl implements RouteDetalService {
    private RouteDetalDaoImpl routeDetalDao = new RouteDetalDaoImpl();

    @Override
    public Route findDetal(String rid) {
        Route byRid = routeDetalDao.findByRid(rid);
        List<RouteImg> detalImg = routeDetalDao.findDetalImg(rid);
        Seller seller = routeDetalDao.findSeller(byRid.getSid());
        byRid.setSeller(seller);
        byRid.setRouteImgList(detalImg);
        return byRid;
    }
}
