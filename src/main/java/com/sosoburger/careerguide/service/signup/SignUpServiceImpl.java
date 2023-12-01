package com.sosoburger.careerguide.service.signup;

import com.sosoburger.careerguide.dao.SignUpDAO;
import com.sosoburger.careerguide.dto.request.RequestSignUpDTO;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.repository.SignUpRepository;
import com.sosoburger.careerguide.service.company.CompanyService;
import com.sosoburger.careerguide.service.file.FileService;
import com.sosoburger.careerguide.service.institution.InstitutionService;
import com.sosoburger.careerguide.service.schedule.ScheduleService;
import jakarta.mail.Message;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private SignUpRepository signUpRepository;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private FileService fileService;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private JavaMailSender emailSender;

    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Override
    public SignUpDAO save(RequestSignUpDTO signUpDTO) {
        try {
            SignUpDAO signUpDAO = signUpDTO.toDAO();
            signUpDAO.setSchedule(scheduleService.get(signUpDTO.getSchedule()));
            signUpDAO.setInstitution(institutionService.get(signUpDTO.getInstitution()));
            signUpDAO.setStatus(null);
            signUpDAO.setFile(
                    signUpDTO.getFile() == null ?
                            null :
                            fileService.get(signUpDTO.getFile())
            );

            return signUpRepository.save(signUpDAO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public SignUpDAO update(Integer id, RequestSignUpDTO signUpDTO) {
        SignUpDAO savedSignUp = get(id);
        try {
            SignUpDAO signUpDAO = signUpDTO.toDAO();
            signUpDAO.setSignUpId(savedSignUp.getSignUpId());
            signUpDAO.setSchedule(scheduleService.get(signUpDTO.getSchedule()));
            signUpDAO.setInstitution(institutionService.get(signUpDTO.getInstitution()));
            signUpDAO.setFile(fileService.get(signUpDTO.getFile()
            ));
            return signUpRepository.save(signUpDAO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SignUpDAO get(Integer id) {
        String notFound = String.format("Заявка %d не найдена.", id);
        if (signUpRepository.findById(id).isEmpty()) {
            throw new NotFoundException(notFound);
        } else {
            return signUpRepository.findById(id).get();
        }
    }

    @Override
    public void delete(Integer id) {
        SignUpDAO signUpDAO = get(id);
        try {
            signUpRepository.delete(signUpDAO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeStatus(Integer id, Boolean status) {
        SignUpDAO signUpDAO = get(id);
        signUpDAO.setStatus(status);
        if (status && signUpDAO.getFile()!=null) {
            MimeMessagePreparator preparator = message -> {
                message.setFrom("nik.isaev2004@mail.ru");
                message.setRecipients(Message.RecipientType.TO, signUpDAO.getSchedule().getCompany().getEmail());
                message.setSubject("Оповещение!");
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.addAttachment(signUpDAO.getFile().getName(),
                        new ByteArrayDataSource(signUpDAO.getFile().getData(), signUpDAO.getFile().getType()));
                helper.setText("Вы приняли заявку на экскурсию: " +
                        formatter.format(signUpDAO
                                .getSchedule()
                                .getDate()
                                .getTime())
                        +
                        ".\nК вам придет " +
                        signUpDAO.getCount() +
                        " человек");

            };
            emailSender.send(preparator);
        }
        signUpRepository.save(signUpDAO);
    }

    @Override
    public List<SignUpDAO> getCompanyPendingSignUps(Integer id) {
        companyService.get(id);
        return new ArrayList<>(signUpRepository.getCompanyPendingSignUps(id));
    }

    @Override
    public List<SignUpDAO> getCompanySignUpsArchive(Integer id) {
        companyService.get(id);
        List<SignUpDAO> list = new ArrayList<>();
        list.addAll(signUpRepository.getCompanyReviewedSignUps(id, true));
        list.addAll(signUpRepository.getCompanyReviewedSignUps(id, false));
        return list;
    }

    @Override
    public List<SignUpDAO> getInstitutionPendingSignUps(String login) {
        return new ArrayList<>(signUpRepository.getInstitutionPendingSignUps(institutionService.getByLogin(login).getId()));
    }

    @Override
    public List<SignUpDAO> getInstitutionSignUpsArchive(String login) {
        Integer id = institutionService.getByLogin(login).getId();
        List<SignUpDAO> list = new ArrayList<>();
        list.addAll(signUpRepository.getInstitutionReviewedSignUps(id, true));
        list.addAll(signUpRepository.getInstitutionReviewedSignUps(id, false));
        return list;
    }
}
