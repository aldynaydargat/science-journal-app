package ru.mfu.cs.sciencejournalfront.external.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.mfu.cs.sciencejournalfront.external.client.ResearchPaperClient;

@Component
@RequiredArgsConstructor
public class ResearchPaperFileAdapter {

    private final ResearchPaperClient researchPaperClient;

    public void uploadFile(Long researchPaperId,
                           MultipartFile multipartFile) {
        researchPaperClient.uploadPdfFile(researchPaperId, multipartFile);
    }

    public Resource downloadFile(Long researchPaperId) {
        return researchPaperClient.downloadPdfFile(researchPaperId);
    }

}
