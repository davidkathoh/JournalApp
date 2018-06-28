package com.example.david.journalapp.addedditentry;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.data.source.local.LocalDb;
import com.example.david.journalapp.util.AppExecutors;
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

    @Override
    public void populateTask(FragmentActivity fragmentActivity) {
        if (!isNewNote()){
            LiveData< Note> note = mDb.mNoteDaoDao().getNote(mNoteId);
            note.observe(fragmentActivity,v->{
                mView.setNote(v.getNotedescription());
                mView.setNoteDate(v.getUpdateDate());
            });
        }

    }

    public void createNote(String description){
        Note newNote = new Note(description);
        if (description.isEmpty()){
            mView.showEmptynote();
        }else {
            AppExecutors.getInstance().diskIO().execute(()->{
                mDb.mNoteDaoDao().saveNote(newNote);
                mView.showEntryList();
            });

        }
    }
    public void updateNote(String note){
        if (isNewNote()) {
            throw new RuntimeException("updateNote() was called but note is new.");
        }
        AppExecutors.getInstance().diskIO().execute(()->{
            mDb.mNoteDaoDao().upDateNote(new Note(mNoteId,note));
            mView.showEntryList();
        });

    }
}
