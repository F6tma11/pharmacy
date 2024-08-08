package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class Setting extends AppCompatActivity {

    View v;
    SwitchMaterial stch;
    TextView texe;
    ImageView back;
    UserSettings setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int black= ContextCompat.getColor(this,R.color.black);
        final int white= ContextCompat.getColor(this,R.color.white);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        v=findViewById(R.id.setting);
        texe=findViewById(R.id.theme);
        stch=findViewById(R.id.themeSwitch);

        setting=(UserSettings) getApplication();
        SharedPreferences sharedPreferences=getSharedPreferences(UserSettings.PREFERENCE,MODE_PRIVATE);
        String theme=sharedPreferences.getString(UserSettings.CUSTOMTHEME,UserSettings.LIGHTTHEME);
        setting.setCustomTheme(theme);

        stch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    setting.setCustomTheme(UserSettings.DARKTHEME);
                else
                    setting.setCustomTheme(UserSettings.LIGHTTHEME);

                SharedPreferences.Editor editor=getSharedPreferences(UserSettings.PREFERENCE,MODE_PRIVATE).edit();
                editor.putString(UserSettings.CUSTOMTHEME,setting.getCustomTheme());
                editor.apply();
                editor.commit();



                if (setting.getCustomTheme().equals(UserSettings.DARKTHEME)){
                    v.setBackgroundColor(black);
                    texe.setText("Dark");
                    texe.setTextColor(white);
                    stch.setChecked(true);
                }
                else {
                    v.setBackgroundColor(white);
                    texe.setText("white");
                    texe.setTextColor(black);
                    stch.setChecked(false);

                }

            }
        });






    }


}