package com.jiadun.dispatch.common;

/**
 * 
    * @ClassName: CommonConstants  
    * @Description: 公用常量  
    * @author coder  
    * @date 2017年12月3日  
    *
 */
public class CommonConstants {
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String CONTEXT_KEY_USER_NAME = "currentUsername";
    public static final String SIGN_KEY = "eagle";

    public static final String REQ_HEADER = "Authorization";
    public static final String TOKEN_SPLIT = "Bearer ";
    /**
     * @describe: feign 是否需要解包响应头名称
     * @author: hcl  
     * @date: 2018/6/8 18:52
     */
    public static final String REST_UNPACK_HEAD_NAME = "Rest-Unpack";

    /**
     * @describe: 下载文件响应头名称
     * @author: hcl
     * @date: 2018/6/11 10:23
     */
    public static final String CONTENT_DISPOSITION_HEAD_NAME = "Content-Disposition";


}
