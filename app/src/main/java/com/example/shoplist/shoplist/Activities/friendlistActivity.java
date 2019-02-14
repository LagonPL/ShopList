package com.example.shoplist.shoplist.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.shoplist.shoplist.R;
import com.example.shoplist.shoplist.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class friendlistActivity extends AppCompatActivity {

    protected TextView NicknameTextView;
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        NicknameTextView = (TextView) findViewById(R.id.friendName);
        Bundle b = getIntent().getExtras();
        String nickname = b.getString("usernameclicked");
        NicknameTextView.setText(nickname);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("testujemy3", dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError dataSnapshot) {
                Util.ToastMaker(getApplicationContext(), getString(R.string.adderror));
            }
        }
        );
    }
}
