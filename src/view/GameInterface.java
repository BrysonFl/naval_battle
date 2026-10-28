package view;

import game.Board;
import game.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * {@summary Gestiona la interfaz de usuario en consola para la entrada y salida de datos del juego.}
 * Esta clase proporciona métodos para interactuar con los participantes mediante la consola estándar,
 * capturando y validando nombres de usuario, dimensiones del tablero y coordenadas de disparo, además
 * de renderizar visualmente el tablero de juego y las estadísticas finales de los jugadores.
 *
 * @author Nicolas Niviayo
 * @author Diego Diaz
 * @author Jesus Solis
 * @author Bryson Florez
 */
public class GameInterface {

    /**
     * {@summary Lector de flujo de entrada estándar para capturar datos desde la consola.}
     * Objeto {@link BufferedReader} compartido para procesar las líneas de texto ingresadas por el usuario.
     */
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    /**
     * {@summary Constructor por defecto de la interfaz de juego.}
     * Inicializa los recursos requeridos para la interacción por consola con los jugadores.
     */
    public GameInterface() {
    }

    /**
     * {@summary Solicita y valida el ingreso del nombre o apodo para el jugador humano.}
     * Realiza un ciclo interactivo que solicita el nickname por consola y valida que el valor no sea nulo
     * ni vacío, manejando posibles excepciones de entrada/salida para asegurar una captura correcta sin interrumpir la aplicación.
     *
     * @return Cadena de texto con el nickname validado del jugador.
     */
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

    /**
     * {@summary Solicita y valida la dimensión numérica deseada para el tablero de juego.}
     * Pide al usuario por consola un valor entero dentro del rango reglamentario (entre 5 y 10).
     * Si el valor ingresado no es numérico o se encuentra fuera de los límites, informa el error
     * y repite la solicitud hasta que se suministre una dimensión válida.
     *
     * @return Entero que representa la dimensión del tablero (número de filas y columnas).
     */
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

    /**
     * {@summary Solicita al usuario las coordenadas numéricas para efectuar un disparo en su turno.}
     * Lee secuencialmente las entradas para la coordenada horizontal (X) y la coordenada vertical (Y),
     * validando que ambos valores correspondan a números enteros válidos y atrapando errores de formato.
     *
     * @return Arreglo de dos enteros {@code [x, y]} con las coordenadas del disparo ingresadas por el usuario.
     */
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

    /**
     * {@summary Imprime en la consola el estado visual actual del tablero de juego.}
     * Itera sobre las filas y celdas de la matriz visual del {@link Board} proporcionado,
     * imprimiendo cada símbolo separado por un espacio para facilitar la lectura del jugador.
     *
     * @param board Objeto {@link Board} cuyo tablero visual se desea imprimir en pantalla.
     */
    public void showBoard(Board board) {
        for (String[] row : board.getShowBoard()) {
            for (Object item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    /**
     * {@summary Muestra en pantalla el resumen de estadísticas acumuladas de un jugador.}
     * Imprime un encabezado con el nombre del participante, seguido de la representación en cadena
     * de sus estadísticas (aciertos, fallos y disparos restantes) generada por {@link Player#toString()}.
     *
     * @param player Objeto {@link Player} cuyas estadísticas van a ser visualizadas.
     */
    public void showStatistics(Player player) {
        System.out.println("A continuación podrás visualizar las estadisticas de " + player.getName());
        System.out.println(player);
        System.out.println("*********************");
    }
    
}
