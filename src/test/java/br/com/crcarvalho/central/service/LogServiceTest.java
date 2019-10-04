package br.com.crcarvalho.central.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.crcarvalho.central.entity.Log;
import br.com.crcarvalho.central.repository.LogRepository;
import br.com.crcarvalho.central.service.exception.LogNotFoundException;

@RunWith(SpringRunner.class)
public class LogServiceTest {
	
	@MockBean
	private LogRepository logRepository;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private LogService logService;
	
	private Log log;
	
	@Before
	public void setUp() {
		logService = new LogServiceImpl(logRepository);
		
		log = new Log();
		log.setTitle("Teste log");
		log.setDetails("Testando o registro de log");
		log.setEnvironment("dev");
		log.setLevel("error");
		log.setSource("localhost");
		
		when(logRepository.findById(1L)).thenReturn(Optional.empty());
	}
	
	@Test
	public void shouldSaveLogToRepository() {
		logService.toSave(log);
		
		verify(logRepository).save(log);
	}
	
	@Test
	public void shouldReturnEmptyListWhenNoLogsAreRegistered() {
		when(logRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<Log>(Arrays.asList()));
		Page<Log> logs = logService.findAll(PageRequest.of(0, 10));
		
		verify(logRepository).findAll(PageRequest.of(0, 10));
		assertThat(logs.isEmpty()).isTrue();
	}
	
	@Test
	public void shouldFetchAllLogs() {
		when(logRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<Log>(Arrays.asList(log)));
		Page<Log> logs = logService.findAll(PageRequest.of(0, 10));
		
		verify(logRepository).findAll(PageRequest.of(0, 10));
		assertThat(logs.getNumberOfElements()).isEqualTo(1);
	}
	
	@Test
	public void shouldReturnLogById() {
		when(logRepository.findById(1L)).thenReturn(Optional.of(log));
		Log log = logService.findById(1L);
		
		verify(logRepository).findById(1L);
		assertThat(log).isNotNull();
		assertThat(log.getTitle()).isEqualTo(log.getTitle());
		assertThat(log.getDetails()).isEqualTo(log.getDetails());
		
	}
	
	@Test
	public void mustThrowExceptionForFeatureNotFound() {
		expectedException.expect(LogNotFoundException.class);
		expectedException.expectMessage("Log não encontrado para o id " + 1L);
		
		logService.findById(1L);
	}
	
	@Test
	public void shouldRemoveLogById() {
		when(logRepository.findById(1L)).thenReturn(Optional.of(log));
		logService.toRemove(1L);
		
		verify(logRepository).delete(log);
	}
	
	@Test
	public void mustReturnExceptionToRemoveLogNotFound() {
		expectedException.expect(LogNotFoundException.class);
		expectedException.expectMessage("Log não encontrado para o id " + 1L);
		
		logService.toRemove(1L);
	}
	
	@Test
	public void mustArchiveLogById() {
		when(logRepository.findById(1L)).thenReturn(Optional.of(log));
		Log logChanged = logService.toFile(1L);
		
		verify(logRepository).findById(1L);
		assertThat(logChanged.isFiled()).isTrue();
	}
	
	@Test
	public void mustReturnExceptionToFileLogNotFound() {
		expectedException.expect(LogNotFoundException.class);
		expectedException.expectMessage("Log não encontrado para o id " + 1L);
		
		logService.toFile(1L);
	}
	
}
