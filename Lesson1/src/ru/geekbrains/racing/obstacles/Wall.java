package ru.geekbrains.racing.obstacles;

import ru.geekbrains.racing.participant.Animal;
import ru.geekbrains.racing.participant.Participantable;

public class Wall implements Obstaclable
{
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Participantable participant) {
        participant.jump(height);
    }
}
