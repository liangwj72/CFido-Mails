package com.cfido.snapshot.formAndVo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

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

	/**
	 * 根据正文内容标注那些是回复的原文
	 * 
	 * @param msg
	 * @return
	 */
	private static String mailQuote(String msg) {
		if (!StringUtils.hasText(msg)) {
			return msg;
		}

		StringBuffer sb = new StringBuffer();
		String[] ar = msg.split("\n");

		for (String str : ar) {
			int index = str.indexOf(">");
			if (index > 1 && index < 5) {
				sb.append("<div class='mailquote'>");
			} else {
				sb.append("<div>");
			}
			if (!StringUtils.hasText(str.trim())) {
				sb.append("<br/>");
			} else {
				sb.append(HtmlUtils.htmlEscape(str));
			}
			sb.append("</div>");
		}
		return sb.toString();
	}

	private final String html;

	private List<MailModel> replyList;

	private MailModel origin;

	public boolean isHasOriginMail() {
		return this.origin != null;
	}

	public MailModel getOrigin() {
		return origin;
	}

	public void setOrigin(MailModel origin) {
		this.origin = origin;
	}

	public List<MailModel> getReplyList() {
		return replyList;
	}

	public MailModel(Mail po) {
		super(po);
		this.html = mailQuote(po.getMsg());
	}

	/**
	 * 添加回复本贴的回帖
	 * 
	 * @param list
	 */
	public void addReplys(List<Mail> list) {
		this.replyList = new LinkedList<>();
		for (Mail po : list) {
			this.replyList.add(new MailModel(po));
		}
	}

	public String getHtml() {
		return html;
	}

	public String getSubject() {
		if (!StringUtils.hasText(po.getSubj())) {
			return "无标题";
		} else {
			return po.getSubj();
		}
	}

	public boolean isHasReply() {
		return this.replyList != null && this.po.getReplays() > 0;
	}

}
