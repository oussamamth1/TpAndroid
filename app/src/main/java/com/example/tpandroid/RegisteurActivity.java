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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisteurActivity extends AppCompatActivity {
    private TextInputEditText Email,Password,Name,LastName,Phone;
     private TextView goback;
     private Button RegisteurButton;
     private ProgressBar progC;
     FirebaseAuth mAuth;
DatabaseReference ref;
user userdetail;
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
        ref= FirebaseDatabase.getInstance().getReference().child("users");
        RegisteurButton=findViewById(R.id.RegisteurButton);
        userdetail=new user();
        mAuth=FirebaseAuth.getInstance();
        RegisteurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ValidName() | !ValidLastName() | !ValidEmail() | !ValidPhone() | !ValidPassword()){
                    return;
                }
/*userdetail.setName(Name.getText().toString().trim());
userdetail.setLastName(LastName.getText().toString().trim());
userdetail.setEmail(Email.getText().toString().trim());
userdetail.setPhone(Phone.getText().toString().trim());*/


                String name=Name.getText().toString().trim();
                String email=Email.getText().toString().trim();
                String lastName=LastName.getText().toString().trim();
                String passwd=Password.getText().toString().trim();
                String phone=Phone.getText().toString().trim();
                HashMap<String ,String> usermap=new HashMap<>();
                usermap.put("name",name);
                usermap.put("lastname",lastName);
                usermap.put("email",email);
                usermap.put("phone",phone);
ref.push().setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull  Task<Void> task) {
Toast.makeText(RegisteurActivity.this,"data saved ",Toast.LENGTH_SHORT).show();
    }
});
               // user User=new user(name,lastName,email,phone);
               // ref.child("user").setValue(User);
              //  progC.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisteurActivity.this,"User Created",Toast.LENGTH_LONG).show();

                            //startActivity(new Intent(RegisteurActivity.this, MainActivity.class));

                            ref.push().setValue(usermap);
                            Intent intent=new Intent(RegisteurActivity.this,MainActivity2.class);
                            startActivity(intent);

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
public Boolean ValidName(){
        String noWaitSpace="\\A\\w{4,20}\\z";

    String val=Name.getText().toString().trim();
    if(val.isEmpty()){
        Name.setError("Name is required");
        return false; }
    else if(val.length()<4){
        Name.setError("Name have to be more then 4 caracteur");
        return false;
    }else if(!val.matches(noWaitSpace)){
        Name.setError("White space not allowed");
        return false;
    }
    else {
Name.setError(null);
return true;
    }

}
    public Boolean ValidLastName(){
        String noWaitSpace="\\A\\w{4,20}\\z";
        String val=LastName.getText().toString().trim();
        if(val.isEmpty()){
            LastName.setError("Name is required");
            return false;

        }else if(!val.matches(noWaitSpace)){
            LastName.setError("White space not allowed");
            return false;
        }
        else

        {
            LastName.setError(null);
            return true;
        }

    }
    public Boolean ValidPhone(){
        String val=Phone.getText().toString().trim();
        if(val.isEmpty()){
            Phone.setError("Name is required");
            return false;

        }else

        {
            Phone.setError(null);
            return true;
        }

    }
    public Boolean ValidEmail(){
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String val=Email.getText().toString().trim();
        if(val.isEmpty()){
            Email.setError("Name is required");
            return false;

        }else if(!val.matches(EMAIL_PATTERN)){
            Email.setError("White space not allowed");
            return false;
        }
        else

        {
            Email.setError(null);
            return true;
        }

    }
    public Boolean ValidPassword(){
        String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[@_.]).*$";
        ;
        String val=Password.getText().toString().trim();
        if(val.isEmpty()){
            Password.setError("Name is required");
            return false;

        }else if(!val.matches(PASSWORD_PATTERN)){
            Password.setError("your Password week try to add symbols (=!*..)and A..Z");
            return false;
        }
        else

        {
            Password.setError(null);
            return true;
        }

    }
    public void goBack(View view) {startActivity(new Intent(RegisteurActivity.this,MainActivity2.class));
    }
}