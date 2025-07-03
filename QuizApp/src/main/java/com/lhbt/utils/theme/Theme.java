package com.lhbt.utils.theme;

import javafx.scene.Scene;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
/**
 *
 * @author admin
 */
public enum Theme {
    DARK {
        @Override
        public void updateTheme(Scene scene) {
            ThemeManager.setThemeFactory(new DarkThemeFactory());
            ThemeManager.applyTheme(scene); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }, LIGHT {
        @Override
        public void updateTheme(Scene scene) {
            ThemeManager.setThemeFactory(new LightThemeFactory());
            ThemeManager.applyTheme(scene);
        }
    }, DEFAULT {
        @Override
        public void updateTheme(Scene scene) {
            ThemeManager.setThemeFactory(new DefaultThemeFactory());
            ThemeManager.applyTheme(scene);
        }
    };

    public abstract void updateTheme(Scene scene);
}
