package test;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Welcome to Mastermind!");
        System.out.println("1. Bulls and Cows (2 colors)");
        System.out.println("2. Multi-Color Mastermind");
        System.out.print("Choose your game (1-2): ");
        
        int choice = scanner.nextInt();
        MastermindGame game;
        Random random = new Random();
        
        if (choice == 1) {
            game = new BullsAndCows(random.nextInt(50), 4, BinaryColour.values());
        } else {
            game = new MultiColourMastermindGame(random.nextInt(50), 4, MultiColour.values());
        }
        
        while (true) {
            playRound(game);
            
            System.out.print("Play another round? (y/n): ");
            if (!scanner.next().toLowerCase().startsWith("y")) {
                break;
            }
            game.startNewRound();
        }
        
        System.out.println("Final score: " + game.score());
    }
    
    private static void playRound(MastermindGame game) {
        while (!game.isRoundFinished()) {
            System.out.println("\nCurrent game state:");
            System.out.println(game);
            
            System.out.println("\nOptions:");
            System.out.println("1. Make a guess");
            System.out.println("2. Get a hint");
            System.out.println("3. See best trial");
            
            System.out.print("Choose an option (1-3): ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    makeGuess(game);
                    break;
                case 2:
                    System.out.println("Hint: The secret code contains: " + game.hint());
                    break;
                case 3:
                    Code bestTrial = game.bestTrial();
                    if (bestTrial != null) {
                        System.out.println("Best trial so far: " + bestTrial);
                    } else {
                        System.out.println("No trials yet!");
                    }
                    break;
            }
        }
        
        System.out.println("\nRound finished!");
        System.out.println(game);
    }
    
    private static void makeGuess(MastermindGame game) {
        System.out.println("Enter your guess (");
        
        if (game instanceof BullsAndCows) {
            List<BinaryColour> guess = new ArrayList<>();
            System.out.println("Use B for BLACK, W for WHITE):");
            for (int i = 0; i < 4; i++) {
                String color = scanner.next().toUpperCase();
                guess.add(BinaryColour.valueOf(
                    switch(color) {
                        case "W" -> "WHITE";
                        case "B" -> "BLACK";
                        default -> throw new IllegalArgumentException("Invalid color");
                    }
                ));
            }
            game.play(new BullsAndCowsCode(guess));
        } else {
            List<Colour> guess = new ArrayList<>();
            System.out.println("Use R(ed), B(lue), G(reen), Y(ellow), O(range), P(urple):");
            for (int i = 0; i < 4; i++) {
                String color = scanner.next().toUpperCase();
                guess.add(MultiColour.valueOf(
                    switch(color) {
                        case "R" -> "RED";
                        case "B" -> "BLUE";
                        case "G" -> "GREEN";
                        case "Y" -> "YELLOW";
                        case "O" -> "ORANGE";
                        case "P" -> "PURPLE";
                        default -> throw new IllegalArgumentException("Invalid color");
                    }
                ));
            }
            game.play(new Code(guess));
        }
    }
}
