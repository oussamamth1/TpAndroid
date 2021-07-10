package com.example.tpandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity2 extends AppCompatActivity {
//private TextInputLayout textInputLayout;
private TextInputEditText Email,Password;
private TextView singin;
    FirebaseAuth mAuth;
   private ProgressBar LoginProg;
Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        singin=findViewById(R.id.signIn);
        mAuth = FirebaseAuth.getInstance();


        Password=findViewById(R.id.Password);
        Email=findViewById(R.id.EmailEdit);
        buttonLogin=findViewById(R.id.LOginbutton);
LoginProg=findViewById(R.id.progressBar);
     // String usermail = getIntent().getStringExtra("email");
        /*String username = getIntent().getStringExtra("name");
        String userlastname = getIntent().getStringExtra("lastname");
        String userphone = getIntent().getStringExtra("phone");*/

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=Email.getText().toString().trim();
                String passwd=Password.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(passwd)){
                    Password.setError("Password is required");
                    return;
                }
                if(passwd.length() <6){
                    Password.setError("Password has to be more then 6 Carateur");
                    return;
                }
               // DatabaseReference reference= FirebaseDatabase.getInstance().getReference("user");
               // Query chek=reference.orderByChild("email").equalTo(email);

                //
                 LoginProg.setVisibility(View.VISIBLE);
               mAuth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(MainActivity2.this,HomeActivity.class);
                            intent.putExtra("email", email);
                          //  intent.putExtra("userlaastname", userlastname);
                           // intent.putExtra("username", username);
                           // intent.putExtra("userphone", userphone);
                            startActivity(intent);
                           // startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                            Toast.makeText(MainActivity2.this,"wellcom" + email,Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(MainActivity2.this,"Errer try to sign in " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

    });
   /*     singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,RegisteurActivity.class));
            }
        });*/
}


    public void Listen(View view) {
        startActivity(new Intent(MainActivity2.this,RegisteurActivity.class));
    }
}