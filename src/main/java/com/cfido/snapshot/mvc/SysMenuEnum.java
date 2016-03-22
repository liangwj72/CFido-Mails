package com.cfido.snapshot.mvc;

/**
 * <pre>
 * 菜单枚举
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月21日
 */
public enum SysMenuEnum {

	Index("/", "首页"),
	Mails("/areas", "邮件"),
	Users("/users", "用户"),

	Unknow("", "");

	public final String href;
	public final String name;

	private SysMenuEnum(String href, String name) {
		this.href = href;
		this.name = name;
	}
}
