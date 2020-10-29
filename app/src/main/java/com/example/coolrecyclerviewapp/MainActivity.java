package com.example.coolrecyclerviewapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;
    private MainAdapter adapter;
    private List<Person> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        initItemList();
        initRecyclerView();
        initAdapter();
        initToolBar();
        initFloatingActionButton();
    }

    private void initItemList() {
        list = new ArrayList<>();
        Person defaultPerson = new Person();
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.hm)
                + '/' + getResources().getResourceTypeName(R.drawable.hm) + '/' + getResources().getResourceEntryName(R.drawable.hm) );
        defaultPerson.setPhoto(imageUri);
        defaultPerson.setName("Svyatoslav");
        defaultPerson.setEmail("email@mail.com");
        defaultPerson.setPhone("+79876543210");
        list.add(defaultPerson);
    }

    private void initAdapter() {
        adapter = new MainAdapter(this, list);
        mainRecyclerView.setAdapter(adapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initRecyclerView() {
        mainRecyclerView = findViewById(R.id.main_recycler_view);
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreatePersonActivity.class);
                startActivityForResult(intent, CreatePersonActivity.ADD_PERSON);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == CreatePersonActivity.ADD_PERSON){
                Person person = new Person();
                person.setPhoto(Uri.parse(data.getStringExtra(CreatePersonActivity.PERSON_PHOTO)));
                person.setName(data.getStringExtra(CreatePersonActivity.PERSON_NAME));
                person.setEmail(data.getStringExtra(CreatePersonActivity.PERSON_EMAIL));
                person.setPhone(data.getStringExtra(CreatePersonActivity.PERSON_PHONE));
                list.add(person);
                adapter.notifyDataSetChanged();
            }
            if (requestCode == ChangePersonActivity.EDIT_PERSON){
                int position = data.getIntExtra(ChangePersonActivity.POSITION, -1);
                if (position == -1) return;
                Person person = list.get(position);
                person.setPhoto(Uri.parse(data.getStringExtra(CreatePersonActivity.PERSON_PHOTO)));
                person.setName(data.getStringExtra(CreatePersonActivity.PERSON_NAME));
                person.setEmail(data.getStringExtra(CreatePersonActivity.PERSON_EMAIL));
                person.setPhone(data.getStringExtra(CreatePersonActivity.PERSON_PHONE));
                list.set(position, person);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
