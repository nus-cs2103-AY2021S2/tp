package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLessonInSchedule {

    private final String lessonDetails;
    private final List<JsonAdaptedPerson> personList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given {@code lessonDetails} and {@code personNames}.
     */
    @JsonCreator
    public JsonAdaptedLessonInSchedule(@JsonProperty("lessonDetails") String lessonDetails,
                                       @JsonProperty("personList") List<JsonAdaptedPerson> personList) {
        this.lessonDetails = lessonDetails;
        if (personList != null) {
            this.personList.addAll(personList);
        }
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLessonInSchedule(Lesson source) {
        lessonDetails = source.formatString();
        personList.addAll(source.getPerson().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted subject.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (!Lesson.isValidLesson(Lesson.getDetails(lessonDetails))) {
            throw new IllegalValueException(Lesson.MESSAGE_CONSTRAINTS);
        }
        /*
        final List<Name> personName = new ArrayList<>();
        for (JsonAdaptedName name: this.personList) {
            personName.add(name.toModelType());
        }

        final Set<Name> modelNames = new HashSet<>(personName);
         */
        final List<Person> persons = new ArrayList<>();
        for (JsonAdaptedPerson person : this.personList) {
            persons.add(person.toModelType());
        }
        final Set<Person> modelPersons = new HashSet<>(persons);

        return new Lesson(lessonDetails, modelPersons);
    }

}
