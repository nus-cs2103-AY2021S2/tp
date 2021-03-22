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

    public static final String MESSAGE_SUCCESS = "Listed all events";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists event in PartyPlanet "
            + "according to specified prefix combinations, with optional sort order.\n"
            + "Parameters: [--exact] [--any] [-n NAME] [-r DETAIL] ... [-s SORT] [-o ORDER]\n"
            + "Sort fields: 'n' (name, default), 'd' (date)\n"
            + "Sort orders: 'asc' (ascending, default), 'desc' (descending)\n"
            + "Example: elist --any -n CNY -n Feb -r turkey -s name -o desc\n";

    public static final Comparator<Event> SORT_NAME = Comparator.comparing(x -> x.getName().fullName);
    public static final Comparator<Event> SORT_EVENTDATE = Comparator.comparing(Event::getDate);

    private final Comparator<Event> comparator;
    private final Predicate<Event> predicate;

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
        this(predicate, SORT_NAME);
    }

    /**
     * Most general EListCommand.
     *
     * @param predicate Predicate to filter people by
     * @param comparator Sorting comparator
     */
    public EListCommand(Predicate<Event> predicate, Comparator<Event> comparator) {
        this.predicate = predicate;
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortEventList(comparator);
        model.updateFilteredEventList(predicate);
        if (model.getEventListCopy().size() == model.getFilteredEventList().size()) {
            return new CommandResult(EListCommand.MESSAGE_SUCCESS); // No event filtered out
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
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
