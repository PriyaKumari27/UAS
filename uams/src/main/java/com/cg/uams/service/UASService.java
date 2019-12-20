package com.cg.uams.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.uams.dto.Applicant;
import com.cg.uams.dto.Program;
import com.cg.uams.dto.ScheduleAvailable;
import com.cg.uams.exception.ValidationException;



public interface UASService {
	

	public ScheduleAvailable addSchedule(ScheduleAvailable schedule);
	public boolean removeSchedule(BigInteger scheduleId) throws ValidationException; 
	public ScheduleAvailable findSchedule(BigInteger scheduleId);
	public List<ScheduleAvailable> getScheduleList();

	
	public Program addProgram(BigInteger programId, Program program);
	public boolean removeProgram(BigInteger removeScheduleId, BigInteger removeProgramId) throws ValidationException;
	public List<Program> getListOfPrograms(BigInteger scheduleId);
	public Program findProgram(BigInteger programId);
	
	public Applicant addApplicant(Applicant applicant);
	public List<Applicant> getApplicantList(BigInteger applicantId);
	public List<Applicant> getScheduleApplicantList(BigInteger scheduleId) throws ValidationException;
	public List<Applicant> getApplicants();
	
	public BigInteger validateScheduleId(String centerId, List<ScheduleAvailable> scheduleList)throws ValidationException;
	public BigInteger validateProgramId(String programId,List<Program> programList)throws ValidationException;
	public BigInteger validateApplicantId(String applicantId, List<Applicant> listOfApplicant)throws ValidationException;
	
	public boolean approveApplicant(BigInteger applicantId) throws ValidationException;
	public boolean rejectApplicant(BigInteger applicantId) throws ValidationException;

}
