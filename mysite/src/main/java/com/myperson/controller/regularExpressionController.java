package com.myperson.controller;

import com.myperson.pojo.ResponseBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2019/9/9 0009.
 */
@Controller
public class regularExpressionController {

    @RequestMapping(value="/regularExpression",method = RequestMethod.GET)
    public String regualrExpression(){return "regularExpression";}

    @RequestMapping(value="/RefuseClassification",method = RequestMethod.GET)
    public String RefuseClassification(){return "refuseClassification";}
    @RequestMapping(value="/OK",method = RequestMethod.GET)
    public String ToOK(){return "OK";}
    @RequestMapping(value="/403",method = RequestMethod.GET)
    public String ToErrorPage(){return "403";}


    /*@RequiresRoles("admin")//权限管理;
    @RequestMapping(value="/ManagerIndex",method = RequestMethod.GET)
    public String ManagerIndex(){return "ManagerIndex";}
    */
    /*@RequestMapping(value = "/userList",method = RequestMethod.GET)
    @RequiresPermissions("user:view")//权限管理;
    public String userInfo(){
        return "OK";
    }
    */
}
