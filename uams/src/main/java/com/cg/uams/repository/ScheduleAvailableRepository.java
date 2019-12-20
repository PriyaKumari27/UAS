package com.cg.uams.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.uams.dto.ScheduleAvailable;


/*
 * @author : Priya Kumari
 * Description : ScheduleAvailable Repository
 * Created on : 16-12-2019
 */
public interface ScheduleAvailableRepository extends JpaRepository<ScheduleAvailable, BigInteger>{
	
	@Query("FROM ScheduleAvailable WHERE isDeleted = 'false'")
	public List<ScheduleAvailable> getScheduleList();

}
