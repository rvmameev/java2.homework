package ru.geekbrains.racing.participant;

public interface Participantable extends Runable, Jumpable, Swimable
{
    boolean isOnDistance();
    void info();
}
