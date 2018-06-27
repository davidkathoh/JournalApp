package com.example.david.journalapp.userSignup;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.david.journalapp.R;


public class SignUpFragment extends Fragment  implements  SignUPContract.view{
    private TextInputEditText mUserEmail;
    private TextInputEditText mUserPassWord;
    private TextInputEditText mUserName;
    private Button mCreateUser;
    private Button mSignupWithGoogle;
    private SignUPContract.Presenter mPresenter;


    public SignUpFragment() {

    }


    public static SignUpFragment newInstance() {
     return    new SignUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mUserEmail = view.findViewById(R.id.ed_mail);
        mUserPassWord = view.findViewById(R.id.ed_password);
        mUserName = view.findViewById(R.id.ed_name);
        mCreateUser = view.findViewById(R.id.btn_create_account);
        mSignupWithGoogle = view.findViewById(R.id.btn_signup_with_google);
        mCreateUser.setOnClickListener(v->login());
        return view;
    }

    @Override
    public void showErrorMessage() {
        Log.i("LOGIN","FAIL");
    }

    @Override
    public void launchMainActivity() {
        Log.i("LOGIN","SUCCESS");
    }

    @Override
    public void showLoadingIndicator() {
        Log.i("LOGIN","LOADING");
    }

    @Override
    public void hideLoadingIndicator() {
        Log.i("LOGIN","LOADING DONE");
    }
    public void login(){
        String mail = mUserEmail.getText().toString();
        String name = mUserName.getText().toString();
        String password = mUserPassWord.getText().toString();
//        if(TextUtils.isEmpty(mail)){
//            Toast.makeText(getContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if(TextUtils.isEmpty(password)){
//            Toast.makeText(getContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
//            return;
//        }
        mPresenter.signup(name,mail,password);
    }

    @Override
    public void setPresenter(SignUPContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
