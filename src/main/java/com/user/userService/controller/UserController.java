package com.user.userService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userService.service.UserService;
import com.user.userService.util.user;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	
	@PostMapping("/create")
	public ResponseEntity<user> createuser(@RequestBody user user){
		
		user createuser = userservice.createuser(user);
		
		return new ResponseEntity<user>(createuser,HttpStatus.OK);
	}
	
	int i=0;
	@GetMapping("/{id}")
//	@CircuitBreaker(name = "user-rating-hotel-CB", fallbackMethod = "fallback")
//	@Retry(name = "user-rating-hotel-RETRY", fallbackMethod = "fallback")
	@RateLimiter(name = "user-rating-hotel-rate", fallbackMethod = "fallback")

	public ResponseEntity<user> getuser(@PathVariable long id ){
		
		System.out.println(i++);
		
		user user1 = userservice.getuser(id);
		
		return new ResponseEntity<user>(user1,HttpStatus.OK);
	}
	
	
	public ResponseEntity<user> fallback(long id , Exception ex){
		
		
		user build = user.builder().id(0).username("default").gender("M").build();
	
		return new ResponseEntity<user>(build,HttpStatus.REQUEST_TIMEOUT);

	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<user>> getalluser(){
		
		List<user> allUser = userservice.getAllUser();
		
		
		return new ResponseEntity<List<user>>(allUser,HttpStatus.OK);
	}
	

}
