package com.example.david.journalapp.userlogin;



import android.support.v4.app.Fragment;

import com.example.david.journalapp.BasePresenter;
import com.example.david.journalapp.BaseView;


/**
 * Created by david on 6/26/18.
 */

public interface LoginContract {
    interface view extends BaseView<Presenter> {

        void showErrorMessage();
        void showLoadingIndicator();
        void hideLoadingIndicator();


        void lauchMainActivity();
    }

    interface Presenter extends BasePresenter {

        void loginWithMail(String mail,String password);
        void loginWithGoogle(Fragment fragment);

    }
}
