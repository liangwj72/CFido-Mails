package com.cfido.snapshot.formAndVo;

import com.cfido.snapshot.domain.Mail;
import com.cfido.snapshot.mvc.BaseResponse;

/**
 * <pre>
 * 读取一个邮件
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月21日
 */
public class ReadMailResponse extends BaseResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mail mail;

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

}
