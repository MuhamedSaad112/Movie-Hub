package com.moviehub.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for file management operations.
 * Provides methods for uploading and retrieving files from the system.
 */
public interface FileService {

    /**
     * Uploads a file to the specified directory.
     *
     * @param path the directory path where the file will be saved
     * @param file the file to upload
     * @return the relative path of the uploaded file
     * @throws IOException if an error occurs during file upload
     */
    String uploadFile(String path, MultipartFile file) throws IOException;

    /**
     * Retrieves a file as an InputStream from the specified directory.
     *
     * @param path     the directory path where the file is located
     * @param fileName the name of the file to retrieve
     * @return an InputStream of the requested file
     * @throws FileNotFoundException if the file is not found
     */
    InputStream getResourceFile(String path, String fileName) throws FileNotFoundException;
}
