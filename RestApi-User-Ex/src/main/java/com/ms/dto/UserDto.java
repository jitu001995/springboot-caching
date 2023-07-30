package com.ms.dto;



import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
   
	
	private String userId; 
	
	@Size(min=3, max=15,message="Invalid Name !!")
	private String name;
	
	@Pattern(regexp="^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message="Invalid User Email !!")
	@Email(message="Invalid User Email !!")
	@NotBlank(message="Email is required !!")
	private String email; 
	
	@NotBlank(message="Password is required")
	private String password;
	@Size(min=4, max=6,message="Invalid gender !!")
	private String gender;	
	@NotBlank(message="Write Something About yourSelf")
	private String about;
	
	// @Pattern
	// custom Validator
	
	private String imageName;
}
