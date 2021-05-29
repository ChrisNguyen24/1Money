package com.example.sqlitedemo.model;

import java.io.Serializable;

/**
 * Created by congnguyen on 5/28/21.
 */
public class Money implements Serializable {
    private int id;
    private String amount, date,category,type,note;

    public Money() {
    }

    public Money(int id, String amount, String date, String category, String type, String note) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.type = type;
        this.note = note;
    }

    public Money(String amount, String date, String category, String type, String note) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.type = type;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Money{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
