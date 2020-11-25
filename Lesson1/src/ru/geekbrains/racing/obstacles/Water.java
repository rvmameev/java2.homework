package ru.geekbrains.racing.obstacles;

import ru.geekbrains.racing.participants.Animal;
import ru.geekbrains.racing.participants.Swimable;

public class Water extends Obstacle {
    private int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Animal a) {
        if (a instanceof Swimable) {
            ((Swimable) a).swim(length);
        } else {
            System.out.println(a.getColor().getEnglishColorName() + " " + a.getType() + " " + a.getName() + " - can't swim");
            a.setOnDistance(false);
        }
    }
}
