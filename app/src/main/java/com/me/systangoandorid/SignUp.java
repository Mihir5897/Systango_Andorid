package com.me.systangoandorid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
 EditText email,password,name,phone,age;
 Button register,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email=findViewById(R.id.etemail);
        password=findViewById(R.id.etpassword);
        name=findViewById(R.id.etname);
        phone=findViewById(R.id.etphone);
        age=findViewById(R.id.etage);
        register=findViewById(R.id.btnregster);
        login=findViewById(R.id.btnlogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create user entity
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(email.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setName(name.getText().toString());
                userEntity.setPhone(phone.getText().toString());
                userEntity.setAge(age.getText().toString());
                if (validateInput(userEntity)){
                    //Do insert operation
                    UserDataBase userDataBase = UserDataBase.getUserDataBase(getApplicationContext());
                    final UserDao userDao = userDataBase.userDao();
                     new Thread(new Runnable() {
                         @Override
                         public void run() {
                             //Register User
                             userDao.registerUser(userEntity);
                             runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {
                                     Toast.makeText(getApplicationContext(),"User Registerd!",Toast.LENGTH_SHORT).show();
                                     startActivity(new Intent(SignUp.this,Login.class));
                                 }
                             });

                         }
                     }).start();

                }else {
                    Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SignUp.this,Login.class);
                    startActivity(i);
                }
            });



    }
    private boolean validateInput(UserEntity userEntity){
        if (userEntity.getName().isEmpty()
                || userEntity.getEmail().isEmpty()
                || (!Patterns.EMAIL_ADDRESS.matcher(userEntity.getEmail()).matches())
                || userEntity.getPassword().isEmpty()
                || userEntity.getPassword().length() < 6
                || userEntity.getAge().isEmpty()
                || userEntity.getPhone().isEmpty()){
            return false;
        }
            return true;
    }
}