package br.com.codenation.central.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.codenation.central.entity.User;

public interface UserService extends UserDetailsService {

	User toSave(User user);
	
	User findById(Long id);
	
	List<User> findAll();

}
