package com.example.david.journalapp.diaryentries;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.david.journalapp.R;
import com.example.david.journalapp.util.ActivityUtils;

public class EntriesActivity extends AppCompatActivity {
    private ActionBar mActionBar;

    private EntriesPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(R.string.ab_entries_title);

        EntriesFragment entriesFragment = (EntriesFragment)getSupportFragmentManager().findFragmentById(R.id.mainFrame);
        if (entriesFragment == null){
            entriesFragment = EntriesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),entriesFragment,R.id.mainFrame);
        }

        mPresenter = new EntriesPresenter(entriesFragment,getApplicationContext());
    }
}
