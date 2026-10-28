package game;

/**
 * {@summary Representa una coordenada bidimensional en el plano (X, Y) del tablero de juego.}
 * Esta clase encapsula las posiciones horizontal y vertical utilizadas para localizar celdas específicas,
 * posicionar embarcaciones y procesar los disparos efectuados por los jugadores en la cuadrícula.
 *
 * @author Nicolas Niviayo
 * @author Diego Diaz
 * @author Jesus Solis
 * @author Bryson Florez
 */
public class Coordinate {
    
    /**
     * {@summary Posición en el eje horizontal X del tablero.}
     * Corresponde al índice de la fila o eje de abscisas de la coordenada en la cuadrícula de juego.
     */
    private int coordinateX;

    /**
     * {@summary Posición en el eje vertical Y del tablero.}
     * Corresponde al índice de la columna o eje de ordenadas de la coordenada en la cuadrícula de juego.
     */
    private int coordinateY;
    
    /**
     * {@summary Construye una nueva coordenada con las posiciones especificadas.}
     * Inicializa la coordenada asignando los valores correspondientes para los ejes X e Y.
     *
     * @param coordinateX Valor numérico entero para la posición en el eje X.
     * @param coordinateY Valor numérico entero para la posición en el eje Y.
     */
    public Coordinate(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /**
     * {@summary Obtiene el valor actual de la coordenada en el eje X.}
     * Retorna la posición horizontal almacenada en este objeto de coordenada.
     *
     * @return El índice entero en el eje X.
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * {@summary Establece un nuevo valor para la coordenada en el eje X.}
     * Permite modificar o actualizar la posición horizontal en la cuadrícula.
     *
     * @param coordinateX Nuevo valor entero para la posición en el eje X.
     */
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * {@summary Obtiene el valor actual de la coordenada en el eje Y.}
     * Retorna la posición vertical almacenada en este objeto de coordenada.
     *
     * @return El índice entero en el eje Y.
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * {@summary Establece un nuevo valor para la coordenada en el eje Y.}
     * Permite modificar o actualizar la posición vertical en la cuadrícula.
     *
     * @param coordinateY Nuevo valor entero para la posición en el eje Y.
     */
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
    
}
