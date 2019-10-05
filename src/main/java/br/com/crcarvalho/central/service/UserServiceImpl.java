package br.com.crcarvalho.central.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.crcarvalho.central.entity.User;
import br.com.crcarvalho.central.repository.UserRepository;
import br.com.crcarvalho.central.service.exception.DuplicateEmailException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User toSave(User user) {

		Optional<User> optional = userRepository.findByEmail(user.getEmail());

		if (optional.isPresent()) {
			throw new DuplicateEmailException("E-mail already registered");
		}

		return userRepository.save(user);

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
	}

}
