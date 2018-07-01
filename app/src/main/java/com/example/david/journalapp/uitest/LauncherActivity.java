package com.example.david.journalapp.uitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.david.journalapp.R;
import com.example.david.journalapp.util.ActivityUtils;

public class LauncherActivity extends AppCompatActivity {
    private LauncherPresenter mLauncherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        LauncherFragment launcherFragment = (LauncherFragment)getSupportFragmentManager()
                .findFragmentById(R.id.mainFrame);
        if (launcherFragment == null){
            launcherFragment = LauncherFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),launcherFragment,R.id.mainFrame);

        }
        mLauncherPresenter = new LauncherPresenter(launcherFragment);
    }
}
