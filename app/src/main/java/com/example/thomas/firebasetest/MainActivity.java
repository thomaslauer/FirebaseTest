package com.example.thomas.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView text;

    DatabaseReference ref;

    long value = 0;

    boolean isDataReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isDataReady = false;

        text = (TextView) findViewById(R.id.textView);

        ref = FirebaseDatabase.getInstance().getReference("test");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                isDataReady = true;
                value = (long)dataSnapshot.getValue();
                text.setText(Long.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void buttonClick(View view) {
        Log.d("Click test", "Clicked!");
        if(isDataReady) ref.setValue(value + 1);
    }
}
