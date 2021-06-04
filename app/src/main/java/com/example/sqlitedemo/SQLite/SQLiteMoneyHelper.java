package com.example.sqlitedemo.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitedemo.model.Cong;
import com.example.sqlitedemo.model.Money;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by congnguyen on 5/28/21.
 */
public class SQLiteMoneyHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "moneyDB.db";
    private static final int DB_Version = 1;
    public SQLiteMoneyHelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang
        String sqlCreateDatabase="CREATE TABLE money(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "amount TEXT," +
                "date TEXT," +
                "category TEXT," +
                "type TEXT," +
                "note TEXT)";
        db.execSQL(sqlCreateDatabase);
    }

    //truy van khong tra kq
    //add
    public void addMoney(Money m){
        String sql = "INSERT INTO money(amount,date,category, type, note)"+
                "VALUES(?,?,?,?,?)";
        String[] args = {m.getAmount(), m.getDate(), m.getCategory(), m.getType(), m.getNote()};
        System.out.println("new money: "+m.toString());
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql,args);
    }

    // UPDATE money SET amount = ?, date = ?,category=?, type=?, note=? WHERE id = ?
    public void updateMoney(Money m) {
        System.out.println("check update: "+m.toString());
        String sql = "UPDATE money SET amount = ?, date = ?,category=? ,type=? ,note=? WHERE id = ?";
        String[] args = {m.getAmount(), m.getDate(), m.getCategory(), m.getType(), m.getNote(),String.valueOf(m.getId())};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql,args);
    }

    //del
    public void deleteById(int id){
        String sql = "DELETE FROM money WHERE id = ?";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql, args);
    }

    //truy van tra qk
    //getAll
    public List<Money> getAll(){
        List<Money> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("money", null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()){
            int moneyId = cursor.getInt(cursor.getColumnIndex("id"));
            String amount = cursor.getString(cursor.getColumnIndex("amount"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String note = cursor.getString(cursor.getColumnIndex("note"));
            Money money = new Money(moneyId,amount,date,category,type,note);
            System.out.println(money.toString());
            list.add(money);
        }
        return list;
    }

    //getAll
    public float balance(float wallet){
        float bal = 0;
        List<Money> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("money", null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()){
            int moneyId = cursor.getInt(cursor.getColumnIndex("id"));
            String amount = cursor.getString(cursor.getColumnIndex("amount"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String note = cursor.getString(cursor.getColumnIndex("note"));
            Money money = new Money(moneyId,amount,date,category,type,note);
            System.out.println(money.toString());
            list.add(money);
        }
        for(Money i:list){
            if(i.getType().equalsIgnoreCase("Chi")){
                bal -= Float.parseFloat(i.getAmount());
            }
            else{
                bal += Float.parseFloat(i.getAmount());
            }
        }
        return wallet + bal;
    }
    //getByCategory
    public List<Money> getByCategory(String category) {
        String sql = "category like ?";
        String[] args = { "%" + category + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("money", null, sql, args, null, null, null);
        List<Money> moneys = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String amount = cursor.getString(1);
            String date = cursor.getString(2);
            String cate = cursor.getString(3);
            String type = cursor.getString(4);
            String note = cursor.getString(5);

            Money money = new Money(id,amount,date,cate,type,note);
            moneys.add(money);
        }
        return moneys;
    }
    //getByCategory
    public List<Money> getByType(String type) {
        String sql = "type like ?";
        String[] args = { "%" + type + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("money", null, sql, args, null, null, null);
        List<Money> moneys = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String amount = cursor.getString(1);
            String date = cursor.getString(2);
            String category = cursor.getString(3);
            String typ = cursor.getString(4);
            String note = cursor.getString(5);

            Money money = new Money(id,amount,date,category,typ,note);
            moneys.add(money);
        }
        return moneys;
    }
    public float sumType(String type){
        float total = 0;
        String sql = "type like ?";
        String[] args = { "%" + type + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("money", null, sql, args, null, null, null);
        List<Money> moneys = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String amount = cursor.getString(1);
            total += Float.parseFloat(amount);
        }
        return total;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
