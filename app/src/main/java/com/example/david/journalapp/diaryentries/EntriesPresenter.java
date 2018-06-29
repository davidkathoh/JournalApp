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

    private  List<Note> mNotes;


    public EntriesPresenter(EntriesContract.view view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (mNotes.isEmpty()){
            mView.showEmpyEntries();
        }

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadEntries(FragmentActivity fragmentActivity) {
        EntriesViewModel mNoteList = ViewModelProviders.of(fragmentActivity).get(EntriesViewModel.class);
        mNoteList.getNotes().observe(fragmentActivity, notes -> {
           mNotes = notes;
            mView.setAdapter(notes);

        });



    }




}
