package com.polam.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polam.login.entity.RoleMaster;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Integer> {

}
