package com.myperson.servicesImpl.user;

import com.myperson.dao.MenuDao;
import com.myperson.dao.RoleDao;
import com.myperson.pojo.Menu;
import com.myperson.pojo.MenuFirst;
import com.myperson.pojo.Role;
import com.myperson.services.user.MenuService;
import com.myperson.services.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/9/19 0019.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findMenuList(List<Role> roles) {
        return menuDao.selectAllByRoleName(roles);
    }

}
