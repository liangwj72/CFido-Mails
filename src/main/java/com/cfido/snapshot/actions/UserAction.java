package com.cfido.snapshot.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfido.snapshot.formAndVo.PageQueryResult;
import com.cfido.snapshot.formAndVo.UserModel;
import com.cfido.snapshot.formAndVo.UserNameForm;
import com.cfido.snapshot.mvc.BaseAction;
import com.cfido.snapshot.mvc.BinderUtil;
import com.cfido.snapshot.mvc.SysMenuEnum;
import com.cfido.snapshot.mvc.VoConstants;
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

	@Autowired
	private QueryService queryService;

	/**
	 * 这是个钩子，在所有方法执行前执行
	 * 
	 * @param model
	 * @param session
	 */
	@ModelAttribute
	public void addHeaderForAll(Model model, HttpSession session) {
		this.addMenuModel(model, SysMenuEnum.Users);
	}

	/**
	 * 查询的用户
	 * 
	 * @param area
	 * @param model
	 * @return
	 */
	@RequestMapping("/users")
	public ModelAndView list(HttpServletRequest request, ModelMap model) {

		UserNameForm form = BinderUtil.bindForm(request, UserNameForm.class, true, "reset", "session name:user");

		return this.doList(form, 1, model);
	}

	@RequestMapping("/users/{pageNo}")
	public ModelAndView list(HttpServletRequest request, @PathVariable Integer pageNo, ModelMap model) {
		UserNameForm form = BinderUtil.bindForm(request, UserNameForm.class, true, "reset", "session name:user");

		return this.doList(form, pageNo, model);
	}

	public ModelAndView doList(UserNameForm form, @PathVariable Integer pageNo, ModelMap model) {
		log.debug("/users/{}?username={}", pageNo, form.getUsername());

		int page = 1;
		if (pageNo != null && pageNo >= 1) {
			page = pageNo;
		}

		PageQueryResult<UserModel> pageVo = this.queryService.findUser(form.getUsername(),
				PageRequest.of(page, VoConstants.PAGE_SIZE));

		pageVo.setActionUrl("/users/");

		model.put(VoConstants.VO_PAGE_RESULT, pageVo);// 分页搜索结果
		model.put("username", form.getUsername());

		return new ModelAndView("userList");
	}

}
