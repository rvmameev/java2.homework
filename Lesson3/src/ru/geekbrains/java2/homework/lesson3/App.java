package ru.geekbrains.java2.homework.lesson3;

import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        // 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список
        // уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается
        // каждое слово.
        demoWordCounter();

        // 2. Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
        // В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать
        // номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
        // (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
        // Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
        // взаимодействие с пользователем через консоль и т.д). Консоль использовать только для вывода результатов
        // проверки телефонного справочника.
        demoPhonebook();
    }

    private static void demoWordCounter()
    {
        String[] words = {
            "ba", "a", "ab", "ab", "abc", "a", "ac", "a", "abc", "ab", "ac", "b", "ac",
            "ac", "ba", "abc", "b", "ac", "a", "ac", "a", "abc", "ab", "ac", "b", "ac",
        };

        System.out.println("Подсчитать количества различных слов в массиве:");
        System.out.println(Arrays.toString(words));

        HashMap<String, Integer> map = new LinkedHashMap<>();

        for (String word : words)
        {
            Integer wordCount = map.get(word);

            if (wordCount == null)
                map.put(word, 1);
            else
                map.put(word, wordCount + 1);
        }

        System.out.println(map);
    }

    private static void demoPhonebook()
    {
        Phonebook phonebook = new Phonebook();

        System.out.println("Телефонный справочник:");

        phonebook.add("Иванов", "12345");
        phonebook.add("Петров", "12345");
        phonebook.add("Иванов", "12345");
        phonebook.add("Иванов", "1234581");
        phonebook.add("Смирнов", "456");
        phonebook.add("Смирнов", "123");

        System.out.println(phonebook);

        System.out.println("Телефонные номера Иванова: " + phonebook.get("Иванов"));
    }
}
