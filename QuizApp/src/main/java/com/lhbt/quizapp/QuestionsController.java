/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.lhbt.quizapp;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.lhbt.pojo.Category;
import com.lhbt.pojo.Level;
import com.lhbt.services.CategoryServices;

import com.lhbt.services.LevelServices;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class QuestionsController implements Initializable{
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private VBox vboxChoices;
    

    private static final CategoryServices cateServices = new CategoryServices();
    private static final LevelServices levelServices = new LevelServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(cateServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
        }
    }
    
    public void addChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");
        
        RadioButton rdo = new RadioButton();
        TextField txt = new TextField();
        
        h.getChildren().addAll(rdo, txt);
        
        this.vboxChoices.getChildren().add(h);
    }
}
