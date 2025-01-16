package com.zeus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zeus.common.interceptor.AccessLoggingInterceptor;
import com.zeus.common.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private AccessLoggingInterceptor accessLoggingInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {// 원하는 URI에 적절한 패턴을 적용하여 인터셉터를 지정한다.
		// 원하는 URI에 적절한 패턴을 적용하여 인터셉터를 지정한다.
		registry.addInterceptor(accessLoggingInterceptor).addPathPatterns("/**")
				.excludePathPatterns("/resources/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
