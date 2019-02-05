package beans;

/**
 * 劳务费
 */
public class Labour extends Item{
    private int priority;

    @Override
    public String toString() {
        return "Labour{" +
                "priority=" + priority +
                "} " + super.toString();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
