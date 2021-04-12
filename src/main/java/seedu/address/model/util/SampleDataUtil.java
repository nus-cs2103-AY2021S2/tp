package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AppointmentBook;
import seedu.address.model.GradeBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.TutorBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.model.reminder.ReadOnlyReminderTracker;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDate;
import seedu.address.model.reminder.ReminderTracker;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleTracker;
import seedu.address.model.subject.SubjectExperience;
import seedu.address.model.subject.SubjectLevel;
import seedu.address.model.subject.SubjectList;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectQualification;
import seedu.address.model.subject.SubjectRate;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Gender;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Notes;
import seedu.address.model.tutor.Phone;
import seedu.address.model.tutor.Tutor;

/**
 * Contains utility methods for populating {@code TutorTracker} with sample data.
 */
public class SampleDataUtil {
    public static Tutor[] getSamplePersons() {
        return new Tutor[] {
            new Tutor(new Name("Alex Yeoh"), new Gender("Male"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Notes("Alex is a cool guy, he's very good at teaching mathematics."
                            + "Only available during the weekends."),
                    getSampleSubjectList(getSampleTutorSubject("Mathematics", "Secondary 4", "60", "5", "A-Level")),
                    getTagSet()),
            new Tutor(new Name("Bernice Yu"), new Gender("Female"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Notes(null),
                    getSampleSubjectList(
                            getSampleTutorSubject("English", "Secondary 4", "60", "4", "A-Level"),
                            getSampleTutorSubject("Geography", "Secondary 4", "60", "4", "A-Level")),
                    getTagSet()),
            new Tutor(new Name("Charlotte Oliveiro"), new Gender("Female"), new Phone("93210283"),
                   new Email("charlotte@example.com"),
                   new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Notes("Very good at teaching English"),
                    getSampleSubjectList(
                            getSampleTutorSubject("English", "Secondary 3", "50", "5", "A-Level"),
                            getSampleTutorSubject("Literature", "Secondary 3", "50", "5", "A-Level")),
                    getTagSet()),
            new Tutor(new Name("David Li"), new Gender("Male"), new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Notes("note"),
                    getSampleSubjectList(getSampleTutorSubject("Physics", "Secondary 3", "50", "6", "A-Level")),
                    getTagSet()),
            new Tutor(new Name("Irfan Ibrahim"), new Gender("Male"), new Phone("92492021"),
                     new Email("irfan@example.com"),
                     new Address("Blk 47 Tampines Street 20, #17-35"), new Notes("Alittle impatient sometimes"),
                     getSampleSubjectList(getSampleTutorSubject("Geography", "Secondary 2", "40", "3", "A-Level")),
                     getTagSet()),
            new Tutor(new Name("Roy Balakrishnan"), new Gender("Male"), new Phone("92624417"),
                     new Email("royb@example.com"),
                     new Address("Blk 45 Aljunied Street 85, #11-31"), new Notes(null),
                     getSampleSubjectList(getSampleTutorSubject("History", "Secondary 2", "40", "7", "A-Level")),
                     getTagSet())
        };
    }

    public static Appointment[] getSampleAppointment() {
        return new Appointment[] {
            new Appointment(new Name("Alex Yeoh"), new SubjectName("Mathematics"),
                     new AppointmentDateTime("2021-03-24 2:00PM"),
                     new AppointmentDateTime("2021-03-24 4:00PM"),
                     new Address("Geylang")),
            new Appointment(new Name("Bernice Yu"), new SubjectName("Science"),
                     new AppointmentDateTime("2021-03-27 3:00PM"),
                     new AppointmentDateTime("2021-03-27 5:00PM"),
                     new Address("Hougang"))
        };
    }

    public static Grade[] getSampleGrade() {
        return new Grade[]{
            new Grade(new SubjectName("Science"),
                    new GradedItem("Lab 1"),
                        GradeEnum.valueOf("A1")),
            new Grade(new SubjectName("Mathematics"),
                    new GradedItem("Final"),
                        GradeEnum.valueOf("B3")),
            new Grade(new SubjectName("English"),
                    new GradedItem("Midterm"),
                        GradeEnum.valueOf("C5"))
        };
    }

    public static Schedule[] getSampleSchedule() {
        return new Schedule[] {
            new Schedule(new Title("Math Tuition Homework"),
                    new AppointmentDateTime("2021-03-25 2:00PM"),
                    new AppointmentDateTime("2021-03-25 3:00PM"),
                    new Description("Chapter 5")),
            new Schedule(new Title("Science Tuition Homework"),
                    new AppointmentDateTime("2021-03-26 3:00PM"),
                    new AppointmentDateTime("2021-03-26 5:00PM"),
                    new Description("Chapter 6"))
        };
    }

    public static Reminder[] getSampleReminder() {
        return new Reminder[] {
            new Reminder(new Description("Bring Math Textbook for Tuition"),
                    new ReminderDate("2021-03-31")),
            new Reminder(new Description("Science Tuition Fee Due"),
                    new ReminderDate("2021-03-31"))
        };
    }

    public static ReadOnlyReminderTracker getSampleReminderTracker() {
        ReminderTracker sampleRt = new ReminderTracker();
        for (Reminder sampleReminder : getSampleReminder()) {
            sampleRt.addReminder(sampleReminder);
        }
        return sampleRt;
    }

    public static ReadOnlyScheduleTracker getSampleScheduleTracker() {
        ScheduleTracker sampleSt = new ScheduleTracker();
        for (Schedule sampleSchedule : getSampleSchedule()) {
            sampleSt.addSchedule(sampleSchedule);
        }
        return sampleSt;
    }

    public static ReadOnlyTutorBook getSampleTutorBook() {
        TutorBook sampleAb = new TutorBook();
        for (Tutor sampleTutor : getSamplePersons()) {
            sampleAb.addTutor(sampleTutor);
        }
        return sampleAb;
    }

    public static TutorSubject getSampleTutorSubject(
            String subjectName,
            String subjectLevel,
            String subjectRate,
            String subjectExperience,
            String subjectQualification) {
        SubjectName name = new SubjectName(subjectName);
        SubjectLevel level = new SubjectLevel(subjectLevel);
        SubjectRate rate = new SubjectRate(subjectRate);
        SubjectExperience experience = new SubjectExperience(subjectExperience);
        SubjectQualification qualification = new SubjectQualification(subjectQualification);
        return new TutorSubject(name, level, rate, experience, qualification);
    }

    public static SubjectList getSampleSubjectList(TutorSubject... subjects) {
        SubjectList subjectList = new SubjectList();
        for (TutorSubject subject : subjects) {
            subjectList.add(subject);
        }
        return subjectList;
    }

    public static ReadOnlyAppointmentBook getSampleAppointmentBook() {
        AppointmentBook sampleAb = new AppointmentBook();
        for (Appointment samplePerson : getSampleAppointment()) {
            sampleAb.addAppointment(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyGradeBook getSampleGradeBook() {
        GradeBook sampleAb = new GradeBook();
        for (Grade sampleGrade : getSampleGrade()) {
            sampleAb.addGrade(sampleGrade);
        }
        return sampleAb;
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
