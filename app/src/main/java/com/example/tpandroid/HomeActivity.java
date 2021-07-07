package com.example.tpandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
private ImageView mymage;
private TextView txtemail,txtname,txtlastname,txtphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mymage=findViewById(R.id.imageView);
        txtemail=findViewById(R.id.getemail);
        txtlastname=findViewById(R.id.getlastname);
        txtname=findViewById(R.id.getname);
        txtphone=findViewById(R.id.getphone);
       // mytxt=findViewById(R.id.textView);
        mymage.setImageResource(R.drawable.jamel);
        //String email = getIntent().getStringExtra("EXTRA_SESSION_ID");
       // String name = getIntent().getStringExtra("username");

       // mytxt.setText(name);
    }

    public void chowProfil(View view) {
        startActivity( new Intent(HomeActivity.this,Profile.class));
    }
}