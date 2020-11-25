package ru.geekbrains.racing.participants;

public class Duck extends Animal implements Swimable {

    private int maxSwimDistance;

    public Duck(String name, Color color) {
        super("Duck", name, color,100, 50);
        maxSwimDistance = TYPICAL_SWIM_DISTANCE * 10;
    }

    @Override
    public void swim(int distance) {
        if (distance <= maxSwimDistance) {
            System.out.println(color.getEnglishColorName() + " " + type + " " + name + " - Swim OK");
        } else {
            System.out.println(color.getEnglishColorName() + " " + type + " " + name + " - Swim FAILED");
            onDistance = false;
        }
    }
}
