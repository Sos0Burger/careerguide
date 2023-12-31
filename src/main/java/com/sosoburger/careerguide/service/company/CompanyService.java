package com.sosoburger.careerguide.service.company;

import com.sosoburger.careerguide.dao.CompanyDAO;
import com.sosoburger.careerguide.dao.ScheduleDAO;
import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDAO save (RequestCompanyDTO companyDTO, String login);
    CompanyDAO update (Integer id, RequestCompanyDTO companyDTO);
    CompanyDAO get (Integer id);
    List<CompanyDAO> getAllCompany();
    void delete(Integer id);

    List<ScheduleDAO> getSchedule(Integer id);

    CompanyDAO findByLogin(String login);
}
