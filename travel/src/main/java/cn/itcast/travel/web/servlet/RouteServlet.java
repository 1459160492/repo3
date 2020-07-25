package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.RouteForPage;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.FindFavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/route/*")
public class RouteServlet extends BaseServlet {
    private RouteServiceImpl routeService = new RouteServiceImpl();

    public void findRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String nowPage = request.getParameter("nowPage");
        String numberOfOnePage = request.getParameter("numberOfOnePage");
        String searchKey = request.getParameter("rname");
        if (searchKey == null || searchKey.length() == 0) {
            searchKey = "";
        }

        if (cid == null || cid.length() == 0) {
            cid = "5";
        }
        if (nowPage == null || nowPage.length() == 0) {
            nowPage = "1";
        }
        if (numberOfOnePage == null || numberOfOnePage.length() == 0) {
            numberOfOnePage = "5";
        }
        RouteForPage routeForPage = new RouteForPage(Integer.parseInt(nowPage), Integer.parseInt(cid), Integer.parseInt(numberOfOnePage), searchKey);
        RouteForPage page = routeService.findPage(routeForPage);
        jsonWrite(page, response);
    }


}
