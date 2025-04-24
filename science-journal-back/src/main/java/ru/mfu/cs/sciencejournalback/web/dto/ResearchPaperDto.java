package ru.mfu.cs.sciencejournalback.web.dto;

import lombok.Data;

@Data
public class ResearchPaperDto {

    private String id;

    private String title;

    private String abstractText;

    private String citationText;

    private String keywords;

    private String authors;

    private String filename;

}
