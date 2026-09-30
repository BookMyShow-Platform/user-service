package com.ticketbooking.user_service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketbooking.user_service.Entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);
}
