package game;

/**
 *
 * @author PC
 */
public class Player {

    private final String name;
    private int successes;
    private int failures = 0;
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

    public void setSuccesses() {
        this.successes += 1;
    }

    public int getFailures() {
        return failures;
    }

    public void setFailures() {
        this.failures += 1;
    }

    public int getRemainingShots() {
        return remainingShots;
    }

    public void setRemainingShots(int remainingShots) {
        this.remainingShots = remainingShots;
    }

    public void calculateRemainingShots() {
        this.remainingShots -= 1;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", successes=" + successes +
                ", failures=" + failures +
                ", remainingShots=" + remainingShots +
                '}';
    }
}
