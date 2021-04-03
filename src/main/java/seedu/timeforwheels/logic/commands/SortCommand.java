package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.tag.Tag;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sort all deliveries based on urgency followed by date"
        + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sorted all delivery entries.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Customer> currentList = new ArrayList<>(model.getDeliveryList().getCustomerList());

        currentList.sort((x, y) -> {
            LocalDate d1 = x.getDate().getLocalDate();
            LocalDate d2 = y.getDate().getLocalDate();
            return d1.compareTo(d2);
        });

        List<Customer> doneList = new ArrayList<>();
        List<Customer> notDoneList = new ArrayList<>();
        int size = currentList.size();
        for (int i = 0; i < size; i++) {
            Customer customer = currentList.get(i);
            boolean isDone = customer.getDone().value.equals("[✓]");
            if (isDone) {
                doneList.add(customer);
            } else {
                notDoneList.add(customer);
            }
        }

        List<Customer> urgentList = new ArrayList<>();
        List<Customer> notUrgentList = new ArrayList<>();
        size = notDoneList.size();
        for (int i = 0; i < size; i++) {
            Customer customer = notDoneList.get(i);
            Set<Tag> tags = customer.getTags();
            boolean isUrgent = false;
            for (Tag tag : tags) {
                if (tag.tagName.equals("urgent")) {
                    urgentList.add(customer);
                    isUrgent = true;
                    break;
                }
            }
            if (!isUrgent) {
                notUrgentList.add(customer);
            }
        }
        urgentList.addAll(notUrgentList);
        urgentList.addAll(doneList);

        DeliveryList sortedList = new DeliveryList();
        for (Customer customer : urgentList) {
            sortedList.addCustomer(customer);
        }
        model.setDeliveryList(sortedList);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
