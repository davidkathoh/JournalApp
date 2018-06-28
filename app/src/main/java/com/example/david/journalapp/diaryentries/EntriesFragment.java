package com.example.david.journalapp.diaryentries;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.david.journalapp.R;
import com.example.david.journalapp.addedditentry.AddEditActivity;
import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.entrydetail.EntryDetailActivity;

import java.util.List;


public class EntriesFragment extends Fragment implements EntriesContract.view{

    private RecyclerView mRecyclerView;
    private EntriesContract.Presenter mPresenter;
    private FloatingActionButton mAddButton;
    private EntryAdapter mEntryAdapter;
    private LinearLayout mLinearLayout;



    public EntriesFragment() {

    }


    public static EntriesFragment newInstance() {
        return new EntriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entries, container, false);

        mRecyclerView = view.findViewById(R.id.entries_recycleview);
        mAddButton = view.findViewById(R.id.fab_new_entry);
        mLinearLayout = view.findViewById(R.id.noEnty);


        mAddButton.setOnClickListener(view1 -> lauchAddActivity());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void showEmpyEntries() {
        mLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAdapter(List<Note> notes) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        mEntryAdapter = new EntryAdapter(notes);
        mRecyclerView.setAdapter(mEntryAdapter);
    }

    @Override
    public void lauchAddActivity() {
        Intent launchAdd = new Intent(getContext(), AddEditActivity.class);
       startActivityForResult(launchAdd,AddEditActivity.REQUEST_ADD_NOTE);
    }

    @Override
    public void showDetailUI(String noteId) {
        Intent intent = new Intent(getContext(), EntryDetailActivity.class);
        intent.putExtra(EntryDetailActivity.EXTRA_NOTE_ID,noteId);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setPresenter(EntriesContract.Presenter presenter) {
        mPresenter = presenter;
    }
    public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder>{
            List<Note> mNoteList;

        public EntryAdapter(List<Note> noteList) {
            mNoteList = noteList;
        }

        @NonNull
        @Override
        public EntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.entry_list,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull EntryAdapter.ViewHolder holder, int position) {
            holder.mEntryDate.setText(mNoteList.get(position).getUpdateDate());
            holder.mEntryMessage.setText(mNoteList.get(position).getNotedescription());


        }

        @Override
        public int getItemCount() {
            return mNoteList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
                                    implements View.OnClickListener{
            TextView mEntryMessage;
            TextView mEntryDate;
            ViewGroup mRootContainer;

            public ViewHolder(View itemView) {
                super(itemView);
                mEntryMessage = itemView.findViewById(R.id.tv_entry_title);
                mEntryDate = itemView.findViewById(R.id.tv_entry_date);
                mRootContainer = itemView.findViewById(R.id.root_note_list);
                mRootContainer.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                String uid = mNoteList.get(getAdapterPosition()).getId();
                showDetailUI(uid);


            }
        }
    }
}














































