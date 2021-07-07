package com.example.tpandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
private ImageView mymage;
private TextView mytxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mymage=findViewById(R.id.imageView);
        mytxt=findViewById(R.id.textView);
        mymage.setImageResource(R.drawable.jamel);
        //String email = getIntent().getStringExtra("EXTRA_SESSION_ID");
        String name = getIntent().getStringExtra("username");

        mytxt.setText(name);
    }
}