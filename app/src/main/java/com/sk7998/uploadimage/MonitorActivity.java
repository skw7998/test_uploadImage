// MonitorActivity.java
package com.sk7998.uploadimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MonitorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        ImageButton showGalleryButton = findViewById(R.id.showGalleryButton);
        ImageButton showSecuritySettingButton = findViewById(R.id.showSecuritySettingButton);
        showGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当按钮被点击时，打开 GalleryActivity
                startActivity(new Intent(MonitorActivity.this, GalleryActivity.class));
            }
        });
        showSecuritySettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MonitorActivity.this, SecuritySettingActivity.class));
            }
        });
    }
}

