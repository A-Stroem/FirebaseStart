package com.example.firebasestart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private FirebaseService fs;
    ArrayAdapter adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        // make an adapter
        adapter = new ArrayAdapter<>(this, R.layout.myrow, R.id.rowTextView);
        fs = new FirebaseService(adapter);
        listView.setAdapter(adapter);
        fs.startListener();
    }
}