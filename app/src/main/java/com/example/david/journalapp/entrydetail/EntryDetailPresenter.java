package com.example.david.journalapp.entrydetail;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.data.source.local.LocalDb;
import com.example.david.journalapp.util.AppExecutors;

/**
 * Created by david on 6/27/18.
 */

public class EntryDetailPresenter implements EntryDetailContract.Presenter{
    private EntryDetailContract.view mView;
    private String noteId;
    private LocalDb mDb;
    private Context mContext;

    public EntryDetailPresenter(EntryDetailContract.view view, String noteId, Context context) {
        mView = view;
        this.noteId = noteId;
        mDb = LocalDb.getLocalDb(context);
        mContext = context;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
    @Override
    public void openNote(FragmentActivity fragmentActivity){

      LiveData<Note> mNote = mDb.mNoteDaoDao().getNote(noteId);
      mNote.observe(fragmentActivity, note -> {
          if (note!= null) {
            //  String date = note.getUpdateDate();
              mView.showDate(note.getUpdateDate());
              mView.showNote(note.getNotedescription());
          }else {
              mView.showMissingData();
          }
      });






    }

    @Override
    public void editNote() {
        mView.showAddEditNote(noteId);

    }

    @Override
    public void deleteNote() {
        AppExecutors.getInstance().diskIO().execute(()->{
            mDb.mNoteDaoDao().deleteNote(noteId);
            mView.showDeleteNote();
        });


    }
}
