package com.example.myproject;

import android.app.Application;

public class UserSettings extends Application {

    public static final String PREFERENCE="preference";
    public static final String CUSTOMTHEME="customTheme";
    public static final String LIGHTTHEME="lightTheme";
    public static final String DARKTHEME="darkTheme";
    private String customTheme;

    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }
}
