package com.example.sqlitedemo.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitedemo.model.Note;

import java.util.ArrayList;
import java.util.List;

public class SQLiteNoteDone extends SQLiteOpenHelper {
    private static final String DB_Name = "noteDoneDB.db";
    private static final int DB_Version = 1;

    public SQLiteNoteDone(@Nullable Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang
        String sqlCreateDatabase="CREATE TABLE complete(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "description TEXT," +
                "date TEXT)";
        db.execSQL(sqlCreateDatabase);
    }

    //truy van khong tra kq
    //add
    public void addDone(Note note){
        String sql = "INSERT INTO complete(title,description,date)"+
                "VALUES(?,?,?)";
        String[] args = {note.getTitle(),note.getDescription(),note.getDate()};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql,args);
    }

    public void deleteAll(){
        String sql = "DELETE FROM complete";
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql);
    }

    //truy van tra qk
    //getAll
    public List<Note> getAllDone(){
        List<Note> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("complete", null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()){
            int noteId = cursor.getInt(cursor.getColumnIndex("id"));
            String noteTitle = cursor.getString(cursor.getColumnIndex("title"));
            String noteDes = cursor.getString(cursor.getColumnIndex("description"));
            String noteDate = cursor.getString(cursor.getColumnIndex("date"));
            list.add(new Note(noteId,noteTitle,noteDes,noteDate));
        }
        return list;
    }

//    public List<Note> getByName(String name) {
//        String sql = "name like ?";
//        String[] args = { "%" + name + "%" };
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.query("student", null, sql, args, null, null, null);
//        List<Cong> students = new ArrayList<>();
//        while(cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String studentName = cursor.getString(1);
//            boolean gender = cursor.getInt(2) == 1;
//            double mark = cursor.getDouble(3);
//            Cong student = new Cong(id, studentName, gender, mark);
//            students.add(student);
//        }
//        return students;
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
}
