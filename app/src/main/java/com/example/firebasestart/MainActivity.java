package com.example.firebasestart;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private FirebaseService fs;
    ArrayAdapter adapter;
    private ListView listView;
    private ImageView imageView;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        imageView = findViewById(R.id.imageView);
        adapter = new ArrayAdapter<>(this, R.layout.myrow, R.id.rowTextView);
        fs = new FirebaseService(adapter);
        listView.setAdapter(adapter);
        fs.startListener();

        // Initialize Firebase Storage
        storageReference = FirebaseStorage.getInstance().getReference();

        // Load the image from Firebase Storage
        loadImageFromFirebaseStorage();
    }

    private void loadImageFromFirebaseStorage() {
        // Replace "path/to/image.jpg" with the path to the image in Firebase Storage
        StorageReference imageRef = storageReference.child("P7150001.JPG");
        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Failed to load the image", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
