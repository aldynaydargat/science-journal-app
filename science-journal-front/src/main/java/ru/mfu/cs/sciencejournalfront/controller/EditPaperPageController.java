package ru.mfu.cs.sciencejournalfront.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
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
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.mfu.cs.sciencejournalfront.model.EditPaperRequest;
import ru.mfu.cs.sciencejournalfront.model.ResearchPaper;
import ru.mfu.cs.sciencejournalfront.service.ResearchPaperFileService;
import ru.mfu.cs.sciencejournalfront.service.ResearchPaperService;
import ru.mfu.cs.sciencejournalfront.util.DesktopApi;

import java.io.File;

@Component
@FxmlView("EditPaper.fxml")
@RequiredArgsConstructor
@Slf4j
public class EditPaperPageController {

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

    private ResearchPaper researchPaperToUpdate;

    private File selectedFile;

    public void setResearchPaperToUpdate(ResearchPaper researchPaperToUpdate) {
        this.researchPaperToUpdate = researchPaperToUpdate;
        refreshView();
    }

    @FXML
    public void initialize() {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                "Pdf files filter",
                "*.pdf"
        ));
    }

    @FXML
    public void handleEditPaper() {
        EditPaperRequest editPaperRequest = buildPaperRequest();
        ResearchPaper researchPaper = researchPaperService.editResearchPaper(editPaperRequest);
        goToPaperDetails(researchPaper);
    }

    @FXML
    public void chooseFile() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile == null) {
            // TODO: обработать
            return;
        }

        pdfFileLabel.setText(selectedFile.getName());
        researchPaperFileService.uploadFile(researchPaperToUpdate.getId(), selectedFile);
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
            selectedFile = researchPaperFileService.downloadFile(
                    researchPaperToUpdate.getFilename(),
                    researchPaperToUpdate.getId()
            );
        }

        DesktopApi.open(selectedFile);
    }

    private void goToPaperDetails(ResearchPaper researchPaper) {
        FxControllerAndView<PaperDetailsController, Node> controllerAndView = fxWeaver.load(PaperDetailsController.class);

        PaperDetailsController paperDetailsController = controllerAndView.getController();
        paperDetailsController.setResearchPaperId(researchPaper.getId());

        Stage stage = (Stage) titleField.getScene().getWindow();
        Parent root = (Parent) controllerAndView.getView().orElse(null);
        stage.setScene(new Scene(root));
    }

    private EditPaperRequest buildPaperRequest() {
        return EditPaperRequest.builder()
                .id(researchPaperToUpdate.getId())
                .title(titleField.getText())
                .abstractText(abstractField.getText())
                .citation(citationField.getText())
                .keywords(keywordsField.getText())
                .authorNames(authorsField.getText())
                .filename(pdfFileLabel.getText())
                .build();
    }

    private void refreshView() {
        titleField.setText(researchPaperToUpdate.getTitle());
        abstractField.setText(researchPaperToUpdate.getAbstractText());
        citationField.setText(researchPaperToUpdate.getCitationText());
        keywordsField.setText(researchPaperToUpdate.getKeywords());
        authorsField.setText(researchPaperToUpdate.getAuthors());
        pdfFileLabel.setText(researchPaperToUpdate.getFilename());
    }
}
