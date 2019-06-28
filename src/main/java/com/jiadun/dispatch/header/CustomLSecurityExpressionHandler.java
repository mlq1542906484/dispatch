package com.jiadun.dispatch.header;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/20 16:19
 */

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
public class CustomLSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    public CustomLSecurityExpressionHandler(){
        super();
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation){
        CustomSecurityExpressionRoot root = new CustomSecurityExpressionRoot(authentication);
        root.setThis(invocation.getThis());
        root.setPermissionEvaluator(getPermissionEvaluator());
        return root;
    }
}