package ru.mfu.cs.sciencejournalback.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.mfu.cs.sciencejournalback.domain.model.ResearchPaper;
import ru.mfu.cs.sciencejournalback.domain.service.ResearchPaperService;
import ru.mfu.cs.sciencejournalback.web.dto.ResearchPaperDto;
import ru.mfu.cs.sciencejournalback.web.mapper.ResearchPaperMapper;

@RestController
@RequestMapping("/api/research-paper")
@RequiredArgsConstructor
public class ResearchPaperController {

    private final ResearchPaperMapper researchPaperMapper;
    private final ResearchPaperService researchPaperService;

    @PostMapping
    public ResearchPaperDto createResearchPaper(@RequestBody ResearchPaperDto researchPaperToCreateDto) {
        ResearchPaper researchPaperToCreate = researchPaperMapper.researchPaperToDto(researchPaperToCreateDto);
        ResearchPaper createdResearchPaper = researchPaperService.createResearchPaper(researchPaperToCreate);
        return researchPaperMapper.dtoToResearchPaper(createdResearchPaper);
    }

    @PutMapping
    public ResearchPaperDto updateResearchPaper(@RequestBody ResearchPaperDto researchPaperToUpdateDto) {
        ResearchPaper researchPaperToUpdate = researchPaperMapper.researchPaperToDto(researchPaperToUpdateDto);
        ResearchPaper updatedReseachPaper = researchPaperService.updateResearchPaper(researchPaperToUpdate);
        return researchPaperMapper.dtoToResearchPaper(updatedReseachPaper);
    }

    @DeleteMapping("/{researchPaperId}")
    public void deleteResearchPaper(@PathVariable("researchPaperId") Long researchPaperId) {
        researchPaperService.deleteResearchPaper(researchPaperId);
    }

    @GetMapping("/{researchPaperId}")
    public ResearchPaperDto findResearchPaper(@PathVariable("researchPaperId") Long researchPaperId) {
        ResearchPaper foundResearchPaper = researchPaperService.findResearchPaper(researchPaperId);
        return researchPaperMapper.dtoToResearchPaper(foundResearchPaper);
    }

    @PutMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadPdfFile(@RequestParam("researchPaperId") Long researchPaperId,
                              @RequestPart(value = "file") MultipartFile file) {
        researchPaperService.saveFile(researchPaperId, file);
    }

    @GetMapping(value = "/download-file", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Resource downloadPdfFile(@RequestParam("researchPaperId") Long researchPaperId) {
        return researchPaperService.downloadFile(researchPaperId);
    }

}
