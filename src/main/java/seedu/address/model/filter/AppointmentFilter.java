package seedu.address.model.filter;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Name;

public class AppointmentFilter implements Predicate<Appointment> {
    private final FilterSet<Name> nameFilters;
    private final FilterSet<SubjectName> subjectNameFilters;
    private final FilterSet<AppointmentDateTime> timeFromFilters;
    private final FilterSet<AppointmentDateTime> timeToFilters;
    private final FilterSet<Address> locationFilters;

    private final ObservableList<String> stringList = FXCollections.observableArrayList();
    private final ObservableList<String> unmodifiableStringList =
            FXCollections.unmodifiableObservableList(stringList);

    /**
     * Constructs an empty {@code AppointmentFilter} that shows all appointments by default.
     */
    public AppointmentFilter() {
        this.nameFilters = new InclusiveFilterSet<>();
        this.subjectNameFilters = new InclusiveFilterSet<>();
        this.timeFromFilters = new ExclusiveFilterSet<>();
        this.timeToFilters = new ExclusiveFilterSet<>();
        this.locationFilters = new InclusiveFilterSet<>();

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

        this.nameFilters = new InclusiveFilterSet<>(nameFilters);
        this.subjectNameFilters = new InclusiveFilterSet<>(subjectNameFilters);
        this.timeFromFilters = new ExclusiveFilterSet<>(timeFromFilters);
        this.timeToFilters = new ExclusiveFilterSet<>(timeToFilters);
        this.locationFilters = new InclusiveFilterSet<>(locationFilters);

        buildStringList();
    }

    /**
     * Checks if appointment filter contains any of the filters from another appointment filter.
     */
    public boolean hasAny(AppointmentFilter appointmentFilter) {
        return nameFilters.hasAny(appointmentFilter.nameFilters)
                || subjectNameFilters.hasAny(appointmentFilter.subjectNameFilters)
                || timeFromFilters.hasAny(appointmentFilter.timeFromFilters)
                || timeToFilters.hasAny(appointmentFilter.timeToFilters)
                || locationFilters.hasAny(appointmentFilter.locationFilters);
    }

    /**
     * Checks if appointment filter contains all of the filters from another appointment filter.
     */
    public boolean hasAll(AppointmentFilter appointmentFilter) {
        return nameFilters.hasAll(appointmentFilter.nameFilters)
                && subjectNameFilters.hasAll(appointmentFilter.subjectNameFilters)
                && timeFromFilters.hasAll(appointmentFilter.timeFromFilters)
                && timeToFilters.hasAll(appointmentFilter.timeToFilters)
                && locationFilters.hasAll(appointmentFilter.locationFilters);
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
        isFiltered = isFiltered && nameFilters.test(appointment.getName());
        isFiltered = isFiltered && subjectNameFilters.test(appointment.getSubject());
        isFiltered = isFiltered && timeFromFilters.test(appointment.getTimeFrom());
        isFiltered = isFiltered && timeToFilters.test(appointment.getTimeTo());
        isFiltered = isFiltered && locationFilters.test(appointment.getLocation());

        return isFiltered;
    }

    private void buildStringList() {
        stringList.clear();

        stringList.addAll(nameFilters.toStringList());
        stringList.addAll(subjectNameFilters.toStringList());
        stringList.addAll(timeFromFilters.toStringList());
        stringList.addAll(timeToFilters.toStringList());
        stringList.addAll(locationFilters.toStringList());
    }
}
