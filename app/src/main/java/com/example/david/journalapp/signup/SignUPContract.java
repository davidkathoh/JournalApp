package com.example.david.journalapp.signup;

import com.example.david.journalapp.BasePresenter;
import com.example.david.journalapp.BaseView;

/**
 * Created by david on 6/26/18.
 */

public interface SignUPContract {
    interface view extends BaseView<Presenter> {
        void showErrorMessage();
        void launchMainActivity();
        void  showLoadingIndicator();
        void hideLoadingIndicator();


    }

    interface Presenter extends BasePresenter {
        void signup(String name,String mail,String password);


    }
}
