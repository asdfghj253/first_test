package com.myperson.services.user;

import com.myperson.pojo.ResponseBean;
import com.myperson.pojo.UserMain;


/**
 * Created by Administrator on 2018/12/19 0019.
 */
public interface UserMainService {
    //查
    UserMain findUserByUserName(String userName);
    public ResponseBean login(String userName, String password);
}
