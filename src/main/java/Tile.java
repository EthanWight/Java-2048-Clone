public class Tile {
    private int value;

    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void merge() {
        value *= 2; // Merge logic (doubling the value)
    }

    public boolean isEmpty() {
        return value == 0;
    }
}
