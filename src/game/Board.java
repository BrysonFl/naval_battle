package game;

import java.util.Random;

/**
 *
 * @author Bryson Florez
 */
public class Board {

    private final int dimension;
    
    private String[][] showBoard;
    private Boat[][] gameBoard;

    private final Random random;

    private static final byte TOTAL_BOATS = 5;
    private static final String CELL_EMPTY = "-";
    private static final String CELL_PLAYED = "X";
    private static final String CELL_BOAT = "B";
    private int boatsDead;

    public Board(int dimension) {
        this.boatsDead = 0;
        this.dimension = dimension;
        this.random = new Random();

        initializeShowBoard();
        initializeBoatsBoard();
    }
    
    private void initializeShowBoard() {
        showBoard = new String[dimension][dimension];

        for (int i = 0; i < showBoard.length; i++) {
            for (int j = 0; j < showBoard[i].length; j++) {
                showBoard[i][j] = "-";
            }
        }
    }

    private void initializeBoatsBoard() {
        int boatsAdded = 0;
        this.gameBoard = new Boat[this.dimension][this.dimension];

        while (boatsAdded < TOTAL_BOATS) {
            int coordinateX = random.nextInt(this.dimension);
            int coordinateY = random.nextInt(this.dimension);

            if (gameBoard[coordinateX][coordinateY] == null) {
                gameBoard[coordinateX][coordinateY] = new Boat(new Coordinate(coordinateX, coordinateY));
                boatsAdded++;
            }
        }
    }

    public int receiveShoot(int x, int y) {
        if (!this.getShowBoard()[x][y].equals(CELL_EMPTY)) {
            return 0;
        }

        if (this.getGameBoard()[x][y] != null) {
            this.showBoard[x][y] = CELL_BOAT;
            boatsDead++;
            return 1;
        } else {
            this.showBoard[x][y] = CELL_PLAYED;
            return 2;
        }
    }

    public String[][] getShowBoard() {
        return showBoard;
    }

    public Boat[][] getGameBoard() {
        return gameBoard;
    }

    public boolean isAllBoatsDead() {
        return boatsDead >= TOTAL_BOATS;
    }
}
