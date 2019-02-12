package com.cfido.snapshot.actions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfido.snapshot.mvc.BaseAction;
import com.cfido.snapshot.mvc.SysMenuEnum;

/**
 * <pre>
 * 首页
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a> 2016年3月18日
 */
@Controller
public class IndexActions extends BaseAction {

	//private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IndexActions.class);

	@ModelAttribute
	public void addHeaderForAll(Model model) {
		this.addMenuModel(model, SysMenuEnum.Index);
	}

	@RequestMapping("/")
	public ModelAndView welcome() {

		return new ModelAndView("index");
	}
}
