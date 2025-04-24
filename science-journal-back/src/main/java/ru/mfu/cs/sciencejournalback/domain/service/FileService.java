package ru.mfu.cs.sciencejournalback.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileService {

    private final Path fileDirectoryPath;

    public FileService(@Value("${research-paper-backend.research-papers.directory}") String fileDirectoryPathAsString)
            throws IOException {
        this.fileDirectoryPath = Paths.get(fileDirectoryPathAsString).toAbsolutePath().normalize();
        Files.createDirectories(this.fileDirectoryPath);
    }

    public Resource getFile(String filename) {
        Path sourceLocation = fileDirectoryPath.resolve(filename);

        try {
            return new UrlResource(sourceLocation.toUri());
        } catch (IOException e) {
            log.error("Couldn't get file {}", filename, e);
        }

        return null;
    }

    public void saveFile(MultipartFile multipartFileToSave) throws IOException {
        Path targetLocation = fileDirectoryPath.resolve(multipartFileToSave.getOriginalFilename());
        Files.copy(multipartFileToSave.getInputStream(), targetLocation);
    }

}
