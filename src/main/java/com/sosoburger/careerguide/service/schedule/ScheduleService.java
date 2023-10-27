package com.sosoburger.careerguide.service.schedule;

import com.sosoburger.careerguide.dao.ScheduleDAO;
import com.sosoburger.careerguide.dto.request.RequestScheduleDTO;

public interface ScheduleService {
    ScheduleDAO save (RequestScheduleDTO scheduleDTO);
    ScheduleDAO update (Integer id, RequestScheduleDTO scheduleDTO);
    ScheduleDAO get (Integer id);
    void delete(Integer id);
}
