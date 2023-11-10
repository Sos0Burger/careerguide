package com.sosoburger.careerguide.service.institution;


import com.sosoburger.careerguide.dao.InstitutionDAO;
import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.repository.InstitutionRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public InstitutionDAO save(RequestInstitutionDTO institutionDTO) {
        try {
            return institutionRepository.save(institutionDTO.toDAO());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstitutionDAO update(Integer id, RequestInstitutionDTO institutionDTO) {
        InstitutionDAO savedInstitutionDAO = get(id);
        try {
            InstitutionDAO institutionDAO = institutionDTO.toDAO();
            institutionDAO.setId(savedInstitutionDAO.getId());
            institutionDAO.setSignUps(savedInstitutionDAO.getSignUps());
            return institutionRepository.save(institutionDAO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstitutionDAO get(Integer id) {
        String notFound = String.format("Учебное заведение %d не найден.", id);
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
}
