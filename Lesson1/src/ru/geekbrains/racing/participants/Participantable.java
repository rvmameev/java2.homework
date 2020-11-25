package ru.geekbrains.racing.participants;

public interface Participantable extends Runable, Jumpable, Swimable
{
    boolean isOnDistance();
    void info();
}
