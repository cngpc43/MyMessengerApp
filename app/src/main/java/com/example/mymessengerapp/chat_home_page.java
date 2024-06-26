package com.example.mymessengerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.search.SearchView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Arrays;

public class chat_home_page extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseStorage storage;
    ChatAdapter chatAdapter;
    SearchView searchView;
    ListView lv_list_chat;
    FrameLayout user, message;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_home_page);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        searchView = findViewById(R.id.sv_search);
        lv_list_chat = findViewById(R.id.lv_list_chat);

        chatAdapter = new ChatAdapter(this, new ArrayList<>(Arrays.asList("Chat 1", "Chat 2", "Chat 3")));
        lv_list_chat.setAdapter(chatAdapter);

        // Tìm kiếm tên chat, ở trên là data mẫu thôi, tự xem rồi fix lai
//        TextInputEditText searchEditText = (TextInputEditText) searchView.getEditText();
        EditText searchEditText = searchView.getEditText();
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // khong lam gi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                chatAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // khong lam gi
            }
        });
        ImageView home = findViewById(R.id.home);
        message = findViewById(R.id.message);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(chat_home_page.this, MainActivity.class);
            startActivity(intent);
        });
        message.setOnClickListener(v -> {
            Intent intent = new Intent(chat_home_page.this, chat_home_page.class);
            startActivity(intent);
        });
        user = findViewById(R.id.user);
        user.setOnClickListener(v -> {
            Intent intent = new Intent(chat_home_page.this, setting.class);
            startActivity(intent);
        });
    }
}