package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.person.level.Level;
import seedu.address.model.subject.Subject;

/**
 * Edits the details of an existing student in TutorsPet.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Missing details will be added in.\n"
            + "Existing details will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer and lesser than 2147483648) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_GUARDIAN_NAME + "GUARDIAN_NAME] "
            + "[" + PREFIX_GUARDIAN_PHONE + "GUARDIAN_PHONE] "
            + "[" + PREFIX_LEVEL + "LEVEL] "
            + "[" + PREFIX_SUBJECT + "SUBJECT]... "
            + "[" + PREFIX_LESSON + "LESSON]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_NAME_LESSON = "The student name %1$s already exists "
            + "with a different phone number. \n" + "You also have a lesson at %2$s with %3$s. \n"
            + "Do you wish to proceed? y/n";
    public static final String MESSAGE_DUPLICATE_PERSON = "%1$s already belongs to another student in TutorsPet. \n"
            + "Please assign a unique phone number to student %2$s.";
    public static final String MESSAGE_POTENTIAL_DUPLICATE = "This student name %1$s already exists "
            + "with a different phone number. \n" + "Do you wish to proceed? y/n";
    public static final String MESSAGE_DUPLICATE_LESSON = "You have a lesson at %1$s with %2$s. \n"
            + "Do you wish to proceed? y/n";
    public static final int DUPLICATE_PERSON = 1;
    public static final int DUPLICATE_LESSON = 2;
    public static final int DUPLICATE_LESSON_AND_PERSON = 3;

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;
    private int duplicate;
    private ArrayList<Lesson> duplicateLessons;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editPersonDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        duplicate = 0;
        duplicateLessons = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getTransformedPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON,
                    editedPerson.getPhone(), editedPerson.getName()));
        }

        if (!model.isSavedState()) {
            checkForDuplicateNameOrLesson(model, personToEdit, editedPerson);
            if (this.duplicate != 0) {
                handleDuplicateNameOrLesson(model, editedPerson);
            }
        }
        model.removePersonFromLesson(personToEdit);
        model.addPersonToLesson(editedPerson);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.setSavedState(false);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Optional<School> updatedSchool = editPersonDescriptor.getSchool().isPresent()
                ? editPersonDescriptor.getSchool() : personToEdit.getSchool();
        Optional<Email> updatedEmail = editPersonDescriptor.getEmail().isPresent()
                ? editPersonDescriptor.getEmail() : personToEdit.getEmail();
        Optional<Address> updatedAddress = editPersonDescriptor.getAddress().isPresent()
                ? editPersonDescriptor.getAddress() : personToEdit.getAddress();
        Optional<Name> updatedGuardianName = editPersonDescriptor.getGuardianName().isPresent()
                ? editPersonDescriptor.getGuardianName() : personToEdit.getGuardianName();
        Optional<Phone> updatedGuardianPhone = editPersonDescriptor.getGuardianPhone().isPresent()
                ? editPersonDescriptor.getGuardianPhone() : personToEdit.getGuardianPhone();
        Optional<Level> updatedLevel = editPersonDescriptor.getLevel().isPresent()
                ? editPersonDescriptor.getLevel() : personToEdit.getLevel();
        Set<Subject> updatedSubjects = editPersonDescriptor.getSubjects().orElse(personToEdit.getSubjects());
        Set<Lesson> updatedLessons = editPersonDescriptor.getLessons().orElse(personToEdit.getLessons());

        return new Person(updatedName, updatedPhone, updatedSchool, updatedEmail, updatedAddress,
                updatedGuardianName, updatedGuardianPhone, updatedLevel, updatedSubjects, updatedLessons);
    }

    /**
     * Checks if person to be edited has the same name or same lessons as the contacts in TutorsPet.
     */
    public void checkForDuplicateNameOrLesson(Model model, Person personToEdit, Person editedPerson) {
        if (!personToEdit.isPotentialSamePerson(editedPerson) && model.hasPotentialPerson(editedPerson)
                && editPersonDescriptor.isNameEdited()) {
            model.setSavedState(true);
            duplicate = DUPLICATE_PERSON;
        }

        if (editPersonDescriptor.isLessonEdited()) {
            for (Lesson lesson : editedPerson.getLessons()) {
                if (personToEdit.getLessons().stream().anyMatch(lesson::isSameLesson)) {
                    continue;
                }
                if (model.hasLesson(lesson) && !(model.getLesson(lesson).getNumberOfPerson() == 1
                        && model.getLesson(lesson).containsPerson(personToEdit))) {
                    model.setSavedState(true);
                    this.duplicateLessons.add(lesson);
                    this.duplicate = this.duplicate == 0 ? DUPLICATE_LESSON : DUPLICATE_LESSON_AND_PERSON;
                }
            }
        }
    }

    /**
     * Handles the situation where there is a duplicate name or lesson or both by throwing
     * an appropriate exception.
     */
    public void handleDuplicateNameOrLesson(Model model, Person editedPerson) throws CommandException {
        switch(duplicate) {
        case DUPLICATE_PERSON:
            throw new CommandException(String.format(MESSAGE_POTENTIAL_DUPLICATE, editedPerson.getName()));
        case DUPLICATE_LESSON:
            throw new CommandException(String.format(MESSAGE_DUPLICATE_LESSON,
                    duplicateLessons.stream().map(Lesson::formatString).collect(Collectors.joining(", ")),
                    duplicateLessons.stream().map(model::getLesson).map(Lesson::getPersonInString)
                            .collect(Collectors.joining(" and "))));
        case DUPLICATE_LESSON_AND_PERSON:
            throw new CommandException(String.format(MESSAGE_DUPLICATE_NAME_LESSON, editedPerson.getName(),
                    duplicateLessons.stream().map(Lesson::formatString).collect(Collectors.joining(", ")),
                    duplicateLessons.stream().map(model::getLesson).map(Lesson::getPersonInString)
                            .collect(Collectors.joining(" and "))));
        default:
            return;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Optional<School> school = Optional.empty();
        private Optional<Email> email = Optional.empty();
        private Optional<Address> address = Optional.empty();
        private Optional<Name> guardianName = Optional.empty();
        private Optional<Phone> guardianPhone = Optional.empty();
        private Optional<Level> level = Optional.empty();
        private Set<Subject> subjects;
        private Set<Lesson> lessons;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code subjects} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setSchool(toCopy.school);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setGuardianName(toCopy.guardianName);
            setGuardianPhone(toCopy.guardianPhone);
            setLevel(toCopy.level);
            setSubjects(toCopy.subjects);
            setLessons(toCopy.lessons);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, subjects, lessons)
                    || school.isPresent() || email.isPresent() || address.isPresent()
                    || guardianName.isPresent() || guardianPhone.isPresent() || level.isPresent();
        }

        public boolean isLessonEdited() {
            return CollectionUtil.isAnyNonNull(lessons);
        }

        public boolean isNameEdited() {
            return CollectionUtil.isAnyNonNull(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setSchool(Optional<School> school) {
            this.school = school;
        }

        public Optional<School> getSchool() {
            return school;
        }

        public void setEmail(Optional<Email> email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return email;
        }

        public void setAddress(Optional<Address> address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return address;
        }

        public void setGuardianName(Optional<Name> guardianName) {
            this.guardianName = guardianName;
        }

        public Optional<Name> getGuardianName() {
            return guardianName;
        }

        public void setGuardianPhone(Optional<Phone> guardianPhone) {
            this.guardianPhone = guardianPhone;
        }

        public Optional<Phone> getGuardianPhone() {
            return guardianPhone;
        }

        public void setLevel(Optional<Level> level) {
            this.level = level;
        }

        public Optional<Level> getLevel() {
            return level;
        }
        /**
         * Sets {@code subjects} to this object's {@code subjects}.
         * A defensive copy of {@code subjects} is used internally.
         */
        public void setSubjects(Set<Subject> subjects) {
            this.subjects = (subjects != null) ? new HashSet<>(subjects) : null;
        }

        /**
         * Returns an unmodifiable subject set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code subjects} is null.
         */
        public Optional<Set<Subject>> getSubjects() {
            return (subjects != null) ? Optional.of(Collections.unmodifiableSet(subjects)) : Optional.empty();
        }

        public void setLessons(Set<Lesson> lessons) {
            this.lessons = (lessons != null) ? new HashSet<>(lessons) : null;
        }

        public Optional<Set<Lesson>> getLessons() {
            return (lessons != null) ? Optional.of(Collections.unmodifiableSet(lessons)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getSchool().equals(e.getSchool())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getGuardianName().equals(e.getGuardianName())
                    && getGuardianPhone().equals(e.getGuardianPhone())
                    && getLevel().equals(e.getLevel())
                    && getSubjects().equals(e.getSubjects())
                    && getLessons().equals(e.getLessons());
        }
    }
}
