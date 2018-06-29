package com.example.david.journalapp.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.david.journalapp.R;
import com.example.david.journalapp.diaryentries.EntriesActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginFragment extends Fragment implements LoginContract.view, GoogleApiClient.OnConnectionFailedListener {
    private TextInputEditText mUserEmail;
    private TextInputEditText mUserPassWord;
    private Button mLoginWithEmail;
    private Button mLoginWithGoogle;
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private LoginContract.Presenter mLoginPresenter;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;


    public LoginFragment() {

    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initloginWithGoogle();
        mAuth = FirebaseAuth.getInstance();
        mUserEmail = view.findViewById(R.id.ed_mail);
        mUserPassWord = view.findViewById(R.id.ed_password);
        mLoginWithEmail = view.findViewById(R.id.btn_login_with_mail);
        mLoginWithGoogle = view.findViewById(R.id.btn_login_with_google);
        mLoginWithEmail.setOnClickListener(view1 -> signin());
        mLoginWithGoogle.setOnClickListener(view1 -> loginWithGoogle());

        return  view;
    }

    @Override
    public void showErrorMessage() {

        Toast.makeText(getContext(),R.string.error_connect_message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingIndicator() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.loading_message));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();

    }

    @Override
    public void hideLoadingIndicator() {
        mProgressDialog.hide();
    }

    @Override
    public void lauchMainActivity() {
        Intent launchMainActivity = new Intent(getContext(), EntriesActivity.class);
        startActivity(launchMainActivity);
        getActivity().finish();
        Log.i("LOGIN","SUCCESS");
    }
    public void signin(){
        String mail = mUserEmail.getText().toString();
        String password = mUserPassWord.getText().toString();
        mLoginPresenter.loginWithMail(mail,password);
    }

    public void  initloginWithGoogle(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(),this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        mGoogleApiClient.connect();
    }
    public void loginWithGoogle(){
        showLoadingIndicator();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                hideLoadingIndicator();
              showErrorMessage();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        hideLoadingIndicator();
                        lauchMainActivity();
                    }else {
                        hideLoadingIndicator();
                        showErrorMessage();
                    }
                });
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
