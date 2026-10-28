package main;

import controller.GameController;
import view.GameInterface;

/**
 * Clase que contiene el punto de entrada del programa
 *
 * @author Bryson Florez
 */
public class NavalBattleMain {

    /**
     * Método principal, desde este punto se inicia la ejecución del juego
     * @param args
     */
    public static void main(String[] args) {
        GameInterface gameInterface = new GameInterface();
        GameController controller = new GameController(gameInterface);
        controller.start();
    }
    
}
