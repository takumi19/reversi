import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        Board board = new Board();
        Bot bot = new Bot(board, Color.WHITE);
        Scanner scanner = new Scanner(System.in);
        int twoPlayers = 0;
        int botMove[] = new int[2];

        do {
            System.out.println("Type 1 if you wanna play with a computational intelligence or 0 if you wanna play against another human:\n");
            twoPlayers = scanner.nextInt();
        }
        while (twoPlayers != 0 && twoPlayers != 1);

        if (twoPlayers == 1) {
            board.setBot(true);
            System.out.printf("You are playing for the black figures, which are represented by the \"+\" sign on the board.\nThe fields where you can make a move are marked by the \"*\" sign.\n");
            while (board.playable()) {
                board.print();
                if (board.blackPlayable()) {
                    System.out.printf("Enter the row and the column of the field where you wanna make a move.\n");
                    while (!board.blackMove(scanner.nextInt(), scanner.nextInt())) {
                        System.out.printf("Please enter a valid field\n");
                    }
                    board.print();
                }
                else {
                    System.out.printf("You skip your turn\n");
                }
                if (board.whitePlayable()) {
                    System.out.printf("Opponent's turn:\n");
                    TimeUnit.SECONDS.sleep(1);
                    botMove = bot.move();
                    System.out.printf("%d %d\n", botMove[0], botMove[1]);
                }
                else {
                    System.out.printf("Your opponent skips their turn\n");
                }
            }
            System.out.printf("The game has ended, your score is: %d", board.countBlacks());    
        }
        else {
            board.setBot(false);
            System.out.printf("The first turn is after the black figures, which are represented by the \"+\" sign on the board.\nThen it is the whites' turn, which are represented by \"0\" signs.\nThe fields where you can make a move are marked by the \"*\" sign.\n");
            while (board.playable()) {
                board.print();
                if (board.blackPlayable()) {
                    System.out.printf("Enter the row and the column of the field where you wanna make a move.\n");
                    while (!board.blackMove(scanner.nextInt(), scanner.nextInt())) {
                        System.out.printf("Please enter a valid field\n");
                    }
                    board.print();
                }
                else {
                    System.out.printf("You skip your turn\n");
                }
                if (board.whitePlayable()) {
                    System.out.printf("Enter the row and the column of the field where you wanna make a move.\n");
                    while (!board.whiteMove(scanner.nextInt(), scanner.nextInt())) {
                        System.out.printf("Please enter a valid field\n");
                    }
                }
                else {
                    System.out.printf("You skip your turn\n");
                }
        }
        System.out.printf("The game has ended, blacks' and whites' scores are respectively: %d %d", board.countBlacks(), board.countWhites());
        }
    }
}
