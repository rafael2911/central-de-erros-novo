package br.com.crcarvalho.central.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crcarvalho.central.entity.Log;
import br.com.crcarvalho.central.repository.helper.LogRepositoryQueries;

public interface LogRepository extends JpaRepository<Log, Long>, LogRepositoryQueries {

}
