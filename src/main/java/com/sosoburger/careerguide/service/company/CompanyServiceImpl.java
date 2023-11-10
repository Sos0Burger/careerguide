package com.sosoburger.careerguide.service.company;

import com.sosoburger.careerguide.dao.CompanyDAO;
import com.sosoburger.careerguide.dao.ScheduleDAO;
import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.repository.CompanyRepository;
import com.sosoburger.careerguide.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public CompanyDAO save(RequestCompanyDTO companyDTO) {
        try {
            return companyRepository.save(companyDTO.toDAO());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompanyDAO update(Integer id, RequestCompanyDTO companyDTO) {
        CompanyDAO savedCompanyDAO = get(id);
        try {
            CompanyDAO companyDAO = companyDTO.toDAO();
            companyDAO.setCompanyId(savedCompanyDAO.getCompanyId());
            companyDAO.setSchedule(savedCompanyDAO.getSchedule());
            return companyRepository.save(companyDAO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompanyDAO get(Integer id) {
        String notFound = String.format("Компания %d не найден.", id);
        if (companyRepository.findById(id).isEmpty()) {
            throw new NotFoundException(notFound);
        } else {
            return companyRepository.findById(id).get();
        }
    }

    @Override
    public List<CompanyDAO> getAllCompany() {
            return companyRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        CompanyDAO deleteCompanyDAO = get(id);
        try {
            companyRepository.delete(deleteCompanyDAO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ScheduleDAO> getSchedule(Integer id) {
        get(id);
        return scheduleRepository.findByCompany(id);
    }
}
