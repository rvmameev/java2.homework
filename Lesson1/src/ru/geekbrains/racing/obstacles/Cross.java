package ru.geekbrains.racing.obstacles;

import ru.geekbrains.racing.participants.Animal;

public class Cross extends Obstacle {
    private int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Animal a) {
        a.run(length);
    }
}
