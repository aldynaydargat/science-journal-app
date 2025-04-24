package ru.mfu.cs.sciencejournalback.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResearchPaper {

    private Long id;

    private String title;

    private String abstractText;

    private String citationText;

    private String keywords;

    private String authors;

    private String filename;

}

