// HomeActivity.java
package com.sk7998.uploadimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FrameLayout elderlyButton = findViewById(R.id.oldPersonButton);

        FrameLayout monitorButton = findViewById(R.id.monitorButton);

        elderlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动老年端功能界面
                Intent intent = new Intent(HomeActivity.this, ElderlyActivity.class);
                startActivity(intent);
            }
        });

        monitorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动监控端功能界面
                Intent intent = new Intent(HomeActivity.this, MonitorActivity.class);
                startActivity(intent);
            }
        });
    }
}
