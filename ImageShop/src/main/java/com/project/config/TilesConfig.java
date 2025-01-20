package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer; 
import org.springframework.web.servlet.view.tiles3.TilesView; 
import org.springframework.web.servlet.view.tiles3.TilesViewResolver; 

@Configuration
public class TilesConfig {

	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "/WEB-INF/tiles/tiles.xml" });
//타일정의 파일을 변경을감지하고 새로고침을 할수 있도록 진행 
		configurer.setCheckRefresh(true);
		return configurer;
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
//Tiles 뷰를 해석하여 실제 뷰 오브젝트를 생성한다. 
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
}
