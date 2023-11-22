package com.sosoburger.careerguide.service.signup;

import com.sosoburger.careerguide.dao.SignUpDAO;
import com.sosoburger.careerguide.dto.request.RequestSignUpDTO;

import java.util.List;

public interface SignUpService {
    SignUpDAO save (RequestSignUpDTO signUpDTO);
    SignUpDAO update (Integer id, RequestSignUpDTO signUpDTO);
    SignUpDAO get (Integer id);
    void delete(Integer id);
    void changeStatus(Integer id, Boolean status);

    List<SignUpDAO> getCompanyPendingSignUps(Integer id);

    List<SignUpDAO> getCompanySignUpsArchive(Integer id);

    List<SignUpDAO> getInstitutionPendingSignUps(String login);

    List<SignUpDAO> getInstitutionSignUpsArchive(String login);
}
