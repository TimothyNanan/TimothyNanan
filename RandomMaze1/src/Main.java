import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application
{
    private MazeCanvas mazeCanvas; // Canvas to display the maze
    private MazeGenerator mazeGenerator; // Maze generator

    // Entry point for the JavaFX application
    @Override
    public void start(Stage primaryStage)
    {
        mazeCanvas = new MazeCanvas(); // Initialize the maze canvas
        BorderPane root = new BorderPane(); // Create the main layout
        VBox bottomBox = new VBox(); // Create a VBox for the button

        // Create a button to generate a new maze
        Button generateButton = new Button("Generate Maze");
        generateButton.setOnAction(e -> generateMaze()); // Set button action to generate maze

        bottomBox.getChildren().add(generateButton); // Add button to the VBox
        root.setCenter(mazeCanvas); // Set the maze canvas in the center of the layout
        root.setBottom(bottomBox); // Set the VBox with button at the bottom

        primaryStage.setTitle("Maze Generator"); // Set the window title
        primaryStage.setScene(new Scene(root, 600, 650)); // Set the scene with root layout
        primaryStage.show(); // Show the window

        generateMaze(); // Generate the initial maze
    }

    // Generate a new maze and draw it on the canvas
    private void generateMaze()
    {
        mazeGenerator = new MazeGenerator(30, 30); // Create a new maze generator
        mazeCanvas.drawMaze(mazeGenerator.getMaze()); // Draw the generated maze on the canvas
    }

    // Main method to launch the application
    public static void main(String[] args)
    {
        launch(args); // Launch the JavaFX application
    }
}

