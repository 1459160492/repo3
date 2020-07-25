package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FindFavoriteDao;
import cn.itcast.travel.dao.impl.FindFavoriteDaoImpl;
import cn.itcast.travel.service.FindFavoriteService;

public class FindFavoriteServiceImpl implements FindFavoriteService {

    private FindFavoriteDaoImpl findFavoriteDao = new FindFavoriteDaoImpl();
    @Override
    public boolean findFavorite(String rid, String uid) {
        boolean favorite = findFavoriteDao.findFavorite(rid, uid);
        return favorite;
    }
}
