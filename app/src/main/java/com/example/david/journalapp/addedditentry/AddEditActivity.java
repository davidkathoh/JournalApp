package com.example.david.journalapp.addedditentry;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.david.journalapp.R;
import com.example.david.journalapp.util.ActivityUtils;

public class AddEditActivity extends AppCompatActivity {
    public static final int REQUEST_ADD_NOTE = 2;
    private AddEditPresenter mEditPresenter;
    ActionBar ab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

       ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        String noteId = getIntent().getStringExtra(AddEditFragment.ARGUMENT_EDIT_NOTE_ID);
        setToolbarTitle(noteId);

        AddEditFragment editFragment =
                (AddEditFragment)getSupportFragmentManager().findFragmentById(R.id.mainFrame);

        if (editFragment == null){
            editFragment = AddEditFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),editFragment,R.id.mainFrame);
        }
        mEditPresenter = new AddEditPresenter(editFragment,noteId,getApplicationContext());
    }

    private void setToolbarTitle(@Nullable String noteId) {
        if(noteId == null) {
            ab.setTitle(R.string.add_note);
        } else {
            ab.setTitle(R.string.edit_note);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
