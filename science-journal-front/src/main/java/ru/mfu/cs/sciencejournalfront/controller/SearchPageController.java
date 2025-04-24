package ru.mfu.cs.sciencejournalfront.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.mfu.cs.sciencejournalfront.model.table.ResearchPaperTableItem;
import ru.mfu.cs.sciencejournalfront.service.ResearchPaperService;

import java.util.List;

@Component
@RequiredArgsConstructor
@FxmlView("SearchPage.fxml")
@Slf4j
public class SearchPageController {

    @FXML
    private TextField searchField;
    @FXML
    private TableView<ResearchPaperTableItem> papersTable;
    @FXML
    private TableColumn<ResearchPaperTableItem, String> titleColumn;
    @FXML
    private TableColumn<ResearchPaperTableItem, String> authorsColumn;
    @FXML
    private TableColumn<ResearchPaperTableItem, String> keywordsColumn;

    private final ResearchPaperService researchPaperService;

    private final FxWeaver fxWeaver;

    @FXML
    private void handleSearch(ActionEvent event) {
        String query = searchField.getText().trim();

        List<ResearchPaperTableItem> researchPaperTableItems = researchPaperService.searchBriefInfo(query);

        papersTable.getItems().clear();
        papersTable.getItems().addAll(researchPaperTableItems);
    }

    @FXML
    private void handlePaperSelection() {
        ResearchPaperTableItem selectedPaper = papersTable.getSelectionModel().getSelectedItem();

        if (selectedPaper == null) {
            log.error("Selected paper is null");
            return;
        }

        log.info("Selected paper ID [{}]", selectedPaper.getId());

        FxControllerAndView<PaperDetailsController, Node> controllerAndView = fxWeaver.load(PaperDetailsController.class);

        PaperDetailsController paperDetailsController = controllerAndView.getController();
        paperDetailsController.setResearchPaperId(selectedPaper.getId());

        Stage stage = (Stage) searchField.getScene().getWindow();
        Parent root = (Parent) controllerAndView.getView().orElse(null);
        stage.setScene(new Scene(root));
    }

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorsColumn.setCellValueFactory(cellData -> cellData.getValue().authorsProperty());
        keywordsColumn.setCellValueFactory(cellData -> cellData.getValue().keywordsProperty());

        papersTable.setOnMouseClicked(event -> handlePaperSelection());
    }
}
