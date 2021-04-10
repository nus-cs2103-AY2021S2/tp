package seedu.address.model.schedule;


import java.time.LocalDateTime;

/**
 * Represents a simple period in time that can be scheduled into the scheduler.
 */

public class SimplePeriod implements Schedulable {
    private final String name;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a simple period of time that can be scheduled.
     * @param name
     * @param start
     * @param end
     */
    public SimplePeriod(String name, LocalDateTime start, LocalDateTime end) {
        assert end.isAfter(start);
        this.name = name;
        this.start = start;
        this.end = end;
    }


    @Override
    public String getNameString() {
        return name;
    }

    @Override
    public LocalDateTime getStartLocalDateTime() {
        return start;
    }

    @Override
    public LocalDateTime getTerminateLocalDateTime() {
        return end;
    }
    @Override
    public boolean isConflict(Schedulable schedulable) {
        return !(this.end.compareTo(schedulable.getStartLocalDateTime()) <= 0
                || this.start.compareTo(schedulable.getTerminateLocalDateTime()) >= 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof SimplePeriod) {
            SimplePeriod other = (SimplePeriod) obj;
            return other.start.isEqual(this.start)
                    && other.end.isEqual(this.end)
                    && other.name.equals(this.name);
        } else {
            return false;
        }
    }


}
