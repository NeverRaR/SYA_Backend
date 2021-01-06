package com.sya.aspect;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sya.SYAApplication;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;


@Component
@Aspect
public class HttpLogger {

    private static final Logger logger = LoggerFactory.getLogger(HttpLogger.class);

    private static final ObjectMapper mapper=new ObjectMapper();

    @Pointcut("execution(* com.sya.controller.*.*(..))")
    private void httpRequestService(){};

    @Around("httpRequestService()")
    public Object aroundHttpRequest(ProceedingJoinPoint joinPoint)throws Throwable{

        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();



        Class[] paramTypeArray = methodSignature.getParameterTypes();
        logger.info("调用方法为:{}",className+"."+methodName);
        logger.info("请求参数为:{}",args);
        Object result = joinPoint.proceed(args);
        logger.info("响应结果为:{}",result);
        return result;

    }
}
