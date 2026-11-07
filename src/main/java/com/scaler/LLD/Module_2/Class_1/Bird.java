package com.scaler.LLD.Module_2.Class_1;

/**
 * Base class holds only what EVERY bird has (state + eat()).
 * Optional behaviours (fly, dance) live in interfaces -- see Flyable, Danceable (LSP + ISP).
 */
public abstract class Bird {
    private final String name;
    private final int age;
    private final String color;

    public Bird(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public void eat() {
        System.out.println(name + " is Eating");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }
}
