package com.genpact.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.shoppingcart.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}