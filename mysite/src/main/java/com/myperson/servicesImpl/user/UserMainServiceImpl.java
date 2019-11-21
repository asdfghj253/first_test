package com.myperson.servicesImpl.user;

import com.myperson.dao.UserMainDao;
import com.myperson.pojo.ResponseBean;
import com.myperson.pojo.UserMain;
import com.myperson.services.user.UserMainService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/9/19 0019.
 */
@Service
public class UserMainServiceImpl implements UserMainService {
    @Autowired
    private UserMainDao userDao;

    @Override
    public UserMain findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Override
    public ResponseBean login(String userName, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("subject:"+subject);
        ResponseBean responseBean = null;
        try{
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            return new ResponseBean(401,"密码错误",null);
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            return new ResponseBean(401,"账号异常",null);
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            return new ResponseBean(401,"错误次数过多,账号被锁定,请于10分钟后再进行尝试",null);
        }catch (LockedAccountException lae){
            return new ResponseBean(401,"帐号未激活",null);
        }/*catch (AuthenticationException ae){
            return new ResponseBean(401,"帐号不存在",null);
        }*/
        UserMain user = findUserByUserName(userName);
        subject.getSession().setAttribute("user", user);
        SecurityUtils.getSubject().getSession().setTimeout(-1000l);
        return new ResponseBean(200,"登陆成功",user);
    }
}
