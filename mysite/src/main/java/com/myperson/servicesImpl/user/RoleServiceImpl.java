package com.myperson.servicesImpl.user;

import com.myperson.dao.RoleDao;
import com.myperson.dao.UserMainDao;
import com.myperson.pojo.ResponseBean;
import com.myperson.pojo.Role;
import com.myperson.pojo.UserMain;
import com.myperson.services.user.RoleService;
import com.myperson.services.user.UserMainService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/9/19 0019.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao  roleDao;

    @Override
    public List<Role> selectRoleByUserId(int userID) {
        return roleDao.selectRoleByUserId(userID);
    }
}
