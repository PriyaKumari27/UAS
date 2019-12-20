package com.cg.uams.service;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.uams.dto.Applicant;
import com.cg.uams.dto.Program;
import com.cg.uams.dto.ScheduleAvailable;
import com.cg.uams.exception.ValidationException;
import com.cg.uams.repository.ApplicantRepository;
import com.cg.uams.repository.ProgramRepository;
import com.cg.uams.repository.ScheduleAvailableRepository;
import com.cg.uams.exception.UserErrorMessage;


@Service
@Transactional
public class UASServiceImpl implements UASService{
	
	@Autowired
	private ScheduleAvailableRepository scheduleAvailableRepository;
	
	@Autowired
	private ProgramRepository programRepository;
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to add schedule
	 * Created on       : 16-12-2019
	 * Last Modified on : 16-12-2019
	 */

	@Override
	public ScheduleAvailable addSchedule(ScheduleAvailable schedule) {
		// TODO Auto-generated method stub
		return scheduleAvailableRepository.save(schedule);
	}
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to remove a schedule
	 * Created on       : 16-12-2019
	 * Last Modified on : 16-12-2019
	 */

	@Override
	public boolean removeSchedule(BigInteger scheduleId) throws ValidationException {
		// TODO Auto-generated method stub
		
		Optional<ScheduleAvailable> scheduleToBeDeleted  =  scheduleAvailableRepository.findById(scheduleId);
		if(!scheduleToBeDeleted.isPresent()) {
			throw new ValidationException(UserErrorMessage.userErrorInvalidScheduleId);
		}
		ScheduleAvailable schedule=scheduleToBeDeleted.get();
		schedule.setDeleted(true);
		return true;
	}
	
	
	/*
	 * @author : Priya Kumari
	 * Description : Method to add schedule
	 * Created on : 16-12-2019
	 * Last Modified on : 16-12-2019
	 */

	@Override
	public ScheduleAvailable findSchedule(BigInteger scheduleId) {
		// TODO Auto-generated method stub
		return scheduleAvailableRepository.findById(scheduleId).get();
	}

	@Override
	public List<ScheduleAvailable> getScheduleList() {
		// TODO Auto-generated method stub
		return scheduleAvailableRepository.getScheduleList();
	}

	@Override
	public Program addProgram(BigInteger scheduleId, Program program) {
		// TODO Auto-generated method stub
		ScheduleAvailable schedule = scheduleAvailableRepository.findById(scheduleId).get();
		schedule.getListOfPrograms().add(program);
		return programRepository.save(program);
	}
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to remove a program
	 * Created on       : 17-12-2019
	 * Last Modified on : 17-12-2019
	 */

	@Override
	public boolean removeProgram(BigInteger removeScheduleId, BigInteger removeProgramId) throws ValidationException {
		// TODO Auto-generated method stub
		
		ScheduleAvailable schedule = scheduleAvailableRepository.findById(removeScheduleId).get();
		if(schedule== null) {
			throw new ValidationException("Schedule not found!");
		}
		Program programToBeDeleted = programRepository.findById(removeProgramId).get();
		if(programToBeDeleted == null) {
			throw new ValidationException("Program not found!");
		}
		programToBeDeleted.setDeleted(true);
		schedule.getListOfPrograms().remove(programToBeDeleted);
		return true;
	}
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to get list of programs in a given schedule
	 * Created on       : 17-12-2019
	 * Last Modified on : 17-12-2019
	 */

	@Override
	public List<Program> getListOfPrograms(BigInteger scheduleId) {
		// TODO Auto-generated method stub
		ScheduleAvailable schedule = scheduleAvailableRepository.findById(scheduleId).get();
		List<Program> programList = schedule.getListOfPrograms();
		return programList;
	}
	

	/*
	 * @author          : Priya Kumari
	 * Description      : Method to find a program
	 * Created on       : 17-12-2019
	 * Last Modified on : 17-12-2019
	 */

	@Override
	public Program findProgram(BigInteger programId) {
		// TODO Auto-generated method stub
		return programRepository.findById(programId).get();
	}
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to add an applicant
	 * Created on       : 18-12-2019
	 * Last Modified on : 18-12-2019
	 */

	@Override
	public Applicant addApplicant(Applicant applicant) {
		// TODO Auto-generated method stub
		return  applicantRepository.save(applicant);
	}

	@Override
	public List<Applicant> getApplicantList(BigInteger applicantId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to get the list of applicants in a particular schedule
	 * Created on       : 18-12-2019
	 * Last Modified on : 18-12-2019
	 */

	@Override
	public List<Applicant> getScheduleApplicantList(BigInteger scheduleId) throws ValidationException {
		// TODO Auto-generated method stub
		
		Optional<ScheduleAvailable> schedule = scheduleAvailableRepository.findById(scheduleId);
		if(schedule.isPresent()) {
			return applicantRepository.findByScheduleAndApplicantStatus(schedule.get(), 0);
		}
		throw new ValidationException(UserErrorMessage.userErrorInvalidScheduleId);
	}
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to get the applicants
	 * Created on       : 18-12-2019
	 * Last Modified on : 18-12-2019
	 */

	@Override
	public List<Applicant> getApplicants() {
		// TODO Auto-generated method stub
		return applicantRepository.findAll();
	}
	

	/*
	 * @author          : Priya Kumari
	 * Description      : Method to validate scheduleId
	 * Created on       : 16-12-2019
	 * Last Modified on : 16-12-2019
	 */


	@Override
	public BigInteger validateScheduleId(String scheduleId, List<ScheduleAvailable> scheduleList)
			throws ValidationException {
		// TODO Auto-generated method stub
		
		if(scheduleId.matches("^[0-9]+")) {
			Iterator<ScheduleAvailable> scheduleIterator = scheduleList.iterator();
			while (scheduleIterator.hasNext()) {
				ScheduleAvailable schedule = scheduleIterator.next();
				if (schedule.getScheduleId().compareTo(new BigInteger(scheduleId)) == 0) {
					
					return schedule.getScheduleId();
				}
			}
			
		}
		throw new ValidationException(UserErrorMessage.userErrorInvalidScheduleId);
		
	}
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to validate programId
	 * Created on       : 17-12-2019
	 * Last Modified on : 17-12-2019
	 */


	@Override
	public BigInteger validateProgramId(String programId, List<Program> programList) throws ValidationException {
		// TODO Auto-generated method stub
		if (programId.matches("^[0-9]+")) {
			Iterator<Program> programIterator = programList.iterator();
			while (programIterator.hasNext()) {
				Program program = programIterator.next();
				if (program.getProgramId().compareTo(new BigInteger(programId)) == 0) {
					
					return program.getProgramId();
				}
			}
		}
		
		throw new ValidationException(UserErrorMessage.userErrorInvalidProgramId);
	}
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to validate applicantId
	 * Created on       : 18-12-2019
	 * Last Modified on : 18-12-2019
	 */

	@Override
	public BigInteger validateApplicantId(String applicantId, List<Applicant> listOfApplicant)
			throws ValidationException {
		// TODO Auto-generated method stub
		if (applicantId.matches("^[0-9]+")) {
			Iterator<Applicant> applicantIterator = listOfApplicant.iterator();
			while (applicantIterator.hasNext()) {
				Applicant applicant = applicantIterator.next();
				if ((applicant.getApplicantId().compareTo(new BigInteger(applicantId)) == 0)
						&& (applicant.getApplicantAppliedStatus() == 0)) {
					return new BigInteger(applicantId);
				}
			}
		}
		throw new ValidationException(UserErrorMessage.userErrorInvalidApplicantId);

	}
	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to approve applicant
	 * Created on       : 18-12-2019
	 * Last Modified on : 18-12-2019
	 */

	@Override
	public boolean approveApplicant(BigInteger applicantId) throws ValidationException {
		// TODO Auto-generated method stub
		Optional<Applicant> applicantToBeApproved = applicantRepository.findById(applicantId);
		if(applicantToBeApproved.isPresent()) {
			Applicant applicantObject = applicantToBeApproved.get();
			applicantObject.setApplicantAppliedStatus(1);
		}
		else {
			throw new ValidationException(UserErrorMessage.userErrorInvalidApplicantId);
		}
		return true;
	}

	
	/*
	 * @author          : Priya Kumari
	 * Description      : Method to reject applicant
	 * Created on       : 18-12-2019
	 * Last Modified on : 18-12-2019
	 */
	
	@Override
	public boolean rejectApplicant(BigInteger applicantId) throws ValidationException {
		// TODO Auto-generated method stub
		Optional<Applicant> applicantToBeRejected = applicantRepository.findById(applicantId);
		if(applicantToBeRejected.isPresent()) {
			Applicant applicantObject = applicantToBeRejected.get();
			applicantObject.setApplicantAppliedStatus(2);
			
		}else {
			
			throw new ValidationException(UserErrorMessage.userErrorInvalidApplicantId);
		}
		return true;
		
		
	}

}
