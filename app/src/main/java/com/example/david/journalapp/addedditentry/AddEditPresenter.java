package com.example.david.journalapp.addedditentry;

import android.content.Context;

import com.example.david.journalapp.BasePresenter;
import com.example.david.journalapp.BaseView;
import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.data.source.local.LocalDb;
import com.example.david.journalapp.data.source.local.NoteDao;
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

    private  final LocalDb mDb;



    private Context mContext;

    private String mNoteId;

    private Note mNote;



    public AddEditPresenter(AddEditContract.view view, String noteId, Context context) {
        database = FirebaseDatabase.getInstance();
        mNoteId = noteId;
        mDb = LocalDb.getLocalDb(context);
        mContext = context;
        mView = view;
        mView.setPresenter(this);
    }



    @Override
    public void subscribe() {
        if (!isNewNote()){
            populateNote();
        }

    }

    private void populateNote() {
        Note note = mDb.mNoteDaoDao().getNote(mNoteId);
        mView.setNote(note.getNotedescription());
        mView.setNoteDate(note.getUpdateDate());
    }

    @Override
    public void unsubscribe() {
        mDb.close();
    }

    public boolean isNewNote(){
        return mNoteId == null;
    }

    @Override
    public void saveNote(String content) {
      if (isNewNote()){
          createNote(content);
      }else {
          updateNote(content);
      }
    }

    public void createNote(String description){
        Note newNote = new Note(description);
        if (description.isEmpty()){
            mView.showEmptynote();
        }else {
            mDb.mNoteDaoDao().saveNote(newNote);
            mView.showEntryList();

        }
    }
    public void updateNote(String note){
        if (isNewNote()) {
            throw new RuntimeException("updateNote() was called but note is new.");
        }
        mDb.mNoteDaoDao().upDateNote(new Note(mNoteId,note));
    }
}
