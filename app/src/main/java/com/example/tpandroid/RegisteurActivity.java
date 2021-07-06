package com.example.tpandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisteurActivity extends AppCompatActivity {
    private TextInputEditText Email,Password,Name,LastName,Phone;
     private TextView goback;
     private Button RegisteurButton;
     private ProgressBar progC;
     FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // progC=findViewById(R.id.ProgCreate);
        setContentView(R.layout.activity_registeur);
        Email=findViewById(R.id.Emailuserin);
        Name=findViewById(R.id.firstnameuser);
        LastName=findViewById(R.id.lastNameuser);
        Phone=findViewById(R.id.Phoneuser);
        Password=findViewById(R.id.Password);
        RegisteurButton=findViewById(R.id.RegisteurButton);
        mAuth=FirebaseAuth.getInstance();
        RegisteurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=Name.getText().toString().trim();
                String email=Email.getText().toString().trim();
                String lastName=LastName.getText().toString().trim();
                String passwd=Password.getText().toString().trim();
                String phone=Phone.getText().toString().trim();
                if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(lastName)&&TextUtils.isEmpty(phone)&&TextUtils.isEmpty(email)&&TextUtils.isEmpty(passwd) ){
                    Toast.makeText(getApplicationContext(),"Please enter information",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(name)|| name.length()<4){
                    Name.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(lastName) ||lastName.length()<4){
                    LastName.setError("Last Name is required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    Phone.setError("Phone is required");
                    return;
                }
                if(TextUtils.isEmpty(passwd)){
                    Password.setError("Password is required");
                    return;
                }
                if(passwd.length() <6 ){
                    Password.setError("Password has to be more then 6 Carateur");
                    return;
                }
              //  progC.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisteurActivity.this,"User Created",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisteurActivity.this, MainActivity.class));
                        }
                        else{
                            Toast.makeText(RegisteurActivity.this,"Errer " +passwd+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                //startActivity(new Intent(RegisteurActivity.this,MainActivity2.class));
               /// Toast.makeText(RegisteurActivity.this,"hello "+name,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goBack(View view) {startActivity(new Intent(RegisteurActivity.this,MainActivity2.class));
    }
}