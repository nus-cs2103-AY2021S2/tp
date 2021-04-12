package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.storemando.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.predicate.LocationContainsKeywordsPredicate;
import seedu.storemando.model.tag.predicate.TagContainsKeywordsPredicate;

/**
 * Lists all items in the storemando to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all items.";
    public static final String MESSAGE_SUCCESS_TAG_PREDICATE = "Listed all items with the following "
        + "tag %s (if the tag exists).";
    public static final String MESSAGE_SUCCESS_LOCATION_PREDICATE = "Listed all items located in %s "
        + "(if the location exists).";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List items in the inventory.\n"
        + "Parameters: "
        + "[" + PREFIX_LOCATION + "LOCATION] " + "/ [" + PREFIX_TAG + "TAG]\n"
        + "Example:\n"
        + "1. " + COMMAND_WORD + "\n"
        + "2. " + COMMAND_WORD + " l/bedroom\n"
        + "3. " + COMMAND_WORD + " t/favourite\n";
    public static final List<String> NO_KEYWORD = new ArrayList<>();

    private final Predicate<Item> predicate;
    private final List<String> keywords;

    /**
     * Constructor for List command with LocationContainsKeywordsPredicate predicate and keywords specified.
     * @param predicate The location predicate that will be use to filter the item.
     * @param keywords The location keywords that the predicate will be filtering on.
     */
    public ListCommand(LocationContainsKeywordsPredicate predicate, List<String> keywords) {
        this.predicate = predicate;
        this.keywords = keywords;
    }

    /**
     * Constructor for List command with TagContainsKeywordsPredicate predicate and keywords specified.
     * @param predicate The tag predicate that will be use to filter the item.
     * @param keywords The tag keywords that the predicate will be filtering on.
     */
    public ListCommand(TagContainsKeywordsPredicate predicate, List<String> keywords) {
        this.predicate = predicate;
        this.keywords = keywords;
    }

    /**
     * Constructor for List Command without any predicate specified.
     */
    public ListCommand() {
        this.predicate = PREDICATE_SHOW_ALL_ITEMS;
        this.keywords = NO_KEYWORD;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        String message = getMessage();
        requireNonNull(model);
        List<Item> currentList = model.getFilteredItemList();
        if (currentList.isEmpty() && !predicate.equals(PREDICATE_SHOW_ALL_ITEMS)) {
            throw new CommandException(Messages.MESSAGE_NO_ITEM_IN_LIST);
        }
        model.updateCurrentPredicate(predicate);
        model.updateFilteredItemList(model.getCurrentPredicate());
        return new CommandResult(message);
    }

    private String getMessage() {
        if (this.predicate instanceof LocationContainsKeywordsPredicate) {
            return String.format(MESSAGE_SUCCESS_LOCATION_PREDICATE, String.join(" ", keywords));
        } else if (this.predicate instanceof TagContainsKeywordsPredicate) {
            return String.format(MESSAGE_SUCCESS_TAG_PREDICATE, String.join(" ", keywords));
        } else {
            return MESSAGE_SUCCESS;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ListCommand // instanceof handles nulls
            && predicate.equals(((ListCommand) other).predicate)); // state check
    }
}
