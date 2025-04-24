package ru.mfu.cs.sciencejournalback.domain.model;

import lombok.Data;

@Data
public class BriefResearchPaper {

    private Long id;

    private String title;

    private String authors;

    private String keywords;

}
