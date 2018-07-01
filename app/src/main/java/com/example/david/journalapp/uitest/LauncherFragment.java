package com.example.david.journalapp.uitest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.david.journalapp.R;
import com.example.david.journalapp.diaryentries.EntriesActivity;
import com.example.david.journalapp.signup.SignUpActivity;
import com.example.david.journalapp.login.LoginActivity;

public class LauncherFragment extends Fragment  implements LauncherContract.view{
    private LauncherContract.Presenter mPresenter;
    private Button mLoginButton;
    private Button mSignupButton;

    public LauncherFragment() {

    }

    public static LauncherFragment newInstance() {
        return new LauncherFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_launcher,container,false);
        mLoginButton = view.findViewById(R.id.btn_login);
        mSignupButton = view.findViewById(R.id.btn_signup);
        mLoginButton.setOnClickListener(v-> loadLogin());
        mSignupButton.setOnClickListener(v->loadSignup());
        return view;
    }


    @Override
    public void loadLogin() {
//        Toast.makeText(getContext(),"login clicked",Toast.LENGTH_LONG).show();
        Intent launchLoginActivity = new Intent(getContext(), LoginActivity.class);
        startActivity(launchLoginActivity);

    }

    @Override
    public void loadSignup() {
        Intent launchSignupActivity = new Intent(getContext(), SignUpActivity.class);
        startActivity(launchSignupActivity);
    }

    @Override
    public void loadMainActivity() {
        Intent launchMainActivity = new Intent(getContext(), EntriesActivity.class);
        startActivity(launchMainActivity);

    }

    @Override
    public void onStart() {
        super.onStart();
       mPresenter.isUserAuthentified();
    }

    @Override
    public void setPresenter(LauncherContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
