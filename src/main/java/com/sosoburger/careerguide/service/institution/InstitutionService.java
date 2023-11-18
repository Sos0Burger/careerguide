package com.sosoburger.careerguide.service.institution;

import com.sosoburger.careerguide.dao.CompanyDAO;
import com.sosoburger.careerguide.dao.InstitutionDAO;
import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;

import java.util.List;

public interface InstitutionService {
    InstitutionDAO save (RequestInstitutionDTO institutionDTO, String login);
    InstitutionDAO update (Integer id, RequestInstitutionDTO institutionDTO);
    InstitutionDAO get (Integer id);
    List<InstitutionDAO> getAllInstitution();
    void delete(Integer id);

    InstitutionDAO getByLogin(String login);
}
