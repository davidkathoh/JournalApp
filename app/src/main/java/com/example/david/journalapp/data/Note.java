package com.example.david.journalapp.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by david on 6/25/18.
 */

public class Note {
    private String mId;
    private String mNotedescription;
    private String mUpdateDate;
    static DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");


    public Note() {
    }

    public Note(String notedescription) {
        mId = UUID.randomUUID().toString();
        mNotedescription = notedescription;
        mUpdateDate = df.format(Calendar.getInstance().getTime());
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getNotedescription() {
        return mNotedescription;
    }

    public void setNotedescription(String notedescription) {
        mNotedescription = notedescription;
    }

    public String getUpdateDate() {
        return mUpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        mUpdateDate = updateDate;
    }
}
