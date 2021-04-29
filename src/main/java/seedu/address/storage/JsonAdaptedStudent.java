package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String studyLevel;
    private final String guardianPhone;
    private final String relationship;

    private final List<JsonAdaptedSession> sessions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("studyLevel") String studyLevel,
                              @JsonProperty("guardianPhone") String guardianPhone,
                              @JsonProperty("relationship") String relationship,
                              @JsonProperty("sessions") List<JsonAdaptedSession> sessions) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.studyLevel = studyLevel;
        this.guardianPhone = guardianPhone;
        this.relationship = relationship;
        this.sessions.addAll(sessions);
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        studyLevel = source.getStudyLevel();
        guardianPhone = source.getGuardianPhone().value;
        relationship = source.getRelationship();
        sessions.addAll(source.getListOfSessions().stream()
                .map(session -> session instanceof RecurringSession
                        ? new JsonAdaptedRecurringSession((RecurringSession) session)
                        : new JsonAdaptedSession(session))
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (studyLevel == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Study Level"));
        }
        final String modelStudyLevel = studyLevel;

        if (guardianPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(guardianPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelGuardianPhone = new Phone(guardianPhone);

        if (relationship == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "relationship"));
        }
        final String modelRelationship = relationship;

        final List<Session> modelSession = new ArrayList<>();
        for (JsonAdaptedSession jsonAdaptedSession : sessions) {
            Session session = jsonAdaptedSession.toModelType();
            modelSession.add(session);
        }

        return new Student(modelName, modelPhone, modelEmail, modelAddress, modelStudyLevel, modelGuardianPhone,
            modelRelationship, modelSession);
    }

}
