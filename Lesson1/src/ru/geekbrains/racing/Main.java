package ru.geekbrains.racing;

import ru.geekbrains.racing.obstacles.*;
import ru.geekbrains.racing.participants.*;

public class Main
{
    public static void main(String[] args)
    {
        Participantable[] participants = {
            new Cat("Barsik", Color.RED),
            new Dog("Bobik", Color.BLACK),
            new Duck("Scrooge", Color.WHITE),
            new Robot("T1000")
        };

        Team team = new Team("Moon", participants);
        team.info();

        Obstaclable[] obstacles = {
            new Cross(100),
            new Wall(10),
            new Water(15)
        };

        Course course = new Course(obstacles);

        course.doIt(team);

        team.infoResult();
    }
}
