package com.example.david.journalapp.addedditentry;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.david.journalapp.R;
import com.example.david.journalapp.util.ActivityUtils;

public class AddEditActivity extends AppCompatActivity {
    private AddEditPresenter mEditPresenter;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        mActionBar = getSupportActionBar();
        String noteId = getIntent().getStringExtra(AddEditFragment.ARGUMENT_EDIT_NOTE_ID);
        setToolbarTitle(noteId);

        AddEditFragment editFragment =
                (AddEditFragment)getSupportFragmentManager().findFragmentById(R.id.mainFrame);

        if (editFragment == null){
            editFragment = AddEditFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),editFragment,R.id.mainFrame);
        }
        mEditPresenter = new AddEditPresenter(editFragment);
    }

    private void setToolbarTitle(@Nullable String noteId) {
        if(noteId == null) {
            mActionBar.setTitle(R.string.add_note);
        } else {
            mActionBar.setTitle(R.string.edit_note);
        }
    }

}
