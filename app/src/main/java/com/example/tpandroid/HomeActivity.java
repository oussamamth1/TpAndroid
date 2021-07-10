package com.example.tpandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity {
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference("users");

   private FirebaseStorage storage;
    FirebaseAuth mAuth;
   private StorageReference storageReference;
private ImageView mymage;
private String image;
public Uri imageUri;
private TextView txtemail,txtname,txtlastname,txtphone;
    String email;
    //private Object UploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        mymage=findViewById(R.id.imageView);
       txtemail=findViewById(R.id.getemail);
        txtlastname=findViewById(R.id.getlastname);
        txtname=findViewById(R.id.getname);
        txtphone=findViewById(R.id.getphone);
        //mytxt=findViewById(R.id.getemail);
        mymage.setImageResource(R.drawable.unknow);
        email = getIntent().getStringExtra("email");
        mymage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
       // String name = getIntent().getStringExtra("username");
        txtemail.setText(email);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                if(dataSnapshot.child("email").getValue().equals(email)){
                    txtphone.setText(dataSnapshot.child("phone").getValue(String.class));

                }

            }}

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

    }
    public void getUserinfo(){
        root.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.getChildrenCount()>0){
                    if(snapshot.hasChild("image")){
                        String image=snapshot.child("image").getValue().toString();


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void choosePicture() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK &&data!=null && data.getData()!=null){
            imageUri=data.getData();
            mymage.setImageURI(imageUri);
uplodePic();

        }
    }

   private void uplodePic() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploding Imge.....");
        pd.show();

        final String randomkey= UUID.randomUUID().toString();
       //Uri file=Uri.fromFile(new File("path/to/mountains.jpg"));
        StorageReference mountainsRef = storageReference.child("image/" +randomkey);
        mountainsRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Snackbar.make(findViewById(android.R.id.content),"Image uplode",Snackbar.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"image null",Toast.LENGTH_LONG).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull  UploadTask.TaskSnapshot snapshot) {
                double per=(100.00* snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                pd.setMessage("Progress : "+(int)per +"%");

            }
        });
    }

    private void updateProfile(){
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploding Imge.....");
        pd.show();
        pd.setMessage("plase wait ...");
        if(imageUri !=null){
          final   StorageReference fileRef= storageReference.child(mAuth.getCurrentUser().getUid() + ".jpg");
         // UploadTask=fileRef.putFile(imageUri);
        }

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