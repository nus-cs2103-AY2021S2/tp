package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.booking.commons.core.Messages;
import seedu.booking.commons.util.CollectionUtil;
import seedu.booking.model.Model;
import seedu.booking.model.ModelPredicate;
import seedu.booking.model.Tag;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.EmailContainsKeywordsPredicate;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;

/**
 * Finds and lists the persons in the system who match the given predicates.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "find_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who match the given predicates.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " e/johndoe@gmail.com";

    private final List<Predicate<Person>> predicates;

    public FindPersonCommand(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> personPredicate = predicates.stream().reduce(Predicate::and).orElse(person -> true);
        model.updateFilteredPersonList(personPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicates.equals(((FindPersonCommand) other).predicates)); // state check
    }

}
