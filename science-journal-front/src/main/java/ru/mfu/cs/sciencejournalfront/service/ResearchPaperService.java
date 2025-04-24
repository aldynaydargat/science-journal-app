package ru.mfu.cs.sciencejournalfront.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mfu.cs.sciencejournalfront.external.adapter.ResearchPaperAdapter;
import ru.mfu.cs.sciencejournalfront.model.CreatePaperRequest;
import ru.mfu.cs.sciencejournalfront.model.EditPaperRequest;
import ru.mfu.cs.sciencejournalfront.model.ResearchPaper;
import ru.mfu.cs.sciencejournalfront.model.table.ResearchPaperTableItem;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResearchPaperService {

    private final ResearchPaperAdapter researchPaperAdapter;

    public List<ResearchPaperTableItem> searchBriefInfo(String searchQuery) {
        return researchPaperAdapter.searchBriefInfo(searchQuery);
    }

    public ResearchPaper findResearchPaper(Long researchPaperId) {
        return researchPaperAdapter.findResearchPaper(researchPaperId);
    }

    public ResearchPaper editResearchPaper(EditPaperRequest editPaperRequest) {
        ResearchPaper researchPaper = buildResearchPaper(editPaperRequest);
        return researchPaperAdapter.updateResearchPaper(researchPaper);
    }

    public ResearchPaper createResearchPaper(CreatePaperRequest createPaperRequest) {
        ResearchPaper researchPaper = buildResearchPaper(createPaperRequest);
        return researchPaperAdapter.createResearchPaper(researchPaper);
    }

    public void deleteResearchPaper(Long researchPaperId) {
        researchPaperAdapter.deleteResearchPaper(researchPaperId);
    }

    private ResearchPaper buildResearchPaper(CreatePaperRequest createPaperRequest) {
        return ResearchPaper.builder()
                .title(createPaperRequest.getTitle())
                .abstractText(createPaperRequest.getAbstractText())
                .citationText(createPaperRequest.getCitation())
                .keywords(createPaperRequest.getKeywords())
                .authors(createPaperRequest.getAuthorNames())
                .build();
    }

    private ResearchPaper buildResearchPaper(EditPaperRequest editPaperRequest) {
        return ResearchPaper.builder()
                .id(editPaperRequest.getId())
                .title(editPaperRequest.getTitle())
                .abstractText(editPaperRequest.getAbstractText())
                .citationText(editPaperRequest.getCitation())
                .keywords(editPaperRequest.getKeywords())
                .authors(editPaperRequest.getAuthorNames())
                .build();
    }
}
