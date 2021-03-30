package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sort all deliveries based on urgency followed by date"
        + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sorted all delivery entries.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Customer> currentList = model.getFilteredCustomerList();
        currentList.sort((x, y) -> x.getDate().compareTo(y.getDate()));
        List<Customer> sortedList = new ArrayList<>();
        int numberOfEntries = currentList.size();
        for (int i = 0; i < numberOfEntries; i++) {
            Set<Tag> tags = currentList.get(i).getTags();
            for (Tag tag : tags) {
                if (tag.tagName.equals("urgent")) {
                    Customer urgentCustomer = currentList.remove(i);
                    sortedList.add(urgentCustomer);
                    break;
                }
            }
        }
        sortedList.addAll(currentList);
        DeliveryList finalList = new DeliveryList();
        for (Customer customer : sortedList) {
            finalList.addCustomer(customer);
        }
        model.setDeliveryList(finalList);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
