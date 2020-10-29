package com.example.coolrecyclerviewapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreatePersonActivity extends AppCompatActivity {

    public static final int ADD_PERSON = 1;
    public static final int SELECT = 3;

    public static final String PERSON_PHOTO = "person photo";
    public static final String PERSON_NAME = "person name";
    public static final String PERSON_EMAIL = "person email";
    public static final String PERSON_PHONE = "person phone";
    public static final String POSITION = "POSITION";

    private int position;
    private Intent intentResult;
    private CircleImageView personImage;
    private Uri photo;
    private EditText editTextPersonName, editTextPersonEmail, editTextPersonPhone;
    private Button createPersonButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_person);
        initComponents();
    }

    private void initComponents() {
        initImageView();
        initEditText();
        initButton();
        initIntent();
    }

    private void initIntent() {
        intentResult = new Intent();
        setResult(RESULT_CANCELED, intentResult);
        Intent mainIntent = getIntent();

        if (mainIntent.getExtras() == null){
            position = -1;
        } else {

            position = mainIntent.getIntExtra(POSITION, -1);
            editTextPersonName.setText(mainIntent.getStringExtra(PERSON_NAME));
            editTextPersonEmail.setText(mainIntent.getStringExtra(PERSON_EMAIL));
            editTextPersonPhone.setText(mainIntent.getStringExtra(PERSON_PHONE));

            if (mainIntent.getStringExtra(PERSON_PHOTO) != null) {
                Picasso.get().load(Uri.parse(mainIntent.getStringExtra(PERSON_PHOTO))).into(personImage);
            }
        }
    }

    private void initImageView() {
        personImage = findViewById(R.id.image_view_choose_image);
        personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            photo = data.getData();
            Picasso.get().load(photo).into(personImage);
        }
    }

    private void initEditText() {
        editTextPersonName = findViewById(R.id.edit_text_create_person_name);
        editTextPersonEmail = findViewById(R.id.edit_text_create_person_email);
        editTextPersonPhone = findViewById(R.id.edit_text_create_person_phone);
    }

    private void initButton() {
        createPersonButton = findViewById(R.id.button_create_new_person);
         createPersonButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 intentResult.putExtra(POSITION, position);
                 intentResult.putExtra(PERSON_PHOTO, photo.toString());
                 intentResult.putExtra(PERSON_NAME, editTextPersonName.getText().toString());
                 intentResult.putExtra(PERSON_EMAIL, editTextPersonEmail.getText().toString());
                 intentResult.putExtra(PERSON_PHONE, editTextPersonPhone.getText().toString());
                 setResult(RESULT_OK, intentResult);
                 finish();
             }
         });
    }
}
