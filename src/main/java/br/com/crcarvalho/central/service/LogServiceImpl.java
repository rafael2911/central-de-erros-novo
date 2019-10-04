package br.com.crcarvalho.central.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.crcarvalho.central.entity.Log;
import br.com.crcarvalho.central.repository.LogRepository;
import br.com.crcarvalho.central.service.exception.LogNotFoundException;

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
	public Page<Log> findAll(Pageable paginacao) {
		
		return logRepository.findAll(paginacao);
	}

	@Override
	public Log findById(Long id) {
		
		return logRepository.findById(id).orElseThrow(() -> new LogNotFoundException("Log não encontrado para o id " + id));
	}

	@Override
	public void toRemove(Long id) {
		
		logRepository.delete(findById(id));
	}

	@Override
	public Log toFile(Long id) {
		Log log = findById(id);
		log.setFiled(true);
		
		return log;
	}

}
