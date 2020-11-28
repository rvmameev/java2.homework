package ru.geekbrains.java2.homework.lesson2.exceptions;

public class MyArraySizeException extends Exception
{
    private final int rowCount;

    private final int columnCount;

    public MyArraySizeException(int rowCount, int columnCount)
    {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public int getRowCount()
    {
        return rowCount;
    }

    public int getColumnCount()
    {
        return columnCount;
    }
}
