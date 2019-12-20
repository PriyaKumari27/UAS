package com.cg.uams.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.uams.dto.Program;

/*
 * @author : Priya Kumari
 * Description : Program Repository
 * Created on : 16-12-2019
 */
public interface ProgramRepository extends JpaRepository<Program, BigInteger>{

}
