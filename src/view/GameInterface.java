package view;

import game.Board;
import game.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Bryson Florez
 */
public class GameInterface {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public String giveNameToHumanPlayer() {
        String name = "";
        boolean hasError;

        do {
            try {
                hasError = false;
                System.out.print("Hola. Por favor, ingresa un nickname para tu jugador(a): ");
                name = READER.readLine();

                if (name == null || name.isEmpty()) {
                    System.out.println("ALERTA: Debes ingresar un valor para tu nickname \n");
                    hasError = true;
                }
            } catch (IOException e) {
                System.out.println("ERROR: Hubo un problema con el ingreso. Por favor, intenta nuevamente.");
                hasError = true;
            }
        } while(hasError);

        return name;
    }

    public int giveUserDimensionBoard() {
        int dimension = 0;
        boolean hasError;

        do {
            hasError = false;
            System.out.print("Ingresa la dimensión del tablero (Valor: 5 - 10): ");

            try {
                dimension = Integer.parseInt(READER.readLine());

                if (dimension < 5 || dimension > 10) {
                    System.out.println("ALERTA: El valor ingresado debe estar entre 5 - 10 \n");
                    hasError = true;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("ERROR: Debes ingresar un valor numérico");
                hasError = true;
            }
        } while (hasError);

        return dimension;
    }

    public int[] giveUserTurnCoordinates() {
        do {
            int x, y;

            try {
                System.out.print("Ingresa el valor para la coordenada x: ");
                x = Integer.parseInt(READER.readLine());

                System.out.print("Ingresa el valor para la coordenada y: ");
                y = Integer.parseInt(READER.readLine());

                return new int[]{ x, y };
            } catch (IOException | NumberFormatException e) {
                System.out.println("ERROR: Debes ingresar un valor numérico");
            }
        } while (true);
    }

    public void showBoard(Board board) {
        for (String[] row : board.getShowBoard()) {
            for (Object item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public void showStatistics(Player player) {
        System.out.println("A continuación podrás visualizar las estadisticas de " + player.getName());
        System.out.println(player);
        System.out.println("*********************");
    }
    
}
