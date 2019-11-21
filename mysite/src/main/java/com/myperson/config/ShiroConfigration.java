package com.myperson.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Filter;

/**
 * Created by Administrator on 2019/4/17 0017.
 */
@Configuration
public class ShiroConfigration {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("MysecurityManager")DefaultWebSecurityManager mysecurityManager){
        ShiroFilterFactoryBean mybean=new ShiroFilterFactoryBean();
        //设置事物管理器对象
        mybean.setSecurityManager(mysecurityManager);
        //配置登录的URL（未登录的用户访问的页面）
        //mybean.setLoginUrl("/auth/login");
        // 配置登录成功的url(登录后用户访问的页面)
        //mybean.setSuccessUrl("/auth/index");
        mybean.setLoginUrl("/ManagerLogin");
        //自定义拦截器
        Map<String,Filter> MyfiltersMap=new LinkedHashMap<String,Filter>();
        //限制同一个账号的同时在线个数
        //MyfiltersMap.put("kickout",kickout)

        //配置访问权限
        //<url,权限类型>
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        //filterChainDefinitionMap.put("/auth/logout","logout");
        // 配置不会被拦截的链接 顺序判断
        //authc:所有url都必须认证通过才可以访问;
        // anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/", "anon");
        //filterChainDefinitionMap.put("/css/**","anon");
        //filterChainDefinitionMap.put("/js/**","anon");
        //filterChainDefinitionMap.put("/img/**","anon");
        //filterChainDefinitionMap.put("/auth/login","anon");
        //filterChainDefinitionMap.put("/auth/kickout","anon");
        filterChainDefinitionMap.put("/index/**","authc");
        filterChainDefinitionMap.put("/*", "anon");
        filterChainDefinitionMap.put("/**", "anon");//表示需要认证才可以访问
        //设置未授权界面，但无用
        mybean.setUnauthorizedUrl("/403");
        //配置shiro的主要拦截器（默认有个拦截器）
        mybean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return mybean;
    }

    //配置核心安全事务管理器
    @Bean(name="MysecurityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("authRealm") shiroRealm authRealm) {
        //System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();

        //Shiro 从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份
        // 那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；
        //也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；可以把Realm看成DataSource ， 即安全数据源。
        //设置realm，realm为域，
        manager.setRealm(authRealm);
        //自定义session管理 使用redis
        manager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        manager.setCacheManager(cacheManager());
        return manager;
    }

    // 配置自定义的权限登录器-身份验证realm
    @Bean(name="authRealm")
    public shiroRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher){
        //System.out.println("启动AuthRealm()...");
        shiroRealm myShrioRealm=new shiroRealm();
        myShrioRealm.setCredentialsMatcher(matcher);
        myShrioRealm.setCachingEnabled(false);

        return myShrioRealm;
    }
    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher(@Qualifier("ehCacheManager")EhCacheManager ehCacheManager) {
        //RetryLimitHashedCredentialsMatcher为另外类的构造函数
        HashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager);
        credentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        credentialsMatcher.setHashIterations(0);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return credentialsMatcher;
    }
    @Bean(name="ehCacheManager")
    public EhCacheManager ehCacheManager(){
        System.out.println("ShiroConfiguration.getEhCacheManager()");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }

    /***
     * 限制同时登陆的人数
     * @return
     */
    //@Bean
    //public Kickou


    ///整体缓存设置
    //设置缓存管理器
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * redis管理器
     * 配置shiro redisManager
     * 使用shiro-redis开源插件
     */
    //session管理器和缓存管理器都会用到
    @Bean
    public RedisManager redisManager(){
        RedisManager myredisManager=new RedisManager();
        myredisManager.setHost("127.0.0.1");
        myredisManager.setPort(6379);
        myredisManager.setTimeout(3600);
        myredisManager.setExpire(3600);//配置缓存过期时间
        myredisManager.setPassword(null);
        return myredisManager;
    }

    ///整体Session设置
    //设置session管理
    @Bean
    public MySessionManager sessionManager(){
        MySessionManager mysessionManager=new MySessionManager();
        mysessionManager.setSessionDAO(redisSessionDAO());
        return mysessionManager;
    }
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO myredisSessionDAO=new RedisSessionDAO();
        myredisSessionDAO.setRedisManager(redisManager());
        return myredisSessionDAO;
    }

    /**
     * Shiro生命周期处理器
     */
     @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor(){
         return new LifecycleBeanPostProcessor();
     }
    /**
     * 授权所用配置
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    /****
     * 使授权注解起作用
     * 不想配置可在pom加入
     *<dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("MysecurityManager") DefaultWebSecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    //@Override
    public void onStartup(ServletContext servletContext){
        servletContext.setSessionTimeout(172800);
    }
}
