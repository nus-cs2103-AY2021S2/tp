package seedu.budgetbaby.model.statistics;

import java.util.List;

/**
 * Custom class which handles statistics shown by the day.
 */
public class DayStatistic extends Statistic {

    private final String day;
    private final int x;

    DayStatistic(String day, int x) {
        this.day = day;
        this.x = x;
    }

    @Override
    public String getDisplayedStatistic() {
        return null;
    }

    @Override
    protected List<String> getExceeded(String arg) {
        // TODO: use current day and x as filtering criteria
        return null;
    }
}
