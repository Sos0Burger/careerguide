package com.sosoburger.careerguide.service.institution;

import com.sosoburger.careerguide.dao.CompanyDAO;
import com.sosoburger.careerguide.dao.InstitutionDAO;
import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;

public interface InstitutionService {
    InstitutionDAO save (RequestInstitutionDTO institutionDTO);
    InstitutionDAO update (Integer id, RequestInstitutionDTO institutionDTO);
    InstitutionDAO get (Integer id);
    void delete(Integer id);
}
