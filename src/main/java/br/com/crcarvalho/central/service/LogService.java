package br.com.crcarvalho.central.service;

import java.util.List;

import br.com.crcarvalho.central.entity.Log;

public interface LogService {

	Log toSave(Log log);

	List<Log> findAll();

	Log findById(Long id);

	void toRemove(Long id);

	Log toFile(Long id);

}
