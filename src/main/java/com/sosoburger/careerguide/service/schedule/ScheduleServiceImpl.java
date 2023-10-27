package com.sosoburger.careerguide.service.schedule;

import com.sosoburger.careerguide.dao.ScheduleDAO;
import com.sosoburger.careerguide.dto.request.RequestScheduleDTO;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDAO save(RequestScheduleDTO scheduleDTO) {
        try {
            return scheduleRepository.save(scheduleDTO.toDAO());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ScheduleDAO update(Integer id, RequestScheduleDTO scheduleDTO) {
        ScheduleDAO saveScheduleDAO = get(id);
        try {
            ScheduleDAO scheduleDAO = scheduleDTO.toDAO();
            scheduleDAO.setId(saveScheduleDAO.getId());
            scheduleDAO.setSignUps(saveScheduleDAO.getSignUps());
            return scheduleRepository.save(scheduleDAO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ScheduleDAO get(Integer id) {
        String notFound = String.format("Расписание %d не найден.", id);
        if (scheduleRepository.findById(id).isEmpty()) {
            throw new NotFoundException(notFound);
        } else {
            return scheduleRepository.findById(id).get();
        }
    }

    @Override
    public void delete(Integer id) {
        ScheduleDAO scheduleDAO = get(id);
        try {
            scheduleRepository.delete(scheduleDAO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
