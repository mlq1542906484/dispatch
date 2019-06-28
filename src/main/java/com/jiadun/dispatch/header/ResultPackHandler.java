package com.jiadun.dispatch.header;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiadun.dispatch.common.CommonConstants;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.vo.RestHeader;
import com.jiadun.dispatch.vo.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @describe: 结果包装器
 * @author: hcl
 * @date: 2018/6/8 20:50
 */
@RestControllerAdvice
public class ResultPackHandler implements ResponseBodyAdvice<Object> {


    /**
     * @describe: 结果包装器配置文件
     */
    @Autowired
    private ResultPackHandlerProperties resultPackHandlerProperties;


    /**
     * @describe: json转换对象
     */
    @Autowired
    private ObjectMapper mapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object objectRestResult,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        boolean ignoreFlag = false;
        String path = serverHttpRequest.getURI().getPath();
        for(String ignore : resultPackHandlerProperties.getResultPackIgnores()){
            if(path.startsWith(ignore)){
                ignoreFlag = true;
                break;
            }
        }
        HttpHeaders httpHeaders = serverHttpResponse.getHeaders();
        if(ignoreFlag || httpHeaders.containsKey(CommonConstants.REST_UNPACK_HEAD_NAME) || httpHeaders.containsKey(CommonConstants.CONTENT_DISPOSITION_HEAD_NAME)){
            return objectRestResult;
        }
        RestResult<Object> restResult = new RestResult<Object>();
        RestHeader restHeader = new RestHeader();
        restHeader.setCode(RestCommBusinessStatus.BUSINESS_SUCCESS.getCode());
        restHeader.setMsg(RestCommBusinessStatus.BUSINESS_SUCCESS.getDesc());
        restResult.setStatus(restHeader);
        restResult.setData(objectRestResult);

        //表示服务和服务之间调用解包
        httpHeaders.set(CommonConstants.REST_UNPACK_HEAD_NAME, "true");

        if(aClass == StringHttpMessageConverter.class){
            try {
                return mapper.writeValueAsString(restResult);
            } catch (JsonProcessingException e) {
                throw new RestBusinessException(e, RestCommBusinessStatus.BUSINESS_OBJECT_CAST_ERROR,restResult.getClass().getName(),"JSON");
            }
        }
        return restResult;
    }
}