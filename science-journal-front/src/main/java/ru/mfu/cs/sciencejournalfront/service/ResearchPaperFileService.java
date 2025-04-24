package ru.mfu.cs.sciencejournalfront.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mfu.cs.sciencejournalfront.external.adapter.ResearchPaperFileAdapter;
import ru.mfu.cs.sciencejournalfront.model.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class ResearchPaperFileService {

    private Path fileDirectoryPath;
    private final ResearchPaperFileAdapter researchPaperFileAdapter;

    public ResearchPaperFileService(@Value("${research-paper-front.research-papers.directory}") String fileDirectoryPathAsString,
                                    ResearchPaperFileAdapter researchPaperFileAdapter) throws IOException {
        this.fileDirectoryPath = Paths.get(fileDirectoryPathAsString).toAbsolutePath().normalize();
        Files.createDirectories(this.fileDirectoryPath);
        this.researchPaperFileAdapter = researchPaperFileAdapter;
    }

    public void uploadFile(Long researchPaperId, File file) {
        MultipartFile multipartFile = new MockMultipartFile(file);
        researchPaperFileAdapter.uploadFile(researchPaperId, multipartFile);
    }

    public File downloadFile(String filename, Long researchPaperId) {
        Resource resource = researchPaperFileAdapter.downloadFile(researchPaperId);
        Path targetDestination = fileDirectoryPath.resolve(filename);
        try {
            Files.copy(resource.getInputStream(), targetDestination);
        } catch (IOException e) {
            log.error("Couldn't download file", e);
        }

        return new File(targetDestination.toString());
    }

}
