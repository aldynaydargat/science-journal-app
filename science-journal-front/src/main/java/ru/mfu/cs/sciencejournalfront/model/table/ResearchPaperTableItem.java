package ru.mfu.cs.sciencejournalfront.model.table;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

public class ResearchPaperTableItem {

    @Getter
    @Setter
    private Long id;

    private SimpleStringProperty title;

    private SimpleStringProperty keywords;

    private SimpleStringProperty authors;

    public ResearchPaperTableItem(
            Long id,
            String title,
            String keywords,
            String authors
    ) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.keywords = new SimpleStringProperty(keywords);
        this.authors = new SimpleStringProperty(authors);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getKeywords() {
        return keywords.get();
    }

    public SimpleStringProperty keywordsProperty() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords.set(keywords);
    }

    public String getAuthors() {
        return authors.get();
    }

    public SimpleStringProperty authorsProperty() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors.set(authors);
    }
}
