package com.user.userService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.userService.util.user;

public interface userRepo extends JpaRepository<user, Long>{

}
