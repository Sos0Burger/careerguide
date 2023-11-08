package com.sosoburger.careerguide.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/file")
public interface FileApi {
    @Operation(summary = "Загрузка файла")
    @PostMapping(value = "/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Integer> upload(@RequestPart MultipartFile file);

    @Operation(summary = "Получение файла по ID")
    @GetMapping(value = "/{id}")
    ResponseEntity<byte[]> getFile(@PathVariable Integer id);
}
