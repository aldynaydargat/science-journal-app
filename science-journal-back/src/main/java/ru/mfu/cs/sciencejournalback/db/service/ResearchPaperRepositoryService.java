package ru.mfu.cs.sciencejournalback.db.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mfu.cs.sciencejournalback.db.entity.ResearchPaperEntity;
import ru.mfu.cs.sciencejournalback.db.entity.projections.BriefResearchPaperProjection;
import ru.mfu.cs.sciencejournalback.db.mapper.BriefResearchPaperProjectionMapper;
import ru.mfu.cs.sciencejournalback.db.mapper.ResearchPaperEntityMapper;
import ru.mfu.cs.sciencejournalback.db.repository.ResearchPaperRepository;
import ru.mfu.cs.sciencejournalback.domain.model.BriefResearchPaper;
import ru.mfu.cs.sciencejournalback.domain.model.ResearchPaper;
import ru.mfu.cs.sciencejournalback.domain.port.ResearchPaperRepositoryPort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResearchPaperRepositoryService implements ResearchPaperRepositoryPort {

    private final ResearchPaperEntityMapper mapper;
    private final ResearchPaperRepository repository;
    private final BriefResearchPaperProjectionMapper briefResearchPaperProjectionMapper;

    @Override
    public List<BriefResearchPaper> searchBriefResearchPapers(String searchQuery) {
        List<BriefResearchPaperProjection> briefResearchPaperProjections =
                repository.searchBriefResearchPapers(searchQuery);
        return briefResearchPaperProjectionMapper.entitiesToResearchPaper(briefResearchPaperProjections);
    }

    @Override
    public ResearchPaper save(ResearchPaper researchPaper) {
        ResearchPaperEntity researchPaperEntity = mapper.researchPaperToEntity(researchPaper);
        ResearchPaperEntity savedResearchPaperEntity = repository.save(researchPaperEntity);
        return mapper.entityToResearchPaper(savedResearchPaperEntity);
    }

    @Override
    public void delete(Long researchPaperId) {
        repository.deleteById(researchPaperId);
    }

    @Override
    public ResearchPaper findById(Long researchPaperId) {
        ResearchPaperEntity researchPaperEntity = repository.findById(researchPaperId).orElse(null);
        return mapper.entityToResearchPaper(researchPaperEntity);
    }

    @Override
    public String findFilenameById(Long researchPaperId) {
        return repository.findFilenameById(researchPaperId);
    }

    @Override
    @Transactional
    public void updateFile(Long id, String filename) {
        repository.updateFileInfo(id, filename);
    }
}
