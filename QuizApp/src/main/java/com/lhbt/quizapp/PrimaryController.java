package com.lhbt.quizapp;

import com.lhbt.utils.MyAlerts;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {
    public void handleQuestionsManager(ActionEvent event) throws IOException {
        Scene scene = new Scene(new FXMLLoader(App.class.getResource("questions.fxml")).load());
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quiz App");
        stage.show();
    }
    
    public void handlePractice(ActionEvent event) {
        MyAlerts.getInstance().showMessage("Comming soon...");
    }
    
    public void handleExam(ActionEvent event) {
        MyAlerts.getInstance().showMessage("Comming soon...");
    }
}
