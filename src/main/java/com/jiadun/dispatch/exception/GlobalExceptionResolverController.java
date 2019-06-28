package com.jiadun.dispatch.exception;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.common.CommonConstants;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.state.RestSystemStatus;
import com.jiadun.dispatch.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.websocket.DecodeException;
import java.util.List;


/**
 * Controller通知,进行全局的异常处理
 * @author hcl
 *
 */
@Slf4j
@ControllerAdvice
@Component
public class GlobalExceptionResolverController {
	
	
	/**
	 * 处理所有的Exception.class异常,抛异常给客户端
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResponseEntity<Object> globalExceptionResolver(Throwable e){
		ResponseEntity<Object> entity = null;
		RestHeader restHeader = new RestHeader();
		Object respData = null;
		Object errorInfo = new RestErrorMsgInfo(e.getMessage());;

		if(e instanceof DecodeException){
			//远程调用拆包异常
			e = e.getCause();
		}

		if(e instanceof CustomException){
			CustomException ce = (CustomException)e;
			if(e instanceof RestSystemException){
				log.error("远程调用系统异常！",e);
				restHeader.setCode(ce.getStatus().getCode());
				restHeader.setMsg(ce.getDesc());
			}else if(e instanceof RestBusinessException){
				log.error("远程调用业务异常！",e);
				restHeader.setCode(ce.getStatus().getCode());
				restHeader.setMsg(ce.getDesc());
			}else if(e instanceof RestException){
				log.error("远程调用异常！",e);
				restHeader.setCode(ce.getStatus().getCode());
				restHeader.setMsg(ce.getDesc());
			}else{
				log.error("其他的自定义远程调用异常！",e);
				restHeader.setCode(ce.getStatus().getCode());
				restHeader.setMsg("自定义远程调用异常！未配置全局异常处理机制！");
			}
			respData = ce.getRespData();
			errorInfo = ce.getErrorInfo();
		}else if(e instanceof MissingServletRequestParameterException){
			//缺少参数
			MissingServletRequestParameterException ce = (MissingServletRequestParameterException)e;
			log.error("缺少参数{}！",ce.getParameterName(),e);
			restHeader.setCode(RestCommBusinessStatus.BUSINESS_ATTRIBUTE_ILLEGAL_ERROR.getCode());
			restHeader.setMsg(String.format("缺少参数%s！",ce.getParameterName()));
			RestErrorInfos restErrorInfos = new RestErrorInfos();
			restErrorInfos.add(new RestFieldValidErrorInfo(ce.getParameterName(),String.format("缺少参数%s！",ce.getParameterName())));
			errorInfo = restErrorInfos;
		}else if(e instanceof MethodArgumentTypeMismatchException){
			//参数类型异常
			MethodArgumentTypeMismatchException ce = (MethodArgumentTypeMismatchException)e;
			log.error("参数{}的类型必须是'{}'！",ce.getName(),ce.getRequiredType().getName(),e);
			restHeader.setCode(RestCommBusinessStatus.BUSINESS_ATTRIBUTE_ILLEGAL_ERROR.getCode());
			restHeader.setMsg(String.format("参数%s的类型必须是'%s'！",ce.getName(),ce.getRequiredType().getName()));
			RestErrorInfos restErrorInfos = new RestErrorInfos();
			restErrorInfos.add(new RestFieldValidErrorInfo(ce.getName(),String.format("参数%s的类型必须是'%s'！",ce.getName(),ce.getRequiredType().getName())));
			errorInfo = restErrorInfos;
		}else if(e instanceof NoHandlerFoundException){
			NoHandlerFoundException ce = (NoHandlerFoundException) e;
			log.error("资源[{}]不存在！",ce.getRequestURL(),e);
			restHeader.setCode(RestSystemStatus.SYS_RESOURCES_NOT_FOUND.getCode());
			restHeader.setMsg(String.format(RestSystemStatus.SYS_RESOURCES_NOT_FOUND.getDesc(),ce.getRequestURL()));
		}else if(e instanceof HttpMessageNotReadableException){
			log.error("请求参数解析异常！",e);
			restHeader.setCode(RestSystemStatus.SYS_REQUEST_PARAMETER_ERROR.getCode());
			restHeader.setMsg(RestSystemStatus.SYS_REQUEST_PARAMETER_ERROR.getDesc());
		}else if(e instanceof HttpMediaTypeNotSupportedException){
			HttpMediaTypeNotSupportedException ce = (HttpMediaTypeNotSupportedException)e;
			log.error("请求方式[{}]不支持！",ce.getContentType(),e);
			restHeader.setCode(RestSystemStatus.SYS_REQUEST_TYPE_ERROR.getCode());
			restHeader.setMsg(String.format(RestSystemStatus.SYS_REQUEST_TYPE_ERROR.getDesc(),ce.getContentType()));
		}else if(e instanceof HttpRequestMethodNotSupportedException){
			HttpRequestMethodNotSupportedException ce = (HttpRequestMethodNotSupportedException)e;
			log.error("请求方式[{}]不支持！",ce.getMethod(),e);
			restHeader.setCode(RestSystemStatus.SYS_REQUEST_TYPE_ERROR.getCode());
			restHeader.setMsg(String.format(RestSystemStatus.SYS_REQUEST_TYPE_ERROR.getDesc(),ce.getMethod()));
		}else if(e instanceof MethodArgumentNotValidException){
			MethodArgumentNotValidException ce = (MethodArgumentNotValidException)e;
			List<ObjectError> errors = ce.getBindingResult().getAllErrors();
			RestErrorInfos restErrorInfos = new RestErrorInfos();
			String errowMsg = null;
			for(ObjectError error : errors){
				if(error instanceof FieldError){
					FieldError fieldError = (FieldError)error;
					if(EmptyUtils.isNotEmpty(fieldError.getDefaultMessage())){
						restErrorInfos.add(new RestFieldValidErrorInfo(fieldError.getField(),fieldError.getDefaultMessage()));
						if(errowMsg == null){
							errowMsg = fieldError.getDefaultMessage();
						}
					}
				}
			}
			if(errowMsg == null){
				errowMsg = "参数验证失败！";
			}
			errorInfo = restErrorInfos;
			restHeader.setCode(RestCommBusinessStatus.BUSINESS_ATTRIBUTE_ILLEGAL_ERROR.getCode());
			restHeader.setMsg(errowMsg);
			log.error("参数验证失败！",e);
		}else if(e.getClass().getName().equals("org.springframework.security.authentication.InsufficientAuthenticationException")){
			log.error("身份验证失败！",e);
			//未知的异常
			restHeader.setCode(RestSystemStatus.SYS_AUTHENTICATION_FAILED_ERROR.getCode());
			restHeader.setMsg(RestSystemStatus.SYS_AUTHENTICATION_FAILED_ERROR.getDesc());
		}else if(e.getClass().getName().equals("org.springframework.security.access.AccessDeniedException")){
			log.error("权限不足，访问被拒绝！",e);
			//未知的异常
			restHeader.setCode(RestSystemStatus.SYS_ACCESS_DENIED_ERROR.getCode());
			restHeader.setMsg(RestSystemStatus.SYS_ACCESS_DENIED_ERROR.getDesc());
		}else{
			log.error("未知的异常！",e);
			//未知的异常
			restHeader.setCode(RestSystemStatus.SYS_UNKNOWN_ERROR.getCode());
			restHeader.setMsg(RestSystemStatus.SYS_UNKNOWN_ERROR.getDesc());
		}
		RestResult<Object> restResult = new RestResult<Object>();
		restResult.setStatus(restHeader);
		restResult.setData(respData);
		restResult.setErrorInfo(errorInfo);
		HttpHeaders headers = new HttpHeaders();
		//表示服务和服务之间调用解包
		headers.set(CommonConstants.REST_UNPACK_HEAD_NAME,"true");
		entity = new ResponseEntity<Object>(restResult,headers, HttpStatus.OK);
		return entity;
	}
}
