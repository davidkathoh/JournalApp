package com.example.david.journalapp.diaryentries;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.data.source.local.LocalDb;
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

public class EntriesPresenter  implements EntriesContract.Presenter {
    private EntriesContract.view mView;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private FirebaseDatabase mDatabase;
    private LocalDb mDb;
    private List<Note> mNoteList;

    public EntriesPresenter(EntriesContract.view view, Context applicationContext) {
        mView = view;
        mDb = LocalDb.getLocalDb(applicationContext);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child(mAuth.getCurrentUser().getUid());
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        loadEntries();

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadEntries() {
        mNoteList = new ArrayList<>();
        mNoteList = mDb.mNoteDaoDao().getAllEntries();

        if (mNoteList.isEmpty()) {
            mView.showEmpyEntries();
        }
        mView.setAdapter(mNoteList);

    }




}
