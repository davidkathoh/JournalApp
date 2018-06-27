package com.example.david.journalapp.addedditentry;

import com.example.david.journalapp.BasePresenter;
import com.example.david.journalapp.BaseView;
import com.example.david.journalapp.data.Note;


/**
 * Created by david on 6/27/18.
 */

public interface AddEditContract {
    interface view extends BaseView<Presenter> {

        void showEmptynote();
        void noteadded();
        void setNote(String note);
        void showEntryList();


    }

    interface Presenter extends BasePresenter {

        void saveNote(String note);

    }
}