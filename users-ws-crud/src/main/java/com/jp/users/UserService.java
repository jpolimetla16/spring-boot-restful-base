package com.jp.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jp.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	public UserDto createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString().split("-")[0]);
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		UserEntity savedUserEntity = userRepository.save(userEntity);

		return modelMapper.map(savedUserEntity, UserDto.class);

	}

	public UserDto getUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(userId));
		return modelMapper.map(userEntity, UserDto.class);
	}

	public List<UserDto> getAllUsers() {
		List<UserEntity> list = userRepository.findAll();
		List<UserDto> userDtoList = list.stream()
				.map(userEntity -> new UserDto(userEntity.getUserId(), userEntity.getEmail(), userEntity.getPassword()))
				.collect(Collectors.toList());

		return userDtoList;
	}

	public UserDto updateUser(String userId, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(userId));
		userEntity.setEmail(userDto.getEmail());
		UserEntity updatedUserEntity =  userRepository.save(userEntity);
		return modelMapper.map(updatedUserEntity, UserDto.class);
	}

	@Transactional
	public void deleteUser(String userId) {
		userRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(userId));
		userRepository.deleteByUserId(userId);
	}

}
