package com.example.david.journalapp.entrydetail;

import android.support.v4.app.FragmentActivity;

import com.example.david.journalapp.BasePresenter;
import com.example.david.journalapp.BaseView;


/**
 * Created by david on 6/27/18.
 */

public interface EntryDetailContract {

    interface view extends BaseView<Presenter> {
        void showAddEditNote(String noteId);
        void showDate(String date);
        void showNote(String note);
        void showDeleteNote();



    }

    interface Presenter extends BasePresenter {
        void editNote();
        void deleteNote();
        void openNote(FragmentActivity activity);



    }

}
