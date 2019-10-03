package br.com.crcarvalho.central.repository.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.crcarvalho.central.entity.Log;
import br.com.crcarvalho.central.repository.filtro.LogFilter;

@Component
public class LogRepositoryQueriesImpl implements LogRepositoryQueries {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Log> filterOut(LogFilter filter) {
		
		final StringBuilder sb = new StringBuilder();
		final Map<String, Object> params = new HashMap<>();
		
		sb.append("SELECT l FROM Log l WHERE 1=1");
		
		// preenche os parâmetros da query
		fillEnvironment(filter, sb, params);		
		fillField(filter, sb, params);
		fillInOrder(filter, sb, params);
		
		// cria a typed query
		TypedQuery<Log> query = em.createQuery(sb.toString(), Log.class);
		
		// adiciona os parâmetros a query
		params.forEach((s, o) -> query.setParameter(s, o));
		
		// executa a query
		return query.getResultList();
	}

	private void fillInOrder(LogFilter filter, final StringBuilder sb, final Map<String, Object> params) {
		if(StringUtils.hasText(filter.getOrderBy())) {
			sb.append(" ORDER BY l.title ASC");
		}
	}

	private void fillField(LogFilter filter, final StringBuilder sb, final Map<String, Object> params) {
		if(StringUtils.hasText(filter.getSearchFor()) && StringUtils.hasText(filter.getValue())) {
			sb.append(" AND l." + filter.getSearchFor() + " LIKE :value");
			params.put("value", "%" + filter.getValue() + "%");
		}
	}

	private void fillEnvironment(LogFilter filter, final StringBuilder sb, final Map<String, Object> params) {
		if(StringUtils.hasText(filter.getEnvironment())) {
			sb.append(" AND l.environment = :environment");
			params.put("environment", filter.getEnvironment());
		}
	}

}
