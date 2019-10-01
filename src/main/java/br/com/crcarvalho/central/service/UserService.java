package br.com.crcarvalho.central.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.crcarvalho.central.entity.User;

public interface UserService extends UserDetailsService {

	User toSave(User user);

}
