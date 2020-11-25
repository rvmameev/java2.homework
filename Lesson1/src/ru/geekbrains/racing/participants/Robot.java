package ru.geekbrains.racing.participants;

public class Robot implements Participantable
{
    private String name;
    private int maxRunDistance;
    private int maxJumpHeight;
    private int maxSwimDistance;
    private boolean onDistance;

    public Robot(String name)
    {
        this.name = name;
        this.maxRunDistance = 1000;
        this.maxJumpHeight = 20;
        this.maxSwimDistance = 100;
        this.onDistance = true;
    }

    @Override
    public boolean isOnDistance()
    {
        return onDistance;
    }

    @Override
    public void info()
    {
        System.out.println("Robot " + name + ": " + onDistance);
    }

    @Override
    public void jump(int height)
    {
        if (height <= maxJumpHeight)
        {
            System.out.println(toString() + " - Jump OK");
        } else
        {
            System.out.println(toString() + " - Jump FAILED");
            onDistance = false;
        }
    }

    @Override
    public void run(int distance)
    {
        if (distance <= maxRunDistance)
        {
            System.out.println(toString() + " - Run OK");
        } else
        {
            System.out.println(toString() + " - Run FAILED");
            onDistance = false;
        }
    }

    @Override
    public void swim(int distance)
    {
        if (distance <= maxSwimDistance)
        {
            System.out.println(toString() + " - Swim OK");
        } else
        {
            System.out.println(toString() + " - Swim FAILED");
            onDistance = false;
        }
    }

    @Override
    public String toString()
    {
        return "Robot " + name;
    }
}
