package com.myperson.config;

import com.myperson.pojo.Role;
import com.myperson.pojo.UserMain;
import com.myperson.services.user.RoleService;
import com.myperson.services.user.UserMainService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2019/4/17 0017.
 */
//shiro登录认证
// 自定义Realm，在configration类中有调用
public class shiroRealm extends AuthorizingRealm {
    //Authentication（认证）：用户身份识别，通常被称为用户“登录”
    //Authorization（授权）：访问控制。比如某个用户是否具有某个操作的使用权限。
    //Session Management（会话管理）：特定于用户的会话管理,甚至在非web 或 EJB 应用程序。
    //Cryptography（加密）：在对数据源使用加密算法加密的同时，保证易于使用。

    //LOG日志
    private static  final org.slf4j.Logger logger= LoggerFactory.getLogger(shiroRealm.class);

    //如果项目中用到了事物，@Autowired注解会使事物失效，可以自己用get方法获取值
    @Autowired
    private UserMainService userService;
    @Autowired
    private RoleService roleService;
    //@Autowired
    //private SysRoleService sysRoleService;

    //只有当需要检测用户权限的时候才会调用此方法，
    // 例如checkRole,checkPermission之类的
    //Authorization（授权）：访问控制。比如某个用户是否具有某个操作的使用权限。
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
       /* for (SysRole role : userInfo.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getRoleCode());
            for (SysPermission p : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(p.getPermissionCode());
            }
        }*/
        logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
        //获取登录时的数据
        //.getPrincipal()实际上是调用getPrimaryPrincipal（）
        //.getPrimaryPrincipal()实际上是获取principalCollection的第一个元素，迭代方式获取
        Object principal = principalCollection.getPrimaryPrincipal();
        //创建权限信息对象info,用来存放查出的所有用户角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof String){
            String username =principal.toString();
            System.out.println("登录进的帐号信息username="+username);
        }
        if (principal instanceof String) {
            //获取登录信息相应的用户对象实体
            String username =principal.toString();
            UserMain userLogin=userService.findUserByUserName(username);
            //获取用户所拥有的角色
            //List<Role> roles=userLogin.getRoleList();
            List<Role> roles =roleService.selectRoleByUserId(userLogin.getUserID());
            //设置权限组
            Set<String> permissions=new HashSet<>();
            //获取每个角色的权限，并为返回的authorizationInfo对象赋予角色
            for(Role role :roles){
                authorizationInfo.addRole(role.getRoleName());
            }
            //为返回的authorizationInfo对象赋予权限组
            authorizationInfo.addStringPermissions(permissions);
        }
        //return authorizationInfo;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    /* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
    //AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
    //Authentication（认证）：用户身份识别，通常被称为用户“登录”
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //String username=(String) authenticationToken.getPrincipal();
        //logger.info("authenticationToken带来的数据："+username);
        //System.out.println("Shiro 凭证认证");
        UsernamePasswordToken myToken = (UsernamePasswordToken)authenticationToken;
        System.out.println("Shiro获取用户名");
        String username=myToken.getUsername();
        //String password=String.valueOf(myToken.getPassword());
        //从数据库获取相应的对象
        System.out.println("从数据库获取相应的对象");
        UserMain userInfo=userService.findUserByUserName(username);
        System.out.println("判断对象");
        if(userInfo==null){
            System.out.println("帐号不存在");
            throw new AuthenticationException("帐号不存在");
        }
        else{
            if(userInfo.getUserEnable()!=1){
                System.out.println("帐号被冻结");
                throw new AuthenticationException("帐号被冻结");
            }
            logger.info("---------------- Shiro 凭证认证成功 ----------------------");
            System.out.println("Shiro 凭证认证成功");
        }

        //String passwordDataSource=userService.findPass(username);
        //logger.info("从数据库查询到的数据密码"+passwordDataSource);

        return new SimpleAuthenticationInfo(username,userInfo.getPassWord(),this.getClass().getName());
    }
}
