package ru.mfu.cs.sciencejournalfront.exception;

import javafx.scene.control.Alert;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        alert.setTitle("Error");
        alert.showAndWait();
    }

}
