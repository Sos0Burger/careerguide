package com.sosoburger.careerguide.service.signup;

import com.sosoburger.careerguide.dao.SignUpDAO;
import com.sosoburger.careerguide.dto.request.RequestSignUpDTO;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private SignUpRepository signUpRepository;
    @Override
    public SignUpDAO save(RequestSignUpDTO signUpDTO) {
        try {
            return signUpRepository.save(signUpDTO.toDAO());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SignUpDAO update(Integer id, RequestSignUpDTO signUpDTO) {
        SignUpDAO savedSignUp = get(id);
        try {
            SignUpDAO SignUpDAO = signUpDTO.toDAO();
            SignUpDAO.setId(savedSignUp.getId());
            SignUpDAO.setSchedule(savedSignUp.getSchedule());
            return signUpRepository.save(SignUpDAO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SignUpDAO get(Integer id) {
        String notFound = String.format("Пользователь %d не найден.", id);
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
}
