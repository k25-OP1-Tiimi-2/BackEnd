package com.backend.projekti.tiimityo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.projekti.tiimityo.web.ProductController;

@SpringBootTest
class TiimityoApplicationTests {

	@Autowired
	private ProductController pcontroller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(pcontroller).isNotNull();
	}

}
