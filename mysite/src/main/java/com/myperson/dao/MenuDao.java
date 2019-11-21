package com.myperson.dao;

import com.myperson.pojo.Menu;
import com.myperson.pojo.MenuFirst;
import com.myperson.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/12/20 0020.
 */
@Mapper
public interface MenuDao {
    List<Menu> selectAllByRoleName(List<Role> roles);
}
