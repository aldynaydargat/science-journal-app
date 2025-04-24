package ru.mfu.cs.sciencejournalback.db.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.mfu.cs.sciencejournalback.db.entity.ResearchPaperEntity;
import ru.mfu.cs.sciencejournalback.domain.model.ResearchPaper;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ResearchPaperEntityMapper {

    ResearchPaperEntity researchPaperToEntity(ResearchPaper researchPaper);

    ResearchPaper entityToResearchPaper(ResearchPaperEntity researchPaperEntity);

}
