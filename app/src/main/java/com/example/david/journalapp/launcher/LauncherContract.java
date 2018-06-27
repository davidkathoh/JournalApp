package com.example.david.journalapp.launcher;

import android.view.View;

import com.example.david.journalapp.BasePresenter;
import com.example.david.journalapp.BaseView;

/**
 * Created by david on 6/26/18.
 */

public interface LauncherContract {

    interface view extends BaseView<Presenter> {

        void loadLogin();

        void loadSignup( );

        void loadMainActivity( );


    }

    interface Presenter extends BasePresenter {

       boolean isUserAuthentified();

    }
}
