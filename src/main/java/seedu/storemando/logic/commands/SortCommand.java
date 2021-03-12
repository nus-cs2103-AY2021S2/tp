package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.storemando.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Comparator;
import java.util.function.Predicate;

import seedu.storemando.model.Model;

public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = "sorts the items in StoreMando by quantity or expiry date. "
        + "Parameter:"
        + PREFIX_QUANTITY
        + "or "
        + PREFIX_EXPIRYDATE;

    public static final String MESSAGE_SUCCESS = "sorted all items";


    public abstract CommandResult execute(Model model);

    public abstract boolean equals(Object other);
}
