package com.example.firebasestart;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.firebasestart.model.Note;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseService {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayAdapter adapter;
    public FirebaseService(ArrayAdapter adapter) {
       this.adapter = adapter;
    }

    public void addNote(String text){
        DocumentReference ref = db.collection("notes").document();
        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        ref.set(map);
    }

    public void add2Note(String text){
        DocumentReference ref = db.collection("notes").document();
        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        ref.set(map).addOnSuccessListener(unused ->
                        System.out.println("document saved, " + text))
        .addOnFailureListener(e ->
                System.out.println("document NOT saved, " + text));
    }
    private String notes = "notes";
    public List<Note> list = new ArrayList<>();
    public void startListener(){
        db.collection(notes).addSnapshotListener((snap,error)->{
            if(error == null){
                list.clear();
                for(DocumentSnapshot s :snap.getDocuments()){
                    System.out.println(s.getData().get("text"));
                    String t = s.getData().get("text").toString();
                    Note note = new Note(t, s.getId());
                    list.add(note);
                }
                adapter.clear();
                adapter.addAll(list);
                adapter.notifyDataSetChanged(); // will update the gui
            }
        });
    }

}
