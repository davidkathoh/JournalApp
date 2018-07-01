package com.example.david.journalapp.databasetest;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.david.journalapp.data.Note;
import com.example.david.journalapp.data.source.local.LocalDb;
import com.example.david.journalapp.data.source.local.NoteDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by david on 6/30/18.
 */
@RunWith(AndroidJUnit4.class)
public class TestDao {
    LocalDb mDb;
    Note mNote;


    @Mock
    private Observer<List<Note>> mObserver;

    @Before
    public void initialize() throws Exception{
        MockitoAnnotations.initMocks(this);
        Context context = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(context,LocalDb.class)
                .allowMainThreadQueries()
                .build();
    }
    @After
    public void clauseDb(){
        mDb.close();
    }

    @Test
    public void insert_select_Test(){
        mNote = new Note("this is my note");
       mDb.mNoteDaoDao().getAllEntries().observeForever(mObserver);
       mDb.mNoteDaoDao().saveNote(mNote);
       verify(mObserver).onChanged(Collections.singletonList(mNote));


    }



}
