package com.cfido.snapshot.actions;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfido.snapshot.mvc.BaseAction;
import com.cfido.snapshot.mvc.SysMenuEnum;
import com.cfido.snapshot.service.QueryService;

/**
 * <pre>
 * 首页
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a> 2016年3月18日
 */
@Controller
public class UserAction extends BaseAction {

	private static final Logger log = LoggerFactory.getLogger(UserAction.class);

	@ModelAttribute
	public void addHeaderForAll(Model model) {
		this.addMenuModel(model, SysMenuEnum.Users);
	}

	@Autowired
	private QueryService queryService;

	/**
	 * 查询的用户
	 * 
	 * @param area
	 * @param model
	 * @return
	 */
	@RequestMapping("/users")
	public ModelAndView list(String userName, Integer pageNo, Map<String, Object> model) {
		log.debug("/users/list?userName={}", userName);

		return new ModelAndView("userList");
	}

	/**
	 * 查询该用户的所有邮件
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping("/user/{userId}")
	public ModelAndView userMails(@PathVariable String userId, Map<String, Object> model) {
		log.debug("/user/{}", userId);

		return new ModelAndView("userMails");
	}

}
