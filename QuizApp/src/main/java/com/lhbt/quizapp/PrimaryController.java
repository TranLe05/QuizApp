package com.lhbt.quizapp;

import com.lhbt.utils.MyAlerts;
import com.lhbt.utils.MyStage;
import com.lhbt.utils.theme.Theme;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable{
<<<<<<< HEAD
    @FXML ComboBox<Theme> cbThemes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
=======
    @FXML ComboBox <Theme> cbThemes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
    }
    
    public void handleChangeThemes(ActionEvent event) {
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
    }
    
    public void handleQuestionsManager(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("questions.fxml");
>>>>>>> c696abc57aa9089a7a18c37e741a9c08560d9830
    }

    public void handleChangeThemes(ActionEvent event) {
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
    }

    public void handleQuestionsManager(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("questions.fxml");
    }

    public void handlePractice(ActionEvent event) {
        MyAlerts.getInstance().showMessage("Comming soon...");
    }

    public void handleExam(ActionEvent event) {
        MyAlerts.getInstance().showMessage("Comming soon...");
    }
}
