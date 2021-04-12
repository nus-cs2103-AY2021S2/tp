package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.passenger.Passenger;

/**
 * Deletes a passenger identified using it's displayed index from the passenger list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the passenger identified by the index number used in the displayed passenger list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PASSENGER_SUCCESS = "Deleted Passenger(s): %1$s";
    public static final String MESSAGE_DELETE_PASSENGER_FAIL_HAS_POOL = "Failed to delete. One or more Pools "
            + "contain Passenger(s): %1$s.";

    private final List<Index> targetIndexes;

    /**
     * Creates a DeleteCommand to remove passengers at the specified {@code targetIndexes}.
     *
     * @param targetIndexes The list of target indexes for passengers to be deleted.
     */
    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    private static String printPassengersInList(List<Passenger> passengers) {
        StringJoiner sb = new StringJoiner(", ");

        for (Passenger p : passengers) {
            sb.add(p.getName().toString());
        }

        return sb.toString();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Passenger> lastShownList = model.getFilteredPassengerList();
        List<Passenger> targetedPassengers = new ArrayList<>();

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
            }

            Passenger passengerToDelete = lastShownList.get(targetIndex.getZeroBased());
            targetedPassengers.add(passengerToDelete);
        }

        List<Passenger> passengersWithPools = targetedPassengers.stream()
                .filter(model::hasPoolWithPassenger)
                .collect(Collectors.toList());

        if (passengersWithPools.size() > 0) {
            throw new CommandException(
                    String.format(MESSAGE_DELETE_PASSENGER_FAIL_HAS_POOL,
                            printPassengersInList(passengersWithPools)
                    )
            );
        }

        targetedPassengers.forEach(model::deletePassenger);

        return new CommandResult(
                String.format(MESSAGE_DELETE_PASSENGER_SUCCESS,
                        printPassengersInList(targetedPassengers)
                )
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }
}
