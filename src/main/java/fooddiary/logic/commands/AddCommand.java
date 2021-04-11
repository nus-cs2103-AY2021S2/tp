package fooddiary.logic.commands;

import static java.util.Objects.requireNonNull;

import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.logic.parser.CliSyntax;
import fooddiary.model.Model;
import fooddiary.model.entry.Entry;

/**
 * Adds a person to the food diary.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final int MAX_NO_OF_ENTRIES_ALLOWED = 1000000;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new entry to the food diary.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_RATING + "RATING "
            + CliSyntax.PREFIX_PRICE + "PRICE "
            + CliSyntax.PREFIX_REVIEW + "REVIEW "
            + CliSyntax.PREFIX_ADDRESS + "ADDRESS "
            + "[" + CliSyntax.PREFIX_TAG_CATEGORY + "TAG]... "
            + "[" + CliSyntax.PREFIX_TAG_SCHOOL + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "Subway "
            + CliSyntax.PREFIX_RATING + "5 "
            + CliSyntax.PREFIX_PRICE + "6 "
            + CliSyntax.PREFIX_REVIEW + "I like this food a lot! "
            + CliSyntax.PREFIX_ADDRESS + "3155 Commonwealth Ave W, Singapore 129588 "
            + CliSyntax.PREFIX_TAG_CATEGORY + "FastFood "
            + CliSyntax.PREFIX_TAG_CATEGORY + "Vegan "
            + CliSyntax.PREFIX_TAG_SCHOOL + "SOC";

    public static final String MESSAGE_SUCCESS = "New entry added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the food diary";
    public static final String MESSAGE_ENTRY_LIMIT_REACHED = "Food Diary has reached the maximum limit of "
            + MAX_NO_OF_ENTRIES_ALLOWED + ". Remove some existing entries to make space for more entries";

    private final Entry toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Entry}
     */
    public AddCommand(Entry entry) {
        requireNonNull(entry);
        toAdd = entry;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEntry(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
        }

        if (model.getFoodDiary().getEntryList().size() >= MAX_NO_OF_ENTRIES_ALLOWED) {
            throw new CommandException(MESSAGE_ENTRY_LIMIT_REACHED);
        }

        model.addEntry(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
