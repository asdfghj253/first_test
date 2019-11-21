package com.myperson.services.user;

import com.myperson.pojo.ResponseBean;
import com.myperson.pojo.Role;
import com.myperson.pojo.UserMain;

import java.util.List;


/**
 * Created by Administrator on 2018/12/19 0019.
 */
public interface RoleService {
    //查
    List<Role> selectRoleByUserId(int userID);
}
