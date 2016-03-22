package com.cfido.snapshot.mvc;

import java.util.LinkedList;
import java.util.List;

import org.springframework.ui.Model;

import com.cfido.snapshot.formAndVo.SysMenuModel;

/**
 * <pre>
 * 所有action的基类
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月21日
 */
public abstract class BaseAction {

	/**
	 * 往model中添加菜单列表
	 * 
	 * @param model
	 * @param curMenu
	 *            当前菜单
	 */
	protected void addMenuModel(Model model, SysMenuEnum curMenu) {
		List<SysMenuModel> menuList = new LinkedList<>();
		for (SysMenuEnum menu : SysMenuEnum.values()) {
			SysMenuModel vo = new SysMenuModel(menu);
			if (menu == curMenu) {
				vo.setActive();
			}
			menuList.add(vo);
		}
		model.addAttribute(VoConstants.VO_MENU_LIST, menuList);
	}
}
