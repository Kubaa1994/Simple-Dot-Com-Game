
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleDotComGame {
    public static void main(String[] args) {
        // Create and initialize the game
        GameHelper helper = new GameHelper();
        SimpleDotComGame game = new SimpleDotComGame();
        game.setUpGame();

        // Start playing the game
        int numOfGuesses = 0;
        boolean isGameWon = false;

        while (!isGameWon) {
            String userGuess = helper.getUserInput("Enter a guess: ");
            String result = game.checkUserGuess(userGuess);
            System.out.println(result);

            numOfGuesses++;

            if (result.equals("Kill")) {
                isGameWon = true;
                System.out.println("Congratulations! You sank the SimpleDotCom in " + numOfGuesses + " guesses.");
            }
        }
    }

    // Example methods for setup, checking guesses, and finishing the game
    private SimpleDotCom dotCom;

    private void setUpGame() {
        // Initialize a SimpleDotCom object
        dotCom = new SimpleDotCom();

        // Set the initial locations for the SimpleDotCom
        dotCom.setLocationCells(new int[] { 2, 3, 4 });
    }

    private String checkUserGuess(String userGuess) {
        // Check the user's guess against the SimpleDotCom
        String result = dotCom.checkYourself(Integer.parseInt(userGuess));
        return result;
    }
}

class SimpleDotCom {
    private int[] locationCells;

    public void setLocationCells(int[] locations) {
        locationCells = locations;
    }

    public String checkYourself(int userInput) {
        String result = "Miss";

        for (int cell : locationCells) {
            if (userInput == cell) {
                result = "Hit";
                break;
            }
        }

        if (result.equals("Hit")) {
            int index = -1;
            for (int i = 0; i < locationCells.length; i++) {
                if (userInput == locationCells[i]) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                System.arraycopy(locationCells, index + 1, locationCells, index, locationCells.length - index - 1);
                locationCells[locationCells.length - 1] = 0;
                if (locationCells[0] == 0) {
                    result = "Kill";
                }
            }
        }

        return result;
    }
}

class GameHelper {
    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) {
                return null;
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }
}
