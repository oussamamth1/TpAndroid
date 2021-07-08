package com.example.tpandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        //mytxt=findViewById(R.id.getemail);
        mymage.setImageResource(R.drawable.jamel);
        String email = getIntent().getStringExtra("email");
       // String name = getIntent().getStringExtra("username");

       txtemail.setText(email);
    }

    public void chowProfil(View view) {
        String email=txtemail.getText().toString().trim();
Intent intent=new Intent(HomeActivity.this,Profile.class);
intent.putExtra("testemail",email);
        Toast.makeText(HomeActivity.this,"email "+ email,Toast.LENGTH_SHORT).show();
        startActivity(intent);
       // startActivity( new Intent(HomeActivity.this,Profile.class));
    }
}