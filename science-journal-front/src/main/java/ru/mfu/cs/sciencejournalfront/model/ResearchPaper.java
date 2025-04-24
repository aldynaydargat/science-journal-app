package ru.mfu.cs.sciencejournalfront.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResearchPaper {

    private Long id;

    private String title;

    private String abstractText;

    private String citationText;

    private String keywords;

    private String authors;

    private String filename;

}
