package com.example.david.journalapp.util;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.data.source.local.LocalDb;

import java.util.List;

/**
 * Created by david on 6/28/18.
 */

public class EntriesViewModel extends AndroidViewModel {
    private LiveData<List<Note>> notes;
    public EntriesViewModel(@NonNull Application application) {
        super(application);
        LocalDb localDb = LocalDb.getLocalDb(application);
        notes = localDb.mNoteDaoDao().getAllEntries();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }
}
