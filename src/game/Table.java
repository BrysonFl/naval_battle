package game;

import java.util.Random;

/**
 *
 * @author Bryson Florez
 */
public class Table {
    
    private Object[][] board;
    private Player player;

    private final int size;
    
    public Table(int size, Player player) {
        this.size = size;
        this.player = player;

        initializeBoardEmpty();
        initializeBoats();
    }
    
    private void initializeBoardEmpty() {
        board = new Object[size][size];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "*";
            }
        }
    }

    private void initializeBoats() {
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int coordinateX = random.nextInt(this.size);
            int coordinateY = random.nextInt(this.size);

            System.out.println(coordinateX);
            System.out.println(coordinateY);

            this.board[coordinateX][coordinateY] = new Boat(new Coordinate(coordinateX, coordinateY));
        }
    }

    public void takeTurn(int coordinateX, int coordinateY) {
        Object dummyBoard = this.board[coordinateX][coordinateY];

        if (dummyBoard instanceof String) {
            player.setFailures();
        }

        if (dummyBoard instanceof Boat) {
            player.setSuccesses();
        }
    }

    public Object[][] getBoard() {
        return board;
    }
}
