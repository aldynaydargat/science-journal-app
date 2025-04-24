package ru.mfu.cs.sciencejournalback.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mfu.cs.sciencejournalback.domain.model.BriefResearchPaper;
import ru.mfu.cs.sciencejournalback.domain.port.ResearchPaperRepositoryPort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BriefResearchPaperService {

    private final ResearchPaperRepositoryPort researchPaperRepositoryPort;

    public List<BriefResearchPaper> search(String searchQuery) {
        return researchPaperRepositoryPort.searchBriefResearchPapers(searchQuery);
    }

}
