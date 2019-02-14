package com.example.shoplist.shoplist.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shoplist.shoplist.R;

public class friendlistActivity extends AppCompatActivity {

    protected TextView NicknameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        NicknameTextView = (TextView) findViewById(R.id.friendName);
        Bundle b = getIntent().getExtras();
        String nickname = b.getString("usernameclicked");
        NicknameTextView.setText(nickname);


    }
}
