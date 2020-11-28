package ru.geekbrains.java2.homework.lesson2;

import java.util.*;
import ru.geekbrains.java2.homework.lesson2.exceptions.*;

public class App
{
    public static void main(String[] args)
    {
        HashMap<String, String[][]> demoMap = new LinkedHashMap<>();
        demoMap.put("Bad size array", getBadSizeArray());
        demoMap.put("Good size bad data array", getGoodSizeBadDataArray());
        demoMap.put("Good array", getGoodArray());

        for (String message : demoMap.keySet())
        {
            demoSumArrayAggregator(message, demoMap.get(message));
        }
    }

    private static void demoSumArrayAggregator(String message, String[][] array)
    {
        System.out.println(message + ":");

        System.out.println(Arrays.deepToString(array));

        try
        {
            ArrayAggregator aggregator = new ArrayAggregator(array);

            int sum = aggregator.sum();

            System.out.println("array sum = " + sum);
        }
        catch (MyArraySizeException e)
        {
            System.out.printf("Bad array size (must be %d). rowCount = %d. columnCount = %d.%n",
                ArrayAggregator.SIZE_ARRAY, e.getRowCount(), e.getColumnCount());
        }
        catch (MyArrayDataException e)
        {
            System.out.printf("Bad array data. array[%d][%d] = \"%s\".%n", e.getRow(), e.getCol(), e.getData());
        }
    }

    private static String[][] getBadSizeArray()
    {
        return new String[][] {
            { "1", "a", "b" },
            { "2"}
        };
    }

    private static String[][] getGoodSizeBadDataArray()
    {
        return new String[][] {
            { "1", "2", "3", "4" },
//            null,
            { "1", "2", "s", "4" },
            { "1", "2", "3", "4" },
            { "1", "2", "3", "4" },
        };
    }

    private static String[][] getGoodArray()
    {
        return new String[][] {
            { "1", "2", "3", "4" },
            { "1", "2", "3", "4" },
            { "1", "2", "3", "4" },
            { "1", "2", "3", "4" },
        };
    }
}
