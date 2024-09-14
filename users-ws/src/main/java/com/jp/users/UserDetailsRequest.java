package com.jp.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDetailsRequest {
    
	@NotBlank(message = "username should not be null or empty")
	private String username;
	
	@NotBlank(message="password should not be null or empty")
	@Size(min = 4,message="Pssword should have a valid Length")
	private String password;

	public UserDetailsRequest() {

	}

	public UserDetailsRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
