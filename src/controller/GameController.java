package controller;

import game.Player;
import view.GameInterface;

public class GameController {

    private final GameInterface gameInterface;

    private final Player playerOne;
    private final Player playerTwo;

    private final int dimension;

    public GameController(GameInterface gameInterface) {
        this.gameInterface = gameInterface;

        String name = gameInterface.giveNameToHumanPlayer();
        this.dimension = gameInterface.giveUserDimensionBoard();

        this.playerOne = new Player(name, true, this.dimension);
        this.playerTwo = new Player("Computer", false, this.dimension);
    }

    public void start() {
        Player currentPlayer = playerOne;
        Player defenderPlayer = playerTwo;

        while (true) {
            System.out.println("\nINFO: Turno del jugador " + currentPlayer.getName());

            int[] coords = currentPlayer.makeShoot(this.gameInterface, this.dimension);
            int result = defenderPlayer.getBoard().receiveShoot(coords[0], coords[1]);

            if (result == 0) {
                System.out.println("Coordenada repetida. Perdiste el turno.");
                currentPlayer.setFailures();
                currentPlayer.setRemainingShots();
            }

            if (result == 1) {
                System.out.println("Wow. Disparo directo. Barco caído.");
                currentPlayer.setSuccesses();
                currentPlayer.setRemainingShots();
            } else {
                System.out.println("Le diste al agua.");
                currentPlayer.setFailures();
                currentPlayer.setRemainingShots();
            }

            System.out.println("Tablero del jugador " + defenderPlayer.getName());
            gameInterface.showBoard(defenderPlayer.getBoard());

            if (defenderPlayer.getBoard().isAllBoatsDead()) {
                System.out.println("FELICIDADES: El ganador es " + currentPlayer.getName());
                gameInterface.showStatistics(currentPlayer);
                gameInterface.showStatistics(defenderPlayer);
                return;
            }

            Player tempPlayer = currentPlayer;
            currentPlayer = defenderPlayer;
            defenderPlayer = tempPlayer;
        }
    }

}
