package com.cfido.snapshot.mvc;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

public class BinderEditorSupport {

	static public interface IValue<T> {
		T toValue(String propName, String text);
	}

	private class ToBoolean implements IValue<Boolean> {

		@Override
		public Boolean toValue(String propName, String text) {
			return true;
		}
	}

	private class ToDate implements IValue<Date> {

		@Override
		public Date toValue(String propName, String text) {
			// 日期的处理方式
			boolean toBegin = !"endDate".equals(propName);
			Date date;
			try {
				date = BinderUtil.DATE_FORMAT.parse(text);
			} catch (Exception e) {
				date = new Date();
			}
			return DateUtil.ceilDateToDay(date, toBegin);
		}
	}

	private class ToDouble implements IValue<Double> {

		@Override
		public Double toValue(String propName, String text) {
			try {
				return Double.parseDouble(text);
			} catch (Exception e) {
				return 0.0d;
			}
		}
	}

	private class ToFloat implements IValue<Float> {

		@Override
		public Float toValue(String propName, String text) {
			try {
				return Float.parseFloat(text);
			} catch (Exception e) {
				return 0f;
			}
		}
	}

	private class ToInt implements IValue<Integer> {

		@Override
		public Integer toValue(String propName, String text) {
			try {
				return Integer.parseInt(text);
			} catch (Exception e) {
				return 0;
			}
		}
	}

	private class ToLong implements IValue<Long> {

		@Override
		public Long toValue(String propName, String text) {
			try {
				return Long.parseLong(text);
			} catch (Exception e) {
				return 0L;
			}
		}
	}

	private class ToString implements IValue<String> {

		@Override
		public String toValue(String propName, String text) {
			return text;
		}
	}

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BinderEditorSupport.class);

	private static BinderEditorSupport instance = new BinderEditorSupport();

	public static void addEditorSupport(Class<?> clazz, IValue<?> support) {
		instance.toValueMap.put(clazz, support);
	}

	public static void updateObj(Map<String, String[]> map, Object form) {
		//直通银行项目特殊字段名处理，全大写，并写存在下划线
		Map<String, String> paramsNameMap = getNameMap(map.keySet());
		
		for (Method m : form.getClass().getMethods()) {

			String methodName = m.getName();
			if (methodName.startsWith("set") && methodName.length() > 3) {

				// 必须是setXXX的方法
				Class<?>[] paramTypes = m.getParameterTypes();
				if (paramTypes.length == 1 && instance.isValidType(paramTypes[0])) {
					// 并且只有一个参数，并且是合法的类型
					String propName = StringUtils.uncapitalize(methodName.substring(3));// 参数名
					Class<?> param = paramTypes[0];

					String[] values = map.get(propName);
					//直通银行项目特殊字段名处理，全大写，并写存在下划线
					if(values ==null || values.length<=0){
						values = map.get(getDirectBankPro(paramsNameMap, propName));
					}
					if (values != null && values.length > 0) {

						if (log.isDebugEnabled()) {
							StringBuffer vb = new StringBuffer();
							for (String str : values) {
								vb.append(str).append(",");
							}
							log.debug("需要设置的属性:{}, 类型:{}, 值:{}", propName, param.getSimpleName(),
									vb.toString());
						}

						Object propValue;

						if (param.isArray()) {
							// 如果要设置的是数组
							propValue = Array.newInstance(param.getComponentType(), values.length);
							for (int i = 0; i < values.length; i++) {
								Object arg = instance.getValueFormString(propName, values[i], param.getComponentType());
								Array.set(propValue, i, arg);
							}
						} else {
							propValue = instance.getValueFormString(propName, values[0], param);
						}
						try {
							m.invoke(form, propValue);
						} catch (Exception e) {
							log.error(e.getMessage(), e);
						}
					}
				}
			}
		}
	}

	private final Map<Class<?>, IValue<?>> toValueMap = new HashMap<Class<?>, IValue<?>>();

	private BinderEditorSupport() {
		this.toValueMap.put(Date.class, new ToDate());

		this.toValueMap.put(int.class, new ToInt());
		this.toValueMap.put(Integer.class, new ToInt());

		this.toValueMap.put(Long.class, new ToLong());
		this.toValueMap.put(long.class, new ToLong());

		this.toValueMap.put(float.class, new ToFloat());
		this.toValueMap.put(Float.class, new ToFloat());

		this.toValueMap.put(double.class, new ToDouble());
		this.toValueMap.put(Double.class, new ToDouble());

		this.toValueMap.put(String.class, new ToString());

		this.toValueMap.put(boolean.class, new ToBoolean());
		this.toValueMap.put(Boolean.class, new ToBoolean());
	}

	private Object getValueFormString(String protName, String text, Class<?> paramClass) {
		IValue<?> toValue = this.toValueMap.get(paramClass);
		if (toValue != null) {
			return toValue.toValue(protName, text);
		}
		return null;
	}

	boolean isValidType(Class<?> clazz) {
		if (clazz.isArray()) {
			return this.toValueMap.containsKey(clazz.getComponentType());
		} else {
			return this.toValueMap.containsKey(clazz);
		}
	}
	
	/**
	 * 生成字段名小写去下划线后作为key与原本的字段名的map
	 * @param names   原本的字段名
	 * @return
	 */
	private static Map<String,String> getNameMap(Set<String> names){
		Map<String,String> resultMap = new HashMap<String, String>();
		for(String name : names){
			String nameKey = name.toLowerCase().replaceAll("_", "");
			resultMap.put(nameKey, name);
		}
		return resultMap;
	}

	/**
	 * 通过对象名获取目标属性名,直通银行项目特殊字段名处理，全大写，并写存在下划线
	 * @param paramsName
	 * @param proName
	 * @return
	 */
	private static String getDirectBankPro(Map<String, String> paramsNameMap,String proName){
		String result = null;
		if(paramsNameMap.containsKey(proName.toLowerCase())){
			result = paramsNameMap.get(proName.toLowerCase());
		}
		return result;
	}
}
