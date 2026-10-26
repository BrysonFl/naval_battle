package main;

import game.Player;
import game.Table;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Bryson Florez
 */
public class NavalBattleMain {

    private void initializeTableGame() {
        int size;
        Table humanTable;
        Table computerTable;

        boolean turnPlayerOne = true;
        boolean hasWinner = false;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.print("Holaaaa. \nPor favor, ingresa tu nombre: ");
            String name = reader.readLine();

            Player humanPlayer = new Player(name);
            Player computerPlayer = new Player("Computer");

            do {
                System.out.print(humanPlayer.getName() + ", ingresa el tamaño del tablero con el que quieres jugar (Minímo 5x5, Máximo 10x10 - Solo debes ingresar un número): ");
                size = Integer.parseInt(reader.readLine());
            } while (size < 5 || size > 10);

            humanTable = new Table(size, humanPlayer);
            computerTable = new Table(size, computerPlayer);

            Interface humanInterface = new Interface(humanTable);
            Interface computerInterface = new Interface(computerTable);

            humanInterface.showTable();

            System.out.println();
            computerInterface.showTable();

            do {
                String message = "Es el turno de ";
                String playerTurn = turnPlayerOne ? message + " Jugador: " + humanPlayer.getName() : message + " Jugador: " + computerPlayer.getName();
                System.out.println();

                if (turnPlayerOne) {
                    System.out.println("Ingresa las coordenadas de tu jugada: ");
                    System.out.print("X: ");
                    int playerOneCoordinateX = reader.read();

                    System.out.print("Y: ");
                    int playerOneCoordinateY = reader.read();

                    computerTable.takeTurn(playerOneCoordinateX, playerOneCoordinateY);
                    turnPlayerOne = !turnPlayerOne;
                }
            } while(!hasWinner);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        NavalBattleMain game = new NavalBattleMain();
        game.initializeTableGame();
    }
    
}
