package com.luminousid.luminousid;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.*;

import java.io.IOException;

/**
 * Created by chase on 2/9/2017.
 */

public class FG_DB_Helper extends SQLiteOpenHelper {
    //Database schema number (in localFieldGuideContract) must match here
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "localFieldGuide.db";
    public android.content.Context context;

    public FG_DB_Helper(android.content.Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        for(int index=0; index < localFieldGuideContract.SQL_CREATE_TABLE_ARRAY.length; index++){
            try {
                db.execSQL(localFieldGuideContract.SQL_CREATE_TABLE_ARRAY[index]);
                System.out.println("Created table: " + localFieldGuideContract.SQL_TABLE_NAME_ARRAY[index]);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Cannot create " + localFieldGuideContract.SQL_TABLE_NAME_ARRAY[index]);
            }
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // This deals with upgraded the local database.
        // We just pull from raw .csv files so we can delete all the old data.
        for(int index=0; index < localFieldGuideContract.SQL_DELETE_TABLE_ARRAY.length; index++){
            db.execSQL(localFieldGuideContract.SQL_DELETE_TABLE_ARRAY[index]);
            System.out.println("Created table: " + localFieldGuideContract.SQL_TABLE_NAME_ARRAY[index]);
        }
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
