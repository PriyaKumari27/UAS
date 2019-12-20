package com.cg.uams.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.uams.dto.Applicant;
import com.cg.uams.dto.ScheduleAvailable;


/*
 * @author : Priya Kumari
 * Description : Applicant Repository
 * Created on : 16-12-2019
 */

public interface ApplicantRepository extends JpaRepository<Applicant, BigInteger>{
	@Query("FROM Applicant WHERE schedule = :schedule AND	 applicant_applied_status = :status")
	public List<Applicant> findByScheduleAndApplicantStatus(@Param("schedule")ScheduleAvailable schedule,@Param("status") int status);

}
