package com.myperson.services.user;

import com.myperson.pojo.Menu;
import com.myperson.pojo.MenuFirst;
import com.myperson.pojo.Role;

import java.util.List;

/**
 * Created by Administrator on 2018/12/20 0020.
 */
public interface MenuService {
    List<Menu> findMenuList(List<Role> roles);
}
