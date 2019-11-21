package com.myperson.dao;

import com.myperson.pojo.UserMain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2019/9/19 0019.
 */
@Mapper
public interface UserMainDao {
    UserMain findUserByUserName(String userName);
}
