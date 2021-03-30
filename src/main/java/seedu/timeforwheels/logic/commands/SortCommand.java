package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.tag.Tag;

import java.util.*;
import java.time.LocalDate;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sort all deliveries based on urgency followed by date"
        + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sorted all delivery entries.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Customer> currentList = model.getFilteredCustomerList();
        Collections.sort(currentList, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
               LocalDate d1 = o1.getDate().getLocalDate();
               LocalDate d2 = o2.getDate().getLocalDate();
               return d1.compareTo(d2);
            }
        });
        /*Collections.sort(currentList, (x, y) -> {
            LocalDate ld1 = x.getDate().getLocalDate();
            LocalDate ld2 = y.getDate().getLocalDate();
            String s1 = ld1.toString();
            String s2 = ld2.toString();
            String[] sa1 = s1.split("-");
            String[] sa2 = s2.split("-");
            int year1 = Integer.parseInt(sa1[0]);
            int year2 = Integer.parseInt(sa2[0]);
            int month1 = Integer.parseInt(sa1[1]);
            int month2 = Integer.parseInt(sa2[1]);
            int day1 = Integer.parseInt(sa1[2]);
            int day2 = Integer.parseInt(sa2[2]);
            if (year1 < year2) {
                return -1;
            } else if (year1 > year2) {
                return 1;
            } else if (month1 < month2) {
                return -1;
            } else if (month1 > month2) {
                return 1;
            } else if (day1 < day2) {
                return -1;
            } else if (day1 > day2) {
                return 1;
            } else {
                return 0;
            }
        });*/
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
