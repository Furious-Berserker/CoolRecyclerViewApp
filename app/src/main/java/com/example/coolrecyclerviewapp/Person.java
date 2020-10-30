package com.example.coolrecyclerviewapp;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {
    @Ignore
    private Uri photo;
    @NonNull
    @PrimaryKey
    private String name;

    private String email;
    private String phone;

    public Uri getPhoto() {
        return photo;
    }
    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
