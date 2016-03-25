package com.cfido.snapshot.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cfido.snapshot.MainApplication;
import com.cfido.snapshot.domain.Areas;
import com.cfido.snapshot.domain.Mail;
import com.cfido.snapshot.repository.MailRepository;

/**
 * Integration tests
 *
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MainApplication.class)
@ActiveProfiles("dev")
public class QueryServiceTest {

	@Autowired
	QueryService queryService;


	@Autowired
	MailRepository mailDao;

	@Test
	public void testFindAllArea() {
		List<Areas> list = this.queryService.findAllArea();
		System.out.println(list.size());
		// assertThat(cities.getTotalElements(), is(greaterThan(20L)));
	}


	// @Test
	public void testFindMailByAreaId() {
		Page<Integer> page = this.mailDao.findIdByAreaId(1, new PageRequest(2500, 20));
		List<Mail> list = this.mailDao.findByIdIn(page.getContent().toArray(new Integer[0]));

		System.out.println(list.size());
	}
}
