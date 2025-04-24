package ru.mfu.cs.sciencejournalfront.external.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mfu.cs.sciencejournalfront.external.client.ResearchPaperClient;
import ru.mfu.cs.sciencejournalfront.external.mapper.BriefResearchPaperMapper;
import ru.mfu.cs.sciencejournalfront.model.ResearchPaper;
import ru.mfu.cs.sciencejournalfront.model.table.ResearchPaperTableItem;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResearchPaperAdapter {

    private final BriefResearchPaperMapper briefResearchPaperMapper;
    private final ResearchPaperClient researchPaperClient;

    public List<ResearchPaperTableItem> searchBriefInfo(String searchQuery) {
        return briefResearchPaperMapper.dtoToResearchPaperTableItems(researchPaperClient.searchBriedResearchPapers(searchQuery));
    }

    public void deleteResearchPaper(Long id) {
        researchPaperClient.deleteResearchPaper(id);
    }

    public ResearchPaper createResearchPaper(ResearchPaper researchPaper) {
        return researchPaperClient.createResearchPaper(researchPaper);
    }

    public ResearchPaper updateResearchPaper(ResearchPaper researchPaper) {
        return researchPaperClient.updateResearchPaper(researchPaper);
    }

    public ResearchPaper findResearchPaper(Long researchPaperId) {
        return researchPaperClient.findResearchPaper(researchPaperId);
    }

}
