package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.AddFavoriteServiceImpl;
import cn.itcast.travel.service.impl.FindFavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteDetalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/routeDetal/*")
public class RouteDetalServlet extends BaseServlet {
    private FindFavoriteServiceImpl findFavoriteService = new FindFavoriteServiceImpl();
    private RouteDetalServiceImpl routeDetalService = new RouteDetalServiceImpl();
    private AddFavoriteServiceImpl addFavoriteService = new AddFavoriteServiceImpl();

    public void findRouteDetal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        if (rid == null || rid.length() == 0) {
            rid = "1";
        }
        Route routeDetal = routeDetalService.findDetal(rid);
        jsonWrite(routeDetal, response);
    }

    public void findFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return;
        }
        String rid = request.getParameter("rid");
        boolean result = findFavoriteService.findFavorite(rid, Integer.toString(user.getUid()));
        jsonWrite(result, response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return;
        }
        String rid = request.getParameter("rid");
        addFavoriteService.addFavorite(rid,Integer.toString(user.getUid()));
    }
}
