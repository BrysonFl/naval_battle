package game;

/**
 *
 * @author PC
 */
public class Player {
    
    private String name;
    private int successes;
    private int failures;
    private int remainingShots;
    
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSuccesses() {
        return successes;
    }

    public void setSuccesses(int successes) {
        this.successes = successes;
    }

    public int getFailures() {
        return failures;
    }

    public void setFailures(int failures) {
        this.failures = failures;
    }

    public int getRemainingShots() {
        return remainingShots;
    }

    public void setRemainingShots(int remainingShots) {
        this.remainingShots = remainingShots;
    }
    
}
