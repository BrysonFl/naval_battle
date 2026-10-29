package game;

import java.util.Random;

/**
 * {@summary Representa el tablero de batalla naval para un jugador.}
 * Administra la cuadrícula de juego bidimensional, conteniendo tanto la matriz visible al usuario
 * (donde se reflejan disparos al agua, impactos y celdas sin explorar) como la matriz interna donde
 * se posicionan aleatoriamente los barcos {@link Boat}. También se encarga de procesar los impactos recibidos
 * y validar las condiciones de victoria o fin de flota.
 *
 * @author Nicolas Niviayo
 * @author Diego Diaz
 * @author Jesus Solis
 * @author Bryson Florez
 */
public class Board {

    /**
     * {@summary Tamaño o dimensión lineal del tablero cuadrado.}
     * Define la cantidad de filas y columnas que componen la cuadrícula (matriz dimension x dimension).
     */
    private final int dimension;

    /**
     * {@summary Matriz visible que representa el estado público del tablero en consola.}
     * Almacena caracteres indicadores de celda vacía ("-"), agua/disparo efectuado ("X") o barco impactado ("B").
     */
    private String[][] showBoard;

    /**
     * {@summary Matriz interna que contiene la ubicación real de las embarcaciones.}
     * Cada celda contiene una referencia a un objeto {@link Boat} si hay una nave ubicada allí, o {@code null} si está libre.
     */
    private Boat[][] gameBoard;

    /**
     * {@summary Generador de números aleatorios para la colocación de naves.}
     * Empleado durante la inicialización para ubicar los barcos en coordenadas aleatorias sin colisiones.
     */
    private final Random random;

    /**
     * {@summary Cantidad total de barcos que deben posicionarse en el tablero.}
     * Establece el número reglamentario de naves navales por jugador (por defecto 5).
     */
    private static final byte TOTAL_BOATS = 5;

    /**
     * {@summary Representación visual de una celda que aún no ha sido atacada.}
     * Carácter utilizado para denotar que la casilla permanece inexplorada ("-").
     */
    private static final String CELL_EMPTY = "-";

    /**
     * {@summary Representación visual de un disparo que impactó en el agua.}
     * Carácter utilizado para marcar un ataque fallido en una casilla sin nave ("X").
     */
    private static final String CELL_PLAYED = "X";

    /**
     * {@summary Representación visual de un impacto certero sobre un barco.}
     * Carácter utilizado para revelar y marcar una casilla donde un barco ha sido destruido ("B").
     */
    private static final String CELL_BOAT = "B";

    /**
     * {@summary Contador de la cantidad de barcos que han sido destruidos.}
     * Se incrementa cada vez que un disparo impacta exitosamente sobre una embarcación enemiga.
     */
    private int boatsDead;

    /**
     * {@summary Construye e inicializa un nuevo tablero de juego con la dimensión indicada.}
     * Configura el tamaño del tablero, inicializa el contador de naves destruidas en cero, prepara
     * la cuadrícula visual y coloca aleatoriamente la flota de barcos en la cuadrícula interna.
     *
     * @param dimension Tamaño de las filas y columnas del tablero cuadrado (generalmente entre 5 y 10).
     */
    public Board(int dimension) {
        this.boatsDead = 0;
        this.dimension = dimension;
        this.random = new Random();

        initializeShowBoard();
        initializeBoatsBoard();
    }
    
    /**
     * {@summary Inicializa la matriz visual del tablero con celdas vacías.}
     * Rellena todas las posiciones de la matriz {@code showBoard} con el símbolo predeterminado {@link #CELL_EMPTY} ("-").
     */
    private void initializeShowBoard() {
        showBoard = new String[dimension][dimension];

        for (int i = 0; i < showBoard.length; i++) {
            for (int j = 0; j < showBoard[i].length; j++) {
                showBoard[i][j] = "-";
            }
        }
    }

    /**
     * {@summary Distribuye y coloca aleatoriamente los barcos en la matriz del tablero.}
     * Genera pares de coordenadas aleatorias (X, Y) dentro de los límites del tablero y ubica instancias
     * de {@link Boat} hasta completar el total requerido por {@link #TOTAL_BOATS}, asegurando que ninguna casilla se superponga.
     */
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

    /**
     * {@summary Procesa el disparo efectuado en las coordenadas especificadas del tablero.}
     * Valida el impacto verificando si la casilla ya fue atacada previamente, si impacta en un barco o si cae en el agua:
     * <ul>
     *   <li>Retorna {@code 0} si la casilla ya había sido jugada (coordenada repetida).</li>
     *   <li>Retorna {@code 1} si el disparo impacta exitosamente sobre un barco, marcando la casilla con {@link #CELL_BOAT} e incrementando {@link #boatsDead}.</li>
     *   <li>Retorna {@code 2} si el disparo cae en el agua, marcando la casilla visual con {@link #CELL_PLAYED}.</li>
     * </ul>
     *
     * @param x Coordenada de la fila donde se dirige el disparo.
     * @param y Coordenada de la columna donde se dirige el disparo.
     * @return Un código entero que representa el resultado del disparo:
     *         {@code 0} para casilla repetida, {@code 1} para impacto directo a barco, o {@code 2} para agua.
     */
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

    /**
     * {@summary Obtiene la matriz visual que refleja el estado público del tablero.}
     * Proporciona acceso a la matriz bidimensional de cadenas que se muestra en consola con los avances del juego.
     *
     * @return Matriz bidimensional de {@link String} correspondiente al tablero visual.
     */
    public String[][] getShowBoard() {
        return showBoard;
    }

    /**
     * {@summary Obtiene la matriz interna con la distribución de los barcos.}
     * Proporciona acceso a la matriz de objetos {@link Boat} donde están registradas las posiciones reales de las naves.
     *
     * @return Matriz bidimensional de {@link Boat} con las embarcaciones del jugador.
     */
    public Boat[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * {@summary Determina si todos los barcos del tablero han sido destruidos.}
     * Compara el número de barcos hundidos ({@link #boatsDead}) contra la cantidad total reglamentaria ({@link #TOTAL_BOATS})
     * para verificar si se cumple la condición de derrota de este tablero.
     *
     * @return {@code true} si todos los barcos han sido hundidos; de lo contrario, {@code false}.
     */
    public boolean isAllBoatsDead() {
        return boatsDead >= TOTAL_BOATS;
    }
}
