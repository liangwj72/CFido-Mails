package com.cfido.snapshot.service;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cfido.snapshot.domain.Areas;
import com.cfido.snapshot.repository.AreasRepository;

/**
 * <pre>
 * 信区相关的处理方法
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月18日
 */
@Component
@Transactional
public class QueryService {

	
	@Autowired
	private AreasRepository areasRepository;

	public List<Areas> findAllArea() {

		List<Areas> list = new LinkedList<>();

		Iterable<Areas> iterable = this.areasRepository.findAll();

		Iterator<Areas> it = iterable.iterator();
		
		while (it.hasNext()) {
			list.add(it.next());
		}

		return list;
	}

}
