package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.appointment.AppointmentDateTime;

public class AppointmentDateTimeFilter implements Predicate<AppointmentDateTime> {
    public static final String MESSAGE_CONSTRAINTS = "AppointmentDateTimeFilter should be in"
            + " YYYY-MM-DD HH:MM AM/PM format."
            + " Optionally, the inequalities >, <, >=, <=, = can be used";

    public static final String INEQUALITY_VALIDATION_REGEX = "^[><]?=?";

    public final String appointmentDateTimeFilterInequality;
    public final AppointmentDateTime appointmentDateTimeFilter;

    /**
     * Constructs a {@code AppointmentDateTimeFilter}.
     *
     * @param appointmentDateTimeFilter A valid appointment date time filter.
     */
    public AppointmentDateTimeFilter(String appointmentDateTimeFilter) {
        requireNonNull(appointmentDateTimeFilter);
        checkArgument(isValidAppointmentDateTimeFilter(appointmentDateTimeFilter), MESSAGE_CONSTRAINTS);

        String dateTimeString = appointmentDateTimeFilter.replaceAll(INEQUALITY_VALIDATION_REGEX, "");
        this.appointmentDateTimeFilterInequality = appointmentDateTimeFilter.replaceAll(dateTimeString, "");
        this.appointmentDateTimeFilter = new AppointmentDateTime(dateTimeString);
    }

    /**
     * Returns true if a given string is a valid appointment date time filter.
     */
    public static boolean isValidAppointmentDateTimeFilter(String test) {
        String dateTimeString = test.replaceAll(INEQUALITY_VALIDATION_REGEX, "");
        return AppointmentDateTime.isValidDateTime(dateTimeString);
    }

    @Override
    public String toString() {
        return "Date Time: " + appointmentDateTimeFilterInequality + " " + appointmentDateTimeFilter.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentDateTimeFilter)) {
            return false;
        }

        AppointmentDateTimeFilter otherFilter = (AppointmentDateTimeFilter) other;
        return otherFilter.appointmentDateTimeFilterInequality.equals(appointmentDateTimeFilterInequality)
                && otherFilter.appointmentDateTimeFilter.equals(appointmentDateTimeFilter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentDateTimeFilterInequality, appointmentDateTimeFilter);
    }

    @Override
    public boolean test(AppointmentDateTime appointmentDateTime) {
        if (appointmentDateTime == null) {
            return false;
        }

        boolean isFiltered = false;

        isFiltered = isFiltered || appointmentDateTimeFilterInequality.isBlank()
                && appointmentDateTime.equals(appointmentDateTimeFilter);

        isFiltered = isFiltered || appointmentDateTimeFilterInequality.contains("=")
                && appointmentDateTime.equals(appointmentDateTimeFilter);

        isFiltered = isFiltered || appointmentDateTimeFilterInequality.contains(">")
                && appointmentDateTime.isAfter(appointmentDateTimeFilter);

        isFiltered = isFiltered || appointmentDateTimeFilterInequality.contains("<")
                && appointmentDateTime.isBefore(appointmentDateTimeFilter);

        return isFiltered;
    }
}
