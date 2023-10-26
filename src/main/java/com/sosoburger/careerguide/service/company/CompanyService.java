package com.sosoburger.careerguide.service.company;

import com.sosoburger.careerguide.dao.CompanyDAO;
import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;

public interface CompanyService {
    CompanyDAO save (RequestCompanyDTO companyDTO);
    CompanyDAO update (Integer id, RequestCompanyDTO companyDTO);
    CompanyDAO get (Integer id);
    void delete(Integer id);
}
