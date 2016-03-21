
package com.cfido.snapshot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cfido.snapshot.domain.Areas;

/**
 * <pre>
 * 和Areas表对应的repository
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月18日
 */
public interface AreasRepository extends PagingAndSortingRepository<Areas, Long> {

}
