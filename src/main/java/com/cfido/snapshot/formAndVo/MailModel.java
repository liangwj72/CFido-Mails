package com.cfido.snapshot.formAndVo;

import org.springframework.util.StringUtils;

import com.cfido.snapshot.domain.Mail;

/**
 * <pre>
 * mail的modal
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月22日
 */
public class MailModel extends BasePoModel<Mail> {

	public MailModel(Mail po) {
		super(po);
	}

	public String getSubject() {
		if (StringUtils.isEmpty(po.getSubj())) {
			return "无标题";
		} else {
			return po.getSubj();
		}
	}

}
