package seedu.student.model.util;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.student.model.ReadOnlyStudentBook;
import seedu.student.model.StudentBook;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.Address;
import seedu.student.model.student.Email;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.MedicalDetails;
import seedu.student.model.student.Name;
import seedu.student.model.student.Phone;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.Student;
import seedu.student.model.student.VaccinationStatus;

/**
 * Contains utility methods for populating {@code StudentBook} with sample data.
 */
public class SampleDataUtil {

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new MatriculationNumber("A0182345T"), new Faculty("COM"),
                    new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new VaccinationStatus("vaccinated"),
                    new MedicalDetails("none"), new SchoolResidence("PGPH")),
            new Student(new Name("Bernice Yu"), new MatriculationNumber("A0175678U"), new Faculty("LAW"),
                    new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new VaccinationStatus("unvaccinated"), new MedicalDetails("shellfish allergy"),
                    new SchoolResidence("RH")),
            new Student(new Name("Charlotte Oliveiro"), new MatriculationNumber("A0164567V"), new Faculty("DNUS"),
                    new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new VaccinationStatus("vaccinated"),
                    new MedicalDetails("none"), new SchoolResidence("SH")),
            new Student(new Name("David Li"), new MatriculationNumber("A0209875D"), new Faculty("MED"),
                    new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new VaccinationStatus("unvaccinated"),
                    new MedicalDetails("history of anaphylaxis"), new SchoolResidence("CAPT")),
            new Student(new Name("Irfan Ibrahim"), new MatriculationNumber("A0214432E"), new Faculty("SPP"),
                    new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new VaccinationStatus("vaccinated"),
                    new MedicalDetails("none"), new SchoolResidence("TC")),
            new Student(new Name("Roy Balakrishnan"), new MatriculationNumber("A0221234N"), new Faculty("SCI"),
                    new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new VaccinationStatus("unvaccinated"),
                    new MedicalDetails("penicillin allergy"), new SchoolResidence("UTR")),
            new Student(new Name("To Yu Shan"), new MatriculationNumber("A0182330T"), new Faculty("BIZ"),
                    new Phone("81864219"), new Email("shan@to.com"),
                    new Address("Blk 43 Serangoon Gardens Street 72, #11-06"), new VaccinationStatus("vaccinated"),
                    new MedicalDetails("none"), new SchoolResidence("PGPR")),
            new Student(new Name("Pang Xue Qian Lenny"), new MatriculationNumber("A0375678U"), new Faculty("SDE"),
                    new Phone("99925433"), new Email("lenny@pang.com"),
                    new Address("6 Orchard Road View, #06-10"),
                    new VaccinationStatus("unvaccinated"), new MedicalDetails("morphine allergy"),
                    new SchoolResidence("RH")),
            new Student(new Name("Gabriella Chang"), new MatriculationNumber("A0164367V"), new Faculty("ENG"),
                    new Phone("99062788"), new Email("gabriella.change@example.com"),
                    new Address("62 Jalan Adat"), new VaccinationStatus("vaccinated"),
                    new MedicalDetails("none"), new SchoolResidence("RH")),
            new Student(new Name("Jeromy Kee"), new MatriculationNumber("A0209175D"), new Faculty("FASS"),
                    new Phone("91061216"), new Email("jeromy.kee@example.com"),
                    new Address("76 Sin Ming Terrace"), new VaccinationStatus("unvaccinated"),
                    new MedicalDetails("history of anaphylaxis"), new SchoolResidence("RVRC")),
            new Student(new Name("Neo Ching Wi"), new MatriculationNumber("A0214412E"), new Faculty("DEN"),
                    new Phone("98654569"), new Email("neo.ching.wi@example.com"),
                    new Address("Blk 21 Sengkang Street 14, #01-05"), new VaccinationStatus("vaccinated"),
                    new MedicalDetails("none"), new SchoolResidence("RC4")),
            new Student(new Name("Ayden Toy"), new MatriculationNumber("A0224234N"), new Faculty("SCI"),
                    new Phone("97055955"), new Email("ayden.toy@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new VaccinationStatus("unvaccinated"),
                    new MedicalDetails("penicillin allergy"), new SchoolResidence("UTR")),
            new Student(new Name("Bernard Shum"), new MatriculationNumber("A0164360V"), new Faculty("ENG"),
                    new Phone("87538557"), new Email("bernard.sh@example.com"),
                    new Address("64 Aljunied Vista"), new VaccinationStatus("vaccinated"),
                    new MedicalDetails("none"), new SchoolResidence("RH")),
            new Student(new Name("Cora Wong"), new MatriculationNumber("A0209475D"), new Faculty("FASS"),
                    new Phone("91061216"), new Email("jeromy.kee@example.com"),
                    new Address("51 Mandai Junction"), new VaccinationStatus("unvaccinated"),
                    new MedicalDetails("history of anaphylaxis"), new SchoolResidence("RVRC")),
            new Student(new Name("Godfrey Selverajah"), new MatriculationNumber("A0214512E"), new Faculty("DEN"),
                    new Phone("93513743"), new Email("godfrey.selverajah@example.com"),
                    new Address("Blk 234 Lorong 3 Queenstown, #07-04"), new VaccinationStatus("vaccinated"),
                    new MedicalDetails("none"), new SchoolResidence("RC4")),
            new Student(new Name("Samihah Nordin"), new MatriculationNumber("A0224235N"), new Faculty("SCI"),
                    new Phone("94058046"), new Email("samihah@example.com"),
                    new Address("Blk 112 Lorong 7 Buona Vista, #08-21"), new VaccinationStatus("unvaccinated"),
                    new MedicalDetails("penicillin allergy"), new SchoolResidence("UTR"))
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[] {
            new Appointment(new MatriculationNumber("A0182345T"), LocalDate.parse("2021-04-15"),
                    LocalTime.parse("10:00")),
            new Appointment(new MatriculationNumber("A0175678U"), LocalDate.parse("2021-04-15"),
                    LocalTime.parse("11:00")),
            new Appointment(new MatriculationNumber("A0164567V"), LocalDate.parse("2021-04-16"),
                    LocalTime.parse("13:00")),
            new Appointment(new MatriculationNumber("A0209875D"), LocalDate.parse("2021-04-16"),
                    LocalTime.parse("14:00")),
            new Appointment(new MatriculationNumber("A0214432E"), LocalDate.parse("2021-04-16"),
                    LocalTime.parse("10:00")),
            new Appointment(new MatriculationNumber("A0221234N"), LocalDate.parse("2021-02-01"),
                    LocalTime.parse("10:00"))
        };
    }

    public static ReadOnlyStudentBook getSampleStudentBook() {
        StudentBook sampleAb = new StudentBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }
}
