package com.ms.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
   // @GeneratedValue(strategy=GenerationType.AUTO)
	private String userId;
   
    @Column(name="user_name")
	private String name;
    @Column(name="user_eamil",unique=true)
	private String email;
    @Column(name="user_password",length=10)
	private String password;
	private String gender;
	@Column(name="user_about",length=1000)
	private String about;
	
	@Column(name="user_image_name")
	private String imageName;
}
