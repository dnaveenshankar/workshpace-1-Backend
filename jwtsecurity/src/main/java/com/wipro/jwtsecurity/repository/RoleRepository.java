package com.wipro.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.jwtsecurity.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
