package br.com.crcarvalho.central.service;

import java.util.List;

import br.com.crcarvalho.central.entity.Log;
import br.com.crcarvalho.central.repository.LogRepository;
import br.com.crcarvalho.central.service.exception.LogNotFoundException;

public class LogServiceImpl implements LogService {

	private LogRepository logRepository;

	public LogServiceImpl(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public Log toSave(Log log) {
		
		return logRepository.save(log);
	}

	@Override
	public List<Log> findAll() {
		
		return logRepository.findAll();
	}

	@Override
	public Log findById(Long id) {
		
		return logRepository.findById(id).orElseThrow(() -> new LogNotFoundException("Log não encontrado para o id " + id));
	}

}
