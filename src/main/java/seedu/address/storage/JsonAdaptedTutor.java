package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.subject.SubjectList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Gender;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Notes;
import seedu.address.model.tutor.Phone;
import seedu.address.model.tutor.Tutor;


/**
 * Jackson-friendly version of {@link Tutor}.
 */
class JsonAdaptedTutor {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutor's %s field is missing!";
    public static final String MESSAGE_FAVOURITE_CONSTRAINT = "Favourite should be true or false";

    private final String name;
    private final String gender;
    private final String phone;
    private final String email;
    private final String address;
    private final String notes;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedTutorSubject> tutorSubjects = new ArrayList<>();
    private final String isFavourite;

    /**
     * Constructs a {@code JsonAdaptedTutor} with the given tutor details.
     */
    @JsonCreator
    public JsonAdaptedTutor(@JsonProperty("name") String name,
                            @JsonProperty("gender") String gender,
                            @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email,
                            @JsonProperty("address") String address,
                            @JsonProperty("notes") String notes,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("tutorSubjects") List<JsonAdaptedTutorSubject> tutorSubjects,
                            @JsonProperty("isFavourite") String isFavourite) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.notes = notes;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (tutorSubjects != null) {
            this.tutorSubjects.addAll(tutorSubjects);
        }
        this.isFavourite = isFavourite;
    }

    /**
     * Converts a given {@code Tutor} into this class for Jackson use.
     */
    public JsonAdaptedTutor(Tutor source) {
        name = source.getName().fullName;
        gender = source.getGender().personGender;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        notes = source.getNotes().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        tutorSubjects.addAll(source.getSubjectList()
                .asUnmodifiableObservableList()
                .stream()
                .map(JsonAdaptedTutorSubject::new)
                .collect(Collectors.toList()));
        isFavourite = Boolean.toString(source.isFavourite());
    }

    /**
     * Converts this Jackson-friendly adapted tutor object into the model's {@code Tutor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutor.
     */
    public Tutor toModelType() throws IllegalValueException {
        final List<Tag> tutorTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tutorTags.add(tag.toModelType());
        }

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

        if (notes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Notes.class.getSimpleName()));
        }
        final Notes modelNotes;
        if (notes.equals("")) {
            modelNotes = new Notes(null);
        } else {
            modelNotes = new Notes(notes);
        }

        final Set<Tag> modelTags = new HashSet<>(tutorTags);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        final SubjectList modelSubjectList = new SubjectList();
        for (JsonAdaptedTutorSubject tutorSubject : tutorSubjects) {
            modelSubjectList.add(tutorSubject.toModelType());
        }

        boolean modelIsFavourite = false;
        if (isFavourite != null) {
            if (isFavourite.equals("false") || isFavourite.equals("true")) {
                modelIsFavourite = Boolean.parseBoolean(isFavourite);
            } else {
                throw new IllegalValueException(MESSAGE_FAVOURITE_CONSTRAINT);
            }
        }

        return new Tutor(modelName, modelGender, modelPhone, modelEmail,
                modelAddress, modelNotes, modelSubjectList, modelTags, modelIsFavourite);
    }

}
