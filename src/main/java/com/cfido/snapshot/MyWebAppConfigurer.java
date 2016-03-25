package com.cfido.snapshot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <pre>
 * 自定义的配置，主要是将web文件分离出jar，方便修改
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月25日
 */
@Configuration
public class MyWebAppConfigurer
		extends WebMvcConfigurerAdapter {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MyWebAppConfigurer.class);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.debug("注册资源路径");
		registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
		super.addResourceHandlers(registry);
	}

}