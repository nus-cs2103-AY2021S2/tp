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
import static seedu.address.logic.parser.ParserUtil.parsePropertyDeadline;
import static seedu.address.model.property.client.AskingPrice.MESSAGE_CONSTRAINTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindClientCommand;
import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddressPredicate;
import seedu.address.model.property.PropertyClientContactPredicate;
import seedu.address.model.property.PropertyClientEmailPredicate;
import seedu.address.model.property.PropertyClientNamePredicate;
import seedu.address.model.property.PropertyDeadlinePredicate;
import seedu.address.model.property.PropertyNamePredicate;
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

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TYPE, PREFIX_ADDRESS, PREFIX_POSTAL,
                        PREFIX_DEADLINE, PREFIX_REMARK, PREFIX_TAGS, PREFIX_PROPERTY_PRICE_MORE,
                        PREFIX_PROPERTY_PRICE_LESS, PREFIX_CLIENT_CONTACT, PREFIX_CLIENT_EMAIL,
                        PREFIX_CLIENT_NAME);

        if (args.strip().equals("") || !checkMultiMap(argMultimap)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE)
            );
        }

        List<Predicate<Property>> predicates = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<Predicate<Property>> nameList = new ArrayList<>();
            try {
                argMultimap.getAllValues(PREFIX_NAME)
                        .forEach(s -> {
                            nameList.add(new PropertyNamePredicate(Arrays.asList(s.split("\\s+"))));
                        });
                predicates.add(new PropertyPredicateList(nameList).combineDisjunction());
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                        "n/ used but no name found! \n"
                        + e.getMessage()
                        + "\n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            }
        }

        if (argMultimap.getValue(PREFIX_PROPERTY_PRICE_MORE).isPresent()) {
            for (String argument: argMultimap.getAllValues(PREFIX_PROPERTY_PRICE_MORE)) {
                try {
                    predicates.add(new PropertyPricePredicate(argument, false));
                } catch (IllegalArgumentException e) {
                    throw new ParseException(
                            "Wrong input format for pm/ !\n"
                            + MESSAGE_CONSTRAINTS
                            + "\n"
                            + FindPropertyCommand.MESSAGE_USAGE);
                }
            }
        }

        if (argMultimap.getValue(PREFIX_PROPERTY_PRICE_LESS).isPresent()) {
            for (String argument: argMultimap.getAllValues(PREFIX_PROPERTY_PRICE_LESS)) {
                try {
                    predicates.add(new PropertyPricePredicate(argument, true));
                } catch (IllegalArgumentException e) {
                    throw new ParseException(
                            "Wrong input format for pl/ !\n"
                                    + MESSAGE_CONSTRAINTS
                                    + "\n"
                                    + FindPropertyCommand.MESSAGE_USAGE);
                }
            }
        }

        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            List<Predicate<Property>> typeList = new ArrayList<>();
            try {
                argMultimap.getAllValues(PREFIX_TYPE)
                        .forEach(x -> typeList.add(new PropertyTypePredicate(x)));
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage() + "\n" + FindClientCommand.MESSAGE_USAGE);
            }
            predicates.add(new PropertyPredicateList(typeList).combineDisjunction());
        }

        if (argMultimap.getValue(PREFIX_POSTAL).isPresent()) {
            List<String> postalCodes = argMultimap.getAllValues(PREFIX_POSTAL);
            List<Predicate<Property>> postalList = new ArrayList<>();
            for (String p : postalCodes) {
                postalList.add(new PropertyPostalCodePredicate(p));
            }
            predicates.add(new PropertyPredicateList(postalList).combineDisjunction());
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            List<String> addresses = argMultimap.getAllValues(PREFIX_ADDRESS);
            List<Predicate<Property>> addressList = new ArrayList<>();
            try {
                for (String a : addresses) {
                    addressList.add(new PropertyAddressPredicate(a));
                }
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                        "a/ used but no addresses found! \n"
                        + e.getMessage()
                        + "\n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            }
            predicates.add(new PropertyPredicateList(addressList).combineDisjunction());
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
            List<Predicate<Property>> dateList = new ArrayList<>();
            for (String s : deadlines) {
                try {
                    dateList.add(new PropertyDeadlinePredicate(parsePropertyDeadline(s)));
                } catch (ParseException e) {
                    throw new ParseException("Wrong deadline format! \n"
                            + e.getMessage()
                            + "\n"
                            + FindPropertyCommand.MESSAGE_USAGE);
                }
            }
            predicates.add(new PropertyPredicateList(dateList).combineDisjunction());
        }

        if (argMultimap.getValue(PREFIX_TAGS).isPresent()) {
            List<String> tags = argMultimap.getAllValues(PREFIX_TAGS);
            List<Predicate<Property>> tagList = new ArrayList<>();
            try {
                tags.forEach(s -> tagList.add(new PropertyTagsPredicate(s)));
            } catch (IllegalArgumentException e) {
                throw new ParseException("Wrong tag format! \n"
                        + e.getMessage()
                        + "\n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            }
            predicates.add(new PropertyPredicateList(tagList).combineDisjunction());
        }

        if (argMultimap.getValue(PREFIX_CLIENT_CONTACT).isPresent()) {
            List<String> contacts = argMultimap.getAllValues(PREFIX_CLIENT_CONTACT);
            List<Predicate<Property>> contactList = new ArrayList<>();
            try {
                contacts.forEach(s -> contactList.add(new PropertyClientContactPredicate(s)));
            } catch (IllegalArgumentException e) {
                throw new ParseException("Wrong client contact format! \n"
                        + e.getMessage()
                        + "\n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            }
            predicates.add(new PropertyPredicateList(contactList).combineDisjunction());
        }

        if (argMultimap.getValue(PREFIX_CLIENT_EMAIL).isPresent()) {
            List<String> emails = argMultimap.getAllValues(PREFIX_CLIENT_EMAIL);
            List<Predicate<Property>> emailList = new ArrayList<>();
            try {
                emails.forEach(s -> emailList.add(new PropertyClientEmailPredicate(s)));
            } catch (IllegalArgumentException e) {
                throw new ParseException("ce/ used but no keywords found! \n"
                        + e.getMessage()
                        + "\n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            }
            predicates.add(new PropertyPredicateList(emailList).combineDisjunction());
        }

        if (argMultimap.getValue(PREFIX_CLIENT_NAME).isPresent()) {
            List<Predicate<Property>> clientList = new ArrayList<>();
            List<String> names = argMultimap.getAllValues(PREFIX_CLIENT_NAME);
            try {
                names.forEach(name ->
                    clientList.add(new PropertyClientNamePredicate(Arrays.asList(name.split("\\s+")))));
            } catch (IllegalArgumentException e) {
                throw new ParseException("cn/ used buy no keywords found! \n"
                        + e.getMessage()
                        + "\n"
                        + FindPropertyCommand.MESSAGE_USAGE);
            }
            predicates.add(new PropertyPredicateList(clientList).combineDisjunction());
        }

        return new FindPropertyCommand(new PropertyPredicateList(predicates));
    }

    private boolean checkMultiMap(ArgumentMultimap args) {
        List<Prefix> prefixes = Arrays.asList(PREFIX_NAME, PREFIX_TYPE, PREFIX_ADDRESS, PREFIX_POSTAL,
                PREFIX_DEADLINE, PREFIX_REMARK, PREFIX_TAGS, PREFIX_PROPERTY_PRICE_MORE,
                PREFIX_PROPERTY_PRICE_LESS, PREFIX_CLIENT_CONTACT, PREFIX_CLIENT_EMAIL,
                PREFIX_CLIENT_NAME);
        for (Prefix p : prefixes) {
            if (args.getValue(p).isPresent()) {
                return true;
            }
        }
        return false;
    }
}
