import java.util.Random;

public class Game {
    private final int SIZE = 4;
    private int[][] grid;
    private Random random;
    private int score;

    public Game() {
        grid = new int[SIZE][SIZE];
        random = new Random();
        score = 0; // Initialize score
        addRandomTile();
        addRandomTile();
    }

    private void addRandomTile() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (grid[x][y] != 0); // Find an empty spot
        grid[x][y] = random.nextBoolean() ? 2 : 4; // 2 or 4
    }

    public void slideLeft() {
        boolean moved = false; // Track if a move has occurred

        for (int i = 0; i < SIZE; i++) {
            // Collect non-zero tiles
            int[] newRow = new int[SIZE];
            int index = 0;

            // Move all tiles to the left
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] != 0) {
                    newRow[index++] = grid[i][j];
                }
            }

            // Merge tiles
            for (int j = 0; j < index - 1; j++) {
                if (newRow[j] == newRow[j + 1]) {
                    newRow[j] *= 2; // Merge tiles
                    score += newRow[j]; // Update score
                    newRow[j + 1] = 0; // Clear the merged tile
                    moved = true; // A move occurred
                    j++; // Skip the next tile
                }
            }

            // Refill the grid row
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] != newRow[j]) {
                    moved = true; // A move occurred
                }
                grid[i][j] = newRow[j];
            }
        }

        if (moved) {
            addRandomTile();
        }

        // Check for game over after the move
        if (isGameOver()) {
            System.out.println("Game Over! Your score: " + score);
            System.exit(0); // Close the application
        }
    }

    public void slideRight() {
        boolean moved = false;

        for (int i = 0; i < SIZE; i++) {
            // Create a temporary array for the row
            int[] newRow = new int[SIZE];
            int index = SIZE - 1; // Start from the end

            // Move all tiles to the right
            for (int j = SIZE - 1; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    newRow[index--] = grid[i][j]; // Move non-zero tiles to the right
                }
            }

            // Merge tiles
            for (int j = SIZE - 1; j > 0; j--) {
                if (newRow[j] == newRow[j - 1]) {
                    newRow[j] *= 2; // Merge tiles
                    score += newRow[j]; // Update score
                    newRow[j - 1] = 0; // Clear the merged tile
                    j--; // Skip the next tile as it's merged
                }
            }

            // Refill the grid row
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] != newRow[j]) {
                    moved = true; // A move occurred
                }
                grid[i][j] = newRow[j]; // Update the original grid
            }
        }

        if (moved) {
            addRandomTile();
        }

        if (isGameOver()) {
            System.out.println("Game Over! Your score: " + score);
            System.exit(0); // Close the application
        }
    }

    public void slideUp() {
        boolean moved = false;

        for (int j = 0; j < SIZE; j++) {
            // Create a temporary array for the column
            int[] newCol = new int[SIZE];
            int index = 0;

            // Move all tiles up
            for (int i = 0; i < SIZE; i++) {
                if (grid[i][j] != 0) {
                    newCol[index++] = grid[i][j]; // Move non-zero tiles
                }
            }

            // Merge tiles
            for (int i = 0; i < index - 1; i++) {
                if (newCol[i] == newCol[i + 1]) {
                    newCol[i] *= 2; // Merge tiles
                    score += newCol[i]; // Update score
                    newCol[i + 1] = 0; // Clear the merged tile
                    i++; // Skip the next tile as it's merged
                }
            }

            // Refill the grid column
            for (int i = 0; i < SIZE; i++) {
                if (grid[i][j] != newCol[i]) {
                    moved = true; // A move occurred
                }
                grid[i][j] = newCol[i]; // Update the original grid
            }
        }

        if (moved) {
            addRandomTile();
        }

        if (isGameOver()) {
            System.out.println("Game Over! Your score: " + score);
            System.exit(0); // Close the application
        }
    }


    public void slideDown() {
        boolean moved = false;

        for (int j = 0; j < SIZE; j++) {
            // Create a temporary array for the column
            int[] newCol = new int[SIZE];
            int index = SIZE - 1; // Start from the bottom

            // Move all tiles down
            for (int i = SIZE - 1; i >= 0; i--) {
                if (grid[i][j] != 0) {
                    newCol[index--] = grid[i][j]; // Move non-zero tiles to the bottom
                }
            }

            // Merge tiles
            for (int i = SIZE - 1; i > 0; i--) {
                if (newCol[i] == newCol[i - 1]) {
                    newCol[i] *= 2; // Merge tiles
                    score += newCol[i]; // Update score
                    newCol[i - 1] = 0; // Clear the merged tile
                    i--; // Skip the next tile as it's merged
                }
            }

            // Refill the grid column
            for (int i = 0; i < SIZE; i++) {
                if (grid[i][j] != newCol[i]) {
                    moved = true; // A move occurred
                }
                grid[i][j] = newCol[i]; // Update the original grid
            }
        }

        if (moved) {
            addRandomTile();
        }

        if (isGameOver()) {
            System.out.println("Game Over! Your score: " + score);
            System.exit(0); // Close the application
        }
    }

    private void rotateGrid() {
        int[][] newGrid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newGrid[j][SIZE - 1 - i] = grid[i][j];
            }
        }
        grid = newGrid;
    }

    private boolean isGameOver() {
        // Check for empty tiles
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    return false; // There's at least one empty tile
                }
            }
        }

        // Check for adjacent merges
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i < SIZE - 1 && grid[i][j] == grid[i + 1][j]) {
                    return false; // Vertical merge possible
                }
                if (j < SIZE - 1 && grid[i][j] == grid[i][j + 1]) {
                    return false; // Horizontal merge possible
                }
            }
        }

        return true; // No moves left
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getScore() {
        return score;
    }
}
