package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView dTitle, dAuth, dDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView = findViewById(R.id.dPoster);
        dTitle = findViewById(R.id.dTitle);
        dAuth = findViewById(R.id.dAuthor);
        dDesc = findViewById(R.id.dsc);

        if (getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("desc") && getIntent().hasExtra("poster")){
            String title = getIntent().getStringExtra("title");
            String author = getIntent().getStringExtra("author");
            String desc = getIntent().getStringExtra("desc");
            String poster = getIntent().getStringExtra("poster");

            dTitle.setText(title);
            dAuth.setText(author);
            dDesc.setText(desc);
            Glide.with(this).load(poster).into(imageView);
        }
    }
}