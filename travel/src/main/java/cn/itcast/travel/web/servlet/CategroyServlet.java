package cn.itcast.travel.web.servlet;


import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/categroyServlet/*")
public class CategroyServlet extends BaseServlet {
    public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jedis jedis = JedisUtil.getJedis();
        Set<String> category1 = jedis.zrange("Category", 0, -1);
        if (category1.size() == 0 || category1 == null) {
            CategoryServiceImpl categoryService = new CategoryServiceImpl();
            List<Category> allFind = categoryService.findAll();

            for (Category category : allFind) {
                jedis.zadd("Category", category.getCid(), category.getCname());
            }
        }
        Set<Tuple> category = jedis.zrangeWithScores("Category", 0, -1);
        jsonWrite(category, response);
    }
}
