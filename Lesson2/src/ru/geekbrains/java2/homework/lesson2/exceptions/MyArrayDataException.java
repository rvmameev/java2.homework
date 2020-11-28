package ru.geekbrains.java2.homework.lesson2.exceptions;

public class MyArrayDataException extends Exception
{

    private final int row;
    private final int col;
    private final Object data;

    public MyArrayDataException(int row, int col, Object data)
    {
        this.row = row;
        this.col = col;
        this.data = data;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Object getData()
    {
        return data;
    }
}
