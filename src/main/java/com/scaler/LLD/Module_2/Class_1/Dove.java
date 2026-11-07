package com.scaler.LLD.Module_2.Class_1;

/**
 * DIP: Dove depends on the Flyer abstraction, not on LowFlyingBird / HighFlyingBird.
 * DI:  the concrete Flyer is injected through the constructor by the client.
 */
public class Dove extends Bird implements Flyable {
    private final Flyer flyingBird;

    public Dove(String name, int age, String color, Flyer flyer) {
        super(name, age, color);
        this.flyingBird = flyer;
    }

    @Override
    public void fly() {
        flyingBird.doFlying();
        System.out.println("Dove is Flying");
    }
}
