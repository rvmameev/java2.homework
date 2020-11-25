package ru.geekbrains.racing.obstacles;

import ru.geekbrains.racing.participant.Participantable;
import ru.geekbrains.racing.participant.Team;

public class Course
{
    private Obstaclable[] obstacles;

    public Course(Obstaclable[] obstacles)
    {
        this.obstacles = obstacles;
    }

    public void doIt(Team team)
    {
        System.out.println("START " + team.getName() + ":");

        for (Participantable p : team.getParticipants())
        {
            for (Obstaclable o : obstacles)
            {
                o.doIt(p);

                if (!p.isOnDistance())
                {
                    break;
                }
            }
        }
    }
}
