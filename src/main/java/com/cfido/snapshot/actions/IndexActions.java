package com.cfido.snapshot.actions;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * 首页
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a> 2016年3月18日
 */
@Controller
public class IndexActions {

	private static final Logger log = LoggerFactory.getLogger(IndexActions.class);

	@RequestMapping("/a")
	public String welcome(Map<String, Object> model) {
		log.debug("hi");

		model.put("test", "hi ");

		return "index";
	}

}
