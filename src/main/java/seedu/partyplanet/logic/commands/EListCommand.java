package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.Comparator;
import java.util.function.Predicate;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;

/**
 * Lists all events in PartyPlanet to the user.
 */
public class EListCommand extends Command {

    public static final String COMMAND_WORD = "elist";

    public static final String MESSAGE_SUCCESS = "Listed all events! ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists event in PartyPlanet "
            + "according to specified prefix combinations, with optional sort order.\n"
            + "Parameters: [--exact] [--any] [-n NAME] [-r REMARK] ... [-s SORT_FIELD] [-o SORT_ORDER]\n"
            + "Sort fields: 'n' (name, default), 'd' (date), 'u' (upcoming)\n"
            + "Sort orders: 'asc' (ascending, default), 'desc' (descending)\n"
            + "Example: elist --any -n CNY -n Feb -r turkey -s name -o desc\n";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD
            + " [--exact] [--any] [-n NAME] [-r REMARK] ... [-s SORT_FIELD] [-o SORT_ORDER]";

    public static final Comparator<Event> SORT_NAME = Comparator.comparing(x -> x.getName().fullName.toLowerCase());
    public static final Comparator<Event> SORT_EVENTDATE = Comparator.comparing(Event::getEventDate);
    public static final Comparator<Event> SORT_EVENTDATE_UPCOMING = (Event x, Event y) -> {
        Long xDaysLeft = x.getEventDate().getDaysLeft();
        Long yDaysLeft = y.getEventDate().getDaysLeft();

        // For pairs of events that are upcoming and not done, sort by date
        if (!x.isDone() && !y.isDone() && xDaysLeft >= 0 && yDaysLeft >= 0) {
            return xDaysLeft.compareTo(yDaysLeft);
        }

        // If event is upcoming and not done, sort in front
        if (!x.isDone() && xDaysLeft >= 0) {
            return -1;
        }
        if (!y.isDone() && yDaysLeft >= 0) {
            return 1;
        }

        // Sort the rest of events by date
        return xDaysLeft.compareTo(yDaysLeft);
    };

    private final Comparator<Event> comparator;
    private final Predicate<Event> predicate;
    private String parseArguments;

    /**
     * Default empty EListCommand.
     * Shows the whole list.
     */
    public EListCommand() {
        this(PREDICATE_SHOW_ALL_EVENTS);
    }

    /**
     * More general EListCommand accepting a single filtering predicate.
     * Default in ascending order, and the ANY flag is not applicable.
     */
    public EListCommand(Predicate<Event> predicate) {
        this(predicate, SORT_NAME, "");
    }

    /**
     * Most general EListCommand.
     *
     * @param predicate Predicate to filter people by.
     * @param comparator Sorting comparator.
     * @param parseArguments String containing information of listing requirements to be displayed.
     */
    public EListCommand(Predicate<Event> predicate, Comparator<Event> comparator, String parseArguments) {
        this.predicate = predicate;
        this.comparator = comparator;
        this.parseArguments = parseArguments;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortEventList(comparator);
        model.updateFilteredEventList(predicate);
        if (model.getEventListCopy().size() == model.getFilteredEventList().size()) {
            return new CommandResult(EListCommand.MESSAGE_SUCCESS + parseArguments); // No event
            // filtered out
        }
        if (model.getFilteredEventList().size() == 0) {
            return new CommandResult(String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW,
                model.getFilteredEventList().size()) + "No events met the requirements.");
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size())
                    + parseArguments);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EListCommand)) {
            return false;
        }
        EListCommand command = (EListCommand) other;
        return comparator.equals(command.comparator)
                && predicate.equals(((EListCommand) other).predicate); // state check
    }
}
