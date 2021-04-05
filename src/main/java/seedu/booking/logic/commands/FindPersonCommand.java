package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.function.Predicate;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.person.Person;

/**
 * Finds and lists the persons in the booking system who match the specified fields.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "find_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who match the given fields.\n"
            + "At least one field must be provided.\n"
            + "Parameters: " + COMMAND_WORD + " "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Jane "
            + PREFIX_PHONE + "93857209 "
            + PREFIX_TAG + "Student ";

    private final List<Predicate<Person>> predicateList;

    public FindPersonCommand(List<Predicate<Person>> predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> predicate = combinePersonPredicates(predicateList);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicateList.equals(((FindPersonCommand) other).predicateList)); // state check
    }

    /**
     * Returns a composition of the predicates in the given person predicate list.
     */
    private static Predicate<Person> combinePersonPredicates(List<Predicate<Person>> predicateList) {
        return predicateList.stream().reduce(Predicate::and).orElse(PREDICATE_SHOW_ALL_PERSONS);
    }

}
