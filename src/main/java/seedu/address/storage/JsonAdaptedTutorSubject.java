package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.subject.SubjectExperience;
import seedu.address.model.subject.SubjectLevel;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectQualification;
import seedu.address.model.subject.SubjectRate;
import seedu.address.model.subject.TutorSubject;

public class JsonAdaptedTutorSubject {

    private final String subjectName;
    private final String subjectLevel;
    private final String subjectRate;
    private final String subjectExperience;
    private final String subjectQualification;

    /**
     * Constructs a {@code JsonAdaptedTutorSubject} with the given details.
     */
    @JsonCreator
    public JsonAdaptedTutorSubject(@JsonProperty("subjectName") String subjectName,
                                   @JsonProperty("subjectLevel") String subjectLevel,
                                   @JsonProperty("subjectRate") String subjectRate,
                                   @JsonProperty("subjectExperience") String subjectExperience,
                                   @JsonProperty("subjectQualification") String subjectQualification) {

        this.subjectName = subjectName;
        this.subjectLevel = subjectLevel;
        this.subjectRate = subjectRate;
        this.subjectExperience = subjectExperience;
        this.subjectQualification = subjectQualification;

    }

    /**
     * Converts a given {@code TutorSubject} into this class for Jackson use.
     */
    public JsonAdaptedTutorSubject(TutorSubject source) {
        subjectName = source.getName().toString();
        subjectLevel = source.getLevel().toString();
        subjectRate = source.getRate().toString();
        subjectExperience = source.getExperience().toString();
        subjectQualification = source.getQualification().toString();
    }

    /**
     * Converts this Jackson-friendly adapted tutor subject object into the model's {@code TutorSubject} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutor subject.
     */
    public TutorSubject toModelType() throws IllegalValueException {
        if (!SubjectName.isValidName(subjectName)) {
            throw new IllegalValueException(SubjectName.MESSAGE_CONSTRAINTS);
        }
        SubjectName modelSubjectName = new SubjectName(subjectName);

        if (!SubjectLevel.isValidLevel(subjectLevel)) {
            throw new IllegalValueException(SubjectLevel.MESSAGE_CONSTRAINTS);
        }
        SubjectLevel modelSubjectLevel = new SubjectLevel(subjectLevel);

        if (!SubjectRate.isValidRate(subjectRate)) {
            throw new IllegalValueException(SubjectRate.MESSAGE_CONSTRAINTS);
        }
        SubjectRate modelSubjectRate = new SubjectRate(subjectRate);

        if (!SubjectExperience.isValidExperience(subjectExperience)) {
            throw new IllegalValueException(SubjectExperience.MESSAGE_CONSTRAINTS);
        }
        SubjectExperience modelSubjectExperience = new SubjectExperience(subjectExperience);

        if (!SubjectQualification.isValidQualification(subjectQualification)) {
            throw new IllegalValueException(SubjectQualification.MESSAGE_CONSTRAINTS);
        }
        SubjectQualification modelSubjectQualification = new SubjectQualification(subjectQualification);

        TutorSubject modelTutorSubject = new TutorSubject(
                modelSubjectName,
                modelSubjectLevel,
                modelSubjectRate,
                modelSubjectExperience,
                modelSubjectQualification
        );

        return modelTutorSubject;
    }
}
