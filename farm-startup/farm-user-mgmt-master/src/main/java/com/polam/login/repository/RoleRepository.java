package com.polam.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polam.login.entity.Role;
import com.polam.login.entity.RolePk;

@Repository
public interface RoleRepository extends JpaRepository<Role, RolePk> {

	List<Role> findByRolePkUserid(String UserId);
}
