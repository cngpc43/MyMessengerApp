package com.example.mymessengerapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.mymessengerapp.adapter.UserAdapter;
import com.example.mymessengerapp.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificationFragment extends Fragment {
    FirebaseAuth auth;
    UserAdapter adapter;
    RelativeLayout matchingRequests, requestsSent;
    TextView badge, sent_badge, title;
    FirebaseDatabase database;
    ArrayList<Users> usersArrayList;
    Context context;
    String currentUserId;
    int matching_request_count = 0, request_sent_count = 0;

    public NotificationFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        badge = view.findViewById(R.id.badge);
        sent_badge = view.findViewById(R.id.sent_badge);
        matchingRequests = view.findViewById(R.id.matching_requests);
        requestsSent = view.findViewById(R.id.requests_sent);
        title = getActivity().findViewById(R.id.title);

        title.setText("Notifications");

        currentUserId = auth.getCurrentUser().getUid();
        DatabaseReference matchRequestsRef = database.getReference("MatchRequests").child(currentUserId);


        database.getReference("MatchRequests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                matching_request_count = 0;
                request_sent_count = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.child("recipientId").getValue(String.class) != null
                            && dataSnapshot.child("requesterId").getValue(String.class) != null
                            && dataSnapshot.child("status").getValue(String.class) != null) {
                        if (dataSnapshot.child("recipientId").getValue(String.class).equals(currentUserId) && dataSnapshot.child("status").getValue(String.class).equals("pending")) {
                            matching_request_count++;
                        }
                        if (dataSnapshot.child("requesterId").getValue(String.class).equals(currentUserId) && dataSnapshot.child("status").getValue(String.class).equals("pending")) {
                            request_sent_count++;
                        }
                    }
                }
                badge.setText("(" + matching_request_count + ")");
                sent_badge.setText("(" + request_sent_count + ")");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        matchingRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MatchingRequestsFragment()).commit();
            }
        });
        requestsSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new RequestsSentFragment()).commit();
            }
        });
        return view;
    }

}