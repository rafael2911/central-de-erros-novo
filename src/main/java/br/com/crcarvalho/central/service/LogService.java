package br.com.crcarvalho.central.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.crcarvalho.central.entity.Log;

public interface LogService {

	Log toSave(Log log);

	Page<Log> findAll(Pageable paginacao);

	Log findById(Long id);

	void toRemove(Long id);

	Log toFile(Long id);

}
