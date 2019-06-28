package com.jiadun.dispatch.exception;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiadun.dispatch.common.CommonConstant;
import com.jiadun.dispatch.vo.RestHeader;
import com.jiadun.dispatch.vo.RestResult;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/1/15 10:38
 */
@Component
@AllArgsConstructor
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding(CommonConstant.UTF8);
        response.setContentType(CommonConstant.CONTENT_TYPE);
        RestHeader restHeader = new RestHeader();
        restHeader.setCode(HttpStatus.HTTP_UNAUTHORIZED+"");
        restHeader.setMsg("请先登录");
        RestResult<Object> restResult = new RestResult<Object>();
        restResult.setStatus(restHeader);
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(restResult));
    }

}
