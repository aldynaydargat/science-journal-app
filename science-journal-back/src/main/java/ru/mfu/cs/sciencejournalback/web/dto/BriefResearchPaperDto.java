package ru.mfu.cs.sciencejournalback.web.dto;

import lombok.Data;

@Data
public class BriefResearchPaperDto {

    private Long id;

    private String title;

    private String authors;

    private String keywords;

}
