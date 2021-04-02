package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMUTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPTIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POOLS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;
import seedu.address.model.tag.Tag;

/**
 * Associates a Driver with the selected Passengers.
 */
public class PoolCommand extends Command {
    public static final String COMMAND_WORD = "pool";
    public static final long MAX_TIME_DIFFERENCE = 15;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Pools commuters together with a driver. "
            + "Parameters: "
            + PREFIX_NAME + "DRIVER NAME "
            + PREFIX_PHONE + "DRIVER PHONE "
            + PREFIX_TRIPDAY + "TRIP DAY "
            + PREFIX_TRIPTIME + "TRIP TIME "
            + PREFIX_COMMUTER + "COMMUTER "
            + "[" + PREFIX_COMMUTER + "COMMUTER]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Florence Lee "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_TRIPDAY + "monday "
            + PREFIX_TRIPTIME + "1930 "
            + PREFIX_COMMUTER + "1 "
            + PREFIX_COMMUTER + "4 "
            + PREFIX_TAG + "female";

    public static final String MESSAGE_NO_COMMUTERS = "No commuters were selected.";
    public static final String MESSAGE_POOL_SUCCESS = "Successfully created pool: %s";
    public static final String MESSAGE_POOL_SUCCESS_WITH_WARNING = "Successfully created pool: %s. \nHowever, note that"
            + " you have passengers with time differences with the pool time of more than 15 minutes.";
    public static final String MESSAGE_DUPLICATE_POOL = "This pool already exists in the GME Terminal";

    private final Driver driver;
    private final TripDay tripDay;
    private final TripTime tripTime;
    private final Set<Index> passengers;
    private final Set<Tag> tags;

    /**
     * //TODO edit java docs
     * @param driver
     * @param passengers
     * @param tripDay
     * @param tripTime
     * @param tags
     */
    public PoolCommand(Driver driver, Set<Index> passengers, TripDay tripDay, TripTime tripTime, Set<Tag> tags) {
        requireNonNull(driver);
        requireNonNull(passengers);
        requireNonNull(tripDay);
        requireNonNull(tripTime);
        this.driver = driver;
        this.passengers = passengers;
        this.tripDay = tripDay;
        this.tripTime = tripTime;
        this.tags = tags;
    }

    private boolean checkTimeDifference(List<Passenger> passengers) {
        return passengers.stream()
                .anyMatch(x -> x.getTripTime().compareMinutes(this.tripTime) > MAX_TIME_DIFFERENCE);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (passengers.size() == 0) {
            throw new CommandException(MESSAGE_NO_COMMUTERS);
        }
        StringJoiner joiner = new StringJoiner(", ");

        // Freeze the list so we don't have to manage the model filtering the passengers
        List<Passenger> lastShownList = List.copyOf(model.getFilteredPassengerList());

        for (Index idx : passengers) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
            }
        }

        // obtain passengers from indices
        List<Passenger> passengersToPool = new ArrayList<>();

        for (Index idx : passengers) {
            Passenger passenger = lastShownList.get(idx.getZeroBased());
            assert passenger != null : "passenger should not be null";
            passengersToPool.add(passenger);
        }

        boolean shouldWarn = checkTimeDifference(passengersToPool);

        //since passengers in list are unique, passenger fetched from idx should also be unique, so as hashset from list
        Pool toAdd = new Pool(driver, tripDay, tripTime, passengersToPool, tags);

        if (model.hasPool(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_POOL);
        }

        model.addPool(toAdd);
        model.updateFilteredPoolList(PREDICATE_SHOW_ALL_POOLS);

        String outputMessage = shouldWarn ? MESSAGE_POOL_SUCCESS_WITH_WARNING : MESSAGE_POOL_SUCCESS;

        return new CommandResult(String.format(outputMessage, driver, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PoolCommand // instanceof handles nulls
                && (driver.equals(((PoolCommand) other).driver)
                && passengers.equals(((PoolCommand) other).passengers)));
    }
}
