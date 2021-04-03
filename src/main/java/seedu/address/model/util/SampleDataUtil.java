package seedu.address.model.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentSchedule;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.person.Address;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(UUID.fromString("b409e370-8250-4882-a1f4-96fe700b4a43"),
                    new Name("Alex Karev"), new Phone("87438807"), new Email("alexkarev@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("PotentialExpressiveDisorder")),
            new Patient(UUID.fromString("1dba7799-d7e2-42b3-8e29-968c5f755707"),
                    new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("Elderly", "Dementia")),
            new Patient(UUID.fromString("58f03932-3222-4049-a901-574cbd969fda"),
                    new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("BrainDamage")),
            new Patient(UUID.fromString("79008edc-5f0e-4d3b-bc0b-6a7da838e89b"),
                    new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("Headache")),
            new Patient(UUID.fromString("889ba8ca-bd6d-4a71-8641-5de7e7f5c743"),
                    new Name("Edward Hyde"), new Phone("92492021"), new Email("edward@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("ViolentTendencies")),
            new Patient(UUID.fromString("748f9bc7-3830-4f2b-b43e-daca7cf4cc4b"),
                    new Name("Freddie Highmore"), new Phone("92624417"), new Email("fredh@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("PotentialAutism"))
        };
    }

    public static ReadOnlyAddressBook<Patient> getSamplePatientRecords() {
        AddressBook<Patient> samplePatientRecords = new AddressBook<>();
        for (Patient samplePatient : getSamplePatients()) {
            samplePatientRecords.addPerson(samplePatient);
        }
        return samplePatientRecords;
    }

    public static Doctor[] getSampleDoctors() {
        return new Doctor[] {
            new Doctor(UUID.fromString("ad9b1acb-286f-4e20-ae4a-00604b96f456"),
                    new Name("Dr Meredith Grey"), getTagSet("GreysAnatomy")),
            new Doctor(UUID.fromString("67ba8da5-356c-4b9d-9828-f144574daae3"),
                    new Name("Dr Who"), getTagSet("DoctorWho")),
            new Doctor(UUID.fromString("994c0ccf-e185-4895-84ea-205373331e22"),
                    new Name("Dr Strange"), getTagSet("BenedictCumberbatch")),
            new Doctor(UUID.fromString("810493c6-0a23-405b-8c53-d3b13cb9dcb5"),
                    new Name("Dr Jekyll"), getTagSet("StrangeCase")),
            new Doctor(UUID.fromString("a7fa98cc-15e0-4030-9ade-d42be3f18ba0"),
                    new Name("Dr Murphy"), getTagSet("TheGoodDoctor")),
            new Doctor(UUID.fromString("1fddebf5-3712-49b3-a44f-af755caa8ac5"),
                    new Name("Dr Drake Ramoray"), getTagSet("JoeyTribbiani"))
        };
    }

    public static ReadOnlyAddressBook<Doctor> getSampleDoctorRecords() {
        AddressBook<Doctor> sampleDoctorRecords = new AddressBook<>();
        for (Doctor sampleDoctor : getSampleDoctors()) {
            sampleDoctorRecords.addPerson(sampleDoctor);
        }
        return sampleDoctorRecords;
    }

    public static Appointment[] getSampleAppointments() {
        List<Patient> samplePatientRecordsList = getSamplePatientRecords().getPersonList();
        List<Doctor> sampleDoctorRecordsList = getSampleDoctorRecords().getPersonList();
        return new Appointment[] {
            new Appointment(samplePatientRecordsList.get(1).getUuid(),
                    sampleDoctorRecordsList.get(1).getUuid(),
                    new Timeslot(LocalDateTime.of(2021, 1, 1, 12, 0), Duration.ofMinutes(60)),
                    getTagSet("Consultation")),
            new Appointment(samplePatientRecordsList.get(0).getUuid(),
                    sampleDoctorRecordsList.get(1).getUuid(),
                    new Timeslot(LocalDateTime.of(2005, 3, 26, 17, 16), Duration.ofMinutes(25)),
                    getTagSet("TimeTravelScan")),
            new Appointment(samplePatientRecordsList.get(4).getUuid(),
                    sampleDoctorRecordsList.get(3).getUuid(),
                    new Timeslot(LocalDateTime.of(2020, 1, 5, 12, 0), Duration.ofMinutes(141)),
                    getTagSet("AlterEgoExamination")),
            new Appointment(samplePatientRecordsList.get(5).getUuid(),
                    sampleDoctorRecordsList.get(4).getUuid(),
                    new Timeslot(LocalDateTime.of(2017, 9, 25, 10, 0), Duration.ofMinutes(44)),
                    getTagSet("AutismDiagnosis")),
        };
    }

    public static ReadOnlyAppointmentSchedule getSampleAppointmentSchedule() {
        AppointmentSchedule sampleAppointmentSchedule = new AppointmentSchedule();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAppointmentSchedule.addAppointment(sampleAppointment);
        }
        return sampleAppointmentSchedule;
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
