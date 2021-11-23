package com.me.systangoandorid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {
    //Initialize variable
    private TextView name, email, password,phone,age;
    UserEntity userEntity;
    UserDao userDao;
    UserDataBase userDataBase;
    String txtemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //assign variable
        name=findViewById(R.id.txname);
        phone=findViewById(R.id.txnumber);
        email=findViewById(R.id.txemail);
        password=findViewById(R.id.txpassword);
        age=findViewById(R.id.txage);
        //

        txtemail = getIntent().getStringExtra("EMAIL");

        //
         userDataBase = UserDataBase.getUserDataBase(getApplicationContext());
         userDao= userDataBase.userDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                 userEntity = userDao.findByEmail(txtemail);
                if (userEntity==null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Null Value",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    name.setText(userEntity.getName());
                    phone.setText(userEntity.getPhone());
                    email.setText(userEntity.getEmail());
                    password.setText(userEntity.getPassword());
                    age.setText(userEntity.getAge());
                }
            }
        }).start();

        //
//        if (userEntity != null) {
//            name.setText(userEntity.getName());
//            phone.setText(userEntity.getPhone());
//            email.setText(userEntity.getEmail());
//            password.setText(userEntity.getPassword());
//            age.setText(userEntity.getAge());
//        }else {
//            name.setText("null");
//        }
    }
}