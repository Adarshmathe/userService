package com.user.userService.service;

import java.util.List;

import com.user.userService.util.user;

public interface UserService {
	
	user createuser(user user);
	user getuser(long id);
	List<user> getAllUser();

}
