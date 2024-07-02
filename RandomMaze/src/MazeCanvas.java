import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MazeCanvas extends Canvas {
    private final int cellSize = 20; // Size of each cell in the maze
    private MazeGenerator.Cell[][] maze; // Reference to the maze

    // Constructor to set the size of the canvas
    public MazeCanvas() {
        setWidth(600); // Set canvas width
        setHeight(600); // Set canvas height
    }

    // Draw the maze on the canvas
    public void drawMaze(MazeGenerator.Cell[][] maze) {
        this.maze = maze; // Store the maze reference
        GraphicsContext gc = getGraphicsContext2D(); // Get the graphics context for drawing
        gc.clearRect(0, 0, getWidth(), getHeight()); // Clear the canvas
        gc.setStroke(Color.BLACK); // Set the drawing color to black

        // Loop through each cell in the maze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                drawCell(gc, maze[i][j]); // Draw each cell
            }
        }
    }

    // Draw a single cell on the canvas
    private void drawCell(GraphicsContext gc, MazeGenerator.Cell cell) {
        int x = cell.col * cellSize; // Calculate x position of the cell
        int y = cell.row * cellSize; // Calculate y position of the cell

        // Draw the walls of the cell if they exist
        if (cell.walls[0]) gc.strokeLine(x, y, x + cellSize, y); // Top wall
        if (cell.walls[1]) gc.strokeLine(x + cellSize, y, x + cellSize, y + cellSize); // Right wall
        if (cell.walls[2]) gc.strokeLine(x, y + cellSize, x + cellSize, y + cellSize); // Bottom wall
        if (cell.walls[3]) gc.strokeLine(x, y, x, y + cellSize); // Left wall
    }
}
