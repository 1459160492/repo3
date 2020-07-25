package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends BaseServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    public void exitServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
        requestDispatcher.forward(request, response);
    }

    public void loginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession sessionCheck = request.getSession();
        Object sessionCheckResult = sessionCheck.getAttribute("CHECKCODE_SERVER");
        sessionCheck.invalidate();
        ResultInfo resultInfo = new ResultInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        String sessionCheckResult1 = (String) sessionCheckResult;
        if (check.equalsIgnoreCase(sessionCheckResult1)) {
            Map<String, String[]> parameterMap = request.getParameterMap();

            User user = new User();
            try {
                BeanUtils.populate(user, parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            List<User> resultUser = userService.login(user);
            if (resultUser != null || resultUser.size() != 0) {
                if ("Y".equalsIgnoreCase(resultUser.get(0).getStatus())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", resultUser.get(0));
                    resultInfo.setFlag(true);
                    response.setContentType("application/json;charset=utf-8");
                    objectMapper.writeValue(response.getWriter(), resultInfo);
                } else {
                    resultInfo.setErrorMsg("登录失败，该用户未被激活！");
                    resultInfo.setFlag(false);
                    response.setContentType("application/json;charset=utf-8");
                    objectMapper.writeValue(response.getWriter(), resultInfo);
                }
            } else {
                resultInfo.setErrorMsg("登录失败，没有查询到该用户名！");
                resultInfo.setFlag(false);
                response.setContentType("application/json;charset=utf-8");
                objectMapper.writeValue(response.getWriter(), resultInfo);
            }
        } else {
            response.setContentType("application/json;charset=utf-8");
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("登录失败!验证码错误");
            String stringJson = objectMapper.writeValueAsString(resultInfo);
            response.getWriter().write(stringJson);
            return;
        }
    }

    public void mailAcctivelServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = null;
        String code = request.getParameter("code");
        if (code != null) {
            boolean result = userService.acctive(code);
            if (result) {
                text = "激活成功<a href='login.html'>登录</a>";
            } else {
                text = "激活失败";
            }
            response.setContentType("test/html;charset=utf-8");
            response.getWriter().write(text);
        }
    }

    public void registerServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String check = request.getParameter("check");
        ObjectMapper objectMapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        HttpSession session = request.getSession();
        Object checkcode_server = session.getAttribute("CHECKCODE_SERVER");
        session.invalidate();

        if (check.equalsIgnoreCase((String) checkcode_server)) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user = new User();
            try {
                BeanUtils.populate(user, parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            boolean statue = userService.regist(user);
            if (statue) {
                response.setContentType("application/json;charset=utf-8");
                resultInfo.setFlag(true);
                String stringJson = objectMapper.writeValueAsString(resultInfo);
                response.getWriter().write(stringJson);
            } else {
                response.setContentType("application/json;charset=utf-8");
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("注册失败!用户名已经存在");
                String stringJson = objectMapper.writeValueAsString(resultInfo);
                response.getWriter().write(stringJson);
            }
        } else {
            response.setContentType("application/json;charset=utf-8");
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败!验证码错误");
            String stringJson = objectMapper.writeValueAsString(resultInfo);
            response.getWriter().write(stringJson);
            return;
        }
    }

    public void showUserServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        User resultUser = (User) user;
        jsonWrite(resultUser, response);
    }
}
