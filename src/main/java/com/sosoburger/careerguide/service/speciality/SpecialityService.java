package com.sosoburger.careerguide.service.speciality;

import com.sosoburger.careerguide.dao.SpecialityDAO;

import java.util.List;

public interface SpecialityService {
    List<SpecialityDAO> getSpecialities(String type);
}
