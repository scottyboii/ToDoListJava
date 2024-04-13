package com.example.todolistjava;

import androidx.room.ColumnInfo;

public class DateColour {

    @ColumnInfo(name="DueDate")
    private String dueDate;
    @ColumnInfo(name="Colour")
    private String colour;

    public DateColour(String dueDate, String colour) {
        this.dueDate = dueDate;
        this.colour = colour;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "DateColour{" +
                "dueDate='" + dueDate + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
