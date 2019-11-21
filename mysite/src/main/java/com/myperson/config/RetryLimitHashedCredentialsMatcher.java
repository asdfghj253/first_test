package com.myperson.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2019/4/17 0017.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
    // 声明一个缓存接口，这个接口是Shiro缓存管理的一部分，它的具体实现可以通过外部容器注入
    private Cache<String, AtomicInteger> passwordRetryCache;

    //获取缓存配置管理
    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache=cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        //获取realm返回的第一个参数（我是username）
        String username = (String) token.getPrincipal();
        // passwordRetryCache.get 取缓存对象 返回的就是AtomicInteger原子类型
        //AtomicInteger原子类型 类似volatile修饰，但更加有效率，防止线程阻塞，使数据同步
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        // 自定义一个验证过程：当用户连续输入密码错误5次以上禁止用户登录一段时间
        //getAndIncrement :以原子方式将当前值加 1 ------->返回旧值（即加1前的原始值）
        //incrementAndGet :以原子方式将当前值加 1 ------->返回新值（即加1后的新值）
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }
        boolean match = super.doCredentialsMatch(token, info);
        if (match) {
            passwordRetryCache.remove(username);
        }
        return match;
    }

}
