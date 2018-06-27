package com.example.david.journalapp.data.source.local;

import android.arch.persistence.room.Dao;
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
    List<Note> getAllEntries();

    @Update
    void upDateNote(Note note);

    @Query("SELECT Note where mId =:noteId")
    Note getNote(String noteId);
}
