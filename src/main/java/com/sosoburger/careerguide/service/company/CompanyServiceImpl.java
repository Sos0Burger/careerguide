package com.sosoburger.careerguide.service.company;

import com.sosoburger.careerguide.dao.CompanyDAO;
import com.sosoburger.careerguide.dao.ScheduleDAO;
import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.repository.CompanyRepository;
import com.sosoburger.careerguide.repository.ScheduleRepository;
import com.sosoburger.careerguide.service.user.UserServiceImpl;
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
    @Autowired
    private UserServiceImpl userService;
    @Override
    public CompanyDAO save(RequestCompanyDTO companyDTO, String login) {
        try {
            var company = companyDTO.toDAO();
            company.setUser(userService.findByLogin(login));
            return companyRepository.save(company);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompanyDAO update(Integer id, RequestCompanyDTO companyDTO) {
        CompanyDAO savedCompanyDAO = get(id);
        savedCompanyDAO.setCompanyName(companyDTO.getCompanyName());
        savedCompanyDAO.setImage(companyDTO.getImage());
        savedCompanyDAO.setAddress(companyDTO.getAddress());
        savedCompanyDAO.setDescription(companyDTO.getDescription());
        savedCompanyDAO.setEmail(companyDTO.getEmail());
        savedCompanyDAO.setPhone(companyDTO.getPhone());
        return companyRepository.save(savedCompanyDAO);
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

    @Override
    public CompanyDAO findByLogin(String login) {
        return companyRepository.findByUser(userService.findByLogin(login));
    }
}
