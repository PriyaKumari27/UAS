package com.cg.uams.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.uams.dto.User;

/*
 * @author : Priya Kumari
 * Description : User Repository
 * Created on : 16-12-2019
 */
public interface UserRepository extends JpaRepository<User, BigInteger>{

}
