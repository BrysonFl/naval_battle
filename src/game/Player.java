package game;

import view.GameInterface;

import java.util.Random;

/**
 * {@summary Representa a un participante (humano o máquina) en la partida de Batalla Naval.}
 * Esta clase mantiene los datos de identificación del jugador, su tablero propio {@link Board},
 * el seguimiento de sus estadísticas de juego (aciertos, fallos y disparos restantes) y
 * la lógica para emitir disparos según si el jugador es controlado por un usuario humano o por la máquina.
 *
 * @author Nicolas Niviayo
 * @author Diego Diaz
 * @author Jesus Solis
 * @author Bryson Florez
 */
public class Player {

    /**
     * {@summary Nombre o apodo distintivo del jugador.}
     * Identifica al jugador durante la interacción en consola y en la presentación de estadísticas finales.
     */
    private final String name;

    /**
     * {@summary Contador de disparos exitosos que impactaron en un barco rival.}
     * Se incrementa en una unidad cada vez que el jugador acierta un tiro directo.
     */
    private int successes;

    /**
     * {@summary Contador de disparos fallidos realizados por el jugador.}
     * Se incrementa cuando el tiro cae al agua o cuando se repite una coordenada previamente atacada.
     */
    private int failures;

    /**
     * {@summary Cantidad de disparos restantes disponibles para el jugador.}
     * Inicializado con el total de casillas del tablero (dimensión al cuadrado) y decrementado tras cada turno.
     */
    private int remainingShots;

    /**
     * {@summary Bandera que indica si el participante es un usuario humano.}
     * Contiene {@code true} si las acciones son ingresadas por un usuario vía consola, o {@code false} si es controlado automáticamente por la máquina.
     */
    private final boolean isHumanPlayer;

    /**
     * {@summary Tablero naval asignado al jugador.}
     * Contiene la cuadrícula donde se posicionan sus barcos defensores y se registran los ataques recibidos del oponente.
     */
    private final Board board;
    
    /**
     * {@summary Construye una nueva instancia de un jugador con su nombre, tipo y tablero.}
     * Inicializa los atributos del jugador, asigna su nombre, tipo de control, crea un nuevo {@link Board}
     * con la dimensión provista y calcula los disparos totales permitidos como la dimensión al cuadrado.
     *
     * @param name Nombre o apodo del jugador.
     * @param isHumanPlayer {@code true} si el jugador es humano; {@code false} si es controlado por la máquina.
     * @param dimension Tamaño de la cuadrícula para inicializar el tablero del jugador.
     */
    public Player(String name, boolean isHumanPlayer, int dimension) {
        this.name = name;
        this.isHumanPlayer = isHumanPlayer;
        this.board = new Board(dimension);
        this.remainingShots = (int) Math.pow(dimension, 2);
    }

    /**
     * {@summary Obtiene el nombre o apodo del jugador.}
     * Retorna la cadena de texto asignada como identificador del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getName() {
        return name;
    }

    /**
     * {@summary Incrementa en una unidad el contador de aciertos del jugador.}
     * Registra un disparo efectivo directo sobre una nave enemiga.
     */
    public void setSuccesses() {
        this.successes++;
    }

    /**
     * {@summary Incrementa en una unidad el contador de fallos del jugador.}
     * Registra un disparo no efectivo (agua o coordenada repetida).
     */
    public void setFailures() {
        this.failures++;
    }

    /**
     * {@summary Decrementa en una unidad el número de disparos restantes del jugador.}
     * Reduce la disponibilidad de tiros al consumir un turno de ataque.
     */
    public void setRemainingShots() {
        this.remainingShots--;
    }

    /**
     * {@summary Indica si el jugador actual corresponde a un usuario humano.}
     * Permite diferenciar entre la toma de decisiones por consola o mediante generación aleatoria de la CPU.
     *
     * @return {@code true} si el jugador es humano; {@code false} si es la máquina.
     */
    public boolean isHumanPlayer() {
        return isHumanPlayer;
    }

    /**
     * {@summary Ejecuta la acción de disparo generando las coordenadas objetivo.}
     * Si el jugador es humano, solicita al usuario las coordenadas a través de {@link GameInterface}
     * validando que se encuentren dentro del rango permitido por la dimensión. Si es la máquina,
     * genera un par de coordenadas pseudoaleatorias dentro de los límites de la matriz.
     *
     * @param gameInterface Instancia de la interfaz de consola para interactuar con el usuario.
     * @param dimension Dimensión del tablero que define el límite superior de las coordenadas.
     * @return Arreglo de dos enteros {@code [x, y]} con las coordenadas elegidas para el ataque.
     */
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

    /**
     * {@summary Obtiene el tablero naval perteneciente a este jugador.}
     * Retorna la referencia al objeto {@link Board} donde se ubica la flota y se reciben ataques.
     *
     * @return El objeto {@link Board} del jugador.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * {@summary Genera una representación en cadena de las estadísticas actuales del jugador.}
     * Incluye información sobre su nombre, aciertos acumulados, fallos cometidos y disparos restantes.
     *
     * @return Cadena formateada con el resumen estadístico del jugador.
     */
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
