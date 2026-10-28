package controller;

import game.Player;
import view.GameInterface;

/**
 * {@summary Controlador principal del flujo, reglas y lógica de la partida de Batalla Naval.}
 * Esta clase orquesta la dinámica del juego interactuando con la vista {@link GameInterface} y los
 * dos participantes {@link Player} (jugador humano y máquina). Gestiona la inicialización de los tableros,
 * la alternancia de turnos de disparo, el cómputo de impactos, la actualización de estadísticas
 * y la detección de la condición de victoria para finalizar la partida.
 *
 * @author Nicolas Niviayo
 * @author Diego Diaz
 * @author Jesus Solis
 * @author Bryson Florez
 */
public class GameController {

    /**
     * {@summary Interfaz de consola para la interacción con los usuarios.}
     * Canal utilizado para solicitar datos de entrada y proyectar en pantalla el estado del juego y los resultados.
     */
    private final GameInterface gameInterface;

    /**
     * {@summary Representación del primer jugador (usuario humano).}
     * Almacena la configuración, tablero y estadísticas correspondientes al jugador humano.
     */
    private final Player playerOne;

    /**
     * {@summary Representación del segundo jugador (máquina o CPU).}
     * Almacena la configuración, tablero y estadísticas correspondientes al adversario controlado por la máquina.
     */
    private final Player playerTwo;

    /**
     * {@summary Dimensión o tamaño de las cuadrículas de juego seleccionada para la partida.}
     * Determina el número de filas y columnas tanto para los tableros de ambos contendientes como para los límites de disparo.
     */
    private final int dimension;

    /**
     * {@summary Construye e inicializa una nueva instancia de {@code GameController}.}
     * Recibe la interfaz de usuario {@link GameInterface}, solicita a través de ella el nombre del jugador humano
     * y la dimensión del tablero deseada, e inicializa a ambos competidores (humano y máquina) con sus tableros correspondientes.
     *
     * @param gameInterface Instancia de {@link GameInterface} utilizada para entradas y salidas por consola.
     */
    public GameController(GameInterface gameInterface) {
        this.gameInterface = gameInterface;

        String name = gameInterface.giveNameToHumanPlayer();
        this.dimension = gameInterface.giveUserDimensionBoard();

        this.playerOne = new Player(name, true, this.dimension);
        this.playerTwo = new Player("Computer", false, this.dimension);
    }

    /**
     * {@summary Inicia y controla el bucle principal de ejecución de la partida de batalla naval.}
     * Gestiona el ciclo continuo de turnos alternos entre el jugador humano y la máquina.
     * En cada turno:
     * <ul>
     *   <li>Obtiene las coordenadas del disparo del atacante en turno.</li>
     *   <li>Evalúa el resultado del impacto en el tablero del defensor (coordenada repetida, impacto certero o agua).</li>
     *   <li>Actualiza las estadísticas correspondientes de aciertos, fallos y disparos restantes en el jugador atacante.</li>
     *   <li>Muestra en consola el estado actualizado del tablero del defensor.</li>
     *   <li>Verifica si todos los barcos del defensor han sido destruidos para declarar la victoria y culminar la partida.</li>
     * </ul>
     * Al determinarse un vencedor, se presenta el mensaje de felicitaciones y las estadísticas finales completas de ambos jugadores.
     */
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
            } else if (result == 1) {
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
