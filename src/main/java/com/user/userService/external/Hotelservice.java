package com.user.userService.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.userService.util.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
@Service
public interface Hotelservice {
	
	
	@GetMapping("/hotel/{id}")
	public Hotel getById(@PathVariable Long id);

}
