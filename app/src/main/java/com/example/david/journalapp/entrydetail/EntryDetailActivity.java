package com.example.david.journalapp.entrydetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.david.journalapp.R;
import com.example.david.journalapp.util.ActivityUtils;

public class EntryDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_ID = "NOTE_ID";

    private EntryDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_detail);

        String noteId = getIntent().getStringExtra(EXTRA_NOTE_ID);

        EntryDetailFragment entryDetailFragment =
                (EntryDetailFragment)getSupportFragmentManager().findFragmentById(R.id.mainFrame);
        if (entryDetailFragment == null){
            entryDetailFragment = EntryDetailFragment.newInstance(noteId);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),entryDetailFragment,R.id.mainFrame);

        }
        mPresenter = new EntryDetailPresenter(entryDetailFragment,noteId);
    }
}
