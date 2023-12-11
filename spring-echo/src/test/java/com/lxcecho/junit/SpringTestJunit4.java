package com.lxcecho.junit;

import com.lxcecho.ioc.iocxml.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lxcecho azaki0426@gmail.com
 * @since 2023/12/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class SpringTestJunit4 {

	@Autowired
	private User user;

	@Test
	public void testUser4() {
		System.out.println(user);
		user.run();
	}
}
