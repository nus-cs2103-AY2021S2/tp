package seedu.budgetbaby.model.statistics;

import java.util.List;

/**
 * Custom class which handles statistics shown by the week.
 */
public class WeekStatistic extends Statistic {

    private final String week;
    private final int x;

    WeekStatistic(String week, int x) {
        this.week = week;
        this.x = x;
    }

    @Override
    public String getDisplayedStatistic() {
        return null;
    }

    @Override
    protected List<String> getExceeded(String arg) {
        // TODO: use current week and x as filtering criteria
        return null;
    }
}
