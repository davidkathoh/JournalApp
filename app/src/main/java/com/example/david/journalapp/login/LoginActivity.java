package com.example.david.journalapp.login;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.example.david.journalapp.R;
import com.example.david.journalapp.util.ActivityUtils;


public class LoginActivity extends AppCompatActivity  {
    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginFragment loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.mainFrame);
        if (loginFragment == null){
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),loginFragment,R.id.mainFrame);
        }
        mLoginPresenter = new LoginPresenter(loginFragment,getApplicationContext());

    }
}

