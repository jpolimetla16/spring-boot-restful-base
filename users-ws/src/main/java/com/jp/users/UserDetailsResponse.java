package com.jp.users;

public class UserDetailsResponse {

	private Integer id;
	private String username;

	public UserDetailsResponse() {
		
	}

	public UserDetailsResponse(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
