package com.cfido.snapshot.formAndVo;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.cfido.snapshot.domain.Areas;

/**
 * <pre>
 * area表的model
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月21日
 */
public class AreaModel {

	private final Areas po;

	public Areas getPo() {
		return po;
	}

	public AreaModel(Areas po) {
		super();
		this.po = po;
	}

	public String getEncodeId() throws UnsupportedEncodingException {
		return URLEncoder.encode(this.po.getId(), "utf-8");
	}

	public String getDesc() {
		if (po.getMemo() != null) {
			return po.getMemo();
		} else {
			return "-";
		}
	}
}
