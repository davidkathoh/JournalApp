package com.example.david.journalapp.userSignup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by david on 6/26/18.
 */

public class SignUpPresenter implements SignUPContract.Presenter{
    private SignUPContract.view mView;
    private FirebaseAuth mAuth;

    public SignUpPresenter(SignUPContract.view view) {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
        mView.setPresenter(this);
    }

    @Override
    public void signup(String name, String mail, String password) {
        mAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        mView.launchMainActivity();
                    }else {
                        mView.showErrorMessage();
                    }
                });

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
