package com.demo2.demo;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author 贺威
 * @create 2019-04-12 15:42
 * 密码效应规则
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
        String password=new String(usernamePasswordToken.getPassword());
        //  获取数据库的密码
        String dbPassword=(String)info.getCredentials();

        return this.equals(password, dbPassword);

    }
}
