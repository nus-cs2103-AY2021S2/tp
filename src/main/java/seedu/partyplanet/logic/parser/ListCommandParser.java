package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.commands.ListCommand.ASC;
import static seedu.partyplanet.logic.commands.ListCommand.DESC;
import static seedu.partyplanet.logic.parser.CliSyntax.FLAG_ANY;
import static seedu.partyplanet.logic.parser.CliSyntax.FLAG_PARTIAL;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.partyplanet.logic.commands.ListCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.person.predicates.NameContainsExactKeywordsPredicate;
import seedu.partyplanet.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.partyplanet.model.person.predicates.TagsContainsExactTagPredicate;
import seedu.partyplanet.model.person.predicates.TagsContainsTagPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_TAG, PREFIX_SORT, FLAG_PARTIAL, FLAG_ANY);

        boolean isPartialSearch = argMap.contains(FLAG_PARTIAL);
        boolean isAnySearch = argMap.contains(FLAG_ANY);

        List<Predicate<Person>> predicates = new ArrayList<>();
        if (isPartialSearch) {
            for (String name : argMap.getAllValues(PREFIX_NAME)) {
                predicates.add(new NameContainsKeywordsPredicate(name));
            }
            for (String tag : argMap.getAllValues(PREFIX_TAG)) {
                predicates.add(new TagsContainsTagPredicate(tag));
            }
        } else {
            for (String name : argMap.getAllValues(PREFIX_NAME)) {
                predicates.add(new NameContainsExactKeywordsPredicate(name));
            }
            for (String tag : argMap.getAllValues(PREFIX_TAG)) {
                predicates.add(new TagsContainsExactTagPredicate(tag));
            }
        }

        Comparator<Person> comparator = getSortOrder(argMap.getValue(PREFIX_SORT));
        return new ListCommand(predicates, isAnySearch, comparator);
    }

    /**
     * Returns the sort order depending on sort argument.
     */
    private Comparator<Person> getSortOrder(Optional<String> sortType) throws ParseException {
        if (sortType.isEmpty()) {
            return ASC;
        }

        switch (sortType.get()) {
        case "asc":
            return ASC;
        case "desc":
            return DESC;
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }

}
