package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_ORDER;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;
import seedu.address.model.sort.descriptor.PropertySortingKey;
import seedu.address.model.sort.descriptor.SortingOrder;

/**
 * Sorts property list.
 */
public class SortPropertyCommand extends Command {

    public static final String COMMAND_WORD = "sort property";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the property list based on the sorting key.\n"
            + "Parameters: "
            + PREFIX_SORTING_ORDER + "SORTING_ORDER "
            + PREFIX_SORTING_KEY + "SORTING_KEY \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORTING_ORDER + "desc "
            + PREFIX_SORTING_KEY + "price ";

    public static final String MESSAGE_SUCCESS = "Property list sorted %1$s";

    private static final Supplier<CommandException> invalidCommandExceptionSupplier = () -> new CommandException(
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

    private final SortPropertyDescriptor sortPropertyDescriptor;

    /**
     * Creates an SortPropertyCommand to sort property list based on the information in
     * {@code sortPropertyDescriptor}.
     */
    public SortPropertyCommand(SortPropertyDescriptor sortPropertyDescriptor) {
        requireNonNull(sortPropertyDescriptor);

        this.sortPropertyDescriptor = new SortPropertyCommand
                .SortPropertyDescriptor(sortPropertyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Comparator<Property> cmp = createPropertyComparator(sortPropertyDescriptor);
        model.sortPropertyList(cmp);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortPropertyDescriptor));
    }

    /**
     * Creates and returns an {@code Comparator} with the details of {@code sortPropertyDescriptor}
     */
    private static Comparator<Property> createPropertyComparator(
            SortPropertyDescriptor sortPropertyDescriptor) throws CommandException {
        assert sortPropertyDescriptor != null;

        SortingOrder sortingOrder = sortPropertyDescriptor.getSortingOrder()
                .orElseThrow(invalidCommandExceptionSupplier);

        PropertySortingKey propertySortingKey = sortPropertyDescriptor.getPropertySortingKey()
                .orElseThrow(invalidCommandExceptionSupplier);

        return (Property o1, Property o2) -> {
            int result;
            if (propertySortingKey.isName()) {
                result = o1.getName().compareTo(o2.getName());
            } else if (propertySortingKey.isPrice()) {
                if (o1.getClient() == null) {
                    return 1;
                } else if (o2.getClient() == null) {
                    return -1;
                }
                result = o1.getAskingPrice().compareTo(o2.getAskingPrice());
            } else if (propertySortingKey.isPostalCode()) {
                result = o1.getPostalCode().compareTo(o2.getPostalCode());
            } else if (propertySortingKey.isAddress()) {
                result = o1.getAddress().compareTo(o2.getAddress());
            } else {
                result = o1.getDeadline().compareTo(o2.getDeadline());
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
                || (other instanceof SortPropertyCommand // instanceof handles nulls
                && sortPropertyDescriptor.equals(((SortPropertyCommand) other).sortPropertyDescriptor));
    }

    public static class SortPropertyDescriptor {
        private SortingOrder sortingOrder;
        private PropertySortingKey propertySortingKey;

        public SortPropertyDescriptor() {}

        /**
         * Copy constructor.
         */
        public SortPropertyDescriptor(SortPropertyDescriptor toCopy) {
            setSortingOrder(toCopy.sortingOrder);
            setPropertySortingKey(toCopy.propertySortingKey);
        }

        public void setSortingOrder(SortingOrder sortingOrder) {
            this.sortingOrder = sortingOrder;
        }

        public Optional<SortingOrder> getSortingOrder() {
            return Optional.ofNullable(sortingOrder);
        }

        public void setPropertySortingKey(PropertySortingKey propertySortingKey) {
            this.propertySortingKey = propertySortingKey;
        }

        public Optional<PropertySortingKey> getPropertySortingKey() {
            return Optional.ofNullable(propertySortingKey);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SortPropertyDescriptor)) {
                return false;
            }

            // state check
            SortPropertyDescriptor e =
                    (SortPropertyDescriptor) other;

            return getSortingOrder().equals(e.getSortingOrder())
                    && getPropertySortingKey().equals(e.getPropertySortingKey());
        }

        @Override
        public String toString() {
            return String.format("in %1$sending order by %2$s", sortingOrder, propertySortingKey);
        }
    }
}
