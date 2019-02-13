package com.example.shoplist.shoplist.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.shoplist.shoplist.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FriendsActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;


    protected Button addButton;
    protected Button deleteButton;
    protected EditText friendName;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        deleteButton = (Button) findViewById(R.id.DeleteButton);
        addButton = (Button) findViewById(R.id.AddButton);
        friendName = (EditText) findViewById(R.id.FriendName);

        final ListView listView = (ListView) findViewById(R.id.friendslist);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);

        mDatabase.child("users").child(mUserId).child("friends").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add((String) dataSnapshot.getValue());

                //                mDatabase.child("users").child(mUserId).child("friendsmail").push().setValue(name);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //adapter.remove((String) dataSnapshot.child("friendsmail").getValue()+" "+dataSnapshot.child("amount").getValue());
                //adapter.remove((String) dataSnapshot.child("title").getValue());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friendsmail = friendName.getText().toString();
                mDatabase.child("users").child(mUserId).child("friends").push().setValue(friendsmail);
                friendName.setText("");
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friendsmail = friendName.getText().toString();

                friendName.setText("");
            }
        });

    }

}
