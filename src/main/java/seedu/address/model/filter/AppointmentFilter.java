package seedu.address.model.filter;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Name;

public class AppointmentFilter implements Predicate<Appointment> {
    private final Set<Predicate<Name>> nameFilters;
    private final Set<Predicate<SubjectName>> subjectNameFilters;
    private final Set<Predicate<AppointmentDateTime>> timeFromFilters;
    private final Set<Predicate<AppointmentDateTime>> timeToFilters;
    private final Set<Predicate<Address>> locationFilters;

    private Predicate<Name> composedNameFilter;
    private Predicate<SubjectName> composedSubjectNameFilter;
    private Predicate<AppointmentDateTime> composedTimeFromFilter;
    private Predicate<AppointmentDateTime> composedTimeToFilter;
    private Predicate<Address> composedLocationFilter;

    private final ObservableList<String> stringList = FXCollections.observableArrayList();
    private final ObservableList<String> unmodifiableStringList =
            FXCollections.unmodifiableObservableList(stringList);

    /**
     * Constructs an empty {@code AppointmentFilter} that shows all appointments by default.
     */
    public AppointmentFilter() {
        this.nameFilters = new LinkedHashSet<>();
        this.subjectNameFilters = new LinkedHashSet<>();
        this.timeFromFilters = new LinkedHashSet<>();
        this.timeToFilters = new LinkedHashSet<>();
        this.locationFilters = new LinkedHashSet<>();

        composeFilters();
        buildStringList();
    }

    /**
     * Constructs a {@code AppointmentFilter} from lists of filters.
     * Every field must be present and not null.
     */
    public AppointmentFilter(Set<Predicate<Name>> nameFilters,
            Set<Predicate<SubjectName>> subjectNameFilters,
            Set<Predicate<AppointmentDateTime>> timeFromFilters,
            Set<Predicate<AppointmentDateTime>> timeToFilters,
            Set<Predicate<Address>> locationFilters) {
        requireAllNonNull(nameFilters,
            subjectNameFilters,
            timeFromFilters,
            timeToFilters,
            locationFilters);

        this.nameFilters = nameFilters;
        this.subjectNameFilters = subjectNameFilters;
        this.timeFromFilters = timeFromFilters;
        this.timeToFilters = timeToFilters;
        this.locationFilters = locationFilters;

        composeFilters();
        buildStringList();
    }

    /**
     * Checks if appointment filter contains any of the filters from another appointment filter.
     */
    public boolean hasAny(AppointmentFilter appointmentFilter) {
        return !Collections.disjoint(this.nameFilters, appointmentFilter.nameFilters)
                || !Collections.disjoint(this.subjectNameFilters, appointmentFilter.subjectNameFilters)
                || !Collections.disjoint(this.timeFromFilters, appointmentFilter.timeFromFilters)
                || !Collections.disjoint(this.timeToFilters, appointmentFilter.timeToFilters)
                || !Collections.disjoint(this.locationFilters, appointmentFilter.locationFilters);
    }

    /**
     * Checks if appointment filter contains all of the filters from another appointment filter.
     */
    public boolean hasAll(AppointmentFilter appointmentFilter) {
        return this.nameFilters.containsAll(appointmentFilter.nameFilters)
                && this.subjectNameFilters.containsAll(appointmentFilter.subjectNameFilters)
                && this.timeFromFilters.containsAll(appointmentFilter.timeFromFilters)
                && this.timeToFilters.containsAll(appointmentFilter.timeToFilters)
                && this.locationFilters.containsAll(appointmentFilter.locationFilters);
    }

    /**
     * Add all filters from another appointment filter and merge them.
     */
    public AppointmentFilter add(AppointmentFilter appointmentFilter) {
        nameFilters.addAll(appointmentFilter.nameFilters);
        subjectNameFilters.addAll(appointmentFilter.subjectNameFilters);
        timeFromFilters.addAll(appointmentFilter.timeFromFilters);
        timeToFilters.addAll(appointmentFilter.timeToFilters);
        locationFilters.addAll(appointmentFilter.locationFilters);

        composeFilters();
        buildStringList();
        return this;
    }

    /**
     * Remove filters from this appointment filter according to another appointment filter.
     */
    public AppointmentFilter remove(AppointmentFilter appointmentFilter) {
        nameFilters.removeAll(appointmentFilter.nameFilters);
        subjectNameFilters.removeAll(appointmentFilter.subjectNameFilters);
        timeFromFilters.removeAll(appointmentFilter.timeFromFilters);
        timeToFilters.removeAll(appointmentFilter.timeToFilters);
        locationFilters.removeAll(appointmentFilter.locationFilters);

        composeFilters();
        buildStringList();
        return this;
    }

    /**
     * Returns the string list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<String> asUnmodifiableObservableList() {
        return unmodifiableStringList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentFilter)) {
            return false;
        }

        AppointmentFilter otherAppointmentFilter = (AppointmentFilter) other;
        return otherAppointmentFilter.nameFilters.equals(nameFilters)
                && otherAppointmentFilter.subjectNameFilters.equals(subjectNameFilters)
                && otherAppointmentFilter.timeFromFilters.equals(timeFromFilters)
                && otherAppointmentFilter.timeToFilters.equals(timeToFilters)
                && otherAppointmentFilter.locationFilters.equals(locationFilters);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nameFilters, subjectNameFilters, timeFromFilters, timeToFilters, locationFilters);
    }

    @Override
    public String toString() {
        return String.join(", ", stringList);
    }

    @Override
    public boolean test(Appointment appointment) {
        if (appointment == null) {
            return false;
        }

        boolean isFiltered = true;
        isFiltered = isFiltered && composedNameFilter.test(appointment.getName());
        isFiltered = isFiltered && composedSubjectNameFilter.test(appointment.getSubject());
        isFiltered = isFiltered && composedTimeFromFilter.test(appointment.getTimeFrom());
        isFiltered = isFiltered && composedTimeToFilter.test(appointment.getTimeTo());
        isFiltered = isFiltered && composedLocationFilter.test(appointment.getLocation());

        return isFiltered;
    }

    private void composeFilters() {
        this.composedNameFilter = nameFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);

        this.composedSubjectNameFilter = subjectNameFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);

        this.composedTimeFromFilter = timeFromFilters.stream()
                .reduce((x, y) -> x.and(y))
                .orElse(x -> true);

        this.composedTimeToFilter = timeToFilters.stream()
                .reduce((x, y) -> x.and(y))
                .orElse(x -> true);

        this.composedLocationFilter = locationFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);
    }

    private void buildStringList() {
        stringList.clear();

        stringList.addAll(nameFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(subjectNameFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(timeFromFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(timeToFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(locationFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
    }
}
