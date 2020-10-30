package com.example.coolrecyclerviewapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Person.class, version = 1)
public abstract class DB extends RoomDatabase {
    public abstract PersonDAO personDAO();
}
