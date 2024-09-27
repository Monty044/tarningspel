package org.example;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        startGame(scanner);
    }

    private static void startGame(Scanner scanner) {
        Timer timer = new Timer();

        System.out.println("What's your name, Player 1: ");
        Player player1 = new Player(scanner.nextLine());

        System.out.println("What's your name, Player 2: ");
        Player player2 = new Player(scanner.nextLine());

        TimerTask gameTask = new TimerTask() {
            int round = 1;

            @Override
            public void run() {
                System.out.println("Round " + round + ":");

                int value1 = player1.Playerturn();
                System.out.println(player1.name + " rolled a " + value1);

                int value2 = player2.Playerturn();
                System.out.println(player2.name + " rolled a " + value2);

                if (round == 3) {
                    if (player1.points == player2.points) {
                        System.out.println("It's a tie!");
                    } else if (player1.points > player2.points) {
                        System.out.println(player1.name + " wins with " + player1.points + " points!");
                        System.out.println(player2.name + " loses with " + player2.points + " points.");
                    } else {
                        System.out.println(player2.name + " wins with " + player2.points + " points!");
                        System.out.println(player1.name + " loses with " + player1.points + " points.");
                    }

                    askToPlayAgain(timer, scanner);
                    this.cancel();
                }

                round++;
            }
        };

        timer.scheduleAtFixedRate(gameTask, 0, 2000);
    }


    private static void askToPlayAgain(Timer timer, Scanner scanner) {
        while (true) {

            System.out.println("Do you want to play again? (y/n)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                System.out.println("Starting a new game...");
                startGame(scanner);
                break;
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println("Thanks for playing!");
                timer.cancel();
                break;


            }else {
                System.out.println("Invalid input use only y or n");
            }
        }

    }
}
