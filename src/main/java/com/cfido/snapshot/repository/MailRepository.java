
package com.cfido.snapshot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Page<Mail> findByAreaId(Integer area, Pageable paramPageable);

	public Page<Mail> findByMailFrom(String mailFrom, Pageable paramPageable);

	public List<Mail> findByOriginMsgId(String originMsgId);

}
