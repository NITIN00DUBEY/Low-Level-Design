package com.scaler.LLD.Module_2.Class_1;

/**
 * SOLID with Birds -- final design from the class.
 * See SolidViolationsDemo for the "before" versions (brute force Bird, Rectangle/Square).
 */
public class Client {
    static void makeBirdFly(Flyable b) {
        System.out.println("Starting to make bird Fly");
        b.fly();
    }

    static void makeBirdDance(Danceable d) {
        System.out.println("Starting to make bird Dance");
        d.dance();
    }

    public static void main(String[] args) {
        System.out.println("=== LSP: every Flyable can be substituted safely ===");
        Pigeon pigeon = new Pigeon("Pidgey", 5, "Grey");
        makeBirdFly(pigeon);

        Penguin penguin = new Penguin("Skipper", 4, "Black");
        // makeBirdFly(penguin);   // does not compile -- Penguin is not Flyable, so it can't break fly() callers
        makeBirdDance(penguin);

        System.out.println("\n=== Common behaviour stays in the parent ===");
        pigeon.eat();
        penguin.eat();

        System.out.println("\n=== DIP + DI: same Dove class, behaviour injected by the client ===");
        Dove lowDove = new Dove("Polly", 10, "White", new LowFlyingBird());
        makeBirdFly(lowDove);

        Dove highDove = new Dove("Polly (after RedBull)", 10, "White", new HighFlyingBird());
        makeBirdFly(highDove);
    }
}
