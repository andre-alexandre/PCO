package test;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int seed = 50;
    private static final int size = 4;
    
    public static void main(String[] args) {
        System.out.println("1 -- Bulls and Cows (2 colors);");
        System.out.println("2 -- Multi-Color Mastermind.\n");
        System.out.println("Choose your game (1|2): ");
        
        int choice = scanner.nextInt();
        MastermindGame game;
        
        while (true) {
            if (choice == 1) {
                game = new BullsAndCows(seed, size, BinaryColour.values());
                break;
            } else if (choice == 2) {
                game = new MultiColourMastermindGame(seed, size, MultiColour.values());
                break;
            } else {
                System.out.print("Invalid choice. Please choose 1 or 2: ");
                choice = scanner.nextInt();
            }
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

            System.out.println("\n"+game);
            
            System.out.println("1 -- Make a guess");
            System.out.println("2 -- Get a hint");
            System.out.println("3 -- See best trial");
            
            System.out.print("\nChoose an option (1|2|3): ");
            int choice = scanner.nextInt();
            
            while (true) {
            	if (choice == 1) {
                    makeGuess(game);
                    break;
            	} else if (choice == 2) {
                    System.out.println("Hint: The secret code contains: " + game.hint());
                    break;
            	} else if (choice == 3) {
                    Code bestTrial = game.bestTrial();
                    if (bestTrial != null) {
                        System.out.println("Best trial so far: " + bestTrial);
                    } else {
                        System.out.println("No trials yet!");
                    }
                    break;
            	} else {
                    System.out.print("Invalid choice. Please choose 1, 2 or 3: ");
                    choice = scanner.nextInt();
            	}
            }
        }
        
        System.out.println("\nRound finished!");
        System.out.println(game);
    }
    
    private static void makeGuess(MastermindGame game) {
        
        if (game instanceof BullsAndCows) {
            List<BinaryColour> guess = new ArrayList<>();
            System.out.println("\nUse B for BLACK, W for WHITE!");
            for (int i = 0; i < size; i++) {
                while (true) {
                    System.out.print("Enter color:");
                    String color = scanner.next().toUpperCase();
                    try {
                        guess.add(BinaryColour.valueOf(
                            switch (color) {
                                case "W" -> "WHITE";
                                case "B" -> "BLACK";
                                default -> throw new IllegalArgumentException();
                            }
                        ));
                        break; 
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid color. Please try again.");
                    }
                }
            }
            game.play(new BullsAndCowsCode(guess));
        } else {
            List<Colour> guess = new ArrayList<>();
            System.out.println("Use R(ed), B(lue), G(reen), Y(ellow), O(range), P(urple):");
            for (int i = 0; i < size; i++) {
                while (true) { // Loop until valid input is provided
                    System.out.print("Enter color:");
                    String color = scanner.next().toUpperCase();
                    try {
		                guess.add(MultiColour.valueOf(
		                    switch(color) {
		                        case "R" -> "RED";
		                        case "B" -> "BLUE";
		                        case "G" -> "GREEN";
		                        case "Y" -> "YELLOW";
		                        case "O" -> "ORANGE";
		                        case "P" -> "PINK";
	                            default -> throw new IllegalArgumentException();
	                        }
	                    ));
	                    break; 
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Invalid color. Please try again.");
	                }
	            }
	        }
            game.play(new Code(guess));
        }
    }
}
