package com.sosoburger.careerguide.service.speciality;

import com.sosoburger.careerguide.dao.SpecialityDAO;
import com.sosoburger.careerguide.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;
    @Override
    public List<SpecialityDAO> getSpecialities(String type) {
        return specialityRepository.findByTypeContainingIgnoreCase(type);
    }
}
