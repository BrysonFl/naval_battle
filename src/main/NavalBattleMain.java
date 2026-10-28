package main;

import controller.GameController;
import view.GameInterface;

/**
 * {@summary Clase principal y punto de entrada para la ejecución del juego de Batalla Naval.}
 * Se encarga de inicializar la interfaz de usuario por consola y el controlador del juego,
 * orquestando el arranque de la partida.
 *
 * @author Nicolas Niviayo
 * @author Diego Diaz
 * @author Jesus Solis
 * @author Bryson Florez
 */
public class NavalBattleMain {

    /**
     * {@summary Constructor por defecto de la clase principal.}
     * Crea una instancia de {@code NavalBattleMain}.
     */
    public NavalBattleMain() {
    }

    /**
     * {@summary Método principal de arranque del programa.}
     * Inicializa la interfaz de juego {@link GameInterface}, instancia el controlador
     * {@link GameController} pasándole dicha interfaz y arranca el ciclo del juego llamando a {@link GameController#start()}.
     *
     * @param args Argumentos de la línea de comandos pasados al iniciar la aplicación (no utilizados en esta versión).
     */
    public static void main(String[] args) {
        GameInterface gameInterface = new GameInterface();
        GameController controller = new GameController(gameInterface);
        controller.start();
    }
    
}
