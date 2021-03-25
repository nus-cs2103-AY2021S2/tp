package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.commands.EListCommand.SORT_EVENTDATE;
import static seedu.partyplanet.logic.commands.EListCommand.SORT_EVENTDATE_UPCOMING;
import static seedu.partyplanet.logic.commands.EListCommand.SORT_NAME;
import static seedu.partyplanet.logic.commands.ListCommand.SORT_BIRTHDAY_UPCOMING;
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
import seedu.partyplanet.model.event.predicates.EventDetailContainsExactKeywordsPredicate;
import seedu.partyplanet.model.event.predicates.EventDetailContainsKeywordsPredicate;
import seedu.partyplanet.model.event.predicates.EventNameContainsExactKeywordsPredicate;
import seedu.partyplanet.model.event.predicates.EventNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class EListCommandParser implements Parser<EListCommand> {


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
        return new EListCommand(predicate, comparator);
    }

    /**
     * Returns the overall filtering predicate.
     */
    private Predicate<Event> getPredicate(ArgumentMultimap argMap) {
        List<Predicate<Event>> predicates = getPredicates(argMap);
        return mergePredicates(predicates, argMap);
    }

    /**
     * Returns a list of filtering predicates depending on whether partial search is disabled.
     */
    private List<Predicate<Event>> getPredicates(ArgumentMultimap argMap) {
        boolean isExactSearch = argMap.contains(FLAG_EXACT);
        List<Predicate<Event>> predicates = new ArrayList<>();
        if (isExactSearch) {
            for (String name : argMap.getAllValues(PREFIX_NAME)) {
                predicates.add(new EventNameContainsExactKeywordsPredicate(name));
            }
            for (String detail : argMap.getAllValues(PREFIX_REMARK)) {
                predicates.add(new EventDetailContainsExactKeywordsPredicate(detail));
            }
        } else {
            for (String name : argMap.getAllValues(PREFIX_NAME)) {
                predicates.add(new EventNameContainsKeywordsPredicate(name));
            }
            for (String detail : argMap.getAllValues(PREFIX_REMARK)) {
                predicates.add(new EventDetailContainsKeywordsPredicate(detail));
            }
        }
        return predicates;
    }

    /**
     * Returns combines a list of filtering predicates depending on whether search is performed for any predicate.
     */
    private Predicate<Event> mergePredicates(List<Predicate<Event>> predicates, ArgumentMultimap argMap) {
        boolean isAnySearch = argMap.contains(FLAG_ANY);
        Predicate<Event> overallPredicate;
        if (predicates.isEmpty()) {
            overallPredicate = PREDICATE_SHOW_ALL_EVENTS;
        } else if (isAnySearch) {
            overallPredicate = x -> false;
            for (Predicate<Event> predicate : predicates) {
                overallPredicate = overallPredicate.or(predicate);
            }
        } else {
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
                return SORT_NAME;
            case "d": // fallthrough
            case "date":
                return SORT_EVENTDATE;
            case "u": // fallthrough
            case "upcoming":
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
            return comparator; // default
        } else {
            switch (orderType.get().toLowerCase()) {
            case "a": // fallthrough
            case "asc":
            case "ascending":
                return comparator;
            case "d": // fallthrough
            case "des":
            case "desc":
            case "descending":
                return comparator.reversed();
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EListCommand.MESSAGE_USAGE));
            }
        }
    }
}
