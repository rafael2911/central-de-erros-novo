package br.com.codenation.central.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.central.entity.User;
import br.com.codenation.central.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("users")
	@ApiOperation("create a new user")
	public User create(@RequestBody User user) throws Exception{

		return this.userService.toSave(user);
	}
	
	@GetMapping("{id}")
	@ApiOperation("find by id")
	public User findById(@PathVariable("id") Long id) {
		return this.userService.findById(id);
	}
	
}
