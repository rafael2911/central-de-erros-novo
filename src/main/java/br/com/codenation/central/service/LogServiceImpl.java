package br.com.codenation.central.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.codenation.central.entity.Log;
import br.com.codenation.central.repository.LogRepository;
import br.com.codenation.central.service.exception.LogNotFoundException;

@Service
public class LogServiceImpl implements LogService {

	private LogRepository logRepository;
	
	@Autowired
	public LogServiceImpl(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public Log toSave(Log log) {
		
		return logRepository.save(log);
	}

	@Override
	public Log findById(Long id) {
		
		return logRepository.findById(id).orElseThrow(() -> new LogNotFoundException("Log não encontrado para o id " + id));
	}

	@Override
	public void toRemove(Long id) {
		
		logRepository.delete(findById(id));
	}
	
	@Transactional
	@Override
	public Log toFile(Long id) {
		Log log = findById(id);
		log.setFiled(true);
		
		return log;
	}

	@Override
	public Page<Log> findAll(Pageable pageable) {
		
		return this.logRepository.findAll(pageable);
	}

	@Override
	public Page<Log> findLogByEnvironment(String environment, Pageable pageable) {
		
		return this.logRepository.findLogByEnvironment(environment, pageable);
	}

	@Override
	public Page<Log> findLogByEnvironmentAndSourceContaining(String environment, String source, Pageable pageable) {
		
		return this.logRepository.findLogByEnvironmentAndSourceContaining(environment, source, pageable);
	}

	@Override
	public Page<Log> findLogByEnvironmentAndLevelContaining(String environment, String level, Pageable pageable) {
		
		return this.logRepository.findLogByEnvironmentAndLevelContaining(environment, level, pageable);
	}

	@Override
	public Page<Log> findLogByEnvironmentAndTitleContaining(String environment, String title, Pageable pageable) {
		
		return this.logRepository.findLogByEnvironmentAndTitleContaining(environment, title, pageable);
	}

	

}
