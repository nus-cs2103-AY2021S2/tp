package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Patient}, {@code Doctor},
 * and {@code Timeslot}, and {@code Appointment} objects to be used in tests.
 */
public class TypicalAppObjects {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    // Manually added - Doctor details
    public static final String DRGRAY = "Dr. Gray";
    public static final String DRWHO = "Dr. Who";
    public static final String DRHOW = "Dr. How";
    public static final String DRSTRANGE = "Dr. Strange";
    public static final String DRWHICH = "Dr. Which";
    public static final String DRWHY = "Dr. Why";
    public static final String DRWHEN = "Dr. When";

    // Manually added - Timeslot details
    public static final Duration APPOINTMENT_DURATION = Duration.ofHours(1);
    public static final Timeslot TIMESLOT_1HOUR_8AM = new Timeslot(
            LocalDateTime.of(2021, 1, 1, 8, 0, 0), APPOINTMENT_DURATION);
    public static final Timeslot TIMESLOT_1HOUR_9AM = new Timeslot(
            LocalDateTime.of(2021, 1, 1, 9, 0, 0), APPOINTMENT_DURATION);
    public static final Timeslot TIMESLOT_1HOUR_10AM = new Timeslot(
            LocalDateTime.of(2021, 1, 1, 10, 0, 0), APPOINTMENT_DURATION);
    public static final Timeslot TIMESLOT_1HOUR_11AM = new Timeslot(
            LocalDateTime.of(2021, 1, 1, 11, 0, 0), APPOINTMENT_DURATION);
    public static final Timeslot TIMESLOT_1HOUR_12PM = new Timeslot(
            LocalDateTime.of(2021, 1, 1, 12, 0, 0), APPOINTMENT_DURATION);
    public static final Timeslot TIMESLOT_1HOUR_1PM = new Timeslot(
            LocalDateTime.of(2021, 1, 1, 13, 0, 0), APPOINTMENT_DURATION);
    public static final Timeslot TIMESLOT_1HOUR_2PM = new Timeslot(
            LocalDateTime.of(2021, 1, 1, 14, 0, 0), APPOINTMENT_DURATION);

    public static final Appointment ALICE_DRGRAY = new AppointmentBuilder()
            .withPatient(ALICE).withDoctor(DRGRAY).withTimeslot(TIMESLOT_1HOUR_8AM).build();
    public static final Appointment BENSON_DRWHO = new AppointmentBuilder()
            .withPatient(BENSON).withDoctor(DRWHO).withTimeslot(TIMESLOT_1HOUR_9AM).build();
    public static final Appointment CARL_DRHOW = new AppointmentBuilder()
            .withPatient(CARL).withDoctor(DRHOW).withTimeslot(TIMESLOT_1HOUR_10AM).build();
    public static final Appointment DANIEL_DRSTRANGE = new AppointmentBuilder()
            .withPatient(DANIEL).withDoctor(DRSTRANGE).withTimeslot(TIMESLOT_1HOUR_11AM).build();
    public static final Appointment ELLE_DRWHICH = new AppointmentBuilder()
            .withPatient(ELLE).withDoctor(DRWHICH).withTimeslot(TIMESLOT_1HOUR_12PM).build();
    public static final Appointment FIONA_DRWHY = new AppointmentBuilder()
            .withPatient(FIONA).withDoctor(DRWHY).withTimeslot(TIMESLOT_1HOUR_1PM).build();
    public static final Appointment GEORGE_DRWHEN = new AppointmentBuilder()
            .withPatient(GEORGE).withDoctor(DRWHEN).withTimeslot(TIMESLOT_1HOUR_2PM).build();

    private TypicalAppObjects() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns an {@code AppointmentSchedule} with all the typical appointments.
     */
    public static AppointmentSchedule getTypicalAppointmentSchedule() {
        AppointmentSchedule as = new AppointmentSchedule();
        for (Appointment appt : getTypicalAppointments()) {
            as.addAppointment(appt);
        }
        return as;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(ALICE_DRGRAY, BENSON_DRWHO,
                CARL_DRHOW, DANIEL_DRSTRANGE, ELLE_DRWHICH, FIONA_DRWHY, GEORGE_DRWHEN));
    }
}
