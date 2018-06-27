package com.example.david.journalapp.diaryentries;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.david.journalapp.data.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 6/26/18.
 */

public class EntriesPresenter  implements EntriesContract.Presenter{
    private EntriesContract.view mView;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private FirebaseDatabase mDatabase;
    private List<Note> mNoteList;

    public EntriesPresenter(EntriesContract.view view) {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child(mAuth.getCurrentUser().getUid());
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public  void loadEntries() {
        mNoteList = new ArrayList<>();
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                GenericTypeIndicator<List<Note>> t = new GenericTypeIndicator<List<Note>>() {};
//                mNoteList = dataSnapshot.getValue(t);
            for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                mNoteList.add(snapshot.getValue(Note.class));
                Log.i("LOAD","loading");
            }
            if (mNoteList.isEmpty()){
                mView.showEmpyEntries();
            }
            mView.setAdapter(mNoteList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.i("List",mNoteList.size()+"");

    }
}
