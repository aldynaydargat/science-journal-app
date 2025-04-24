package ru.mfu.cs.sciencejournalback.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mfu.cs.sciencejournalback.domain.model.ResearchPaper;
import ru.mfu.cs.sciencejournalback.domain.port.ResearchPaperRepositoryPort;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResearchPaperService {

    private final ResearchPaperRepositoryPort repository;
    private final FileService fileService;

    public ResearchPaper createResearchPaper(ResearchPaper researchPaper) {
        return repository.save(researchPaper);
    }

    public void deleteResearchPaper(Long researchPaperId) {
        repository.delete(researchPaperId);
    }

    public ResearchPaper updateResearchPaper(ResearchPaper researchPaper) {
        return repository.save(researchPaper);
    }

    public ResearchPaper findResearchPaper(Long researchPaperId) {
        return repository.findById(researchPaperId);
    }

    public Resource downloadFile(Long researchPaperId) {
        String filename = repository.findFilenameById(researchPaperId);
        return fileService.getFile(filename);
    }

    public void saveFile(Long researchPaperId,
                         MultipartFile multipartFile) {
        try {
            fileService.saveFile(multipartFile);
        } catch (IOException e) {
            log.error("Couldn't save file", e);
            return;
        }

        repository.updateFile(researchPaperId, multipartFile.getOriginalFilename());
    }

    private ResearchPaper update(ResearchPaper updateFrom,
                                 ResearchPaper toUpdate) {
        return toUpdate.toBuilder()
                .title(updateFrom.getTitle())
                .abstractText(updateFrom.getAbstractText())
                .citationText(updateFrom.getCitationText())
                .keywords(updateFrom.getKeywords())
                .authors(updateFrom.getAuthors())
                .build();
    }

}
