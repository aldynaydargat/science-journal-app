package ru.mfu.cs.sciencejournalback.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ResearchPaperEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String abstractText;

    private String citationText;

    private String filename;

    private String keywords;

    private String authors;

}
