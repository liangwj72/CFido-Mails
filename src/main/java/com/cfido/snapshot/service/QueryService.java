package com.cfido.snapshot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cfido.snapshot.domain.Areas;
import com.cfido.snapshot.domain.Mail;
import com.cfido.snapshot.domain.Users;
import com.cfido.snapshot.formAndVo.AreaModel;
import com.cfido.snapshot.formAndVo.MailModel;
import com.cfido.snapshot.formAndVo.PageQueryResult;
import com.cfido.snapshot.formAndVo.UserModel;
import com.cfido.snapshot.repository.AreasRepository;
import com.cfido.snapshot.repository.MailRepository;
import com.cfido.snapshot.repository.UserRepository;

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

	@Autowired
	private UserRepository userRepository;

	/**
	 * 信区的内存缓存，反正才106条记录，干脆全部读到内存中
	 */
	private final List<AreaModel> areaList = new LinkedList<>();

	/**
	 * 以id为key的信区map,用于校验信区的合法性
	 */
	private final Map<Integer, AreaModel> areaMap = new HashMap<>(110);

	private List<UserModel> userList;
	private final Map<Integer, UserModel> userMap = new HashMap<>();// 缓存用户的map

	public List<AreaModel> getAreaList() {
		return areaList;
	}

	@PostConstruct
	public void init() {
		log.info("初始化 QueryService");

		List<Areas> list = this.findAllArea();
		for (Areas po : list) {
			AreaModel vo = new AreaModel(po);
			this.areaList.add(vo);
			this.areaMap.put(po.getId(), vo);
		}
		log.info("初始化 QueryService完成， 加载所有信区到内存");
	}

	/**
	 * 根据areaId返回信区对象
	 * 
	 * @param areaId
	 * @return
	 */
	public AreaModel getAreaModelById(int areaId) {
		AreaModel vo = this.areaMap.get(areaId);
		return vo;
	}

	/**
	 * 根据用户id，返回用户对象
	 * 
	 * @param userId
	 * @return
	 */
	public UserModel getUserModelById(int userId) {
		return this.userMap.get(userId);
	}

	/**
	 * 查找所有的信区，不分页
	 * 
	 * @return
	 */
	protected List<Areas> findAllArea() {

		List<Areas> list = new LinkedList<>();

		Iterable<Areas> iterable = this.areasRepository.findAll();

		Iterator<Areas> it = iterable.iterator();

		while (it.hasNext()) {
			list.add(it.next());
		}

		return list;
	}

	/**
	 * 获取用户的内存缓存列表
	 * 
	 * @return
	 */
	protected synchronized List<UserModel> getUserList() {
		if (this.userList == null) {
			log.debug("初始化用户列表");

			this.userList = new ArrayList<>();

			Iterable<Users> iterable = this.userRepository.findAll();
			Iterator<Users> it = iterable.iterator();
			while (it.hasNext()) {
				UserModel vo = new UserModel(it.next());
				this.userList.add(vo);
				this.userMap.put(vo.getPo().getId(), vo);
			}

			// 排序,并设置名次
			Collections.sort(this.userList);
			int rank = 1;
			for (UserModel vo : userList) {
				vo.setRank(rank);
				rank++;
			}
		}
		return this.userList;
	}

	public PageQueryResult<UserModel> findUser(String username, Pageable pageable) {
		List<UserModel> list = this.getUserList();// 默认是全部用户分页
		if (username != null && username.trim().length() > 0) {
			// 如果有查询关键字，则重新构建分页的列表
			list = new ArrayList<>();

			String key = username.trim().toLowerCase();

			for (UserModel vo : userList) {
				if (vo.getPo().getUserName().toLowerCase().indexOf(key) >= 0) {
					list.add(vo);
				}
			}
		}

		int total = list.size();
		int begin = (pageable.getPageNumber() - 1) * pageable.getPageSize();
		int end = begin + pageable.getPageSize();
		if (end > total) {
			end = total;
		}

		// 构成该页的列表
		List<UserModel> pageList = new LinkedList<>();
		for (int i = begin; i < end; i++) {
			pageList.add(list.get(i));
		}

		return new PageQueryResult<>(total, pageList, pageable);

	}

	/**
	 * 选择一个信区的邮件
	 * 
	 * @param areaId
	 * @param pageable
	 * @return
	 */
	public PageQueryResult<MailModel> findMailByAreaId(Integer areaId, Pageable pageable) {

		// 优化sql，先只选择id
		Page<Integer> page = this.mailRepository.findIdByAreaId(areaId, pageable);

		List<MailModel> list = new LinkedList<>();
		if (page.hasContent()) {
			// 另外执行语句将数据选择出来
			List<Mail> maillist = this.mailRepository.findByIdIn(page.getContent().toArray(new Integer[0]));
			for (Mail po : maillist) {
				MailModel vo = new MailModel(po);
				list.add(vo);
			}
		}

		return new PageQueryResult<MailModel>(page, list, pageable.getPageSize());

	}

	/**
	 * 选择一个用户发的邮件
	 * 
	 * @param mailFrom
	 * @param pageable
	 * @return
	 */
	public PageQueryResult<MailModel> findMailFromByUser(String mailFrom, Pageable pageable) {

		// 优化sql，先只选择id
		Page<Integer> page = this.mailRepository.findIdByMailFrom(mailFrom, pageable);

		List<MailModel> list = new LinkedList<>();
		if (page.hasContent()) {
			// 另外执行语句将数据选择出来
			List<Mail> maillist = this.mailRepository.findByIdIn(page.getContent().toArray(new Integer[0]));
			for (Mail po : maillist) {
				MailModel vo = new MailModel(po);
				list.add(vo);
			}
		}

		return new PageQueryResult<MailModel>(page, list, pageable.getPageSize());

	}

	public MailModel findMail(Integer msgId) {

		Optional<Mail> opt = this.mailRepository.findById(msgId);

		if (!opt.isPresent()) {
			return null;
		}

		Mail po = opt.get();

		MailModel vo = new MailModel(po);

		if (po.getOriginMsgId() != null) {
			// 看能否找到原帖
			List<Mail> originList = this.mailRepository.findByMsgId(po.getOriginMsgId());
			if (originList.size() > 0) {
				vo.setOrigin(new MailModel(originList.get(0)));
			}
		}

		if (po.getReplays() > 0) {
			// 有回复的
			List<Mail> orginReplayList = this.mailRepository.findByOriginMsgId(po.getMsgId());
			vo.addReplys(orginReplayList);
		}

		return vo;
	}

}
