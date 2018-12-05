package com.example.rosa.meetloaf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
    }

    public void launchCreateMeeting(View view) {
        Intent i = new Intent(this, CreateFragment.class);
        startActivity(i);
    }
}
