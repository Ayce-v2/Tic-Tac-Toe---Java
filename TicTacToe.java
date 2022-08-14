import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("This is Tic Tac Toe,  enjoy!");

        char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', ' ', '+', '-' },
                { ' ', '|', ' ', '|', ' ' } };

        printGameBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your spot (1-9):");
            int playerPos = scan.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Enter empty position.");
                playerPos = scan.nextInt();
            }

            chooseSpot(gameBoard, playerPos, "Player");
            String result = checkWinner(gameBoard);
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random randomCPU = new Random();
            int cpuPos = randomCPU.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = scan.nextInt(9) + 1;
            }
            chooseSpot(gameBoard, cpuPos, "CPU");

            printGameBoard(gameBoard);

            result = checkWinner(gameBoard);
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] game : gameBoard) {
            for (char board : game) {
                System.out.print(board);
            }
            System.out.println();
        }

    }

    public static void chooseSpot(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("Player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("CPU")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }

        // printGameBoard(gameBoard);

    }

    public static String checkWinner(char[][] gameBoard) {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List v : winning) {
            if (playerPositions.containsAll(v)) {
                printGameBoard(gameBoard);
                return "Congrats, you won!";
            }
            if (cpuPositions.containsAll(v)) {
                printGameBoard(gameBoard);
                return "CPU wins!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                printGameBoard(gameBoard);
                return "Draw!";
            }

        }

        return "";

    }
}
