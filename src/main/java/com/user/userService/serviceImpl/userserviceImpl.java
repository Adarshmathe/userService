package com.user.userService.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.userService.external.Hotelservice;
import com.user.userService.repo.userRepo;
import com.user.userService.service.UserService;
import com.user.userService.util.Hotel;
import com.user.userService.util.Rating;
import com.user.userService.util.customException;
import com.user.userService.util.user;

@Service
public class userserviceImpl implements UserService{
	
	@Autowired
	private userRepo userrepo;
	
	@Autowired
	private RestTemplate resttemplate;
	
	@Autowired
	private Hotelservice hotelservice;

	@Override
	public user createuser(user user) {
		
		return userrepo.save(user);
	}

	@Override
	public user getuser(long id) {
		
		  Rating[] forObject = resttemplate.getForObject("http://RATING-SERVICE/rating/"+id, Rating[].class);
		
		 List<Rating> ratings = Arrays.stream(forObject).toList();
		 

		 
		List<Rating> ratings1 =  ratings.stream().map(r->{
			 
//			 Hotel hotel = resttemplate.getForObject("http://HOTEL-SERVICE/hotel/"+r.getHotelId(), Hotel.class);

			Hotel hotel = hotelservice.getById(r.getHotelId());
			
			 r.setHotel(hotel);
			return r;
			 
		 }).collect(Collectors.toList());
		  
		 user user1 = userrepo.findById(id).orElseThrow(()->new customException("601","Not found..!!"));
		 
		 user1.setRatings(ratings1);
		 return user1;
	}

	@Override
	public List<user> getAllUser() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}

}
