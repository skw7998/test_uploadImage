//RegisterActivity
package com.sk7998.uploadimage;

import android.app.Activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sk7998.uploadimage.dao.UserDao;
import com.sk7998.uploadimage.entity.UserInfo;


public class RegisterActivity extends AppCompatActivity {
    EditText name = null;
    EditText pwd = null;
    EditText phone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        pwd = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
    }
    public void register(View view){
        String cname = name.getText().toString();
        String cpassword = pwd.getText().toString();
        String cphone = phone.getText().toString();
        if(cname.length() < 2 || cpassword.length() < 6 ){
            Toast.makeText(getApplicationContext(),"输入信息不符合要求请重新输入",Toast.LENGTH_LONG).show();
            return;
        }
        UserInfo user = new UserInfo();

        user.setName(cname);
        user.setPwd(cpassword);
        user.setPhone(cphone);

        new Thread(){
            @Override
            public void run() {

                int msg = 0;

                UserDao userDao = new UserDao();

                UserInfo uu = userDao.findUser(user.getName());
                if(uu != null){
                    msg = 1;
                } else {
                    boolean flag = userDao.register(user);
                    if(flag){
                        msg = 2;
                    }
                }
                hand.sendEmptyMessage(msg);
            }
        }.start();
    }
    final Handler hand = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0)
            {
                Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
            }
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"用户名重复",Toast.LENGTH_LONG).show();
            }
            if(msg.what == 2)
            {
                startActivity(new Intent(getApplication(),LoginActivity.class));

                Intent intent = new Intent();
                //将想要传递的数据用putExtra封装在intent中
                intent.putExtra("a","注册");
                setResult(RESULT_CANCELED,intent);
                finish();
            }

        }
    };
}
