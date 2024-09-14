package com.jp.users;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

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
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDetailsRequest request) {
		UserDto userDto = mapper.map(request, UserDto.class);
		UserDto savedUserDto = userService.createUser(userDto);
		UserDetailsResponse response = mapper.map(savedUserDto, UserDetailsResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Integer id) {
		UserDto userDto = userService.getUser(id);
		UserDetailsResponse response = mapper.map(userDto, UserDetailsResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<UserDto> list = userService.getAllUsers();
		List<UserDetailsResponse> response = list.stream()
				.map(userDto -> mapper.map(userDto, UserDetailsResponse.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDetailsRequest request) {
		UserDto userDto = userService.updateUser(id, mapper.map(request, UserDto.class));
		UserDto updatedUser = userService.updateUser(id, userDto);

		return ResponseEntity.status(HttpStatus.OK).body(mapper.map(updatedUser, UserDetailsResponse.class));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}

}
