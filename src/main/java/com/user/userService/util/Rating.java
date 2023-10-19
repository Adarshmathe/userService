package com.user.userService.util;

import lombok.Data;

@Data
public class Rating {
	
	private String ratingId;
	private long userId;
	private long hotelId;
	private String rating;
	private String feedback;
	
	private Hotel hotel;

	
	
	

}
