package com.scaler.LLD.Module_2.Class_1;

public class Pigeon extends Bird implements Flyable{
    public Pigeon(String name, int age, String color) {
        super(name, age, color);
    }

    @Override
    public void fly() {
        System.out.println("Pigeon is Flying");
    }
}
