package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
UserEntity findByuserName(String userName);

UserEntity findOneByuserNameAndPassword(String userName,String Password);

boolean existsByuserName(String userName);


}
