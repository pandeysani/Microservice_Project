package com.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.entites.User;

public interface UserRepository extends JpaRepository<User, String> {

}
