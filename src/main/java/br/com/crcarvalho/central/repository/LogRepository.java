package br.com.crcarvalho.central.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crcarvalho.central.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

}
