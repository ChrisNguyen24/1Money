package com.example.sqlitedemo.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitedemo.model.Note;

import java.util.ArrayList;
import java.util.List;

public class SQLiteStudentHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "noteDB.db";
    private static final int DB_Version = 1;

    public SQLiteStudentHelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang
        String sqlCreateDatabase="CREATE TABLE student(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "description TEXT," +
                "date TEXT)";
        db.execSQL(sqlCreateDatabase);
    }

    //truy van khong tra kq
    //add
    public void addSutdent(Note note){
        String sql = "INSERT INTO student(title,description,date)"+
                "VALUES(?,?,?)";
        String[] args = {note.getTitle(),note.getDescription(),note.getDate()};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql,args);
    }

    // UPDATE Student SET name = ?, gender = ?,mark=? WHERE id = ?
    public void updateStudent(Note note) {
        String sql = "UPDATE student SET title = ?, description = ?,date=? WHERE id = ?";
        String[] args = {note.getTitle(),note.getDescription(),note.getDate(),String.valueOf(note.getId())};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql,args);
    }

    //del
    public void deleteById(int id){
        String sql = "DELETE FROM student WHERE id = ?";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql, args);
    }

    //truy van tra qk
    //getAll
    public List<Note> getAll(){
        List<Note> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("student", null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()){
            int noteId = cursor.getInt(cursor.getColumnIndex("id"));
            String noteTitle = cursor.getString(cursor.getColumnIndex("title"));
            String noteDes = cursor.getString(cursor.getColumnIndex("description"));
            String noteDate = cursor.getString(cursor.getColumnIndex("date"));

            list.add(new Note(noteId,noteTitle,noteDes,noteDate));
        }

        return list;
    }

    public List<Note> getByName(String title) {
        String sql = "title like ?";
        String[] args = { "%" + title + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("student", null, sql, args, null, null, null);
        List<Note> lsnotes = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String titleNote = cursor.getString(1);
            String desNote = cursor.getString(2);
            String dateNote = cursor.getString(3);
            Note student = new Note(id, titleNote, desNote, dateNote);
            lsnotes.add(student);
            System.out.println("search log"+titleNote);
        }
        return lsnotes;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
}
