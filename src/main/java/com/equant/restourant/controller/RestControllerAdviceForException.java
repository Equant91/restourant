package com.equant.restourant.controller;


import com.equant.restourant.model.Exception.NotFoundedException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

@RestControllerAdvice
public class RestControllerAdviceForException implements ResponseBodyAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseMsg onException(Exception e){

        return new ResponseMsg(e.getMessage());
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        return new Wrapper(o);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private class ResponseMsg {
        private String error;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private class Wrapper{
        private Object data;
    }

}
