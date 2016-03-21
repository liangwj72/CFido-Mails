
package com.cfido.snapshot.repository;

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
public interface MailRepository extends PagingAndSortingRepository<Mail, String> {

}
