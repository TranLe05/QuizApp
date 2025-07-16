/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.lhbt.quizapp;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.lhbt.pojo.Category;
import com.lhbt.pojo.Choice;
import com.lhbt.pojo.Level;
import com.lhbt.pojo.Question;
import com.lhbt.services.CategoryServices;
import com.lhbt.services.LevelServices;
import com.lhbt.services.QuestionServices;
import com.lhbt.utils.MyAlerts;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private VBox vboxChoices;
    @FXML private TextArea txtContent;
    @FXML private ToggleGroup toggleChoice;

    private static final CategoryServices cateServices = new CategoryServices();
    private static final LevelServices levelServices = new LevelServices();
    private static final QuestionServices questionServices = new QuestionServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(cateServices.list()));
            this.cbLevels.setItems(FXCollections.observableList(levelServices.list()));
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
        }
    }

    public void addChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");

        RadioButton rdo = new RadioButton();
        rdo.setToggleGroup(toggleChoice);

        TextField txt = new TextField();
        txt.getStyleClass().add("Input");
        txt.setPromptText("Nội dung lựa chọn.");

        h.getChildren().addAll(rdo, txt);

        this.vboxChoices.getChildren().add(h);
    }

    public void addQuestion(ActionEvent event) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem());

            for (var c: this.vboxChoices.getChildren()) {
                HBox h = (HBox) c;
                Choice choice = new Choice(((TextField)h.getChildren().get(1)).getText(),
                        ((RadioButton)h.getChildren().get(0)).isSelected());
                b.addChoice(choice);
            }
            questionServices.addQuestion(b.build());
            MyAlerts.getInstance().showMessage("Thêm câu hỏi thành công!");
        } catch (SQLException e) {
            MyAlerts.getInstance().showMessage("Thêm không thành công vì " + e.getMessage());
        } catch (Exception e) {
            MyAlerts.getInstance().showMessage("Dữ liệu không hợp lệ!");
        }
    }
}
