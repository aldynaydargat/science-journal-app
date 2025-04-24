package ru.mfu.cs.sciencejournalback.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.mfu.cs.sciencejournalback.domain.model.ResearchPaper;
import ru.mfu.cs.sciencejournalback.web.dto.ResearchPaperDto;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ResearchPaperMapper {

    ResearchPaper researchPaperToDto(ResearchPaperDto researchPaperDto);

    ResearchPaperDto dtoToResearchPaper(ResearchPaper researchPaper);

}
