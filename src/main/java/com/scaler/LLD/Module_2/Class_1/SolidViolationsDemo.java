package com.scaler.LLD.Module_2.Class_1;

/**
 * The "before" code from the class -- runnable so students can see the problems.
 *  1. Brute force Bird: one class, if-else on type (violates SRP + OCP)
 *  2. Quiz 3: Rectangle / Square (violates LSP)
 */
public class SolidViolationsDemo {

    /** Every new bird = another else-if in this class -> SRP + OCP violation. */
    static class BruteForceBird {
        private final String name;
        private final String type;

        BruteForceBird(String name, String type) {
            this.name = name;
            this.type = type;
        }

        void fly() {
            if (type.equalsIgnoreCase("Pigeon")) {
                System.out.println("  " + name + ": Pigeon is Flying");
            } else if (type.equalsIgnoreCase("Dove")) {
                System.out.println("  " + name + ": Dove is Flying");
            } else if (type.equalsIgnoreCase("Crow")) {
                System.out.println("  " + name + ": Crow is Flying");
            } else {
                // Penguin falls through silently -- callers can't tell it "flew" wrongly
                System.out.println("  " + name + ": ??? (no branch for type '" + type + "')");
            }
        }
    }

    static class Rectangle {
        protected int width, height;
        public void setWidth(int w) { width = w; }
        public void setHeight(int h) { height = h; }
        public int getArea() { return width * height; }
    }

    /** Quiz 3: both setters keep width == height. */
    static class Square extends Rectangle {
        @Override public void setWidth(int w)  { width = w; height = w; }
        @Override public void setHeight(int h) { width = h; height = h; }
    }

    /** Written against Rectangle's contract: width and height are independent. */
    static int resizeAndGetArea(Rectangle r) {
        r.setWidth(5);
        r.setHeight(10);
        return r.getArea();
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Brute force Bird (SRP + OCP violation) ===");
        new BruteForceBird("Pidgey", "Pigeon").fly();
        new BruteForceBird("Polly", "Dove").fly();
        new BruteForceBird("Skipper", "Penguin").fly();

        System.out.println("\n=== 2. Rectangle / Square (LSP violation) ===");
        System.out.println("  Rectangle : area = " + resizeAndGetArea(new Rectangle()) + "  (expected 50)");
        System.out.println("  Square    : area = " + resizeAndGetArea(new Square())
                + " (setHeight(10) also set width to 10 -> caller's expectation of 50 breaks)");

        // Same caller logic, other order: the result changes again
        Rectangle r = new Square();
        r.setHeight(10);
        r.setWidth(5);
        System.out.println("  Square, setHeight(10) then setWidth(5): area = " + r.getArea() + " (expected 50)");
    }
}
