package com.example.david.journalapp.addedditentry;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.journalapp.R;


public class AddEditFragment extends Fragment implements AddEditContract.view{

    public static final String ARGUMENT_EDIT_NOTE_ID = "EDIT_NOTE_ID";
    private AddEditContract.Presenter mPresenter;
    private EditText mNoteEditText;



    public AddEditFragment() {

    }


    public static AddEditFragment newInstance() {
      return new AddEditFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_add_edit, container, false);
    mNoteEditText = view.findViewById(R.id.ed_diary_note);
    setHasOptionsMenu(true);
        return  view;
    }

    @Override
    public void showEmptynote() {
        Toast.makeText(getContext(),R.string.empty_entry,Toast.LENGTH_LONG).show();
    }

    @Override
    public void noteadded() {
        Toast.makeText(getContext(),R.string.entry_added,Toast.LENGTH_LONG).show();

    }

    @Override
    public void setNote(String note) {
        mNoteEditText.setText(note);
    }

    @Override
    public void setNoteDate(String noteDate) {

    }

    @Override
    public void showEntryList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save){
            save();

        }
        return true;
    }
    public  void save(){
        String diarynote = mNoteEditText.getText().toString();
        mPresenter.saveNote(diarynote);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_save_note,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setPresenter(AddEditContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
