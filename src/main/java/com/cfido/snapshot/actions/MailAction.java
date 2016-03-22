package com.cfido.snapshot.actions;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cfido.snapshot.domain.Mail;
import com.cfido.snapshot.formAndVo.AreaModel;
import com.cfido.snapshot.formAndVo.MailModel;
import com.cfido.snapshot.formAndVo.PageQueryResult;
import com.cfido.snapshot.formAndVo.ReadMailResponse;
import com.cfido.snapshot.mvc.BaseAction;
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
	@RequestMapping("/areas")
	public ModelAndView allArea(Map<String, Object> model) {
		log.debug("/areas");

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
	@RequestMapping("/area/{areaId}/{pageNo}")
	public ModelAndView areaMails(@PathVariable Integer areaId, @PathVariable Integer pageNo, Map<String, Object> model) {
		log.debug("/area/{}/{}", areaId, pageNo);

		int page = 1;
		if (pageNo != null && pageNo >= 1) {
			page = pageNo;
		}
		
		// 需要判断areaid是否合法
		AreaModel areaVo = this.queryService.getAreaModelById(areaId);
		if (areaVo!=null) {

			// 显示的页面从1页开始，程序是从0页开始
			Pageable pageable = new PageRequest(page - 1, VoConstants.PAGE_SIZE, Sort.Direction.DESC, "createDate");
			PageQueryResult<MailModel> pageVo = this.queryService.findMailByAreaId(areaId, pageable);
			
			pageVo.setActionUrl("/area/" + areaId);

			model.put(VoConstants.VO_PAGE_RESULT, pageVo);// 分页搜索结果
			model.put("areaVo", areaVo);// 信区

			return new ModelAndView("areaMails");
		} else {
			return new ModelAndView(new RedirectView("/areas"));
		}
		
	}

	@RequestMapping("/area/{areaId}")
	public ModelAndView areaMails(@PathVariable Integer areaId, Map<String, Object> model) {
		return this.areaMails(areaId, 1, model);
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
