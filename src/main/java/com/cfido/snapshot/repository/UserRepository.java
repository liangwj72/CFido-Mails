
package com.cfido.snapshot.repository;

import org.springframework.data.repository.CrudRepository;

import com.cfido.snapshot.domain.Users;

/**
 * <pre>
 * 和Usersl表对应的repository
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月18日
 */
public interface UserRepository extends CrudRepository<Users, Integer> {

}
