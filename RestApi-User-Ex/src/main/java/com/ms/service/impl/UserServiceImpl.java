package com.ms.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.dto.UserDto;
import com.ms.entity.User;
import com.ms.exception.ResourceNotFoundException;
import com.ms.repo.UserRepository;
import com.ms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
   
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// dto -> entity
		String userId = UUID.randomUUID().toString();
		userDto.setUserId(userId);
		User user= dotToEntity(userDto);
		User savedUser = userRepo.save(user);
		// entity to dto
		UserDto newDto = entityToDto(savedUser);
		return newDto;
	}

	private UserDto entityToDto(User savedUser) {
//		UserDto newDto =UserDto.builder()
//		           .userId(savedUser.getUserId())
//		           .name(savedUser.getName())
//		           .email(savedUser.getEmail())
//		           .password(savedUser.getPassword())
//		           .about(savedUser.getAbout())
//		           .gender(savedUser.getGender())
//		           .imageName(savedUser.getImageName())
//		           .build();
		return mapper.map(savedUser, UserDto.class);
	}

	private User dotToEntity(UserDto userDto) {
//		User user = User.builder()
//		        .userId(userDto.getUserId())
//		        .name(userDto.getName())
//		        .email(userDto.getEmail())
//		        .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();
		        return mapper.map(userDto, User.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, String userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with given Id"));
		user.setName(userDto.getName());
		// email update
		user.setAbout(userDto.getAbout());
		user.setGender(userDto.getGender());
		user.setPassword(userDto.getPassword());
		user.setImageName(userDto.getImageName());
		
		// save data
		User updatedUser = userRepo.save(user);
		UserDto updatedDto=entityToDto(updatedUser);
		return updatedDto;
	}

	@Override
	public void deleteUser(String userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with given Id"));
		userRepo.delete(user);
		
	}

	@Override
	public List<UserDto> getAllUser() {
		System.out.println(" fetching from database");
		List<User> usersList=userRepo.findAll();
		List<UserDto> dtoList=usersList.stream().map(user->entityToDto(user)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public UserDto getUserById(String userId) {
		 System.out.println("in user service fetch user by id from db");
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with given Id"));
		return entityToDto(user);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user = userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found with given id and password !"));
		
		return entityToDto(user);
	}

}
