package game;

/**
 *
 * @author Bryson Florez
 */
public class Table {
    
    private String[][] table;
    private int size;
    
    public Table(int size) {
        this.size = size;
        
        initializeBoard();
    }
    
    private void initializeBoard() {
        table = new String[size][size];
        
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                
            }
        }
    }
    
}
