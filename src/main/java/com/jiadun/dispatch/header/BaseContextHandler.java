package com.jiadun.dispatch.header;

import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.StringUtil;
import com.jiadun.dispatch.common.CommonConstants;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @ClassName: BaseContextHandler
 * @Description: 上下文
 * @author coder
 * @date 2017年12月3日
 *
 */
public class BaseContextHandler {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();


    /**
     * @describe: 是否是开发模式
     */
    public static boolean devPattern = false;

    @Value("${spring.profiles.active}")
    public void setPattern(String pattern) {
        if(EmptyUtils.isNotEmpty(pattern)){
            if("dev".equals(pattern.trim().toLowerCase())){
                devPattern = true;
            }else{
                devPattern = false;
            }
        }
    }


    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key){
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    /**
     * @description: 获取token
     * @param: []
     * @return: java.lang.String
     * @author: caiping
     * @date: created in 2018/1/19 16:01
     */
    public static String getToken(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return StringUtil.getObjectStr(value);
    }


    /**
     * @description: 设置token
     * @param: [token]
     * @return: void
     * @author: caiping
     * @date: created in 2018/1/19 16:01
     */
    public static void setToken(String token){set(CommonConstants.CONTEXT_KEY_USER_TOKEN,token);}

    /**
     * 获取用户名
     * @param userName
     */
    public static void setUserName(String userName){set(CommonConstants.CONTEXT_KEY_USER_NAME,userName);}

    private static String returnObjectValue(Object value) {
        return value==null?null:value.toString();
    }

    public static void remove(){
        threadLocal.remove();
    }


}
