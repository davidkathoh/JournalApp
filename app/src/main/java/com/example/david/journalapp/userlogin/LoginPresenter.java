package com.example.david.journalapp.userlogin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.david.journalapp.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

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
