package seedu.address.logic.parser.commands.order;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.order.OrderFindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderContainsCustomerNamesPredicate;
import seedu.address.model.order.OrderContainsDishNamePredicate;

public class OrderFindCommandParser implements Parser<OrderFindCommand> {

    @Override
    public OrderFindCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_DISH);

        boolean namePresent = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME);
        boolean dishPresent = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DISH);

        if (!namePresent && !dishPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    OrderFindCommand.MESSAGE_USAGE));
        }

        List<Predicate<Order>> predicates = getPredicates(argMultimap);
        return new OrderFindCommand(predicates);
    }

    private List<Predicate<Order>> getPredicates(ArgumentMultimap argMultimap) throws ParseException {
        List<Predicate<Order>> predicates = new ArrayList<>();

        Optional<String> nameArgs = argMultimap.getValue(PREFIX_NAME);
        Optional<String> dishArg = argMultimap.getValue(PREFIX_DISH);

        if (nameArgs.isPresent()) {
            List<String> keywords;
            try {
                keywords = ParserUtil.parseKeywords(nameArgs.get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        OrderFindCommand.MESSAGE_USAGE), pe);
            }
            predicates.add(new OrderContainsCustomerNamesPredicate(keywords));
        }

        if (dishArg.isPresent()) {
            String keyword;
            try {
                keyword = ParserUtil.parseKeyword(dishArg.get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        OrderFindCommand.MESSAGE_USAGE), pe);
            }
            predicates.add(new OrderContainsDishNamePredicate(keyword));
        }
        return predicates;
    }
}
