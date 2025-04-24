package ru.mfu.cs.sciencejournalback.db.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.mfu.cs.sciencejournalback.db.entity.projections.BriefResearchPaperProjection;
import ru.mfu.cs.sciencejournalback.domain.model.BriefResearchPaper;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BriefResearchPaperProjectionMapper {

    List<BriefResearchPaper> entitiesToResearchPaper(List<BriefResearchPaperProjection> researchPaperEntity);

}
