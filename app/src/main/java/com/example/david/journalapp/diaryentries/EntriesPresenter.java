package com.example.david.journalapp.diaryentries;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.data.source.local.LocalDb;
import com.example.david.journalapp.util.EntriesViewModel;
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

    private Context mContext;

    public EntriesPresenter(EntriesContract.view view, Context applicationContext) {
        mView = view;
        mDb = LocalDb.getLocalDb(applicationContext);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mContext = applicationContext;
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
    public void loadEntries(FragmentActivity fragmentActivity) {
        EntriesViewModel mNoteList = ViewModelProviders.of(fragmentActivity).get(EntriesViewModel.class);
        mNoteList.getNotes().observe(fragmentActivity, notes -> {
            if (notes.isEmpty()) {
                mView.showEmpyEntries();
            }
            mView.setAdapter(notes);

        });



    }




}
