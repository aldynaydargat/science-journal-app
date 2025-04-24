package ru.mfu.cs.sciencejournalfront.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.mfu.cs.sciencejournalfront.model.CreatePaperRequest;
import ru.mfu.cs.sciencejournalfront.model.ResearchPaper;
import ru.mfu.cs.sciencejournalfront.service.ResearchPaperFileService;
import ru.mfu.cs.sciencejournalfront.service.ResearchPaperService;
import ru.mfu.cs.sciencejournalfront.util.DesktopApi;

import java.io.File;

@FxmlView("AddPage.fxml")
@Component
@RequiredArgsConstructor
@Slf4j
public class AddPageController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea abstractField;

    @FXML
    private TextArea citationField;

    @FXML
    private TextField keywordsField;

    @FXML
    private TextField authorsField;

    @FXML
    private Label pdfFileLabel;

    private final FileChooser fileChooser = new FileChooser();

    private final FxWeaver fxWeaver;

    private final ResearchPaperService researchPaperService;

    private final ResearchPaperFileService researchPaperFileService;

    private File selectedFile;

    @FXML
    public void initialize() {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                "Pdf files filter",
                "*.pdf"
        ));
    }

    @FXML
    public void handleSavePaper() {
        CreatePaperRequest createPaperRequest = buildAddPaperRequest();
        ResearchPaper savedResearchPaper = researchPaperService.createResearchPaper(createPaperRequest);
        researchPaperFileService.uploadFile(savedResearchPaper.getId(), selectedFile);
        navigateToSearchPage();
    }

    @FXML
    public void chooseFile() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile == null) {
            return;
        }

        pdfFileLabel.setText(selectedFile.getName());
    }

    @FXML
    public void handleMouseEntered(MouseEvent mouseEvent) {
        pdfFileLabel.setTextFill(Color.BLUE);
        pdfFileLabel.setStyle("-fx-underline: true;");
    }

    @FXML
    public void handleMouseExited(MouseEvent mouseEvent) {
        pdfFileLabel.setTextFill(Color.BLACK);
        pdfFileLabel.setStyle("-fx-underline: false;");
    }

    @FXML
    public void handleMouseClicked(MouseEvent mouseEvent) {
        if (selectedFile == null) {
            log.error("How did we get here?...");
            return;
        }

        DesktopApi.open(selectedFile);
    }

    private CreatePaperRequest buildAddPaperRequest() {
        return CreatePaperRequest.builder()
                .title(titleField.getText())
                .abstractText(abstractField.getText())
                .citation(citationField.getText())
                .keywords(keywordsField.getText())
                .authorNames(authorsField.getText())
                .build();
    }

    private void navigateToSearchPage() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        Parent root = fxWeaver.loadView(SearchPageController.class);
        stage.setScene(new Scene(root));
    }

}
