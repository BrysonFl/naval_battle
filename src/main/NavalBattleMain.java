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
    
    public static void main(String[] args) {
        int size;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.print("Holaaaa. \nPor favor, ingresa tu nombre: ");
            String name = reader.readLine();
            Player player = new Player(name);
            
            do {
                System.out.print(player.getName() + ", ingresa el tamaño del tablero con el que quieres jugar (Minímo 5x5, Máximo 10x10 - Solo ingresar un número): ");
                size = Integer.parseInt(reader.readLine());
                Table table = new Table(size);
            } while (size < 5 || size > 10);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
