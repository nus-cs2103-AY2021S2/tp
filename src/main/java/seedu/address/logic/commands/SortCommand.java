package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.Comparator;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparators.PersonLessonComparator;
import seedu.address.model.person.comparators.PersonNameComparator;
import seedu.address.model.person.comparators.PersonSchoolComparator;
import seedu.address.model.person.comparators.PersonSubjectComparator;
import seedu.address.model.person.predicate.HasLessonPredicate;
import seedu.address.model.person.predicate.HasNamePredicate;
import seedu.address.model.person.predicate.HasSchoolPredicate;
import seedu.address.model.person.predicate.HasSubjectPredicate;

/**
 * Sorts all students in TutorsPet by lesson day and time.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort students according to specified sorting criteria ie name, school, subject, lesson."
            + "The first valid sorting criteria listed will be the sorting criteria used. \n "
            + "Parameters: "
            + "[" + PREFIX_NAME + "] "
            + "OR [" + PREFIX_SCHOOL + "] "
            + "OR [" + PREFIX_SUBJECT + "] "
            + "OR [" + PREFIX_LESSON + "]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SCHOOL;

    public static final String MESSAGE_SUCCESS = "Sorted students by %1$s. ";

    private final Prefix prefix;
    private final String prefixString;
    private final Comparator<Person> comparator;
    private final Predicate<Person> predicate;


    /**
     * Creates a SortCommand object that has a specific {@code Predicate} and {@code comparator}.
     */
    public SortCommand(Prefix prefix) {
        this.prefix = prefix;
        if (prefix.equals(PREFIX_NAME)) {
            this.prefixString = "name";
            this.comparator = new PersonNameComparator();
            this.predicate = new HasNamePredicate();
        } else if (prefix.equals(PREFIX_SCHOOL)) {
            this.prefixString = "school";
            this.comparator = new PersonSchoolComparator();
            this.predicate = new HasSchoolPredicate();
        } else if (prefix.equals(PREFIX_SUBJECT)) {
            this.prefixString = "subject";
            this.comparator = new PersonSubjectComparator();
            this.predicate = new HasSubjectPredicate();
        } else {
            this.prefixString = "lesson";
            this.comparator = new PersonLessonComparator();
            this.predicate = new HasLessonPredicate();
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.filterThenSortPersonList(predicate, comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, prefixString) +
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getTransformedPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }

}
