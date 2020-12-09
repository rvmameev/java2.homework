package ru.geekbrains.java2.homework.lesson5;

import java.util.Arrays;

public class App
{
    private static final int ARRAY_SIZE = 10000000;
    private static final int THREAD_COUNT = 2;

    public static void main(String[] args)
    {
        float[] array1 = getNewArray();
        float[] array2 = getNewArray();

        System.out.println("Вычисления в массиве. 1 поток:");
        long millis = passedMillis(() -> formulaOneThread(array1));
        System.out.println(millis + "ms");

        System.out.printf("Вычисления в массиве. Несколько потоков (%d):%n", THREAD_COUNT);
        millis = passedMillis(() -> formulaMultiThreads(array2, THREAD_COUNT));
        System.out.println(millis + "ms");

        System.out.println("Result arrays is equals: " + isArraysEquals(array1, array2));
    }

    private static long passedMillis(Runnable runnable)
    {
        long start = System.currentTimeMillis();

        runnable.run();

        return System.currentTimeMillis() - start;
    }

    private static float[] getNewArray()
    {
        float[] array = new float[ARRAY_SIZE];

        Arrays.fill(array, 1f);

        return array;
    }

    private static void formulaArray(float[] array, int offset)
    {
        int index;

        for (int i = 0; i < array.length; i++)
        {
            index = i + offset;

            array[i] = (float) (array[i] * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
        }
    }

    private static void formulaOneThread(float[] array)
    {
        formulaArray(array, 0);
    }

    private static void formulaMultiThreads(float[] array, int threadCount)
    {
        int packetSize = (array.length - 1) / threadCount + 1;

        Thread[] threads = new Thread[threadCount];
        float[][] packets = new float[threadCount][];

        long splitMillis = passedMillis(() ->
        {
            for (int i = 0; i < threadCount; i++)
            {
                int from = i * packetSize;
                int to = (i + 1) * packetSize;

                if (to > array.length)
                    to = array.length;

                float[] packet = Arrays.copyOfRange(array, from, to);
                packets[i] = packet;

                Thread thread = new Thread(() -> formulaArray(packet, from));
                threads[i] = thread;
            }
        });

        System.out.println("Разделение массива + создание потоков: " + splitMillis);

        startThreads(threads);

        waitThreads(threads);

        long joinMillis = passedMillis(() ->
        {
            for (int i = 0; i < threadCount; i++)
            {
                System.arraycopy(packets[i], 0, array, i * packetSize, packets[i].length);
            }
        });

        System.out.println("Склейка массивов: " + joinMillis);
    }

    private static boolean isArraysEquals(float[] array1, float[] array2)
    {
        for (int i = 0; i < array1.length; i++)
        {
            if (array1[i] != array2[i])
                return false;
        }

        return true;
    }

    private static void startThreads(Thread[] threads)
    {
        for (int i = 0; i < threads.length; i++)
        {
            threads[i].start();
        }
    }

    private static void waitThreads(Thread[] threads)
    {
        try
        {
            for (int i = 0; i < threads.length; i++)
            {
                threads[i].join();
            }

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
