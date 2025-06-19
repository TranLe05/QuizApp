/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhbt.utils;

import javafx.scene.control.Alert;

/**
 *
 * @author admin
 */
public class MyAlerts {
    private static MyAlerts instance;
    private final Alert alert;
    
    private MyAlerts() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
    }
    
    public static MyAlerts getInstance() {
        if (instance == null)
            instance = new MyAlerts();
        return instance;
    }
    
    public void showMessage(String msg) {
        this.alert.setHeaderText("Quiz App");
        this.alert.setContentText(msg);
        this.alert.showAndWait();
    }
}
