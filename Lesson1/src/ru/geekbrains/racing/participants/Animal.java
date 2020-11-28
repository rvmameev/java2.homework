package ru.geekbrains.racing.participants;

public abstract class Animal implements Participantable {
    private String type;
    private String name;
    private Color color;

    private int maxRunDistance;
    private int maxJumpHeight;
    private int maxSwimDistance;
    private boolean onDistance;

    public Animal(String type, String name, Color color, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.type = type;
        this.name = name;
        this.color = color;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }


    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(toString() + " - Run OK");
        } else {
            System.out.println(toString() + " - Run FAILED");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(toString() + " - Jump OK");
        } else {
            System.out.println(toString() + " - Jump FAILED");
            onDistance = false;
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= maxSwimDistance) {
            System.out.println(toString() + " - Swim OK");
        } else {
            System.out.println(toString() + " - Swim FAILED");
            onDistance = false;
        }
    }

    @Override
    public void info() {
        System.out.println(color.getEnglishColorName() + " " + type + " " + name + ": " + onDistance);
    }

    @Override
    public String toString()
    {
        return color.getEnglishColorName() + " " + type + " " + name;
    }
}
