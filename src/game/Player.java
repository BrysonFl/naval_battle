package game;

import view.GameInterface;

import java.util.Random;

/**
 *
 * @author Bryson Florez
 */
public class Player {

    private final String name;
    private int successes;
    private int failures;
    private int remainingShots;
    private final boolean isHumanPlayer;

    private final Board board;
    
    public Player(String name, boolean isHumanPlayer, int dimension) {
        this.name = name;
        this.isHumanPlayer = isHumanPlayer;
        this.board = new Board(dimension);
        this.remainingShots = (int) Math.pow(dimension, 2);
    }

    public String getName() {
        return name;
    }

    public void setSuccesses() {
        this.successes++;
    }

    public void setFailures() {
        this.failures++;
    }

    public void setRemainingShots() {
        this.remainingShots--;
    }

    public boolean isHumanPlayer() {
        return isHumanPlayer;
    }

    public int[] makeShoot(GameInterface gameInterface, int dimension) {
        int x, y;

        if (this.isHumanPlayer()) {
            while (true) {
                int[] coordinates = gameInterface.giveUserTurnCoordinates();
                x = coordinates[0];
                y = coordinates[1];

                if ((x < 0 && x >= dimension) && (y < 0 && y >= dimension)) {
                    System.out.println("ALERTA: Las coordenadas ingresadas están fuera de rango.");
                } else {
                    return coordinates;
                }
            }
        } else {
            Random random = new Random();
            x = random.nextInt(dimension);
            y = random.nextInt(dimension);

            return new int[]{ x, y };
        }
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "Estadisticas {" +
                "name='" + name + '\'' +
                ", successes=" + successes +
                ", failures=" + failures +
                ", remainingShots=" + remainingShots +
                '}';
    }
}
