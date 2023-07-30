package com.ms.service;

import java.util.List;

import com.ms.dto.UserDto;

public interface UserService {
   // create 
	UserDto createUser(UserDto user);
	
	//update 
	UserDto updateUser(UserDto userDto,String userId);

   // delete user
	void deleteUser(String userId);
	
	// get all users
	List<UserDto> getAllUser();
	
	// get single user by id
	UserDto getUserById(String email);
	
	// get single user by email
	UserDto getUserByEmail(String email);
}
