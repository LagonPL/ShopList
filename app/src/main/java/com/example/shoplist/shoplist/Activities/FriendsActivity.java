package com.example.shoplist.shoplist.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoplist.shoplist.R;
import com.example.shoplist.shoplist.Util;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FriendsActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseUsers;
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
        mDatabaseUsers = mDatabase.child("users");

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
                adapter.remove((String) dataSnapshot.getValue());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FriendsActivity.this, friendlistActivity.class);
                Bundle b = new Bundle();
                b.putString("usernameclicked", (String) listView.getItemAtPosition(position)); //Your id
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friendsmail = friendName.getText().toString();
                if (!friendsmail.isEmpty()) {
                 mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener(){
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         Log.d("testujemy2", dataSnapshot.getValue().toString());
                     }
                     @Override
                     public void onCancelled(DatabaseError dataSnapshot) {
                         Util.ToastMaker(getApplicationContext(), getString(R.string.adderror));
                     }
                 }
                 );
                    mDatabase.child("users").child(mUserId).child("friends").push().setValue(friendsmail);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.uncorrecttext), Toast.LENGTH_SHORT).show();
                }
                friendName.setText("");
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String friendsmail = friendName.getText().toString();
                if (!friendsmail.isEmpty()) {
                    mDatabase.child("users")
                            .child(mUserId)
                            .child("friends")
                            .addListenerForSingleValueEvent(new ValueEventListener(){
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                                        //postSnapshot.getValue();
                                        Log.d("testujemy1", postSnapshot.getValue().toString());
                                        if(postSnapshot.getValue().toString().equals(friendsmail)){
                                            postSnapshot.getRef().setValue(null);
                                        }

                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError dataSnapshot) {
                                    Util.ToastMaker(getApplicationContext(), getString(R.string.deleteerror));

                                }
                            });
                }
                else {
                    Util.ToastMaker(getApplicationContext(), getString(R.string.uncorrecttext));
                }
                friendName.setText("");
            }
        });

    }

}
