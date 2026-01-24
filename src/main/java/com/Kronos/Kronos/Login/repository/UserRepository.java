package com.Kronos.Kronos.Login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kronos.Kronos.Login.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
