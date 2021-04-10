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
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.ArrayList;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

/**
 * Adds a student to TutorsPet.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to TutorsPet. "
            + "Compulsory Details: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE \n"
            + "Optional Details: "
            + "[" + PREFIX_SCHOOL + "SCHOOL] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_GUARDIAN_NAME + "GUARDIAN_NAME] "
            + "[" + PREFIX_GUARDIAN_PHONE + "GUARDIAN_PHONE] "
            + "[" + PREFIX_LEVEL + "LEVEL] "
            + "[" + PREFIX_SUBJECT + "SUBJECT]...\n"
            + "[" + PREFIX_LESSON + "LESSON]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_SCHOOL + "Clementi Secondary School "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_GUARDIAN_NAME + "Helen Doe "
            + PREFIX_GUARDIAN_PHONE + "98765431 "
            + PREFIX_LEVEL + "sec3 "
            + PREFIX_SUBJECT + "math "
            + PREFIX_SUBJECT + "chem "
            + PREFIX_LESSON + "monday 1300";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "%1$s already belongs to another student in TutorsPet. \n"
            + "Please assign a unique phone number to student %2$s.";
    public static final String MESSAGE_DUPLICATE_NAME_LESSON = "The student name %1$s already exists "
            + "with a different phone number. \n" + "You also have a lesson at %2$s with %3$s. \n"
            + "Do you wish to proceed? y/n";
    public static final String MESSAGE_POTENTIAL_DUPLICATE = "This student name %1$s already exists "
            + "with a different phone number. \n" + "Do you wish to proceed? y/n";
    public static final String MESSAGE_DUPLICATE_LESSON = "You have a lesson at %1$s with %2$s. \n"
            + "Do you wish to proceed? y/n";
    public static final int DUPLICATE_PERSON = 1;
    public static final int DUPLICATE_LESSON = 2;
    public static final int DUPLICATE_LESSON_AND_PERSON = 3;

    private Person toAdd;
    private int duplicate;
    private ArrayList<Lesson> duplicateLessons;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
        duplicate = 0;
        duplicateLessons = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasPerson(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, toAdd.getPhone(), toAdd.getName()));
        }

        if (!model.isSavedState()) {
            checksForDuplicateNameOrLesson(model, toAdd);
            if (this.duplicate != 0) {
                handleDuplicateNameOrLesson(model);
            }
        }

        model.addPersonToLesson(toAdd);
        model.addPerson(toAdd);
        model.setSavedState(false);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Checks if person to be added has the same name or same lessons as the contacts in TutorsPet.
     */
    public void checksForDuplicateNameOrLesson(Model model, Person toAdd) {
        if (model.hasPotentialPerson(toAdd)) {
            model.setSavedState(true);
            this.duplicate = DUPLICATE_PERSON;
        }
        for (Lesson lesson : toAdd.getLessons()) {
            if (model.hasLesson(lesson)) {
                this.duplicateLessons.add(lesson);
                model.setSavedState(true);
                this.duplicate = this.duplicate == 0 ? DUPLICATE_LESSON : DUPLICATE_LESSON_AND_PERSON;
            }
        }
    }

    /**
     * Handles the situation where there is a duplicate name or lesson or both by throwing
     * an appropriate exception.
     */
    public void handleDuplicateNameOrLesson(Model model) throws CommandException {
        switch(duplicate) {
        case DUPLICATE_PERSON:
            throw new CommandException(String.format(MESSAGE_POTENTIAL_DUPLICATE, toAdd.getName()));
        case DUPLICATE_LESSON:
            throw new CommandException(String.format(MESSAGE_DUPLICATE_LESSON,
                    duplicateLessons.stream().map(Lesson::formatString).collect(Collectors.joining(", ")),
                    duplicateLessons.stream().map(model::getLesson).map(Lesson::getPersonInString)
                            .collect(Collectors.joining(" and "))));
        case DUPLICATE_LESSON_AND_PERSON:
            throw new CommandException(String.format(MESSAGE_DUPLICATE_NAME_LESSON, toAdd.getName(),
                    duplicateLessons.stream().map(Lesson::formatString).collect(Collectors.joining(", ")),
                    duplicateLessons.stream().map(model::getLesson).map(Lesson::getPersonInString)
                            .collect(Collectors.joining(" and "))));
        default:
            return;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
