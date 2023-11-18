package com.sosoburger.careerguide.service.institution;


import com.sosoburger.careerguide.dao.InstitutionDAO;
import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;
import com.sosoburger.careerguide.exception.ConflictException;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.repository.InstitutionRepository;
import com.sosoburger.careerguide.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    @Autowired
    private final UserServiceImpl userService;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository, UserServiceImpl userService) {
        this.institutionRepository = institutionRepository;
        this.userService = userService;
    }

    @Override
    public InstitutionDAO save(RequestInstitutionDTO institutionDTO, String login) {
        if (institutionRepository.findByUser(userService.findByLogin(login)) != null) {
            throw new ConflictException("Учебное заведение уже создано этим аккаунтом");
        }
        try {
            var institution = institutionDTO.toDAO();
            institution.setUser(userService.findByLogin(login));
            return institutionRepository.save(institution);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstitutionDAO update(Integer id, RequestInstitutionDTO institutionDTO) {
        InstitutionDAO savedInstitutionDAO = get(id);
        savedInstitutionDAO.setName(institutionDTO.getName());
        savedInstitutionDAO.setImage(institutionDTO.getImage());
        return institutionRepository.save(savedInstitutionDAO);
    }

    @Override
    public InstitutionDAO get(Integer id) {
        String notFound = String.format("Учебное заведение %d не найдено.", id);
        if (institutionRepository.findById(id).isEmpty()) {
            throw new NotFoundException(notFound);
        } else {
            return institutionRepository.findById(id).get();
        }
    }

    @Override
    public List<InstitutionDAO> getAllInstitution() {
        return new ArrayList<>(institutionRepository.findAll());
    }

    @Override
    public void delete(Integer id) {
        InstitutionDAO institutionDAO = get(id);
        try {
            institutionRepository.delete(institutionDAO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstitutionDAO getByLogin(String login) {
        return institutionRepository.findByUser(userService.findByLogin(login));
    }
}
