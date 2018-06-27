package com.example.david.journalapp.diaryentries;

import android.support.v4.app.Fragment;

import com.example.david.journalapp.BasePresenter;
import com.example.david.journalapp.BaseView;
import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.userlogin.LoginContract;

import java.util.List;

/**
 * Created by david on 6/26/18.
 */

public interface EntriesContract {
    interface view extends BaseView<Presenter> {


        void showEmpyEntries();
        void setAdapter(List<Note> notes);
        void lauchAddActivity();
        void showDetailUI(String noteId);
    }

    interface Presenter extends BasePresenter {

        void loadEntries();

    }
}