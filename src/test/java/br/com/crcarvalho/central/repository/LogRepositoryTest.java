package br.com.crcarvalho.central.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.crcarvalho.central.CentralDeErrosNovoApplicationTests;
import br.com.crcarvalho.central.entity.Log;

@DataJpaTest
public class LogRepositoryTest extends CentralDeErrosNovoApplicationTests {
	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void shouldListTheLoggedLogs() {
		List<Log> logs = logRepository.findAll();
		
		assertThat(logs.isEmpty()).isFalse();
	}
	
	@Test
	public void shouldFindLogById() {
		Optional<Log> optional = logRepository.findById(2L);
		
		assertThat(optional.isPresent()).isTrue();
		
		Log log = optional.get();
		
		assertThat(log.getId()).isEqualTo(2L);
		assertThat(log.getTitle()).isEqualTo("Titulo do erro");
		assertThat(log.getUser().getName()).isEqualTo("user");
		assertThat(log.getCreatedAt()).isNotNull();
	}
	
	@Test
	public void shouldNotFindLogById() {
		Optional<Log> optional = logRepository.findById(Long.MAX_VALUE);
		
		assertThat(optional.isPresent()).isFalse();
	}
	
	@Test
	public void mustSaveLogToDatabase() {
		Log log = new Log();
		log.setEnvironment("Test");
		log.setLevel("Error");
		log.setTitle("Log de teste");
		log.setDetails("Testando o registro do log");
		log.setSource("localhost");
		log.setUser(userRepository.findByEmail("user@user.com").get());
		
		logRepository.save(log);
		
		assertThat(log.getId()).isEqualTo(3L);
	}
	
	@Test
	public void shouldRemoveLog() {
		logRepository.deleteById(1L);
		
		Optional<Log> optional = logRepository.findById(1L);
		assertThat(optional.isPresent()).isFalse();
	}
	
	@Test
	public void mustArchiveLog() {
		Optional<Log> optional = logRepository.findById(1L);
		Log log = optional.get();
		log.setFiled(true);
		
		Log logAlterado = logRepository.findById(1L).get();
		assertThat(logAlterado.isFiled()).isTrue();
	}
	
}
