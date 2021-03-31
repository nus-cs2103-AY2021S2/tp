package seedu.address.model.util;

import java.time.LocalDate;
import java.time.LocalTime;
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
                new Client(new Name("Bob"), new Contact("98664535"),
                    new Email("bob@gmail.com"), new AskingPrice("$800,000")),
                getTagSet("4 bedrooms", "No renovation")),
            new Property(new Name("Burghley Drive"),
                new Type("Landed"),
                new Address("12 Burghley Drive"),
                new PostalCode("558977"),
                new Deadline(LocalDate.parse("31-07-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Remark("Lowest selling price is $5,040,0000"),
                getTagSet("99 year leasehold", "Balcony")),
            new Property(new Name("Woodlands Crescent"), new Type("Hdb"),
                new Address("Blk 784 Woodlands Crescent #01-01"),
                new PostalCode("731784"),
                new Deadline(LocalDate.parse("01-08-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Client(new Name("Caleb"), new Contact("84459627"),
                    new Email("caleb_goh@gmail.com"), new AskingPrice("$350,000")),
                getTagSet("2 bedrooms", "65 square metres")),
            new Property(new Name("The Interlace"),
                new Type("Condo"),
                new Address("180 Depot Rd"),
                new PostalCode("109684"),
                new Deadline(LocalDate.parse("10-04-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Remark("Urgent!!!"),
                new Client(new Name("Freddy"), new Contact("87438807"),
                    new Email("freddy_123@hotmail.com"), new AskingPrice("$1,000,000")),
                getTagSet("Labrador Park MRT", "2015 World Building of the Year")),
            new Property(new Name("Marina One Residences"),
                new Type("Condo"),
                new Address("21 Marina Way"),
                new PostalCode("018936"),
                new Deadline(LocalDate.parse("01-06-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Remark("Has overlooking city view and 99-year leasehold"),
                new Client(new Name("Simon Lee"), new Contact("91048774"),
                    new Email("simon_lee@hotmail.com"), new AskingPrice("$2,500,000")),
                getTagSet("99 year leasehold")),
            new Property(new Name("Compassvale Walk"),
                new Type("Hdb"),
                new Address("226A Compassvale Walk #10-07"),
                new PostalCode("540236"),
                new Deadline(LocalDate.parse("01-2-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Remark("Big living room"),
                getTagSet("3 bedrooms", "Renovation needed"))
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[]{
            new Appointment(new Name("Meet Alex"),
                new Remark("To celebrate Christmas at Fullerton Hotel"),
                new Date(LocalDate.parse("25-12-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1500", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Bob"),
                new Remark("At his house"),
                new Date(LocalDate.parse("30-04-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1030", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Caleb"),
                new Remark("At Causeway point Mcdonalds"),
                new Date(LocalDate.parse("07-03-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1030", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Darren"),
                new Remark("For signing of sales agreement at HDB"),
                new Date(LocalDate.parse("12-10-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1400", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Emily"),
                new Remark("At MayFair Gardens"),
                new Date(LocalDate.parse("15-06-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1100", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Freddy"),
                new Remark("At Orchard Centrepoint's Starbucks"),
                new Date(LocalDate.parse("17-08-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1930", DateTimeFormat.INPUT_TIME_FORMAT))),
            new Appointment(new Name("Meet Simon"),
                new Remark("At Queenstown MRT station"),
                new Date(LocalDate.parse("20-09-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                new Time(LocalTime.parse("1200", DateTimeFormat.INPUT_TIME_FORMAT))),
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
