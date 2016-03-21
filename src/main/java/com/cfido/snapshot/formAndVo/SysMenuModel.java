package com.cfido.snapshot.formAndVo;

import com.cfido.snapshot.mvc.SysMenuEnum;

/**
 * <pre>
 * 菜单model
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月21日
 */
public class SysMenuModel {
	private final String href;
	private final String name;
	private String classStr;

	public SysMenuModel(SysMenuEnum en) {
		this.href = en.href;
		this.name = en.name;
	}

	public String getHref() {
		return href;
	}

	public String getName() {
		return name;
	}

	public String getClassStr() {
		return classStr;
	}

	public void setActive() {
		this.classStr = "class='active'";
	}

}
