package at.jit.readinggroup.enterprisetrapped;

public class Event {

    private boolean isFalseAttack;
    private boolean isATrap;
    private int numberOfAttackers;

    public Event(boolean isFalse) {
        this.isFalseAttack = isFalse;
        this.isATrap = ! isFalse;
        this.numberOfAttackers = 0;
    }

    public Event(int numberOfAttackers) {
        this.isFalseAttack = false;
        this.isATrap = true;
        this.numberOfAttackers = numberOfAttackers;
    }

    public boolean isFalseAttack() {
        return isFalseAttack;
    }

    public boolean isATrap() {
        return isATrap;
    }

    public int getNumberOfAttackers() {
        return numberOfAttackers;
    }

    public void disableAttacker() {
        numberOfAttackers--;
    }
}
