// ElderlyActivity.java
package com.sk7998.uploadimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ElderlyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elderly);

        //上传照片 UploadImgActivity
        ImageButton openUploadImgButton = findViewById(R.id.btnOpenUploadImg);
        openUploadImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElderlyActivity.this, UploadImgActivity.class);
                startActivity(intent);
            }
        });
        //TODO 呼叫
        // 上传人体体征
        // 后台上传地理位置
    }
}
