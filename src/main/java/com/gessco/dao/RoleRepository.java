package com.gessco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gessco.entite.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByRole(String role);
}
