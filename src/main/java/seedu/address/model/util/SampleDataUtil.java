package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.model.ReadOnlyRemindMe;
import seedu.address.model.RemindMe;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.AssignmentList;
import seedu.address.model.module.Description;
import seedu.address.model.module.Exam;
import seedu.address.model.module.ExamList;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code RemindMe} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alice Pauline"), new Birthday("2000-12-12"),
                getTagSet("friends")),
            new Person(new Name("Benson Meier"), new Birthday("2000-12-12"),
                getTagSet("owesMoney", "friends")),
            new Person(new Name("Carl Kurz"), new Birthday("2000-12-12"),
                getTagSet()),
            new Person(new Name("Daniel Meier"), new Birthday("2000-12-12"),
                getTagSet("friends")),
            new Person(new Name("Elle Meyer"), new Birthday("2000-12-12"),
                getTagSet()),
            new Person(new Name("Fiona Kunz"), new Birthday("2000-12-12"),
                getTagSet()),
            new Person(new Name("George Best"), new Birthday("2000-12-12"),
                getTagSet())
        };
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new Title("CS2103"), new AssignmentList(),
                        new ExamList())
        };
    }

    public static GeneralEvent[] getSampleEvents() {
        return new GeneralEvent[] {
            new GeneralEvent(new Description("Party"), LocalDateTime.parse(("30/03/2021 2359"),
                LocalDateTimeUtil.DATETIME_FORMATTER))
        };
    }


    public static ReadOnlyRemindMe getSampleRemindMe() {
        RemindMe sampleRm = new RemindMe();
        for (Module sampleMod : getSampleModules()) {
            sampleRm.addModule(sampleMod);
        }

        for (Person samplePerson : getSamplePersons()) {
            sampleRm.addPerson(samplePerson);
        }

        for (GeneralEvent sampleGeneralEvent : getSampleEvents()) {
            sampleRm.addEvent(sampleGeneralEvent);
        }

        return sampleRm;
    }
    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static List<Assignment> getAssignments(String... assignments) {
        //todo either take in str representation of date
        LocalDateTime dateTime = LocalDateTime.of(2021, 03, 20, 23, 59);
        return Arrays.stream(assignments)
                .map(Description::new)
                .map(description -> new Assignment(description, dateTime, new Tag("FAKE")))
                .collect(Collectors.toList());
    }

    public static List<Exam> getExams(String... exams) {
        return Arrays.stream(exams)
                .map(date -> LocalDateTime.parse(date, Exam.EXAM_DATE_FORMATTER))
                .map(date -> new Exam(date, new Tag("PLACEHOLDER")))
                .collect(Collectors.toList());
    }
}
