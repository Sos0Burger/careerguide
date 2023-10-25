package com.sosoburger.careerguide.service.company;

import com.sosoburger.careerguide.dao.CompanyDAO;
import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDAO save(RequestCompanyDTO rCDTO) {
        try {
            return companyRepository.save(rCDTO.toDAO());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
