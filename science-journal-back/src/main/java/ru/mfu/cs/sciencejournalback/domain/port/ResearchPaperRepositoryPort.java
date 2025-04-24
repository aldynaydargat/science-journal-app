package ru.mfu.cs.sciencejournalback.domain.port;

import ru.mfu.cs.sciencejournalback.domain.model.BriefResearchPaper;
import ru.mfu.cs.sciencejournalback.domain.model.ResearchPaper;

import java.util.List;

public interface ResearchPaperRepositoryPort {

    List<BriefResearchPaper> searchBriefResearchPapers(String searchQuery);

    ResearchPaper save(ResearchPaper researchPaperToSave);

    void delete(Long researchPaperId);

    ResearchPaper findById(Long researchPaperId);

    String findFilenameById(Long researchPaperId);

    void updateFile(Long id, String filename);

}
