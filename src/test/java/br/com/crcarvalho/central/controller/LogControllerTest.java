package br.com.crcarvalho.central.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.codenation.central.controller.LogController;
import br.com.codenation.central.entity.Log;
import br.com.codenation.central.entity.User;
import br.com.crcarvalho.central.CentralDeErrosNovoApplicationTests;

public class LogControllerTest extends CentralDeErrosNovoApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private LogController logController;
	
	private User user;
	
	private List<Log> logList;
	
	@Before
	public void setUp() {
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(logController).build();
		
		this.user = new User();
		user.setId(1L);
		user.setName("teste");
		user.setEmail("teste@email.com");
		user.setPassword("123456");
		
		this.logList = new ArrayList<>();
		logList.add(new Log(1L, "test", "error", "Erro ao acessar serviço", "Detalhes do erro", "localhost", user));
		logList.add(new Log(2L, "test", "error", "Erro ao acessar serviço", "Detalhes do erro", "localhost", user));
		logList.add(new Log(3L, "test", "error", "Erro ao acessar serviço", "Detalhes do erro", "localhost", user));
	}
	
	@Test
	public void testGetFindAllLogs() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/logs"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
