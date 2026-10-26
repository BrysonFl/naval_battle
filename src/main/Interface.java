package main;

import game.Table;

/**
 *
 * @author Bryson Florez
 */
public class Interface {

    private final Table table;

    public  Interface(Table table) {
        this.table = table;
    }

    public void showTable() {
        for (Object[] row : this.table.getBoard()) {
            for (Object item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
    
}
