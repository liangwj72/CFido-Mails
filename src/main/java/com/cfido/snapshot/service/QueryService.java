package com.cfido.snapshot.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cfido.snapshot.domain.Areas;
import com.cfido.snapshot.domain.Mail;
import com.cfido.snapshot.formAndVo.AreaModel;
import com.cfido.snapshot.formAndVo.MailModel;
import com.cfido.snapshot.formAndVo.PageQueryResult;
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

	private final List<AreaModel> areaList = new LinkedList<>();

	private final Map<Integer, AreaModel> areaMap = new HashMap<>();

	public List<AreaModel> getAreaList() {
		return areaList;
	}

	@PostConstruct
	public void init() {
		log.info("QueryService 初始");

		List<Areas> list = this.findAllArea();
		for (Areas po : list) {
			AreaModel vo = new AreaModel(po);
			this.areaList.add(vo);
			this.areaMap.put(po.getId(), vo);
		}
	}

	/**
	 * 根据areaId返回信区对象
	 * 
	 * @param areaId
	 * @return
	 */
	public AreaModel getAreaModelById(int areaId) {
		if (StringUtils.isEmpty(areaId)) {
			return null;
		} else {
			AreaModel vo = this.areaMap.get(areaId);
			return vo;
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

	/**
	 * 选择一个信区的邮件
	 * 
	 * @param areaId
	 * @param pageable
	 * @return
	 */
	public PageQueryResult<MailModel> findMailByAreaId(Integer areaId, Pageable pageable) {

		Page<Mail> page = this.mailRepository.findByAreaId(areaId, pageable);

		List<MailModel> list = new LinkedList<>();
		if (page.hasContent()) {
			List<Mail> maillist = page.getContent();
			for (Mail po : maillist) {
				MailModel vo = new MailModel(po);
				list.add(vo);
			}
		}

		return new PageQueryResult<MailModel>(page, list, pageable.getPageSize());

	}

	public MailModel findMail(Integer msgId) {

		Mail po = this.mailRepository.findOne(msgId);
		if (po == null) {
			return null;
		}

		MailModel vo = new MailModel(po);

		if (po.getReplays() > 0) {
			// 有回复的
			List<Mail> orginReplayList = this.mailRepository.findByOriginMsgId(po.getMsgId());
			vo.addReplys(orginReplayList);
		}

		return vo;
	}

}
