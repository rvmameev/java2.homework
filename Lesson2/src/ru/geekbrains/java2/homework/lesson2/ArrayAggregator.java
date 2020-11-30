package ru.geekbrains.java2.homework.lesson2;

import ru.geekbrains.java2.homework.lesson2.exceptions.*;

public class ArrayAggregator
{
    public static final int SIZE_ARRAY = 4;

    private final String[][] array;

    public ArrayAggregator(String[][] array) throws MyArraySizeException
    {
        this.array = array;

        for (String[] strings : array)
        {
            if (array.length != SIZE_ARRAY || strings == null || strings.length != SIZE_ARRAY)
                throw new MyArraySizeException(array.length, strings == null ? 0 : strings.length);
        }
    }

    public int sum() throws MyArrayDataException
    {
        int result = 0;

        for (int i = 0; i < SIZE_ARRAY; i++)
        {
            for (int j = 0; j < SIZE_ARRAY; j++)
            {
                try
                {
                    result += Integer.parseInt(array[i][j]);
                }
                catch (NumberFormatException e)
                {
                    throw new MyArrayDataException(i, j, array[i][j]);
                }
            }
        }

        return result;
    }
}
