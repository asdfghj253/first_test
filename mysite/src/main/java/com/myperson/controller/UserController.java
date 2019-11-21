package com.myperson.controller;

import com.myperson.pojo.ResponseBean;
import com.myperson.pojo.Role;
import com.myperson.pojo.UserMain;
import com.myperson.services.user.MenuService;
import com.myperson.services.user.RoleService;
import com.myperson.services.user.UserMainService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2019/9/9 0009.
 */
@Controller
@RequestMapping
public class UserController {
    @Autowired
    private UserMainService userMainService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String TestPage(){return "ManagerLogin";}
    @RequestMapping(value ="/ManagerLogin", method = RequestMethod.GET)
    public String ManagerLogin(HttpServletRequest req,HttpServletResponse resp) {
        return "ManagerLogin";
    }
    @RequestMapping(value ="/ManagerIndex", method = RequestMethod.GET)
    @RequiresAuthentication
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); )
        {
            //System.out.println("Session0:" + (String) e.nextElement());
            String next=(String) e.nextElement();
            System.out.println("Session000:"+next);
                System.out.println("Session:" + next +
                        "-----" + req.getSession().getAttribute(next));
        }
        return "ManagerIndex";
    }
    public RedirectAttributes saveattributes=null;
    @RequestMapping(value = "/ManagerLogin", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp, RedirectAttributes attributes){
        String userName =  req.getParameter("userName");
        String password =  req.getParameter("password");
        System.out.println("userName:"+userName);

        ResponseBean responseBean = userMainService.login(userName,password);
        ModelAndView modelAndView = new ModelAndView();
        if(responseBean.getCode() == 200){
//            modelAndView.addObject("user",responseBean.getData());
//            modelAndView.addObject("menus",menuService.findMenuList(null));
            UserMain usermain = (UserMain) responseBean.getData();
            attributes.addFlashAttribute("xxx",usermain);
            attributes.addFlashAttribute("menuss",usermain);
            List<Role> roles =  roleService.selectRoleByUserId(usermain.getUserID());
            if(roles.size() < 1){
                modelAndView.setViewName("redirect:/ManagerLogin");
                modelAndView.addObject("msg","您的帐号暂无角色,请联系管理员");
                return modelAndView;
            }else{
                modelAndView.setViewName("redirect:/ManagerIndex");
                attributes.addFlashAttribute("menus",menuService.findMenuList(roles));
                for(Role role : roles){
                    if(role.getRoleName().equals("admin")){
                        attributes.addFlashAttribute("menus",menuService.findMenuList(null));
                        break;
                    }
                }
                HttpSession session = req.getSession(true);
                for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); )
                {
                    //System.out.println("Session0:" + (String) e.nextElement());
                    String next=(String) e.nextElement();
                    System.out.println("登录：Session000:"+next);
                    System.out.println("登录：Session:" + next +
                            "-----" + req.getSession().getAttribute(next));
                }
                //modelAndView.req
                /*Subject subject = SecurityUtils.getSubject();
                subject.getSession().setAttribute("menus",menuService.findMenuList(roles));
                subject.getSession().setAttribute("userx",usermain);*/
                //HttpSession session = req.getSession(true);
                //System.out.println("xxx:"+menuService.findMenuList(null));
                //session.setAttribute("menus",menuService.findMenuList(null));
                //System.out.println("getFlashAttributes:"+attributes.getFlashAttributes());
                return modelAndView;
            }
        }else{
            modelAndView.setViewName("redirect:/ManagerLogin");
            modelAndView.addObject("msg",responseBean.getMsg());
            //modelAndView.getModel().put("msg", responseBean.getMsg());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(){
        SecurityUtils.getSubject().logout();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/ManagerLogin");
        return modelAndView;
    }
}
