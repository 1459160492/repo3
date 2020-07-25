package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        List<User> byUsername = userDaoImpl.findByUsername(user);
        if (byUsername != null && byUsername.size() != 0) {
            return false;
        } else {
            String uuid = UuidUtil.getUuid();
            System.out.println(uuid);
            user.setCode(uuid);
            user.setStatus("N");
            userDaoImpl.saveUser(user);
            String text = "<a href='http://localhost:8888/travel/user/mailAcctivelServlet?code='"+user.getCode()+">点击激活</a>";
            MailUtils.sendMail(user.getEmail(),text,"激活邮件");
            return true;
        }
    }

    @Override
    public boolean acctive(String string) {
        List<User> byCode = userDaoImpl.findByCode(string);
        boolean result = userDaoImpl.updateUserCode(string);
        return result;
    }

    @Override
    public List<User> login(User user) {
        List<User> byUsername = userDaoImpl.findByUsername(user);
        return byUsername;
    }
}
