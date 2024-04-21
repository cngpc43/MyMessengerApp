package com.example.mymessengerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymessengerapp.model.ChatRoom;
import com.example.mymessengerapp.model.Users;
import com.google.android.material.imageview.ShapeableImageView;

public class ChatActivity extends AppCompatActivity {
    EditText messageInput;
    ImageButton sendMessBtn, backBtn;
    TextView userName, userStatus;
    ShapeableImageView userAvatar;
    RecyclerView mainChat;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        // lấy ID của users và chatroom


        // Get UI from xml
        userName = findViewById(R.id.user_name_chat);
        userStatus = findViewById(R.id.user_status_chat);
        userAvatar = findViewById(R.id.user_icon_chat);
        messageInput = findViewById(R.id.chat_input);
        sendMessBtn = findViewById(R.id.chat_send_button);
        backBtn = findViewById(R.id.chat_back_button);
        mainChat = findViewById(R.id.chat_main);


        // Set content for UI
//        userName.setText();
//        userStatus.setText();
//        userAvatar.set

        // Xu ly khi nhan back ve
        backBtn.setOnClickListener(v -> onBackPressed());
        sendMessBtn.setOnClickListener(v -> {
            String message = messageInput.toString().trim();

            // Khong thuc hienn khi khong co tin nhan
            if (message.isEmpty())
                return;

            handleSendMessage(message);
        });
    }

    // Xu ly chuc nang gui tin nhan
    void handleSendMessage(String message) {

    }
}