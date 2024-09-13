package com.jp.users;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jp.exception.ResourceNotFoundException;

@Service
public class UserService {

	private UserRepository userRepository;

	private ModelMapper mapper;

	public UserService(UserRepository userRepository, ModelMapper mapper) {
		super();
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	public UserDto createUser(UserDto userDto) {
		UserEntity userEntity = mapper.map(userDto, UserEntity.class);
		UserEntity savedUserEntity = userRepository.save(userEntity);
		return mapper.map(savedUserEntity, UserDto.class);
	}

	public UserDto getUser(Integer id) {
		UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid User Id:"+id));
		return mapper.map(userEntity, UserDto.class);
	}

	public List<UserDto> getAllUsers() {
		List<UserEntity> list = userRepository.findAll();
		return list.stream().map(e->mapper.map(e, UserDto.class)).collect(Collectors.toList());
	}
	
	public UserDto updateUser(Integer id,UserDto userDto) {
		UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid User Id:"+id));
		userEntity.setUsername(userDto.getUsername());
		userEntity.setPassword(userDto.getPassword());
		UserEntity updatedUser = userRepository.save(userEntity);
		return mapper.map(updatedUser, UserDto.class);
	}

	public void deleteUser(Integer id) {
		userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid User Id:"+id));
		userRepository.deleteById(id);
		
	}

}
