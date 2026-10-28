package game;

/**
 * {@summary Representa un barco o embarcación dentro de la cuadrícula del juego.}
 * Modela una nave naval ubicada en una posición fija del tablero representada por un objeto {@link Coordinate}.
 * Permite conocer la ubicación espacial del barco para verificar impactos y determinar su estado durante la batalla.
 *
 * @author Nicolas Niviayo
 * @author Diego Diaz
 * @author Jesus Solis
 * @author Bryson Florez
 */
public class Boat {
    
    /**
     * {@summary Ubicación espacial de la embarcación en el tablero de juego.}
     * Contiene las coordenadas bidimensionales (X, Y) asignadas a este barco dentro de la matriz del tablero.
     */
    private final Coordinate coordinates;

    /**
     * {@summary Construye una nueva instancia de un barco en una coordenada dada.}
     * Asocia la embarcación a una posición geográfica específica dentro del tablero de juego.
     *
     * @param coordinates Objeto {@link Coordinate} que define la posición (X, Y) asignada a la nave.
     */
    public Boat(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * {@summary Obtiene las coordenadas asignadas a la embarcación.}
     * Retorna el objeto {@link Coordinate} que almacena la fila y columna donde se ubica el barco en el tablero.
     *
     * @return El objeto {@link Coordinate} con las posiciones espaciales de la nave.
     */
    public Coordinate getCoordinates() {
        return coordinates;
    }
}
