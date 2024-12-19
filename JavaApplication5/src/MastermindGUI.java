import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MastermindGUI extends Application {
    private Mastermind game;
    private TextField[] guessFields;
    private Label feedbackLabel;

    @Override
    public void start(Stage primaryStage) {
        game = new Mastermind();
        guessFields = new TextField[4];
        feedbackLabel = new Label();

        VBox root = new VBox(10);
        HBox guessBox = new HBox(5);

        for (int i = 0; i < 4; i++) {
            guessFields[i] = new TextField();
            guessFields[i].setPrefWidth(50);
            guessBox.getChildren().add(guessFields[i]);
        }

        Button guessButton = new Button("Guess");
        guessButton.setOnAction(e -> handleGuess());

        root.getChildren().addAll(guessBox, guessButton, feedbackLabel);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Mastermind Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleGuess() {
        int[] guess = new int[4];
        try {
            for (int i = 0; i < 4; i++) {
                guess[i] = Integer.parseInt(guessFields[i].getText());
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter valid numbers (1-6).");
            return;
        }

        int[] feedback = game.checkGuess(guess);
        feedbackLabel.setText("Correct Positions: " + feedback[0] + ", Correct Numbers: " + feedback[1]);

        if (game.isCorrectGuess(guess)) {
            feedbackLabel.setText("Congratulations! You've guessed the code.");
        } else if (game.getAttempts() >= 10) {
            feedbackLabel.setText("Game Over! You've used all attempts.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}