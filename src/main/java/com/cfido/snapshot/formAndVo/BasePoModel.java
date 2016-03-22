package com.cfido.snapshot.formAndVo;

/**
 * <pre>
 * 基础的带有po的model
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月22日
 */
public abstract class BasePoModel<T> {

	protected final T po;

	public BasePoModel(T po) {
		super();
		this.po = po;
	}

	public T getPo() {
		return po;
	}

}
