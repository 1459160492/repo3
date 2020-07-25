package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;

import java.util.List;

public interface RouteDetalDao {
    public List<RouteImg> findDetalImg(String rid);
    public Seller findSeller(Integer sid);
    public Route findByRid(String rid);
}
