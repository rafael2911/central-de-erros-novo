package br.com.crcarvalho.central.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.crcarvalho.central.entity.User;
import br.com.crcarvalho.central.repository.UserRepository;
import br.com.crcarvalho.central.service.exception.DuplicateEmailException;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	private static final String NAME = "User";

	private static final String PASSWORD = "123456";

	private static final String EMAIL = "user@email.com";

	@MockBean
	private UserRepository userRepository;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private UserService userService;

	private User user;
	
	@Before
	public void setUp() {
		userService = new UserServiceImpl(userRepository);
		
		user = new User();
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		user.setName(NAME);
		
		when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
	}
	
	@Test
	public void mustSaveUserInRepository() {
		userService.toSave(user);
		
		verify(userRepository).save(user);
	}
	
	@Test
	public void shouldNotSaveTwoUsersWithTheSameEmail() {
		when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(user));

		expectedException.expect(DuplicateEmailException.class);
		expectedException.expectMessage("E-mail already registered");

		userService.toSave(user);
	}
	
	@Test
	public void mustSearchUserByEmail() {	
		when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(user));
		userService.loadUserByUsername(EMAIL);

		verify(userRepository).findByEmail(EMAIL);
	}

	@Test
	public void mustReturnAuthenticatedUser() {
		when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(user));
		UserDetails authenticatedUser = userService.loadUserByUsername(EMAIL);

		assertThat(authenticatedUser.getUsername()).isEqualTo(EMAIL);
		assertThat(authenticatedUser.getPassword()).isEqualTo(PASSWORD);
	}

	@Test
	public void shouldNotReturnUserWithInvalidCredentials() {
		expectedException.expect(UsernameNotFoundException.class);
		expectedException.expectMessage(EMAIL);

		userService.loadUserByUsername(EMAIL);
	}
	
}
