package com.example.realtimechatapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //variables
    private FirebaseDatabase firebaseDB;
    private DatabaseReference ref;
    TextView textview;
    List<Chat> messages = new ArrayList<Chat>();
    EditText mEditText;
    Button sendBtn;


    ChatAdapter adapter;
    RecyclerView recyclerView;
       //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        FirebaseApp.initializeApp(this);
        firebaseDB= FirebaseDatabase.getInstance();
        ref = firebaseDB.getReference();
        recyclerView=findViewById(R.id.recycler_view);
        adapter = new ChatAdapter(this,messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        sendBtn=findViewById(R.id.btn);
        mEditText=findViewById(R.id.et);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("btn","im here");
                Chat currentMessage= new Chat();
                currentMessage.setName("Seif");
                currentMessage.setMessage(mEditText.getText().toString());
               ref.child("Chat").push().setValue(currentMessage);
                mEditText.getText().clear();
            }
        });



        ref.child("Chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        messages.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    messages.add(chat);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
