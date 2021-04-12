package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.commands.EListCommand.SORT_EVENTDATE;
import static seedu.partyplanet.logic.commands.EListCommand.SORT_EVENTDATE_UPCOMING;
import static seedu.partyplanet.logic.commands.EListCommand.SORT_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.FLAG_ANY;
import static seedu.partyplanet.logic.parser.CliSyntax.FLAG_EXACT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.partyplanet.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.partyplanet.logic.commands.EListCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.predicates.EventNameContainsExactKeywordsPredicate;
import seedu.partyplanet.model.event.predicates.EventNameContainsKeywordsPredicate;
import seedu.partyplanet.model.event.predicates.EventRemarkContainsExactKeywordsPredicate;
import seedu.partyplanet.model.event.predicates.EventRemarkContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new EListCommand object
 */
public class EListCommandParser implements Parser<EListCommand> {

    private String stringFind = "";
    private String stringCriteria = "";
    private String stringSort = "";

    /**
     * Parses the given {@code String} of arguments in the context of the EListCommand
     * and returns an EListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_REMARK, PREFIX_SORT, PREFIX_ORDER, FLAG_EXACT, FLAG_ANY);

        if (!argMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EListCommand.MESSAGE_USAGE));
        }

        Predicate<Event> predicate = getPredicate(argMap);
        Comparator<Event> comparator = getComparator(argMap);
        return new EListCommand(predicate, comparator, stringCriteria + stringSort + stringFind);
    }

    /**
     * Returns the overall filtering predicate.
     */
    private Predicate<Event> getPredicate(ArgumentMultimap argMap) throws ParseException {
        List<Predicate<Event>> predicates = getPredicates(argMap);
        return mergePredicates(predicates, argMap);
    }

    /**
     * Returns a list of filtering predicates depending on whether partial search is disabled.
     */
    private List<Predicate<Event>> getPredicates(ArgumentMultimap argMap) throws ParseException {
        boolean isExactSearch = argMap.contains(FLAG_EXACT);
        List<Predicate<Event>> predicates = new ArrayList<>();
        List<String> allNames = ListCommandUtil.getParsedNames(argMap);
        List<String> allRemarks = ListCommandUtil.getParsedRemarks(argMap);

        if (isExactSearch) {
            if (!allNames.isEmpty()) {
                stringFind += ListCommandUtil.getCriteriaString("exact event name", allNames);
            }
            for (String name : allNames) {
                predicates.add(new EventNameContainsExactKeywordsPredicate(name));
            }
            if (!allRemarks.isEmpty()) {
                stringFind += ListCommandUtil.getCriteriaString("exact event remark", allRemarks);
            }
            for (String remark : allRemarks) {
                predicates.add(new EventRemarkContainsExactKeywordsPredicate(remark));
            }

        } else {
            if (!allNames.isEmpty()) {
                stringFind += ListCommandUtil.getCriteriaString("partial event name", allNames);
            }
            for (String name : allNames) {
                predicates.add(new EventNameContainsKeywordsPredicate(name));
            }
            if (!allRemarks.isEmpty()) {
                stringFind += ListCommandUtil.getCriteriaString("partial event remark", allRemarks);
            }
            for (String remark : allRemarks) {
                predicates.add(new EventRemarkContainsKeywordsPredicate(remark));
            }
        }
        if (isExactSearch && predicates.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EListCommand.MESSAGE_USAGE));
        }
        return predicates;
    }

    /**
     * Returns combines a list of filtering predicates depending on whether search is performed for any predicate.
     */
    private Predicate<Event> mergePredicates(List<Predicate<Event>> predicates, ArgumentMultimap argMap)
        throws ParseException {
        boolean isAnySearch = argMap.contains(FLAG_ANY);
        Predicate<Event> overallPredicate;
        if (isAnySearch && predicates.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EListCommand.MESSAGE_USAGE));
        }
        if (predicates.isEmpty()) {
            overallPredicate = PREDICATE_SHOW_ALL_EVENTS;
        } else if (isAnySearch) {
            stringCriteria += "Each event meets at least 1 requirement stated. ";
            overallPredicate = x -> false;
            for (Predicate<Event> predicate : predicates) {
                overallPredicate = overallPredicate.or(predicate);
            }
        } else {
            stringCriteria += "Each event meets all requirements stated. ";
            overallPredicate = x -> true;
            for (Predicate<Event> predicate : predicates) {
                overallPredicate = overallPredicate.and(predicate);
            }
        }
        return overallPredicate;
    }

    /**
     * Returns the comparator used to sort the filtered list.
     */
    private Comparator<Event> getComparator(ArgumentMultimap argMap) throws ParseException {
        Comparator<Event> comparator = getSortOrder(argMap);
        return applySortDirection(comparator, argMap);
    }

    /**
     * Returns the comparator depending on the specified field to be sorted.
     */
    private Comparator<Event> getSortOrder(ArgumentMultimap argMap) throws ParseException {
        Optional<String> sortType = argMap.getValue(PREFIX_SORT);
        if (sortType.isEmpty()) {
            return SORT_NAME; // default
        } else {
            switch (sortType.get().toLowerCase()) {
            case "n": // fallthrough
            case "name":
                stringSort += "Sorted event names ";
                return SORT_NAME;
            case "d": // fallthrough
            case "date":
                stringSort += "Sorted event dates ";
                return SORT_EVENTDATE;
            case "u": // fallthrough
            case "upcoming":
                stringSort += "Sorted on upcoming event dates. ";
                return SORT_EVENTDATE_UPCOMING;
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EListCommand.MESSAGE_USAGE));
            }
        }
    }

    /**
     * Returns a comparator that is reversed if reverse order is specified.
     */
    private Comparator<Event> applySortDirection(
            Comparator<Event> comparator, ArgumentMultimap argMap) throws ParseException {
        Optional<String> orderType = argMap.getValue(PREFIX_ORDER);
        if (orderType.isEmpty() || comparator == SORT_EVENTDATE_UPCOMING) {
            if (!stringSort.isEmpty() && comparator != SORT_EVENTDATE_UPCOMING) {
                stringSort += "in ascending order. ";
            }
            return comparator; // default
        } else {
            switch (orderType.get().toLowerCase()) {
            case "a": // fallthrough
            case "asc":
            case "ascending":
                if (stringSort.isEmpty()) {
                    stringSort += "Sorted event names in ascending order. ";
                } else {
                    stringSort += "in ascending order. ";
                }
                return comparator;
            case "d": // fallthrough
            case "des":
            case "desc":
            case "descending":
                if (stringSort.isEmpty()) {
                    stringSort += "Sorted event names in descending order. ";
                } else {
                    stringSort += "in descending order. ";
                }
                return comparator.reversed();
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EListCommand.MESSAGE_USAGE));
            }
        }
    }
}
