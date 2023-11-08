package com.sosoburger.careerguide.service.signup;

import com.sosoburger.careerguide.dao.SignUpDAO;
import com.sosoburger.careerguide.dto.request.RequestSignUpDTO;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.repository.SignUpRepository;
import com.sosoburger.careerguide.service.institution.InstitutionService;
import com.sosoburger.careerguide.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private SignUpRepository signUpRepository;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private InstitutionService institutionService;
    @Override
    public SignUpDAO save(RequestSignUpDTO signUpDTO) {
        try {
            SignUpDAO signUpDAO = signUpDTO.toDAO();
            signUpDAO.setSchedule(scheduleService.get(signUpDTO.getSchedule()));
            signUpDAO.setInstitution(institutionService.get(signUpDTO.getInstitution()));
            signUpDAO.setStatus(null);
            return signUpRepository.save(signUpDAO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public SignUpDAO update(Integer id, RequestSignUpDTO signUpDTO) {
        SignUpDAO savedSignUp = get(id);
        try {
            SignUpDAO signUpDAO = signUpDTO.toDAO();
            signUpDAO.setSignUpId(savedSignUp.getSignUpId());
            signUpDAO.setSchedule(savedSignUp.getSchedule());
            signUpDAO.setInstitution(savedSignUp.getInstitution());
            return signUpRepository.save(signUpDAO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SignUpDAO get(Integer id) {
        String notFound = String.format("Заявка %d не найдена.", id);
        if (signUpRepository.findById(id).isEmpty()) {
            throw new NotFoundException(notFound);
        } else {
            return signUpRepository.findById(id).get();
        }
    }

    @Override
    public void delete(Integer id) {
        SignUpDAO signUpDAO = get(id);
        try {
            signUpRepository.delete(signUpDAO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeStatus(Integer id, Boolean status){
        SignUpDAO signUpDAO = get(id);
        signUpDAO.setStatus(status);
        signUpRepository.save(signUpDAO);
    }
}
