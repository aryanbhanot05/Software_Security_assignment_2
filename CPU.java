// CPU types allowed in the system.
// Uses uppercase names because enum constants cannot start with digits.

public enum CPU {
    I5("i5"),
    I7("i7");

    private final String label;

    // Constructor
    CPU(String label) {
        this.label = label;
    }

    // Getter for display
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
