package ru.geekbrains.java2.homework.lesson3;

import java.util.*;

public class Phonebook
{
    private Map<String, Set<String>> map;

    public Phonebook()
    {
        map = new LinkedHashMap<>();
    }

    public void add(String surname, String phoneNumber)
    {
        if (surname == null || surname.trim().isEmpty())
            throw new IllegalArgumentException("surname is null or empty");

        if (phoneNumber == null || phoneNumber.trim().isEmpty())
            throw new IllegalArgumentException("phoneNumber is null or empty");

        Set<String> phones = map.get(surname);

        if (phones == null)
        {
            phones = new LinkedHashSet<>(Arrays.asList(phoneNumber));

            map.put(surname, phones);
        } else
        {
            phones.add(phoneNumber);
        }
    }

    public Set<String> get(String surname)
    {
        return map.get(surname);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (String surname : map.keySet())
        {
            sb.append(surname + ": ");
            sb.append(map.get(surname));
            sb.append(System.lineSeparator());
        }

        if (!map.isEmpty())
            sb.setLength(sb.length() - 1);

        return sb.toString();
    }
}
