package com.example.david.journalapp.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.david.journalapp.data.Note;

import java.util.List;

/**
 * Created by david on 6/27/18.
 */

@Dao
public interface NoteDao {
    @Insert
    void saveNote(Note note);

    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllEntries();

    @Update
    void upDateNote(Note note);

    @Query("SELECT * from Note where mId =:noteId")
    LiveData<Note> getNote(String noteId);

    @Query("DELETE from Note where mId = :uid")
    void deleteNote(String uid);
}
