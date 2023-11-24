package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dao.FileDAO;
import com.sosoburger.careerguide.exception.UploadException;
import com.sosoburger.careerguide.rest.api.FileApi;
import com.sosoburger.careerguide.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class FileController implements FileApi {
    @Autowired
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public ResponseEntity<Integer> upload(MultipartFile file) {
        try {
            Integer id = fileService.save(file).getId();
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new UploadException("Ошибка загрузки");
        }
    }

    @Override
    public ResponseEntity<byte[]> getFile(Integer id) {
        FileDAO file = fileService.get(id);
        String fileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\""
                )
                .body(file.getData());
    }
}
