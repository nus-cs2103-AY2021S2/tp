package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Name;
import seedu.address.testutil.TypicalAppointments;

/**
 * Integration tests for AppointmentFilter.
 */
public class AppointmentFilterTest {
    private Set<Predicate<Name>> nameFilters;
    private Set<Predicate<SubjectName>> subjectNameFilters;
    private Set<Predicate<AppointmentDateTime>> timeFromFilters;
    private Set<Predicate<AppointmentDateTime>> timeToFilters;
    private Set<Predicate<Address>> locationFilters;

    @BeforeEach
    public void setUp() {
        this.nameFilters = new LinkedHashSet<>();
        this.subjectNameFilters = new LinkedHashSet<>();
        this.timeFromFilters = new LinkedHashSet<>();
        this.timeToFilters = new LinkedHashSet<>();
        this.locationFilters = new LinkedHashSet<>();
    }

    @Test
    public void test() {
        Appointment maths = TypicalAppointments.MATHS_APPOINTMENT;
        Appointment science = TypicalAppointments.SCIENCE_APPOINTMENT;

        nameFilters.add(new NameFilter(maths.getName().fullName));
        subjectNameFilters.add(new SubjectNameFilter(maths.getSubject().name));
        timeFromFilters.add(new AppointmentDateTimeFilter(maths.getTimeFrom().toStorageString()));
        timeToFilters.add(new AppointmentDateTimeFilter(maths.getTimeTo().toStorageString()));
        locationFilters.add(new AddressFilter(maths.getLocation().value));

        AppointmentFilter appointmentFilter = new AppointmentFilter(
                nameFilters,
                subjectNameFilters,
                timeFromFilters,
                timeToFilters,
                locationFilters);

        assertTrue(appointmentFilter.test(maths));
        assertFalse(appointmentFilter.test(science));
    }
}
