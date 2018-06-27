package com.example.david.journalapp.userSignup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.david.journalapp.R;
import com.example.david.journalapp.util.ActivityUtils;

public class SignUpActivity extends AppCompatActivity {
    private SignUpPresenter mSignUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SignUpFragment signUpFragment = (SignUpFragment)getSupportFragmentManager().findFragmentById(R.id.mainFrame);
        if (signUpFragment == null){
            signUpFragment = SignUpFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    signUpFragment,R.id.mainFrame);
        }
        mSignUpPresenter = new SignUpPresenter(signUpFragment);
    }

}
