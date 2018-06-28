package com.example.david.journalapp.entrydetail;

import android.content.Context;

import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.data.source.local.LocalDb;

/**
 * Created by david on 6/27/18.
 */

public class EntryDetailPresenter implements EntryDetailContract.Presenter{
    private EntryDetailContract.view mView;
    private String noteId;
    private Note mNote;
    private LocalDb mDb;

    public EntryDetailPresenter(EntryDetailContract.view view, String noteId, Context context) {
        mView = view;
        this.noteId = noteId;
        mDb = LocalDb.getLocalDb(context);
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
        mNote = new Note();
        mNote = mDb.mNoteDaoDao().getNote(noteId);

            mView.showDate(mNote.getUpdateDate());
            mView.showNote(mNote.getNotedescription());



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
