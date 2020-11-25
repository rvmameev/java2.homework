package ru.geekbrains.racing.obstacles;

import ru.geekbrains.racing.participants.Animal;

public class Wall extends Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Animal a) {
        a.jump(height);
    }
}
