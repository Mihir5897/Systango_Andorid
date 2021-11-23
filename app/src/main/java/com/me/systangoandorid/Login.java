package com.me.systangoandorid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    //Initialize variable
    EditText email,password;
    Button login;
    CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //assign variable
        email=findViewById(R.id.etemail);
        password=findViewById(R.id.etpassword);
        //CheckBox
        rememberMe=findViewById(R.id.remember);
        sharedPreferences=getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        editor=sharedPreferences.edit();
        /////////////////To get Stored Data/////////////////////////////////
        String mail=sharedPreferences.getString("email","No Email Stored");
        String passwords=sharedPreferences.getString("passowrd","");
        ////////////////////////////////////////////////////////////////////
        email.setText(mail);
        password.setText(passwords);
        //\\
        login=findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///////////////To Store Data////////////////////////////
                if(rememberMe.isChecked()){
                    editor.putString("email",email.getText().toString());
                    editor.putString("passowrd",password.getText().toString());
                    editor.commit();
                }else{
                    editor.putString("email","");
                    editor.putString("passowrd","");
                    editor.commit();
                }
                //////////////////////////////////////////////////////////////
            String emailtext = email.getText().toString();
            String pawwordtext = password.getText().toString();
            if (SetValidation()){

                UserDataBase userDataBase = UserDataBase.getUserDataBase(getApplicationContext());
                UserDao userDao= userDataBase.userDao();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserEntity userEntity = userDao.login(emailtext,pawwordtext);
                        if (userEntity==null){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Invalid credentials ",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            String nametxt = userEntity.name;
                            String emailtxt = userEntity.email;

                            startActivity(new Intent(Login.this,MainActivity.class)
                                    .putExtra("name",nametxt)
                                    .putExtra("EMAIL",emailtxt));
                        }
                    }
                }).start();
            }

            }
        });
    }
    public boolean SetValidation() {
        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            email.setError(getResources().getString(R.string.email_error));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError(getResources().getString(R.string.error_invalid_email));
            return false;
        }
        // Check for a valid password.
        else if (password.getText().toString().isEmpty()) {
            password.setError(getResources().getString(R.string.password_error));
            return false;
        } else if (password.getText().length() < 6) {
            password.setError(getResources().getString(R.string.error_invalid_password));
            return false;
        }
            return true;

    }
}
