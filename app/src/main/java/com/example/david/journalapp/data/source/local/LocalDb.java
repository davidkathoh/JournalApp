package com.example.david.journalapp.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.david.journalapp.data.Note;

/**
 * Created by david on 6/27/18.
 */
@Database(entities = Note.class,version = 1)
public abstract class LocalDb extends RoomDatabase{
    private static LocalDb INSTANCE;
    public abstract  NoteDao mNoteDaoDao();

    public static LocalDb getLocalDb(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDb.class,"Note")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;

    }
    public static void destroyInstance() {
        INSTANCE = null;

    }
}
