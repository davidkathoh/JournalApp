package com.example.david.journalapp.entrydetail;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.journalapp.R;
import com.example.david.journalapp.addedditentry.AddEditActivity;
import com.example.david.journalapp.addedditentry.AddEditFragment;


public class EntryDetailFragment extends Fragment implements EntryDetailContract.view {
    public static final String ARGUMENT_NOTE_ID = "NOTE_ID";
    private static final int REQUEST_EDIT = 5;
    private TextView mDateTextView;
    private TextView mNoteTextView;
    private EntryDetailContract.Presenter mPresenter;


    public EntryDetailFragment() {

    }


    public static EntryDetailFragment newInstance(String noteId) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_NOTE_ID,noteId);
        EntryDetailFragment fragment = new EntryDetailFragment();
        fragment.setArguments(args);

     return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry_detail, container, false);
        mDateTextView = view.findViewById(R.id.tv_entry_date);
        mNoteTextView = view.findViewById(R.id.tv_entry_title);

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete){
            //delete
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_delete_note,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setPresenter(EntryDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showEditNote(String noteId) {
        Intent intent = new Intent(getContext(), AddEditActivity.class);
        intent.putExtra(AddEditFragment.ARGUMENT_EDIT_NOTE_ID,noteId);
        startActivityForResult(intent,REQUEST_EDIT);
    }
}
