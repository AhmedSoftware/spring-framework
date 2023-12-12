package com.lxcecho.junit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author lxcecho azaki0426@gmail.com
 * @since 2023/12/11
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:bean.xml")
@SpringJUnitConfig(locations = "classpath:bean-junit.xml")
public class SpringTestJunit5 {

	//注入
	@Autowired
	private JUnitUser JUnitUser;

	//测试方法
	@Test
	public void testUser() {
		System.out.println(JUnitUser);
		JUnitUser.run();
	}

}