package com.smengs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smengs.entite.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByRole(String role);
}
