package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;

/**
 * Adds a item to the storemando.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to the storemando. "
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_LOCATION + "LOCATION "
        + PREFIX_QUANTITY + "QUANTITY "
        + "[" + PREFIX_EXPIRYDATE + "EXPIRYDATE] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "Banana "
        + PREFIX_QUANTITY + "2 "
        + PREFIX_EXPIRYDATE + "2023-10-10 "
        + PREFIX_LOCATION + "Kitchen "
        + PREFIX_TAG + "favourite "
        + PREFIX_TAG + "expiring";

    public static final String MESSAGE_SUCCESS = "New item added: %1$s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the storemando";
    public static final String MESSAGE_ITEM_EXPIRED_WARNING = "\nWarning: Item has already expired!";
    public static final String MESSAGE_SIMILAR_ITEM_WARNING = "\nWarning: Similar item exists in the same location!";

    private final Item itemToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Item}
     */
    public AddCommand(Item item) {
        requireNonNull(item);
        itemToAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(itemToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addItem(itemToAdd);

        String feedback = String.format(MESSAGE_SUCCESS, itemToAdd);

        if (model.hasSimilarItem(itemToAdd)) {
            feedback += MESSAGE_SIMILAR_ITEM_WARNING;
        }

        if (itemToAdd.isExpired()) {
            feedback += MESSAGE_ITEM_EXPIRED_WARNING;
        }

        return new CommandResult(feedback);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && itemToAdd.equals(((AddCommand) other).itemToAdd));
    }
}
