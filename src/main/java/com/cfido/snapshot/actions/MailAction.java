package com.cfido.snapshot.actions;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfido.snapshot.domain.Mail;
import com.cfido.snapshot.formAndVo.ReadMailResponse;
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
public class MailAction extends BaseAction {

	private static final Logger log = LoggerFactory.getLogger(MailAction.class);

	@ModelAttribute
	public void addHeaderForAll(Model model) {
		this.addMenuModel(model, SysMenuEnum.Mails);
	}

	@Autowired
	private QueryService queryService;

	/**
	 * 显示所有的信区
	 * 
	 * @param area
	 * @param model
	 * @return
	 */
	@RequestMapping("/areas/list")
	public ModelAndView allArea(Map<String, Object> model) {
		log.debug("/areas/list");

		model.put("areaList", this.queryService.getAreaList());
		return new ModelAndView("areaList");
	}

	/**
	 * 显示该信区所有的邮件
	 * 
	 * @param areaId
	 *            信区id
	 * @param pageNo
	 *            页码
	 * @param model
	 * @return
	 */
	@RequestMapping("/area/{areaId}")
	public ModelAndView areaMails(@PathVariable String areaId, Integer pageNo, Map<String, Object> model) {
		log.debug("/area/{}", areaId);

		return new ModelAndView("areaMails");
	}

	@RequestMapping("/mail/{msgId}")
	public ModelAndView readMail(@PathVariable String msgId) {
		log.debug("/mail/{}", msgId);

		ReadMailResponse res = new ReadMailResponse();

		if (!StringUtils.isEmpty(msgId)) {
			Mail po = this.queryService.findMail(msgId);
			res.setMail(po);
		}

		if (res.getMail() == null) {
			res.setCode(-1);
		}

		return new ModelAndView("readMail");
	}

}
