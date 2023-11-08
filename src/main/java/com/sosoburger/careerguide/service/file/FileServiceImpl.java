package com.sosoburger.careerguide.service.file;

import com.sosoburger.careerguide.dao.FileDAO;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.exception.UploadException;
import com.sosoburger.careerguide.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileDAO save(MultipartFile file) throws IOException {
        try {
            return fileRepository.save(
                    new FileDAO(
                            null,
                            file.getOriginalFilename(),
                            file.getContentType(),
                            file.getBytes()
                    )
            );
        } catch (IOException e) {
            throw new UploadException("Ошибка загрузки файлов");
        }
    }

    @Override
    public FileDAO get(Integer id) {
        String notFound = String.format("Файл %d не найден.", id);
        if (fileRepository.findById(id).isEmpty()) {
            throw new NotFoundException(notFound);
        } else {
            return fileRepository.findById(id).get();
        }
    }
}
