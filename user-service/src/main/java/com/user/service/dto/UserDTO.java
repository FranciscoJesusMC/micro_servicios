package com.user.service.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.user.service.entity.Review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	@NotEmpty(message = "field cannot be empty")
	@Size(min = 5,max = 15)
	private String name;
	@NotEmpty(message = "file cannot be empty")
	@Email
	private String email;
	
	private List<Review> reviews;
}
