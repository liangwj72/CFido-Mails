package com.cfido.snapshot.formAndVo;


import com.cfido.snapshot.domain.Areas;

/**
 * <pre>
 * area表的model
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月21日
 */
public class AreaModel extends BasePoModel<Areas> {

	public AreaModel(Areas po) {
		super(po);
	}

	public String getDesc() {
		if (po.getMemo() != null) {
			return po.getMemo();
		} else {
			return "-";
		}
	}
}
