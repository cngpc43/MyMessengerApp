package com.example.mymessengerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class setting  extends AppCompatActivity {
    LinearLayout logout;
    FirebaseAuth auth;
    TextView username;
    TextView status;
    FirebaseDatabase database;
    FirebaseStorage storage;
    ImageView home;
    FrameLayout user, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        username = findViewById(R.id.profile_username);
        status = findViewById(R.id.profile_status);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();

        }
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("user").child(auth.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                password = snapshot.child("password").getValue().toString();
//                String name = snapshot.child("userName").getValue().toString();
//                String profile = snapshot.child("profilepic").getValue().toString();
//                String status = snapshot.child("status").getValue().toString();
//                setname.setText(name);
//                setstatus.setText(status);
//                Picasso.get().load(profile).into(setprofile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(setting.this,R.style.dialogue);
                dialog.setContentView(R.layout.dialogue_layout);
                Button no,yes;
                yes = dialog.findViewById(R.id.yesbnt);
                no = dialog.findViewById(R.id.nobnt);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(setting.this,login.class);
                        startActivity(intent);
                        finish();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting.this,MainActivity.class);
                startActivity(intent);
            }
        });
        message = findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting.this,chat_home_page.class);
                startActivity(intent);
            }
        });
        user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting.this,setting.class);
                startActivity(intent);
            }
        });
    }

}
