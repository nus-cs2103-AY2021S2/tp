package seedu.address.model.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AppointmentBook;
import seedu.address.model.PropertyBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating both {@code PropertyBook} and {@code AppointmentBook} with sample data.
 */
public class SampleDataUtil {
    public static Property[] getSampleProperties() {
        return new Property[]{
            new Property(new Name("Mayfair"),
                new Type("Condo"),
                new Address("1 Jurong East Street 32, #08-111"),
                new PostalCode("609477"),
                new Deadline(LocalDate.parse("31-12-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                    getTagSet("4 bedrooms", "No need for renovation")),
            new Property(new Name("Burghley Drive"),
                new Type("Landed"),
                new Address("12 Burghley Drive"),
                new PostalCode("123456"),
                new Deadline(LocalDate.parse("21-07-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Remark("99 year leasehold, lowest selling price is $5,040,0000"),
                    getTagSet("99 year leasehold")),
            new Property(new Name("Woodlands Crescent"), new Type("Hdb"),
                    new Address("Blk 784 Woodlands Crescent #01-01"),
                    new PostalCode("730784"),
                    new Deadline(LocalDate.parse("01-08-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                    new Client(new Name("Alice"), new Contact("91234567"),
                            new Email("alice@gmail.com"), new AskingPrice("$800,000")),
                    getTagSet("2 bedrooms", "65 square metres")),
            new Property(new Name("The Interlace"),
                new Type("Condo"),
                new Address("180 Depot Rd"),
                new PostalCode("109684"),
                new Deadline(LocalDate.parse("10-04-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Remark("Urgent!!!"),
                new Client(new Name("Alex Yeoh"), new Contact("87438807"),
                    new Email("alexyeoh@example.com"), new AskingPrice("$1,000,000")),
                    getTagSet("Labrador Park MRT", "2015 World Building of the Year")),
            new Property(new Name("Marina One Residences"),
                new Type("Condo"),
                new Address("21 Marina Way"),
                new PostalCode("018936"),
                new Deadline(LocalDate.parse("01-06-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Remark("Has overlooking city view and 99-year leasehold"),
                new Client(new Name("Simon Lee"), new Contact("91048774"),
                    new Email("simon_lee@hotmail.com"), new AskingPrice("$2,500,000")),
                    getTagSet("Freehold")),
            new Property(new Name("Compassvale Walk"),
                new Type("Hdb"),
                new Address("226A Compassvale Walk #10-07"),
                new PostalCode("540236"),
                new Deadline(LocalDate.parse("01-10-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Remark("3 bedrooms"),
                    getTagSet("3 bedrooms", "Renovation needed"))
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[]{
            new Appointment(new Name("Meet Alex"),
                new Remark("At M Hotel"),
                new Date(LocalDate.parse("25-12-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1500", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Bob"),
                new Remark("At Plaza Singapore Starbucks"),
                new Date(LocalDate.parse("01-02-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("2000", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Alice"),
                new Remark("At client's house"),
                new Date(LocalDate.parse("17-08-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1500", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Caleb"),
                new Remark("At void deck of his house"),
                new Date(LocalDate.parse("07-03-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1030", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Simon"),
                new Remark("At Queenstown MRT station"),
                new Date(LocalDate.parse("20-09-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1200", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Darren"),
                new Remark("At MBS Starbucks"),
                new Date(LocalDate.parse("12-10-2021",
                    DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT))),
                new Time(LocalTime.parse("1400", DateTimeFormatter.ofPattern("HHmm")))),
            new Appointment(new Name("Meet Emily"),
                    new Remark("At MayFair Gardens"),
                    new Date(LocalDate.parse("15-06-2021",
                        DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT))),
                    new Time(LocalTime.parse("1100", DateTimeFormatter.ofPattern("HHmm"))))
        };
    }

    /**
     * Returns a property book containing some sample properties.
     */
    public static ReadOnlyPropertyBook getSamplePropertyBook() {
        PropertyBook samplePropertyBook = new PropertyBook();
        for (Property sampleProperty : getSampleProperties()) {
            samplePropertyBook.addProperty(sampleProperty);
        }
        return samplePropertyBook;
    }

    /**
     * Returns an appointment book containing some sample appointments.
     */
    public static ReadOnlyAppointmentBook getSampleAppointmentBook() {
        AppointmentBook sampleAppointmentBook = new AppointmentBook();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAppointmentBook.addAppointment(sampleAppointment);
        }
        return sampleAppointmentBook;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
