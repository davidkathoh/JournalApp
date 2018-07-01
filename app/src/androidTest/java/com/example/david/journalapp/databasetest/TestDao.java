package com.example.david.journalapp.databasetest;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * Created by david on 6/30/18.
 */
@RunWith(AndroidJUnit4.class)
public class TestDao {
    LocalDb mDb;
    Note mNote;
    NoteDao mDao;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void initialize() throws Exception{
        MockitoAnnotations.initMocks(this);
        Context context = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(context,LocalDb.class)
                .allowMainThreadQueries()
                .build();
        mDao =mDb.mNoteDaoDao();
    }
    @After
    public void clauseDb(){
        mDb.close();
    }

    @Test
    public void onFetchingNotes_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {
        List < Note > noteList = LiveDataTestUtil.getValue(mDao.getAllEntries());
        assertTrue(noteList.isEmpty());
    }
    @Test
    public void onUpdatingANote_checkIf_UpdateHappensCorrectly() throws InterruptedException {
        String newNote = "Original note";
        String upDateNote = "update_note";
        Note note = new Note(newNote);
        mDao.saveNote(note);
        note.setNotedescription(upDateNote);
        mDao.upDateNote(note);
        assertEquals(1, LiveDataTestUtil.getValue(mDao.getAllEntries()).size());
        assertEquals(upDateNote,
                LiveDataTestUtil.getValue(mDao.getNote(note.getId())).getNotedescription());
    }
    @Test
    public void check_if_note_isDeeleted() throws InterruptedException{
        Note  note = new Note("description");
        mDao.saveNote(note);
        mDao.deleteNote(note.getId());
        assertTrue(LiveDataTestUtil.getValue(mDao.getAllEntries()).isEmpty());

    }


}
