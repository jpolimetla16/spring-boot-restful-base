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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final ModelMapper modelMapper;

	public UserController(UserService userService, ModelMapper modelMapper) {
		super();
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDetailsRequest request) {
		
		UserDto savedUserDto = userService.createUser(modelMapper.map(request, UserDto.class));
		UserDetailsResponse response = modelMapper.map(savedUserDto, UserDetailsResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable String userId){
		UserDto userDto = userService.getUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(userDto, UserDetailsResponse.class));
		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUser(){
		List<UserDto> allUsers = userService.getAllUsers();
		List<UserDetailsResponse> response = allUsers.stream()
			.map(userDto->new UserDetailsResponse(userDto.getUserId(),userDto.getPassword()))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDetailsRequest request) {
		UserDto userDto = modelMapper.map(request, UserDto.class);
		UserDto updatedUserDto = userService.updateUser(userId,userDto);
		UserDetailsResponse response = modelMapper.map(updatedUserDto, UserDetailsResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
