package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.RoleEntity;

public interface RoleRepositry extends JpaRepository<RoleEntity, Long>{
	Optional<RoleEntity> findByName(String name);

}
