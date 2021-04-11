package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.commands.ListCommand.SORT_BIRTHDAY;
import static seedu.partyplanet.logic.commands.ListCommand.SORT_BIRTHDAY_UPCOMING;
import static seedu.partyplanet.logic.commands.ListCommand.SORT_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.FLAG_ANY;
import static seedu.partyplanet.logic.parser.CliSyntax.FLAG_EXACT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.partyplanet.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.partyplanet.logic.commands.ListCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.person.predicates.BirthdayContainsMonthPredicate;
import seedu.partyplanet.model.person.predicates.NameContainsExactKeywordsPredicate;
import seedu.partyplanet.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.partyplanet.model.person.predicates.TagsContainsExactTagPredicate;
import seedu.partyplanet.model.person.predicates.TagsContainsTagPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    private String stringFind = "";
    private String stringCriteria = "";
    private String stringSort = "";

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_TAG, PREFIX_BIRTHDAY, PREFIX_SORT, PREFIX_ORDER, FLAG_EXACT, FLAG_ANY);

        if (!argMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        Predicate<Person> predicate = getPredicate(argMap);
        Comparator<Person> comparator = getComparator(argMap);
        return new ListCommand(predicate, comparator, stringCriteria + stringSort + stringFind);
    }

    /**
     * Returns the overall filtering predicate.
     */
    private Predicate<Person> getPredicate(ArgumentMultimap argMap) throws ParseException {
        List<Predicate<Person>> predicates = getPredicates(argMap);
        return mergePredicates(predicates, argMap);
    }

    /**
     * Returns a list of filtering predicates depending on whether partial search is disabled.
     */
    private List<Predicate<Person>> getPredicates(ArgumentMultimap argMap) throws ParseException {
        boolean isExactSearch = argMap.contains(FLAG_EXACT);
        List<Predicate<Person>> predicates = new ArrayList<>();
        List<String> allNames = ListCommandUtil.getParsedNames(argMap);
        List<String> allTags = ListCommandUtil.getParsedTags(argMap);

        if (isExactSearch) {
            if (!allNames.isEmpty()) {
                stringFind += ListCommandUtil.getCriteriaString("exact name", allNames);
            }
            for (String name : allNames) {
                predicates.add(new NameContainsExactKeywordsPredicate(name));
            }
            if (!allTags.isEmpty()) {
                stringFind += ListCommandUtil.getCriteriaString("exact tag", allTags);
            }
            for (String tag : allTags) {
                predicates.add(new TagsContainsExactTagPredicate(tag));
            }
        } else {
            if (!allNames.isEmpty()) {
                stringFind += ListCommandUtil.getCriteriaString("partial name", allNames);
            }
            for (String name : allNames) {
                predicates.add(new NameContainsKeywordsPredicate(name));
            }
            if (!allTags.isEmpty()) {
                stringFind += ListCommandUtil.getCriteriaString("partial tag", allTags);
            }
            for (String tag : allTags) {
                predicates.add(new TagsContainsTagPredicate(tag));
            }
        }
        List<String> allMonths = argMap.getAllValues(PREFIX_BIRTHDAY);
        if (!allMonths.isEmpty()) {
            stringFind += ListCommandUtil.getCriteriaString("birthday month", allMonths);
        }
        for (String month : allMonths) {
            predicates.add(new BirthdayContainsMonthPredicate(month));
        }
        if (isExactSearch && predicates.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
        return predicates;
    }

    /**
     * Returns combines a list of filtering predicates depending on whether search is performed for any predicate.
     */
    private Predicate<Person> mergePredicates(List<Predicate<Person>> predicates, ArgumentMultimap argMap)
        throws ParseException {
        boolean isAnySearch = argMap.contains(FLAG_ANY);
        if (isAnySearch && predicates.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
        Predicate<Person> overallPredicate;
        if (predicates.isEmpty()) {
            overallPredicate = PREDICATE_SHOW_ALL_PERSONS;
        } else if (isAnySearch) {
            stringCriteria += "Each person meets at least 1 requirement stated. ";
            overallPredicate = x -> false;
            for (Predicate<Person> predicate : predicates) {
                overallPredicate = overallPredicate.or(predicate);
            }
        } else {
            stringCriteria += "Each person meets all requirements stated. ";
            overallPredicate = x -> true;
            for (Predicate<Person> predicate : predicates) {
                overallPredicate = overallPredicate.and(predicate);
            }
        }
        return overallPredicate;
    }

    /**
     * Returns the comparator used to sort the filtered list.
     */
    private Comparator<Person> getComparator(ArgumentMultimap argMap) throws ParseException {
        Comparator<Person> comparator = getSortOrder(argMap);
        return applySortDirection(comparator, argMap);
    }

    /**
     * Returns the comparator depending on the specified field to be sorted.
     */
    private Comparator<Person> getSortOrder(ArgumentMultimap argMap) throws ParseException {
        Optional<String> sortType = argMap.getValue(PREFIX_SORT);
        if (sortType.isEmpty()) {
            return SORT_NAME; // default
        } else {
            switch (sortType.get().toLowerCase()) {
            case "n": // fallthrough
            case "name":
                stringSort += "Sorted names ";
                return SORT_NAME;
            case "b": // fallthrough
            case "birthday":
                stringSort += "Sorted birthdays ";
                return SORT_BIRTHDAY;
            case "u": // fallthrough
            case "upcoming":
                stringSort += "Sorted by upcoming birthdays. ";
                return SORT_BIRTHDAY_UPCOMING;
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            }
        }
    }

    /**
     * Returns a comparator that is reversed if reverse order is specified.
     */
    private Comparator<Person> applySortDirection(
            Comparator<Person> comparator, ArgumentMultimap argMap) throws ParseException {
        Optional<String> orderType = argMap.getValue(PREFIX_ORDER);
        if (orderType.isEmpty() || comparator == SORT_BIRTHDAY_UPCOMING) {
            if (!stringSort.isEmpty() && comparator != SORT_BIRTHDAY_UPCOMING) {
                stringSort += "in ascending order. ";
            }
            return comparator; // default
        } else {
            switch (orderType.get().toLowerCase()) {
            case "a": // fallthrough
            case "asc":
            case "ascending":
                if (stringSort.isEmpty()) {
                    stringSort += "Sorted names in ascending order. ";
                } else {
                    stringSort += "in ascending order. ";
                }
                return comparator;
            case "d": // fallthrough
            case "des":
            case "desc":
            case "descending":
                if (stringSort.isEmpty()) {
                    stringSort += "Sorted names in descending order. ";
                } else {
                    stringSort += "in descending order. ";
                }
                return comparator.reversed();
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            }
        }
    }
}
