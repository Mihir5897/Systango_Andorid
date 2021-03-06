package com.me.systangoandorid;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

@Insert
    void registerUser(UserEntity userEntity);
@Query("select * from users where email=(:email) and password=(:password) ")
    UserEntity login(String email,String password);
@Query("SELECT * FROM Users WHERE email = :email")
    UserEntity findByEmail(String email);
}
