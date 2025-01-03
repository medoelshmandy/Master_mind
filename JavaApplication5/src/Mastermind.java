import java.util.Random;

public class Mastermind {
    private final int[] secretCode;
    private int attempts;

    public Mastermind() {
        secretCode = new int[4];
        generateSecretCode();
        attempts = 0;
    }

    private void generateSecretCode() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            secretCode[i] = random.nextInt(6) + 1; // Numbers from 1 to 6
        }
    }

    public int[] checkGuess(int[] guess) {
        int correctPosition = 0;
        int correctNumber = 0;
        boolean[] secretUsed = new boolean[4];
        boolean[] guessUsed = new boolean[4];

        for (int i = 0; i < 4; i++) {
            if (guess[i] == secretCode[i]) {
                correctPosition++;
                secretUsed[i] = true;
                guessUsed[i] = true;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j && !secretUsed[i] && !guessUsed[j] && guess[j] == secretCode[i]) {
                    correctNumber++;
                    secretUsed[i] = true;
                    guessUsed[j] = true;
                    break;
                }
            }
        }

        attempts++;
        return new int[]{correctPosition, correctNumber};
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean isCorrectGuess(int[] guess) {
        return checkGuess(guess)[0] == 4;
    }
}