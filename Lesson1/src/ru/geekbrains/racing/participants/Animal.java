package ru.geekbrains.racing.participants;

public abstract class Animal {
    String type;
    String name;
    Color color;

    int maxRunDistance;
    int maxJumpHeight;

    boolean onDistance;

    public boolean isOnDistance() {
        return onDistance;
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

    public void setOnDistance(boolean onDistance) {
        this.onDistance = onDistance;
    }

    public Animal(String type, String name, Color color, int maxRunDistance, int maxJumpHeight) {
        this.type = type;
        this.name = name;
        this.color = color;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.onDistance = true;
    }

    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(color.getEnglishColorName() + " " + type + " " + name + " - Run OK");
        } else {
            System.out.println(color.getEnglishColorName() + " " + type + " " + name + " - Run FAILED");
            onDistance = false;
        }
    }

    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(color.getEnglishColorName() + " " + type + " " + name + " - Jump OK");
        } else {
            System.out.println(color.getEnglishColorName() + " " + type + " " + name + " - Jump FAILED");
            onDistance = false;
        }
    }

    public void info() {
        System.out.println(color.getEnglishColorName() + " " + type + " " + name + ": " + onDistance);
    }
}
