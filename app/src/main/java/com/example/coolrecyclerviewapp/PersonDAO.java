package com.example.coolrecyclerviewapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert
    void create(Person person);

    @Query("SELECT * from person")
    List<Person> readAll();

    @Update
    void update(Person person);

    @Query("DELETE FROM person")
    void clear();


}
