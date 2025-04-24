package ru.mfu.cs.sciencejournalfront.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.mfu.cs.sciencejournalfront.config.feign.FeignSupportConfig;
import ru.mfu.cs.sciencejournalfront.external.dto.BriefResearchPaperDto;
import ru.mfu.cs.sciencejournalfront.model.ResearchPaper;

import java.util.List;

@FeignClient(
        name = "${science-journal.science-journal-backend.name}",
        url = "${science-journal.science-journal-backend.url}",
        path = "/api/research-paper",
        configuration = FeignSupportConfig.class)
public interface ResearchPaperClient {

    @GetMapping(value = "/brief", consumes = "application/json")
    List<BriefResearchPaperDto> searchBriedResearchPapers(@RequestParam("searchQuery") String searchQuery);

    @DeleteMapping("/{researchPaperId}")
    void deleteResearchPaper(@PathVariable("researchPaperId") Long researchPaperId);

    @PostMapping
    ResearchPaper createResearchPaper(@RequestBody ResearchPaper researchPaper);

    @GetMapping("/{researchPaperId}")
    ResearchPaper findResearchPaper(@PathVariable("researchPaperId") Long researchPaperId);

    @PutMapping
    ResearchPaper updateResearchPaper(@RequestBody ResearchPaper researchPaper);

    @PutMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void uploadPdfFile(@RequestParam("researchPaperId") Long researchPaperId,
                       @RequestPart(value = "file") MultipartFile file);

    @GetMapping(value = "/download-file", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    Resource downloadPdfFile(@RequestParam("researchPaperId") Long researchPaperId);

}
