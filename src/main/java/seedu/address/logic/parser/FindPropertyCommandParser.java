package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_LESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_MORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.ParserUtil.parsePropertyAddress;
import static seedu.address.logic.parser.ParserUtil.parsePropertyDeadline;
import static seedu.address.logic.parser.ParserUtil.parsePropertyPostal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddressPredicate;
import seedu.address.model.property.PropertyNamePredicate;
import seedu.address.model.property.PropertyDeadlinePredicate;
import seedu.address.model.property.PropertyPostalCodePredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.model.property.PropertyPricePredicate;
import seedu.address.model.property.PropertyRemarksPredicate;
import seedu.address.model.property.PropertyTagsPredicate;
import seedu.address.model.property.PropertyTypePredicate;

/**
 * Parses input arguments and creates a new FindPropertyCommand object
 */
public class FindPropertyCommandParser implements Parser<FindPropertyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindPropertyCommand
     * and returns a FindPropertyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPropertyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.strip().equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE)
            );
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TYPE, PREFIX_ADDRESS, PREFIX_POSTAL,
                        PREFIX_DEADLINE, PREFIX_REMARK, PREFIX_TAGS, PREFIX_PROPERTY_PRICE_MORE,
                        PREFIX_PROPERTY_PRICE_LESS);


        // String genericKeywords = argMultimap.getPreamble();
        List<Predicate<Property>> predicates = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            argMultimap.getAllValues(PREFIX_NAME)
                       .forEach(s -> {
                           predicates.add(new PropertyNamePredicate(Arrays.asList(s.split("\\s+"))));
                       });
        }

        if (argMultimap.getValue(PREFIX_PROPERTY_PRICE_MORE).isPresent()) {
            argMultimap.getAllValues(PREFIX_PROPERTY_PRICE_MORE)
                       .forEach(x -> predicates.add(new PropertyPricePredicate(x, false)));
        }

        if (argMultimap.getValue(PREFIX_PROPERTY_PRICE_LESS).isPresent()) {
            argMultimap.getAllValues(PREFIX_PROPERTY_PRICE_LESS)
                    .forEach(x -> predicates.add(new PropertyPricePredicate(x, true)));
        }

        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            argMultimap.getAllValues(PREFIX_TYPE)
                    .forEach(x -> predicates.add(new PropertyTypePredicate(x)));
        }

        if (argMultimap.getValue(PREFIX_POSTAL).isPresent()) {
            List<String> postalCodes = argMultimap.getAllValues(PREFIX_POSTAL);
            if (postalCodes.size() > 1) {
                throw new ParseException("Too many postal codes! Please only use 1 postal code. \n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            }
            for (String p : postalCodes) {
                try {
                    predicates.add(new PropertyPostalCodePredicate(parsePropertyPostal(p)));
                } catch (ParseException e) {
                    throw new ParseException("Wrong postal code format! \n"
                            + e.getMessage()
                            + "\n"
                            + FindPropertyCommand.MESSAGE_USAGE);
                }
            }
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            List<String> addresses = argMultimap.getAllValues(PREFIX_ADDRESS);
            if (addresses.size() > 1) {
                throw new ParseException("Too many addresses! Please only use 1 address. \n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            } else {
                try {
                    predicates.add(new PropertyAddressPredicate(parsePropertyAddress(addresses.get(0))));
                } catch (ParseException e) {
                    throw new ParseException("Wrong address format! \n"
                            + e.getMessage()
                            + "\n"
                            + FindPropertyCommand.MESSAGE_USAGE);
                }
            }
        }

        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            List<String> remarks = argMultimap.getAllValues(PREFIX_REMARK);
            for (String s : remarks) {
                try {
                    predicates.add(new PropertyRemarksPredicate(s));
                } catch (IllegalArgumentException e) {
                    throw new ParseException("r/ used but no remarks found! \n"
                            + e.getMessage()
                            + "\n"
                            + FindPropertyCommand.MESSAGE_USAGE);
                }
            }
        }

        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            List<String> deadlines = argMultimap.getAllValues(PREFIX_DEADLINE);
            if (deadlines.size() > 1) {
                throw new ParseException("Too many deadlines! Please only use 1 deadline. \n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            }
            for (String s : deadlines) {
                try {
                    predicates.add(new PropertyDeadlinePredicate(parsePropertyDeadline(s)));
                } catch (ParseException e) {
                        throw new ParseException("Wrong deadline format! \n"
                                + e.getMessage()
                                + "\n"
                                + FindPropertyCommand.MESSAGE_USAGE);
                }
            }
        }

        if (argMultimap.getValue(PREFIX_TAGS).isPresent()) {
            List<String> tags = argMultimap.getAllValues(PREFIX_TAGS);
            try {
                tags.forEach(s -> predicates.add(new PropertyTagsPredicate(s)));
            } catch (IllegalArgumentException e) {
                throw new ParseException("Wrong tag format! \n"
                        + e.getMessage()
                        + "\n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            }
        }

        return new FindPropertyCommand(new PropertyPredicateList(predicates));
    }

}
