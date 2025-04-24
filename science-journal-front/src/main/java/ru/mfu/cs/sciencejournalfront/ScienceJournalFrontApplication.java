package ru.mfu.cs.sciencejournalfront;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.mfu.cs.sciencejournalfront.exception.GlobalExceptionHandler;

@SpringBootApplication
@EnableFeignClients(basePackages = "ru.mfu.cs.sciencejournalfront.external.**.client")
public class ScienceJournalFrontApplication {

    public static void main(String[] args) {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler);
        Application.launch(ScienceJournalUIApplication.class, args);
    }

}
