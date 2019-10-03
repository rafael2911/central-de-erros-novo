package br.com.crcarvalho.central.repository.helper;

import java.util.List;

import br.com.crcarvalho.central.entity.Log;
import br.com.crcarvalho.central.repository.filtro.LogFilter;

public interface LogRepositoryQueries {
	
	List<Log> filterOut(LogFilter filter);
	
}
