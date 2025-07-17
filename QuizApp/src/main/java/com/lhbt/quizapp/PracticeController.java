/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.lhbt.quizapp;

import com.lhbt.pojo.Question;
import com.lhbt.utils.Configs;
import com.lhbt.utils.MyAlerts;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {
    @FXML private TextField txtNum;
    @FXML Text txtContent;
    @FXML VBox vboxChoices;
    @FXML Text txtResult;
    
    private List<Question> questions;
    private int currentQuestion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void handleStart(ActionEvent event) throws Exception {
        try {
            int num = Integer.parseInt(this.txtNum.getText());
            questions = Configs.questionServices.getQuestions(num);
            
            this.currentQuestion = 0;
            this.loadQuestion();
        } catch (NumberFormatException ex) {
            MyAlerts.getInstance().showMessage("Vui lòng nhập số lượng câu hỏi hợp lệ!");
        }
    }
    
    private void loadQuestion() {
        Question q = this.questions.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());
        
        ToggleGroup g = new ToggleGroup();
        vboxChoices.getChildren().clear();
        for (var c: q.getChoices()) {
            RadioButton rdo = new RadioButton(c.getContent());
            rdo.setToggleGroup(g);
            
            vboxChoices.getChildren().add(rdo);
        }
    }
    
    public void handleCheck(ActionEvent event) {
        Question q = this.questions.get(this.currentQuestion);
        for (int i = 0; i < q.getChoices().size(); i++) {
            if (q.getChoices().get(i).isCorrect()) {
                RadioButton r = (RadioButton)this.vboxChoices.getChildren().get(i);
                if (r.isSelected()) {
                    this.txtResult.setText("ĐÚNG RỒI!");
                    this.txtResult.setStyle("-fx-fill: blue");
                } else {
                    this.txtResult.setText("SAI RỒI!");
                    this.txtResult.setStyle("-fx-fill: red");
                }
            }
        }
    }
    
    public void handleNext(ActionEvent event) {
        if (this.currentQuestion < questions.size() - 1) {
            this.currentQuestion++;
            this.loadQuestion();
        }
    }
}
