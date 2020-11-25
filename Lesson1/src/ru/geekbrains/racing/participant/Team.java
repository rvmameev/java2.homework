package ru.geekbrains.racing.participant;

public class Team
{
    private String name;
    private Participantable[] participants;

    public Team(String name, Participantable[] participants)
    {
        this.name = name;
        this.participants = participants;
    }

    public String getName()
    {
        return name;
    }

    public Participantable[] getParticipants()
    {
        return participants;
    }

    public void info()
    {
        System.out.println("TEAM " + name + ":");

        for (Participantable participant : participants)
        {
            System.out.println(participant);
        }
    }

    public void infoResult()
    {
        System.out.println("RESULTS:");

        for (Participantable p : participants)
        {
            p.info();
        }
    }
}
