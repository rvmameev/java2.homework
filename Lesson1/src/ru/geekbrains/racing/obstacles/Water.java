package ru.geekbrains.racing.obstacles;

import ru.geekbrains.racing.participants.Participantable;

public class Water implements Obstaclable
{
    private int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Participantable participant) {
        participant.swim(length);
    }
}
