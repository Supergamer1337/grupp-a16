package exempel.lv1.rps;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 * The Rock, paper, scissor game.
 * See https://en.wikipedia.org/wiki/Rock%E2%80%93paper%E2%80%93scissors
 *
 * This is exercising smallest step programming (no methods needed)
 *
 * Rules:
 *
 *       -----------  Beats -------------
 *       |                              |
 *       V                              |
 *      Rock (1) --> Scissors (2) --> Paper (3)
 *
 */

public class RPS {

    public static void main(String[] args) {
        new RPS().program();
    }

    // final Random rand = new Random(); // From given code
    final Scanner input = new Scanner(in);

    void program() {
        enum InData {
            None,
            Rock,
            Paper,
            Scissor
        }
        int maxRounds = 5;
        InData human;          // Outcome for player
        InData computer;       // Outcome for computer
        int result;         // Result for this round
        int round = 0;      // Number of rounds
        int total = 0;      // Final result after all rounds

        // All code here ... (no method calls)
        out.println("Welcome to Rock, Paper and Scissors");
        while (round < maxRounds) {
            round++;
            out.printf("Now round %d\n", round);
            // Player input
            out.println("What do you pick:\n1. Rock\n2. Paper\n3. Scissors");
            human = InData.None;
            while (human == InData.None) {
                try {
                    int in_player = input.nextInt();
                    human = InData.values()[in_player];
                } catch (Exception e) {
                    out.println("Faulty input, please input 1-3 for Rock, Paper or Scissors");
                }
            }
            // Computer "input"
            computer = InData.values()[(int) (Math.random() * 3) + 1]; // Gives a number of 1 to 3
            // Output turn info and update total
            out.println("You picked: " + human.name() +"\nComputer picked: " + computer.name());
            if (human.ordinal() - 1 == computer.ordinal() || (human.ordinal() == 1 && computer.ordinal() == 3)) {
                out.printf("Human won round %d!\n", round);
                total++;
            } else if (computer.ordinal() - 1 == human.ordinal() || (computer.ordinal() == 1 && human.ordinal() == 3)) {
                out.printf("Computer won round %d!\n", round);
                total--;
            } else {
                out.println("It was a tie!");
            }
        }
        out.println("Game over! ");
        if (total == 0) {
            out.println("Draw");
        } else if (total > 0) {
            out.println("Human won.");
        } else {
            out.println("Computer won.");
        }
    }

}
