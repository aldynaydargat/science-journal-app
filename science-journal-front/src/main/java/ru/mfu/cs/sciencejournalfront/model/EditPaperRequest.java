package ru.mfu.cs.sciencejournalfront.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditPaperRequest {

    private Long id;

    private String title;

    private String abstractText;

    private String citation;

    private String authorNames;

    private String keywords;

    private String filename;

}
