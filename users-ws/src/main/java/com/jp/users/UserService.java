package com.jp.users;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

}
