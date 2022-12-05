public class Bot {
    private Board board;
    private Color color;

    public Bot(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    private Color inverse(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    
    private double R(int x, int y) {
        double res = 0;
        if (x % 8 == 0 && y % 8 == 0) {
            res = 0.8;
        }
        else if (x % 8 == 0 || y % 8 == 0) {
            res = 0.4;
        }
        int incs[] = {-1, 0, 1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                if ((x + incs[i] <= 8 && x + incs[i] > 0 
                    && y + incs[j] <= 8 && y + incs[j] > 0)
                    && (board.at(x + incs[i], y + incs[j]).getColor() != inverse(this.color))) {
                        continue;
                }
                int sum = 0, x1, y1;
                for (x1 = x + incs[i], y1 = y + incs[j];
                     (x1 + incs[i]) < 9 && (x1 + incs[i]) > 0
                     && (y1 + incs[j]) < 9 && (y1 + incs[j]) > 0
                     && board.at(x1, y1).getColor() == inverse(this.color); 
                     x1 += incs[i], y1 += incs[j]) {
                    if (x1 % 8 == 0 || y1 % 8 == 0) {
                        sum += 2;
                    }
                    else {
                        sum++;
                    }
                }
                if ((x1 + incs[i]) < 9 && (x1 + incs[i]) > 0
                    && (y1 + incs[j]) < 9 && (y1 + incs[j]) > 0 
                    && board.at(x1, y1).getColor() == this.color) {
                    res += sum;
                }
            }
        }
        return res;
    }
    
    public int[] move() {
        int x = 0, y = 0;
        double maxR = 0;
        if (color == Color.WHITE) {
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    if (board.checkWhite(i, j)) {
                        double r = R(i, j);
                        if (r > maxR) {
                            maxR = r;
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            board.whiteMove(x, y);
        }
        else if (color == Color.BLACK) {
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    if (board.checkBlack(i, j)) {
                        double r = R(i, j);
                        if (r > maxR) {
                            maxR = r;
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            board.blackMove(x, y);
        }
        return new int[] {x, y};
    }
}