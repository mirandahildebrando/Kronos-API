package com.Kronos.Kronos.Register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kronos.Kronos.Register.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
