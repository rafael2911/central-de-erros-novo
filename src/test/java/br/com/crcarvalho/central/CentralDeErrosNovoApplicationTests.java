package br.com.crcarvalho.central;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CentralDeErrosNovoApplicationTests.class)
@TestPropertySource("classpath:application-test.properties")
public class CentralDeErrosNovoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
