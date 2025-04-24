package ru.mfu.cs.sciencejournalfront.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.mfu.cs.sciencejournalfront.external.dto.BriefResearchPaperDto;
import ru.mfu.cs.sciencejournalfront.model.table.ResearchPaperTableItem;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BriefResearchPaperMapper {

    List<ResearchPaperTableItem> dtoToResearchPaperTableItems(List<BriefResearchPaperDto> briefResearchPaperDto);

}
