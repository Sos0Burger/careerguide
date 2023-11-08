package com.sosoburger.careerguide.service.file;

import com.sosoburger.careerguide.dao.FileDAO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileDAO save(MultipartFile file) throws IOException;
    FileDAO get(Integer id);
}
