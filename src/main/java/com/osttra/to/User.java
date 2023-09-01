package com.osttra.to;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
	
	private String userid;
	private String name;
	private String email;
	private String password;
	private String role;
	private String status;
	private int attempts;
}
