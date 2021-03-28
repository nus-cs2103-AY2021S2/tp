package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.GradeBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.grade.Grade;

public class JsonSerializableGradeBook {

    public static final String MESSAGE_DUPLICATE_GRADE = "Persons list contains "
            + "duplicate grade(s).";

    private final List<JsonAdaptedGrade> grades = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGradeBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableGradeBook(@JsonProperty("grades") List<JsonAdaptedGrade> gradeList) {
        this.grades.addAll(gradeList);
    }

    /**
     * Converts a given {@code ReadOnlyGradeBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGradeBook}.
     */
    public JsonSerializableGradeBook(ReadOnlyGradeBook source) {
        this.grades.addAll(source.getGradeList().stream()
                .map(JsonAdaptedGrade::new).collect(Collectors.toList()));
    }


    /**
     * Converts this grade book into the model's {@code GradeBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GradeBook toModelType() throws IllegalValueException {
        GradeBook gradeBook = new GradeBook();
        for (JsonAdaptedGrade jsonAdaptedGrade : grades) {
            Grade grade = jsonAdaptedGrade.toModelType();
            if (gradeBook.hasGrade(grade)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GRADE);
            }
            gradeBook.addGrade(grade);
        }
        return gradeBook;
    }
}
