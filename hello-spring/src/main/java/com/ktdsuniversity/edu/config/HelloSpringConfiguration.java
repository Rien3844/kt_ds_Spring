package com.ktdsuniversity.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Application.yml외의 확장된 설정을 만들어 줄 수 있는 수단.
// ==> Application.yml에서 할 수 없는 설정을 만듦.
// @Component의 자식 Annotation
@Configuration

// spring-boot-starter-validation 동작 활성화 시키는 Annotation
// @EnableWebMvc가 추가되면 application.yml의 mvc관련 설정이 모두 무시된다
// ==> application.yml의 mvc관련 설정이 @EnableWebMvc가 써진 Class의 설정으로 덮어 쓰여진다.
// ==> application.yml에서 만든 spring.mvc.view.prefix=/WEB-INF/views/, 
//							 spring.mvc.view.suffix=.jsp 둘 다 새로 만들어줘야함!
@EnableWebMvc
public class HelloSpringConfiguration implements 
		// WebMvc 설정을 위한 Configuration
		// @EnableWebMvc Annotation에서 적용하는 기본 설정들을 변경하기 위함.
		WebMvcConfigurer{
	
	// configureViewResoolvers 설정:
	// spring.mvc.view.prefix, spring.mvc.view.suffix 재설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
		
	}
	
	// addResourceHandlers
	// src/main.resources.static 경로의 endpoint 재설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// /static/css/ 폴더에 있는 파일들에 대한 Endpoint  설정.
		registry.addResourceHandler("/css/**") // /static/css/ 의 엔드포인트.
				.addResourceLocations("classpath:/static/css/"); // /static/css/ 의 물리적인 위치.
		// /static/image/ 폴더에 있는 파일들에 대한 Endpoint  설정.
		registry.addResourceHandler("/image/**") // /static/image/ 의 엔드포인트.
				.addResourceLocations("classpath:/static/image/"); // /static/image/ 의 물리적인 위치.
		// /static/js/ 폴더에 있는 파일들에 대한 Endpoint  설정.
		registry.addResourceHandler("/js/**") // /static/js/ 의 엔드포인트.
				.addResourceLocations("classpath:/static/js/");	 // /static/js/ 의 물리적인 위치.
	}
	
}
