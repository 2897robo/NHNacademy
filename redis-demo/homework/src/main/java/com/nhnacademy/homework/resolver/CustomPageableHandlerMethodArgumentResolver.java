package com.nhnacademy.homework.resolver;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

// 기본 페이지 크기, 최대 페이지 크기 설정
@Configuration
public class CustomPageableHandlerMethodArgumentResolver extends PageableHandlerMethodArgumentResolver {
    public CustomPageableHandlerMethodArgumentResolver() {
        super();
        this.setFallbackPageable(PageRequest.of(0, 5));
        this.setMaxPageSize(10);
    }

    @Override
    public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        return super.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
    }
}
