import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MazeGenerator {
    private final int rows, cols; // Number of rows and columns in the maze
    private final Cell[][] maze;  // 2D array representing the maze

    // Constructor to initialize the maze with specified rows and columns
    public MazeGenerator(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        maze = new Cell[rows][cols]; // Create the 2D array for the maze
        initializeMaze(); // Initialize each cell in the maze
        generateMaze(); // Generate the maze using Depth First Search
    }

    // Initialize the maze with cells
    private void initializeMaze()
    {
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                maze[row][col] = new Cell(row, col); // Create a new cell for each position
            }
        }
    }

    // Generate the maze using Depth First Search algorithm
    private void generateMaze()
    {
        Stack<Cell> stack = new Stack<>(); // Stack to hold cells during the maze generation
        Cell start = maze[0][0]; // Start from the top-left corner
        start.visited = true; // Mark the starting cell as visited
        stack.push(start); // Push the starting cell onto the stack

        // Continue until there are no more cells in the stack
        while (!stack.isEmpty())
        {
            Cell current = stack.peek(); // Look at the top cell in the stack
            Cell next = getUnvisitedNeighbor(current); // Get an unvisited neighbor

            if (next != null)
            {
                next.visited = true; // Mark the neighbor as visited
                stack.push(next); // Push the neighbor onto the stack
                removeWalls(current, next); // Remove walls between the current cell and the neighbor
            }
            else
            {
                stack.pop(); // If there are no unvisited neighbors, pop the current cell off the stack
            }
        }
    }

    // Get an unvisited neighbor of the given cell
    private Cell getUnvisitedNeighbor(Cell cell)
    {
        List<Cell> neighbors = new ArrayList<>(); // List to hold unvisited neighbors

        // Check each possible neighbor (top, bottom, left, right)
        if (cell.row > 0 && !maze[cell.row - 1][cell.col].visited) {
            neighbors.add(maze[cell.row - 1][cell.col]);
        }
        if (cell.row < rows - 1 && !maze[cell.row + 1][cell.col].visited) {
            neighbors.add(maze[cell.row + 1][cell.col]);
        }
        if (cell.col > 0 && !maze[cell.row][cell.col - 1].visited) {
            neighbors.add(maze[cell.row][cell.col - 1]);
        }
        if (cell.col < cols - 1 && !maze[cell.row][cell.col + 1].visited) {
            neighbors.add(maze[cell.row][cell.col + 1]);
        }

        // Randomly select one unvisited neighbor
        if (!neighbors.isEmpty())
        {
            Collections.shuffle(neighbors); // Shuffle to randomize selection
            return neighbors.get(0); // Return the first neighbor
        }
        return null; // Return null if there are no unvisited neighbors
    }

    // Remove walls between the current cell and the next cell
    private void removeWalls(Cell current, Cell next)
    {
        int dx = next.col - current.col; // Difference in columns
        int dy = next.row - current.row; // Difference in rows

        // Determine which walls to remove based on the direction
        if (dx == 1)
        {
            current.walls[1] = false; // Remove right wall of current cell
            next.walls[3] = false; // Remove left wall of next cell
        }
        else if (dx == -1)
        {
            current.walls[3] = false; // Remove left wall of current cell
            next.walls[1] = false; // Remove right wall of next cell
        }
        else if (dy == 1)
        {
            current.walls[2] = false; // Remove bottom wall of current cell
            next.walls[0] = false; // Remove top wall of next cell
        }
        else if (dy == -1)
        {
            current.walls[0] = false; // Remove top wall of current cell
            next.walls[2] = false; // Remove bottom wall of next cell
        }
    }

    // Get the generated maze
    public Cell[][] getMaze() {
        return maze;
    }

    // Inner class representing a cell in the maze
    public static class Cell
    {
        public int row, col; // Position of the cell
        public boolean[] walls = {true, true, true, true}; // Walls (top, right, bottom, left)
        public boolean visited = false; // Whether the cell has been visited

        // Constructor to initialize the cell with its position
        public Cell(int row, int col)
        {
            this.row = row;
            this.col = col;
        }
    }
}
