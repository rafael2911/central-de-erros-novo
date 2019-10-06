package br.com.codenation.central.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.codenation.central.entity.User;
import br.com.codenation.central.repository.UserRepository;
import br.com.codenation.central.service.exception.DuplicateEmailException;

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

	@Override
	public User findById(Long id) {
		Optional<User> optional = userRepository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new DuplicateEmailException("User not found for id " + id);

	}

	@Override
	public List<User> findAll() {
		
		return this.userRepository.findAll();
	}

}
