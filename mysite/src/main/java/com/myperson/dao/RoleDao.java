package com.myperson.dao;

import com.myperson.pojo.Role;
import com.myperson.pojo.UserMain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/9/19 0019.
 */
@Mapper
public interface RoleDao {
    List<Role> selectRoleByUserId(int userID);
}
