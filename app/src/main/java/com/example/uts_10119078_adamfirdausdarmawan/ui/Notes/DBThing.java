package com.example.uts_10119078_adamfirdausdarmawan.ui.Notes;
/*
    Nama : Adam Firdaus Darmawan
    Kelas : IF-2
    NIM : 10119078
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBThing extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Notes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Notes";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_TITLE = "Title";
    private static final String COLUMN_NOTE = "Content";

    public DBThing (@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_NOTE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
    public boolean addNote(NotesModel notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, notes.getTitle());
        values.put(COLUMN_NOTE, notes.getNote());

        return db.insert(TABLE_NAME,null,values) >0;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
    }

    public boolean updateNote(NotesModel notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String id = notes.getId();

        values.put(COLUMN_TITLE, notes.getTitle());
        values.put(COLUMN_NOTE, notes.getNote());

        return db.update(TABLE_NAME,values,COLUMN_ID + "=" + id,null)>0;
    }

    public void deleteNote(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_ID + "=" + id,null);
    }
}
