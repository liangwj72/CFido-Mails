package com.cfido.snapshot.mvc;

import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * FORM绑定工具
 * 
 * @author 梁韦江 2012-3-8
 */
public class BinderUtil {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BinderUtil.class);

	public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/** 默认时间查询周期，目前为60天 */
	public final static int DEFAUT_QUERY_DATE_RANGE = 60;

	/** 默认的重置表单参数名 */
	public final static String DEFAULT_RESET_PARAM_NAME = "reset";

	/**
	 * 从request中获得form对象
	 * 
	 * @param request
	 * @param formClazz
	 *            form的类
	 * @param saveToSession
	 *            是否保存到session
	 * @param extSession
	 *            如果保存到session时，默认的session名字就是路径名，但可额外增加字符串
	 * @param resetParam
	 *            重置form的参数，如果url出现该参数，则创建空实例
	 * @param validate
	 *            是否调用校验
	 * @return
	 * @see AFormValidateMethod 校验的方法需要有 FormValidateMethod 这个声明
	 */
	@SuppressWarnings("unchecked")
	public static <T> T bindForm(HttpServletRequest request, Class<T> formClazz, boolean saveToSession, String extSession,
			String resetParam) {
		try {
			T form = formClazz.newInstance();

			if (request != null && saveToSession) {

				// 将路径信息放到session名字中，防止有session名的重复
				String pathSessionName = "bindForm:" + formClazz.getName();
				if (StringUtils.hasText(extSession)) {
					pathSessionName += extSession;
				}

				// 如果sessionName不为空，才需要从session中获取
				if (!StringUtils.hasText(resetParam) || !request.getParameterMap().containsKey(resetParam)) {
					// 如果不需要重置，才需要从session中获取
					form = (T) request.getSession().getAttribute(pathSessionName);
					if (form == null) {
						// 如果session中没有，就创建新实例
						form = formClazz.newInstance();
					}
				}
				request.getSession().setAttribute(pathSessionName, form);
			}

			Map<String, String[]> map = new HashMap<String, String[]>();
			Enumeration<String> en = request.getParameterNames();
			while (en.hasMoreElements()) {
				String key = en.nextElement();
				String[] value = request.getParameterValues(key);
				map.put(key, value);
			}

			BinderEditorSupport.updateObj(map, form);

			return form;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
