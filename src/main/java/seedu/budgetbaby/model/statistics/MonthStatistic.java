package seedu.budgetbaby.model.statistics;

import java.util.List;

/**
 * Custom class which handles statistics shown by the month.
 */
public class MonthStatistic extends Statistic {

    private final String month;
    private final int x;

    MonthStatistic(String month, int x) {
        this.month = month;
        this.x = x;
    }

    @Override
    public String getDisplayedStatistic() {
        return null;
    }

    @Override
    protected List<String> getExceeded(String arg) {
        // TODO: use current month and x as filtering criteria
        return null;
    }
}
