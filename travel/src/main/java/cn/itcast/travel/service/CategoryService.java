package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.User;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();
}
