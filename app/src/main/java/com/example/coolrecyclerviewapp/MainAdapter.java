package com.example.coolrecyclerviewapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    public Context mainContext;
    public List<Person> personList;

    public MainAdapter(Context context, List<Person> list) {
        this.mainContext = context;
        this.personList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Person person = personList.get(position);
        Picasso.get().load(person.getPhoto()).into(holder.personImage);
        holder.textViewName.setText(person.getName());
        holder.textViewEmail.setText(person.getEmail());
        holder.textViewPhone.setText(person.getPhone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainContext, ChangePersonActivity.class);
                intent.putExtra(ChangePersonActivity.POSITION, position);
                intent.putExtra(ChangePersonActivity.PERSON_NAME, person.getName());
                intent.putExtra(ChangePersonActivity.PERSON_EMAIL, person.getEmail());
                intent.putExtra(ChangePersonActivity.PERSON_PHONE, person.getPhone());
                if (person.getPhoto() != null){
                    intent.putExtra(ChangePersonActivity.PERSON_PHOTO, person.getPhoto().toString());
                }
                ((MainActivity)mainContext).startActivityForResult(intent, ChangePersonActivity.EDIT_PERSON);
            }
        });
        if (person.getPhoto() != null){
            Picasso.get().load(person.getPhoto()).into(holder.personImage);
        }
    }


    @Override
    public int getItemCount(){
        return personList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView personImage;
        TextView textViewName, textViewEmail, textViewPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personImage = itemView.findViewById(R.id.person_photo);
            textViewName = itemView.findViewById(R.id.person_name);
            textViewEmail = itemView.findViewById(R.id.person_email);
            textViewPhone = itemView.findViewById(R.id.person_phone);
        }
    }
 }
