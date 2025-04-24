package ru.mfu.cs.sciencejournalback.db.entity.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BriefResearchPaperProjection {

    private Long id;

    private String title;

    private String authors;

    private String keywords;

}
