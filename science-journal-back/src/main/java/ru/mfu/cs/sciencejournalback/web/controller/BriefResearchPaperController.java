package ru.mfu.cs.sciencejournalback.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mfu.cs.sciencejournalback.domain.model.BriefResearchPaper;
import ru.mfu.cs.sciencejournalback.domain.service.BriefResearchPaperService;
import ru.mfu.cs.sciencejournalback.web.dto.BriefResearchPaperDto;
import ru.mfu.cs.sciencejournalback.web.mapper.BriefResearchPaperMapper;

import java.util.List;

@RestController
@RequestMapping(value = "/api/research-paper/brief",
        produces = "application/json",
        consumes = "application/json")
@RequiredArgsConstructor
public class BriefResearchPaperController {

    private final BriefResearchPaperService briefResearchPaperService;
    private final BriefResearchPaperMapper briefResearchPaperMapper;

    @GetMapping
    public List<BriefResearchPaperDto> searchBriedResearchPapers(@RequestParam("searchQuery") String searchQuery) {
        List<BriefResearchPaper> foundResearchPapers = briefResearchPaperService.search(searchQuery);
        return briefResearchPaperMapper.briefResearchPapersToDto(foundResearchPapers);
    }

}
