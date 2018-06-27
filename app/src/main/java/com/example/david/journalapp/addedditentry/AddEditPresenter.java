package com.example.david.journalapp.addedditentry;

import com.example.david.journalapp.BasePresenter;
import com.example.david.journalapp.BaseView;
import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.launcher.LauncherContract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by david on 6/27/18.
 */

public class AddEditPresenter  implements AddEditContract.Presenter{

    private AddEditContract.view mView;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    FirebaseAuth mAuth;
    private Note mNote;



    public AddEditPresenter(AddEditContract.view view) {
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        myRef = database.getReference(userId).push();
        mView = view;
        mView.setPresenter(this);
    }



    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void saveNote(String content) {
        mNote = new Note(content);
        myRef.setValue(mNote);
    }
}
