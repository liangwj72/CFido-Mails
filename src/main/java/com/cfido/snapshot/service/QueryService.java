package com.cfido.snapshot.service;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cfido.snapshot.domain.Areas;
import com.cfido.snapshot.domain.Mail;
import com.cfido.snapshot.formAndVo.AreaModel;
import com.cfido.snapshot.repository.AreasRepository;
import com.cfido.snapshot.repository.MailRepository;

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
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QueryService.class);
	
	@Autowired
	private AreasRepository areasRepository;

	@Autowired
	private MailRepository mailRepository;

	private List<AreaModel> areaList;

	public List<AreaModel> getAreaList() {
		return areaList;
	}

	@PostConstruct
	public void init() {
		log.info("QueryService 初始");

		this.areaList = new LinkedList<>();
		List<Areas> list = this.findAllArea();
		for (Areas po : list) {
			AreaModel vo = new AreaModel(po);
			this.areaList.add(vo);
		}
	}

	/**
	 * 查找所有的信区，不分页
	 * 
	 * @return
	 */
	protected List<Areas> findAllArea() {

		log.debug("findAllArea()");

		List<Areas> list = new LinkedList<>();

		Iterable<Areas> iterable = this.areasRepository.findAll();

		Iterator<Areas> it = iterable.iterator();
		
		while (it.hasNext()) {
			list.add(it.next());
		}

		return list;
	}

	public Mail findMail(String msgId) {
		return this.mailRepository.findOne(msgId);
	}

}
