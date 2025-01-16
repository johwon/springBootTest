package com.zeus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zeus.common.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {// 원하는 URI에 적절한 패턴을 적용하여 인터셉터를 지정한다.
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/login", "/board", "/api/**");

		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
