package ru.mfu.cs.sciencejournalfront.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("NavigationPanel.fxml")
@RequiredArgsConstructor
public class NavigationPanelController {

    @FXML
    private Button searchPapersButton;

    private final FxWeaver fxWeaver;

    @FXML
    public void navigateToSearchPapers() {
        Stage stage = (Stage) searchPapersButton.getScene().getWindow();
        Parent root = fxWeaver.loadView(SearchPageController.class);
        stage.setScene(new Scene(root));
    }

    @FXML
    public void navigateToAddPaper() {
        Stage stage = (Stage) searchPapersButton.getScene().getWindow();
        Parent root = fxWeaver.loadView(AddPageController.class);
        stage.setScene(new Scene(root));
    }

    @FXML
    public void navigateToAboutAuthor() {
        Stage stage = (Stage) searchPapersButton.getScene().getWindow();
        Parent root = fxWeaver.loadView(AuthorPageController.class);
        stage.setScene(new Scene(root));
    }
}
