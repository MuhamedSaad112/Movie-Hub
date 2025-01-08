package com.moviehub.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        // Validate the file
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload an empty file.");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name is invalid.");
        }

        // Construct the file path using Paths
        Path directoryPath = Paths.get(path);
        Path filePath = directoryPath.resolve(fileName);

        // Ensure the directory exists
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Copy the file to the target location, replacing existing file if necessary
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        // Validate the file name
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name is invalid.");
        }

        // Construct the file path using Paths
        Path filePath = Paths.get(path).resolve(fileName);

        // Check if the file exists before opening
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("File not found: " + filePath.toString());
        }

        try {
            return new FileInputStream(filePath.toFile());
        } catch (IOException e) {
            throw new FileNotFoundException("Error accessing the file: " + filePath.toString());
        }
    }
}
