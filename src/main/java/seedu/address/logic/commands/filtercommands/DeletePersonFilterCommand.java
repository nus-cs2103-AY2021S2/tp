package seedu.address.logic.commands.filtercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.filter.TutorFilter;

/**
 * Deletes tutor filters.
 */
public class DeletePersonFilterCommand extends Command {
    public static final String COMMAND_WORD = "delete_tutor_filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes tutor filters."
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_GENDER + "GENDER]... "
            + "[" + PREFIX_PHONE + "PHONE]... "
            + "[" + PREFIX_EMAIL + "EMAIL]... "
            + "[" + PREFIX_ADDRESS + "ADDRESS]... "
            + "[" + PREFIX_SUBJECT_NAME + "SUBJECT_NAME]... "
            + "[" + PREFIX_EDUCATION_LEVEL + "EDUCATION_LEVEL]... "
            + "[" + PREFIX_RATE + "RATE]... "
            + "[" + PREFIX_YEAR + "YEARS EXPERIENCE]... "
            + "[" + PREFIX_QUALIFICATION + "QUALIFICATION]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SUBJECT_NAME + "English "
            + PREFIX_EDUCATION_LEVEL + "Sec 3 "
            + PREFIX_RATE + "50";

    public static final String MESSAGE_SUCCESS = "Tutor filters deleted: %1$s";
    public static final String MESSAGE_NOT_FOUND = "A given filter does not exist";

    private final TutorFilter tutorFilter;

    /**
     * Creates a DeletePersonFilterCommand.
     */
    public DeletePersonFilterCommand(TutorFilter tutorFilter) {
        requireNonNull(tutorFilter);
        this.tutorFilter = tutorFilter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasAllTutorFilters(tutorFilter)) {
            throw new CommandException(MESSAGE_NOT_FOUND);
        }

        model.removeTutorFilter(tutorFilter);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tutorFilter));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonFilterCommand // instanceof handles nulls
                && tutorFilter.equals(((DeletePersonFilterCommand) other).tutorFilter));
    }
}
