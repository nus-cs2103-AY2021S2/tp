package seedu.budgetbaby.model.statistics;

import java.util.List;

/**
 * Abstract class which encapsulates statistic classes, containing common data and methods.
 */
public abstract class Statistic {

    private int totalExpenses;

    /**
     * Gets the statistics to be displayed on the GUI. Specific implementation corresponding to type of statistic
     * shown.
     * @return details to be displayed on GUI.
     */
    public abstract String getDisplayedStatistic();

    protected int getTotalExpenses() {
        return totalExpenses;
    }

    protected void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    /**
     * Gets list containing months/weeks/days which have exceeded their budget. Used only by Month/Week/DayStatistics
     * classes.
     * @param arg parameter for display.
     * @return list containing months/week/days which have exceeded their budget.
     */
    protected List<String> getExceeded(String arg) {
        return null;
    }
}
