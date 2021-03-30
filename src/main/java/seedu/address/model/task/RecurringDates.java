package seedu.address.model.task;

/**
 * API of RecurringScheduleDates
 */
public interface RecurringDates {
    /**
     * Used to generate the output of the dates of the recurring schedule
     *
     * @param recurringSchedule Input string of recurring schedule requirements given by the user
     * @return Output string of recurring dates in the PlanIt application
     */
    String generateRecurringDates(String recurringSchedule);
}
