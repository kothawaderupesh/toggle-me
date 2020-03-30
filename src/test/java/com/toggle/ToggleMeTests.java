package com.toggle;

import com.toggle.AOPTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ToggleMeTests {

	@Autowired
	AOPTest aopTest;

	@Test
	void contextLoads() {
		aopTest.test();
		aopTest.test1();
	}

}
