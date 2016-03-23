package com.cfido.snapshot.mvc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <pre>
 * 工具类
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月23日
 */
public class MyUtils {

	public static String urlEncode(String str) {
		try {
			if (str != null) {
				return URLEncoder.encode(str, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

}
