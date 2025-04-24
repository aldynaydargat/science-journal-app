package ru.mfu.cs.sciencejournalfront.external.dto;

import lombok.Data;

@Data
public class BriefResearchPaperDto {

    private Long id;

    private String title;

    private String authors;

    private String keywords;

}
