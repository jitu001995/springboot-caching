package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

import com.ms.dto.ApiResponseMessage;
import com.ms.dto.UserDto;
import com.ms.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
		name="Crud Rest Apis for user Resource",
		description="CRUD Rest Apis- Create User, Update User, Get User,Get All Users,Delete User"
     )
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
   
	// create
	@PostMapping("/")
	@Operation(
			summary=" Create User Rest Api",
			description="Create User Rest Api is used to save User in a database"
			)
	@ApiResponse(
			responseCode="201",
			description="HttpStatus 201 Created"
			)
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		 UserDto userDto1=userService.createUser(userDto);
		 return new ResponseEntity<>(userDto1,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{userId}")
	@CachePut(cacheNames="users", key="#userId")
	@Operation(
			summary=" Update User Rest Api",
			description="Update User Rest Api is used to update User in a database"
			)
	@ApiResponse(
			responseCode="201",
			description="HttpStatus 201 Created"
			)
	public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId,@RequestBody UserDto userDto){
		
		UserDto updatedUserDto = userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);		
	}
	
	// delete
	@DeleteMapping("/{userId}")
	@CacheEvict(cacheNames="users", key="#userId",allEntries=true)
	@Operation(
			summary=" Delete User Rest Api",
			description="Delete User Rest Api is used to delete User in a database"
			)
	@ApiResponse(
			responseCode="200",
			description="HttpStatus 200 Created"
			)
	public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){
		userService.deleteUser(userId);
		ApiResponseMessage message = ApiResponseMessage
				                               .builder()
				                               .message("User is deleted Successfully !!")
				                               .success(true)
				                               .status(HttpStatus.OK).build();
		return new ResponseEntity<ApiResponseMessage>(message,HttpStatus.OK);
		
	}
	
	// get all
	@GetMapping("/")
	@Cacheable(cacheNames="users")
	@Operation(
			summary=" Get All User Rest Api",
			description="Get All User Rest Api is used to get All User in a database"
			)
	@ApiResponse(
			responseCode="200",
			description="HttpStatus 200 Created"
			)
	public ResponseEntity<List<UserDto>> getAllUsers(){
		System.out.println("in controller request");
		return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
	}
	
	// get single
	@GetMapping("/{userId}")
	@Cacheable(cacheNames="users", key="#userId")
	@Operation(
			summary=" get User by id Rest Api",
			description="Get User Rest By Id Api is used to get User in a database"
			)
	@ApiResponse(
			responseCode="200",
			description="HttpStatus 200 Created"
			)
	public ResponseEntity<UserDto> getUserById(@PathVariable String userId){
		 UserDto userDto = userService.getUserById(userId);
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
	
}

