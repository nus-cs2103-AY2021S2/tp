package seedu.address.logic.commands.sort;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_ORDER;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.sort.descriptor.AppointmentSortingKey;
import seedu.address.model.sort.descriptor.SortingOrder;

/**
 * Sorts appointment list.
 */
public class SortAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "sort appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the appointment list based on the sorting key.\n"
            + "Parameters: "
            + PREFIX_SORTING_ORDER + "SORTING_ORDER "
            + PREFIX_SORTING_KEY + "SORTING_KEY \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORTING_ORDER + "desc "
            + PREFIX_SORTING_KEY + "datetime ";

    public static final String MESSAGE_SUCCESS = "Appointment list sorted %1$s";

    private static final Supplier<CommandException> invalidCommandExceptionSupplier = () -> new CommandException(
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

    private final SortAppointmentDescriptor sortAppointmentDescriptor;

    /**
     * Creates an SortAppointmentCommand to sort appointment list based on the information in
     * {@code sortAppointmentDescriptor}.
     */
    public SortAppointmentCommand(SortAppointmentDescriptor sortAppointmentDescriptor) {
        requireNonNull(sortAppointmentDescriptor);

        this.sortAppointmentDescriptor = new SortAppointmentCommand
                .SortAppointmentDescriptor(sortAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Comparator<Appointment> cmp = createAppointmentComparator(sortAppointmentDescriptor);
        model.sortAppointmentList(cmp);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortAppointmentDescriptor));
    }

    /**
     * Creates and returns an {@code Comparator} with the details of {@code sortAppointmentDescriptor}
     */
    private static Comparator<Appointment> createAppointmentComparator(
            SortAppointmentDescriptor sortAppointmentDescriptor) throws CommandException {
        assert sortAppointmentDescriptor != null;

        SortingOrder sortingOrder = sortAppointmentDescriptor.getSortingOrder()
                .orElseThrow(invalidCommandExceptionSupplier);

        AppointmentSortingKey appointmentSortingKey = sortAppointmentDescriptor.getAppointmentSortingKey()
                .orElseThrow(invalidCommandExceptionSupplier);

        return (Appointment o1, Appointment o2) -> {
            int result;
            if (appointmentSortingKey.isName()) {
                result = o1.getName().compareTo(o2.getName());
            } else {
                result = o1.getDate().compareTo(o2.getDate());
                if (result == 0) {
                    result = o1.getTime().compareTo(o2.getTime());
                }
            }

            if (sortingOrder.isAscendingOrder()) {
                return result;
            } else {
                return -1 * result;
            }
        };
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortAppointmentCommand // instanceof handles nulls
                && sortAppointmentDescriptor.equals(((SortAppointmentCommand) other).sortAppointmentDescriptor));
    }

    public static class SortAppointmentDescriptor {
        private SortingOrder sortingOrder;
        private AppointmentSortingKey appointmentSortingKey;

        public SortAppointmentDescriptor() {}

        /**
         * Copy constructor.
         */
        public SortAppointmentDescriptor(SortAppointmentDescriptor toCopy) {
            setSortingOrder(toCopy.sortingOrder);
            setAppointmentSortingKey(toCopy.appointmentSortingKey);
        }

        public void setSortingOrder(SortingOrder sortingOrder) {
            this.sortingOrder = sortingOrder;
        }

        public Optional<SortingOrder> getSortingOrder() {
            return Optional.ofNullable(sortingOrder);
        }

        public void setAppointmentSortingKey(AppointmentSortingKey appointmentSortingKey) {
            this.appointmentSortingKey = appointmentSortingKey;
        }

        public Optional<AppointmentSortingKey> getAppointmentSortingKey() {
            return Optional.ofNullable(appointmentSortingKey);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SortAppointmentDescriptor)) {
                return false;
            }

            // state check
            SortAppointmentDescriptor e =
                    (SortAppointmentDescriptor) other;

            return getSortingOrder().equals(e.getSortingOrder())
                    && getAppointmentSortingKey().equals(e.getAppointmentSortingKey());
        }

        @Override
        public String toString() {
            return String.format("in %1$sending order by %2$s", sortingOrder, appointmentSortingKey);
        }
    }
}
