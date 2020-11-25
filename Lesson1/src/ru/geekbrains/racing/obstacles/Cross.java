package ru.geekbrains.racing.obstacles;

import ru.geekbrains.racing.participant.Participantable;

public class Cross implements Obstaclable
{
    private int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Participantable participant) {
        participant.run(length);
    }
}
