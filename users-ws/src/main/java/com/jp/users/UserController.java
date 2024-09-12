package com.jp.users;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	private UserService userService;
	private ModelMapper mapper;

	public UserController(UserService userService, ModelMapper mapper) {
		super();
		this.userService = userService;
		this.mapper = mapper;
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDetailsRequest request) {
		UserDto userDto = mapper.map(request, UserDto.class);
		UserDto savedUserDto = userService.createUser(userDto);
		UserDetailsResponse response =  mapper.map(savedUserDto, UserDetailsResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
