package game;

import java.util.Random;

/**
 *
 * @author Bryson Florez
 */
public class Table {
    
    private String[][] showBoard;
    private Boat[][] gameBoard;

    private final Player player;

    private final int size;
    
    public Table(int size, Player player) {
        this.size = size;
        this.player = player;

        initializeBoardEmpty();
        initializeBoats();
        this.player.setRemainingShots(size * size);
    }
    
    private void initializeBoardEmpty() {
        showBoard = new String[size][size];
        
        for (int i = 0; i < showBoard.length; i++) {
            for (int j = 0; j < showBoard[i].length; j++) {
                showBoard[i][j] = "*";
            }
        }
    }

    private void initializeBoats() {
        this.gameBoard = new Boat[this.size][this.size];

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int coordinateX = random.nextInt(this.size);
            int coordinateY = random.nextInt(this.size);

            gameBoard[coordinateX][coordinateY] = new Boat(new Coordinate(coordinateX, coordinateY));
        }
    }

    public void takeTurn(int coordinateX, int coordinateY) {
        if (gameBoard[coordinateX][coordinateY] != null) {
            showBoard[coordinateX][coordinateY] = "B";
            player.setSuccesses();
        } else {
            showBoard[coordinateX][coordinateY] = "X";
            player.setFailures();
        }

        player.calculateRemainingShots();
    }

    public String[][] getShowBoard() {
        return showBoard;
    }

}
