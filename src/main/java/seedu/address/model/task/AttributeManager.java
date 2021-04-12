package seedu.address.model.task;

import java.time.LocalDate;

import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.attributes.Description;
import seedu.address.model.task.attributes.Duration;
import seedu.address.model.task.attributes.RecurringSchedule;
import seedu.address.model.task.attributes.Status;
import seedu.address.model.task.attributes.Title;

public class AttributeManager {
    private final Date date;
    private final Description description;
    private final Duration duration;
    private final RecurringSchedule recurringSchedule;
    private final Status status;
    private final Title title;

    /**
     * AttributeManager provides access to the Task's attributes functionalities.
     *
     * @param task The task's attribute we are interested to access.
     */
    public AttributeManager(Task task) {
        this.date = task.getDate();
        this.description = task.getDescription();
        this.duration = task.getDuration();
        this.recurringSchedule = task.getRecurringSchedule();
        this.status = task.getStatus();
        this.title = task.getTitle();
    }

    public boolean dateOver() {
        return date.over();
    }


    /**
     * Checks if the given end date in recurring schedule has expired.
     *
     * @return Boolean indicating whether the end date has expired.
     */
    public boolean hasExpiredEndDate() {
        return recurringSchedule.isExpiredEndDate();
    }

    /**
     * Checks whether there are any matching recurring dates being generated.
     *
     * @return Boolean indicating whether there are any matching recurring dates.
     */
    public boolean hasNoMatchingRecurringDates() {
        return recurringSchedule.isNoMatchingRecurringDates();
    }

    /**
     * Checks if the given end date in recurring schedule has been more than 6 months of current system date.
     *
     * @return Boolean indicating whether the end date in recurring date has been more than 6 months.
     */
    public boolean hasEndDateMoreThan6Months() {
        return recurringSchedule.isEndDateMoreThan6Months();
    }

    /**
     * Checks if either the Date input or the Recurring schedule end date input is valid
     * A valid date is where months of Feb does not have more than 28 days except leap years and
     * Days did not exceed for months of 30 days (Apr, Jun, Nov, Sep) and 31 days (Jan, Mar, May, Jul, Aug, Oct, Dec)
     *
     * @return Boolean indicating whether the Date input or the end date input in Recurring Schedule is valid.
     */
    public boolean hasInvalidDate() {
        return recurringSchedule.isInvalidDate() || date.isInvalidDate();
    }

    /**
     * Checks if the provided date has the same date as this task's date.
     *
     * @param date Date to check this task's date with.
     * @return Boolean indicating whether the provided date is on the same day as this task's date.
     */
    public boolean hasSameDate(Date date) {
        return this.date.value != null && this.date.equals(date);
    }

    /**
     * Checks if the provided date string is in the schedule of this task's recurring schedule.
     * Will return false if there is no dates in the schedule.
     *
     * @param dateString Date in string format, to check if it is in this task's schedule.
     * @return Boolean indicating if the provided date is in the schedule.
     */
    public boolean isOnRecurringScheduleDate(String dateString) {
        return this.recurringSchedule.isInRecurringSchedule(dateString);
    }

    /**
     * Checks if the Date attribute contains any data.
     *
     * @return true if the String of Date isEmpty, false otherwise.
     */
    public boolean isEmptyDate() {
        return date.isEmptyValue();
    }

    /**
     * Checks if the Duration attribute contains any data.
     *
     * @return true if the String of Duration isEmpty, false otherwise.
     */
    public boolean isEmptyDuration() {
        return duration.isEmptyValue();
    }

    /**
     * Checks if the Title attribute contains any data.
     *
     * @return true if the String of Title isEmpty, false otherwise.
     */
    public boolean isEmptyTitle() {
        return title.isEmptyValue();
    }

    /**
     * Checks if the RecurringSchedule attribute contains any data.
     *
     * @return true if the String of RecurringSchedule isEmpty, false otherwise.
     */
    public boolean isEmptyRecurringSchedule() {
        return recurringSchedule.isEmptyValue();
    }

    /**
     * Returns true if this task is already done.
     */
    public boolean isDone() {
        return status.isDone();
    }

    public boolean isWithinSevenDays(LocalDate currentDate) {
        return date.isWithinSevenDays(currentDate);
    }

    public String getTitleString() {
        return title.fullTitle;
    }

    /**
     * Get the LocalDate value of the Date attribute.
     *
     * @return LocalDate of the date object.
     */
    public LocalDate getDate() {
        return date.getDate();
    }
}
