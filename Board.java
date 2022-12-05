import java.util.ArrayList;

public class Board {
    private Field board[][];
    private Color turn;
    private Boolean bot;

    public Boolean getBot() {
        return bot;
    }

    public void setBot(Boolean bot) {
        this.bot = bot;
    }

    public Board() {
        board = new Field[9][9];
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                board[i][j] = new Field();
            }
        }
        board[4][4].setColor(Color.WHITE);
        board[5][5].setColor(Color.WHITE);

        board[4][5].setColor(Color.BLACK);
        board[5][4].setColor(Color.BLACK);

        turn = Color.BLACK;

    }

    public int countWhites() {
        int res = 0;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (board[i][j].getColor() == Color.WHITE) {
                    res++;
                }
            }
        }
        return res;
    }

    public int countBlacks() {
        int res = 0;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (board[i][j].getColor() == Color.BLACK) {
                    res++;
                }
            }
        }
        return res;

    }

    public Boolean checkBlack(int x, int y) {
        if (board[x][y].getColor() != Color.NONE || !(x > 0 && x < 9 && y > 0 && y < 9)) {
            return false;
        }
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (board[i][j].getColor() == Color.WHITE 
                    && (Math.abs(i - x) == Math.abs(j - y) && Math.abs(i - x) == 1 
                    || x - i == 0 && Math.abs(j - y) == 1 
                    || y - j == 0 && Math.abs(i - x) == 1)) {
                        int dx = i - x, dy = j - y;
                        for (int x1 = dx, y1 = dy; (x + x1 < 9) && (x + x1 >= 1) && (y + y1 < 9) && (y + y1 >= 1); x1 += dx, y1 += dy) {
                            if (board[x + x1][y + y1].getColor() == Color.NONE) {
                                break;
                            }
                            if (board[x + x1][y + y1].getColor() == Color.BLACK) {
                                return true;
                            }
                        }
                } 
            }
        }
        return false;
    }

    public Boolean checkWhite(int x, int y) {
        if (board[x][y].getColor() != Color.NONE || !(x > 0 && x < 9 && y > 0 && y < 9)) {
            return false;
        }
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (board[i][j].getColor() == Color.BLACK 
                    && (Math.abs(i - x) == Math.abs(j - y) && Math.abs(i - x) == 1 
                    || x - i == 0 && Math.abs(j - y) == 1 
                    || y - j == 0 && Math.abs(i - x) == 1)) {
                        int dx = i - x, dy = j - y;
                        for (int x1 = dx, y1 = dy; (x + x1 < 9) && (x + x1 >= 1) && (y + y1 < 9) && (y + y1 >= 1); x1 += dx, y1 += dy) {
                            if (board[x + x1][y + y1].getColor() == Color.NONE) {
                                break;
                            }
                            if (board[x + x1][y + y1].getColor() == Color.WHITE) {
                                return true;
                            }
                        }
                } 
            }
        }
        return false;
    }

    public Field at(int x, int y) {
        return board[x][y];
    }

    public Boolean whiteMove(int x, int y) {
        if (checkWhite(x, y)) {
            board[x][y].setColor(Color.WHITE);
            int incs[] = {-1, 0, 1};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        continue;
                    }
                    int x1, y1;
                    Boolean dir = false;
                    ArrayList<Field> lst = new ArrayList<Field>(8);
                    for (x1 = x + incs[i], y1 = y + incs[j];
                         (x1 + incs[i]) < 9 && (x1 + incs[i]) > 0
                         && (y1 + incs[j]) < 9 && (y1 + incs[j]) > 0; 
                         x1 += incs[i], y1 += incs[j]) {
                            if (board[x1][y1].getColor() == Color.WHITE) {
                                dir = true;
                                break;
                            }
                            else if (board[x1][y1].getColor() == Color.NONE) {
                                break;
                            }
                            lst.add(board[x1][y1]);
                    }
                    if (dir) {
                        for (int k = 0; k < lst.size(); k++) {
                            lst.get(k).setColor(Color.WHITE);
                        }
                    }
                }
            }
            turn = Color.BLACK;
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean blackMove(int x, int y) {
        if (checkBlack(x, y)) {
            board[x][y].setColor(Color.BLACK);
            int incs[] = {-1, 0, 1};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        continue;
                    }
                    int x1, y1;
                    Boolean dir = false;
                    ArrayList<Field> lst = new ArrayList<Field>(8);
                    for (x1 = x + incs[i], y1 = y + incs[j];
                         (x1 + incs[i]) < 9 && (x1 + incs[i]) > 0
                         && (y1 + incs[j]) < 9 && (y1 + incs[j]) > 0; 
                         x1 += incs[i], y1 += incs[j]) {
                            if (board[x1][y1].getColor() == Color.BLACK) {
                                dir = true;
                                break;
                            }
                            else if (board[x1][y1].getColor() == Color.NONE) {
                                break;
                            }
                            lst.add(board[x1][y1]);
                    }
                    if (dir) {
                        for (int k = 0; k < lst.size(); k++) {
                            lst.get(k).setColor(Color.BLACK);
                        }
                    }
                }
            }    
            turn = Color.WHITE;
            return true;
        }
        else {
            return false;
        }
    }

    public void print() {
        System.out.print("   ");
        for (int i = 1; i < 9; i++) {
            System.out.printf("%d ", i);
        }
        System.out.printf("%n");
        for (int i = 1; i < 9; i++) {
            System.out.printf("%d ", i);
            for (int j = 1; j < 9; j++) {
                if (board[i][j].getColor() == Color.WHITE) {
                    System.out.printf("|0");
                } 
                else if (board[i][j].getColor() == Color.BLACK) {
                    System.out.printf("|+");
                }
                else if ((turn == Color.BLACK) && checkBlack(i, j)) {
                    System.out.printf("|*");
                }
                else  if (!bot && (turn == Color.WHITE) && checkWhite(i, j)) {
                    System.out.printf("|*");
                }
                else {
                    System.out.printf("| ");
                }
            }
            System.out.printf("|%n");
        }
    }

    public Boolean playable() {
        Boolean whitesFound = false;
        Boolean blacksFound = false;
        Boolean emptyFound = false;
        Boolean movePossibility = false;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (board[i][j].getColor() == Color.WHITE) {
                    whitesFound = true;
                }
                else if (board[i][j].getColor() == Color.BLACK) {
                    blacksFound = true;
                }
                else if (board[i][j].getColor() == Color.NONE) {
                    emptyFound = true;
                }
                if (checkBlack(i, j)) {
                    movePossibility = true;
                }
                else if (checkWhite(i, j)) {
                    movePossibility = true;
                }
            }
        }
        if (!movePossibility || !emptyFound || !blacksFound || !whitesFound) {
            return false;
        }
        return true;
    }

    public Boolean whitePlayable() {
        Boolean movePossibility = false;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (checkWhite(i, j)) {
                    movePossibility = true;
                }
            }
        }
        if (!movePossibility) {
            turn = Color.BLACK;
            return false;
        }
        return true;
    }

    public Boolean blackPlayable() {
        Boolean movePossibility = false;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (checkBlack(i, j)) {
                    movePossibility = true;
                }
            }
        }
        if (!movePossibility) {
            turn = Color.WHITE;
            return false;
        }
        return true;
    }
}
