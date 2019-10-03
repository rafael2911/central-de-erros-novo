package br.com.crcarvalho.central.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.crcarvalho.central.CentralDeErrosNovoApplicationTests;
import br.com.crcarvalho.central.entity.User;

@DataJpaTest
public class UserRepositoryTest extends CentralDeErrosNovoApplicationTests {
	
	private static final String EMAIL = "user@user.com";
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void mustSearchUserByEmail() {
		Optional<User> optional = userRepository.findByEmail(EMAIL);
		
		assertThat(optional.isPresent()).isTrue();
		
		User user = optional.get();
		System.out.println(user);
		assertThat(user.getId()).isEqualTo(2L);
		assertThat(user.getEmail()).isEqualTo(EMAIL);
		assertThat(user.getPassword()).isEqualTo("654321");
	}
	
	@Test
	public void shouldNotFindUserWithNonexistentEmail() {
		Optional<User> optional = userRepository.findByEmail("teste@email.com");
		
		assertThat(optional.isPresent()).isFalse();
	}
	
	@Test
	public void mustRegisterUserInTheDatabase() {
		User user = new User();
		user.setEmail("novo@email.com");
		user.setPassword("senha");
		
		userRepository.save(user);
		
		assertThat(user.getId()).isEqualTo(3L);
	}
	
}
