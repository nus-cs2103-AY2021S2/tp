package seedu.address.logic.parser.commands.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.order.OrderEditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class OrderEditCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public OrderEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATETIME, PREFIX_DISH, PREFIX_QUANTITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    OrderEditCommand.MESSAGE_USAGE), pe);
        }

        OrderEditCommand.EditOrderDescriptor editOrderDescriptor = new OrderEditCommand.EditOrderDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editOrderDescriptor.setCustomerId(ParserUtil.parseNonNegativeInt(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editOrderDescriptor.setDateTime(
                    LocalDateTime.parse(argMultimap.getValue(PREFIX_DATETIME).get(), dateTimeFormatter));
        }
        if (argMultimap.getValue(PREFIX_DISH).isPresent()
                && argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            List<String> editedDishNumbers = argMultimap.getAllValues(PREFIX_DISH);
            List<String> editedDishQuantities = argMultimap.getAllValues(PREFIX_QUANTITY);

            List<Pair<Index, Integer>> editedDishIdsQuantityList = new ArrayList<>();
            for (int i = 0; i < editedDishNumbers.size(); i++) {
                Pair<Index, Integer> editedDishComponent =
                        new Pair<>(ParserUtil.parseIndex(editedDishNumbers.get(i)),
                                ParserUtil.parseNonNegativeInt(editedDishQuantities.get(i)));
                editedDishIdsQuantityList.add(editedDishComponent);
            }

            editOrderDescriptor.setDishIdsQuantityList(editedDishIdsQuantityList);
        }

        if (!editOrderDescriptor.isAnyFieldEdited()) {
            throw new ParseException(OrderEditCommand.MESSAGE_NOT_EDITED);
        }

        return new OrderEditCommand(index, editOrderDescriptor);
    }
}
