package com.example.david.journalapp.entrydetail;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;

import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.data.source.local.LocalDb;

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
        openNote();
    }

    @Override
    public void unsubscribe() {

    }
    public void openNote(){

      LiveData<Note> mNote = mDb.mNoteDaoDao().getNote(noteId);
      mNote.observe((LifecycleOwner) mContext, note -> {
          mNote.removeObserver((Observer<Note>) this);
          mView.showDate(note.getUpdateDate());
          mView.showNote(note.getNotedescription());
      });






    }

    @Override
    public void editNote() {
        mView.showAddEditNote(noteId);

    }

    @Override
    public void deleteNote() {
        mDb.mNoteDaoDao().deleteNote(noteId);
        mView.showDeleteNote();

    }
}
