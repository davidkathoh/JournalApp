package com.example.david.journalapp.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by david on 6/26/18.
 */

public class LoginPresenter implements LoginContract.Presenter, GoogleApiClient.OnConnectionFailedListener {
    private LoginContract.view mView;
    private FirebaseAuth mAuth;
    private Context mContext;;

    public LoginPresenter(LoginContract.view view, Context context) {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
        mContext = context;

        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loginWithMail(String mail, String password) {
        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        mView.hideLoadingIndicator();
                        mView.lauchMainActivity();
                    }else {
                        mView.hideLoadingIndicator();
                        mView.showErrorMessage();
                    }
                        }
                );
    }

    @Override
    public void loginWithGoogle(Fragment fragment) {

    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mView.showErrorMessage();
    }
}
