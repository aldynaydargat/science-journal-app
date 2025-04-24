package ru.mfu.cs.sciencejournalfront.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.mfu.cs.sciencejournalfront.model.ResearchPaper;
import ru.mfu.cs.sciencejournalfront.service.ResearchPaperFileService;
import ru.mfu.cs.sciencejournalfront.service.ResearchPaperService;
import ru.mfu.cs.sciencejournalfront.util.DesktopApi;

import java.io.File;

@Component
@RequiredArgsConstructor
@FxmlView("PaperDetails.fxml")
@Slf4j
public class PaperDetailsController {

    @FXML
    private Label titleLabel;
    @FXML
    private Label abstractLabel;
    @FXML
    private Label citationTextLabel;
    @FXML
    private Label keywordsLabel;
    @FXML
    private Label authorsLabel;
    @FXML
    private Label pdfFileLabel;

    private final ResearchPaperService researchPaperService;
    private final ResearchPaperFileService researchPaperFileService;

    private final FxWeaver fxWeaver;

    private ResearchPaper researchPaper;

    private File downloadedFile;

    public void setResearchPaperId(Long researchPaperId) {
        researchPaper = researchPaperService.findResearchPaper(researchPaperId);
        refreshView();
    }

    @FXML
    private void handleEditPage() {
        FxControllerAndView<EditPaperPageController, Node> controllerAndView = fxWeaver
                .load(EditPaperPageController.class);

        EditPaperPageController editPaperPageController = controllerAndView.getController();
        editPaperPageController.setResearchPaperToUpdate(researchPaper);

        Stage stage = (Stage) titleLabel.getScene().getWindow();
        Parent root = (Parent) controllerAndView.getView().orElse(null);
        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleDeletePaper() {
        researchPaperService.deleteResearchPaper(researchPaper.getId());
        navigateToSearchPage();
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
        downloadedFile = researchPaperFileService.downloadFile(
                researchPaper.getFilename(),
                researchPaper.getId()
        );
        DesktopApi.open(downloadedFile);
    }

    private void refreshView() {
        titleLabel.setText(researchPaper.getTitle());
        abstractLabel.setText(researchPaper.getAbstractText());
        citationTextLabel.setText(researchPaper.getCitationText());
        keywordsLabel.setText(researchPaper.getKeywords());
        authorsLabel.setText(researchPaper.getAuthors());
        pdfFileLabel.setText(researchPaper.getFilename());
    }

    private void navigateToSearchPage() {
        Stage stage = (Stage) titleLabel.getScene().getWindow();
        Parent root = fxWeaver.loadView(SearchPageController.class);
        stage.setScene(new Scene(root));
    }

}
