package com.jiadun.dispatch.header;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/20 16:20
 */
public class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    public CustomSecurityExpressionRoot(Authentication a) {
        super(a);
    }

    public boolean decision(String authority){
        Set<String> roles = new HashSet<>();
         getAuthentication().getAuthorities().forEach(auth -> {
             String au = auth.getAuthority();
             roles.add(au);
         });

        String defaultedRole = getRoleWithDefaultPrefix(null, "0"+authority);
        if (roles.contains(defaultedRole)) {
            return true;
        }
        return false;
    }

    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    public Object getFilterObject() {
        return filterObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    void setThis(Object target) {
        this.target = target;
    }

    public Object getThis() {
        return target;
    }
    public boolean hasPermission(Object permission) {
        try {
            return super.hasPermission(null, null, permission);
        } catch (AccessDeniedException e) {
            return false;
        }
    }

    public boolean checkPermission(Object permission) {
        return super.hasPermission(null, null, permission);
    }

    @Override
    public boolean hasPermission(Object targetId, String targetType, Object permission) {
        try {
            return super.hasPermission(targetId, targetType, permission);
        } catch (AccessDeniedException e) {
            return false;
        }
    }

    public boolean checkPermission(Object targetId, String targetType, Object permission) {
        return super.hasPermission(targetId, targetType, permission);
    }

    @Override
    public boolean hasPermission(Object target, Object permission) {
        try {
            return super.hasPermission(target, permission);
        } catch (AccessDeniedException e) {
            return false;
        }
    }

    public boolean checkPermission(Object target, Object permission) {
        return super.hasPermission(target, permission);
    }

    private static String getRoleWithDefaultPrefix(String defaultRolePrefix, String role) {
        if (role == null) {
            return role;
        }
        if (defaultRolePrefix == null || defaultRolePrefix.length() == 0) {
            return role;
        }
        if (role.startsWith(defaultRolePrefix)) {
            return role;
        }
        return defaultRolePrefix + role;
    }
}