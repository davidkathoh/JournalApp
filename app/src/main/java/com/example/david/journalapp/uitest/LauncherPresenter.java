package com.example.david.journalapp.uitest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by david on 6/26/18.
 */

public class LauncherPresenter  implements LauncherContract.Presenter{
    private final LauncherContract.view mView;
    private FirebaseAuth mAuth;

    public LauncherPresenter(LauncherContract.view view) {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
        mView.setPresenter(this);
    }

    @Override
    public boolean isUserAuthentified() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            return false;
        }else {
            mView.loadMainActivity();
            return true;
        }


    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
