package ru.mfu.cs.sciencejournalback.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.mfu.cs.sciencejournalback.domain.model.BriefResearchPaper;
import ru.mfu.cs.sciencejournalback.web.dto.BriefResearchPaperDto;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BriefResearchPaperMapper {

    List<BriefResearchPaperDto> briefResearchPapersToDto(List<BriefResearchPaper> researchPaperDto);

}
