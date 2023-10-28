package com.sosoburger.careerguide.service.signup;

import com.sosoburger.careerguide.dao.SignUpDAO;
import com.sosoburger.careerguide.dto.request.RequestSignUpDTO;

public interface SignUpService {
    SignUpDAO save (RequestSignUpDTO signUpDTO);
    SignUpDAO update (Integer id, RequestSignUpDTO signUpDTO);
    SignUpDAO get (Integer id);
    void delete(Integer id);
}
