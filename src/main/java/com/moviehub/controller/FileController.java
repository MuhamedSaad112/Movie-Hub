package com.moviehub.controller;


import com.moviehub.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file/")
@RequiredArgsConstructor
public class FileController {


    private final FileService fileService;



    @Value("${project.poster}")
    private String path;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException {

        String uploadFileName = fileService.uploadFile(path, file);
        return ResponseEntity.ok("File Uploaded  " + uploadFileName);
    }

//    @GetMapping("{fileName}")
//    public void serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
//
//        InputStream resourceFile = fileService.getResourceFile(path, fileName);
//
//        response.setContentType(MediaType.IMAGE_PNG_VALUE);
//
//        StreamUtils.copy(resourceFile, response.getOutputStream());
//    }

    //    @GetMapping("/video/{fileName}")
    @GetMapping("{fileName}")
    public void serveVideoHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {

        // الحصول على الفيديو كـ InputStream
        InputStream resourceFile = fileService.getResourceFile(path, fileName);

        // تعيين نوع المحتوى إلى الفيديو
        String mimeType = Files.probeContentType(Paths.get(path, fileName));
        response.setContentType(mimeType != null ? mimeType : "video/mp4");

        // نسخ الفيديو إلى استجابة HTTP
        StreamUtils.copy(resourceFile, response.getOutputStream());
    }


}
