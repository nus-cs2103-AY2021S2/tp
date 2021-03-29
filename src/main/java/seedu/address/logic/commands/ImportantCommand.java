package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.date.ImportantDate;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;



/**
 * Adds an important date to TutorsPet.
 */
public class ImportantCommand extends Command {

    public static final String COMMAND_WORD = "important";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an important date to TutorsPet. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DETAILS + "DETAILS ";
    public static final String MESSAGE_SUCCESS = "New important date added: %1$s";
    public static final String MESSAGE_DUPLICATE_IMPORTANT_DATE = "This date already exists in TutorsPet";

    private final ImportantDate toAdd;

    /**
     * Creates an ImportantCommand to add important dates.
     */
    public ImportantCommand(ImportantDate importantDate) {
        requireNonNull(importantDate);
        toAdd = importantDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasImportantDate(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_IMPORTANT_DATE);
        }

        model.addImportantDate(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportantCommand // instanceof handles nulls
                && toAdd.equals(((ImportantCommand) other).toAdd));
    }





}
