package com.jp.users;

public class UserDetailsResponse {

	private String userId;
	private String email;

	public UserDetailsResponse() {

	}

	public UserDetailsResponse(String userId, String email) {
		super();
		this.userId = userId;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
