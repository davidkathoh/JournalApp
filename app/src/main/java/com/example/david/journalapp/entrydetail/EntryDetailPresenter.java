package com.example.david.journalapp.entrydetail;

/**
 * Created by david on 6/27/18.
 */

public class EntryDetailPresenter implements EntryDetailContract.Presenter{
    private EntryDetailContract.view mView;
    private String noteId;

    public EntryDetailPresenter(EntryDetailContract.view view, String noteId) {
        mView = view;
        this.noteId = noteId;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
