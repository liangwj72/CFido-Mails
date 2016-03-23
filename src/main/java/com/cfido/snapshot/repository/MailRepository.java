
package com.cfido.snapshot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cfido.snapshot.domain.Mail;

/**
 * <pre>
 * 和Mail表对应的repository
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月18日
 */
public interface MailRepository extends PagingAndSortingRepository<Mail, Integer> {

	public Page<Mail> findByMailFrom(String mailFrom, Pageable pageable);

	public List<Mail> findByOriginMsgId(String originMsgId);

	@Query("select m.id from Mail m where m.areaId=?1")
	public Page<Integer> findIdByAreaId(Integer area, Pageable pageable);

	public List<Mail> findByIdIn(Integer[] ids);

	@Query("select m.id from Mail m where m.mailFrom=?1")
	public Page<Integer> findIdByMailFrom(String from, Pageable pageable);

}
