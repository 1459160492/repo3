package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.AddFavoriteDaoImpl;
import cn.itcast.travel.service.AddFavoriteService;

public class AddFavoriteServiceImpl implements AddFavoriteService {
    private AddFavoriteDaoImpl addFavoriteDao = new AddFavoriteDaoImpl();
    @Override
    public void addFavorite(String rid, String uid) {
        addFavoriteDao.addFavorite(rid,uid);
    }
}
