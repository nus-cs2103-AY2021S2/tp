package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.timeforwheels.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.tag.Tag;

/**
 *  list a summary report of the current delivery workflow.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";
    public static final String CHECKMARK = "[✓]";
    public static final String MESSAGE_SUCCESS = "Here are the summary statistics \n"
            + "Deliveries Done : %1$s\n" + "Deliveries Not Done : %2$s\n"
            + "Deliveries Due: %3$s\n" + "Deliveries Not Due: %4$s\n"
            + "Fragile Deliveries : %5$s\n" + "Liquid Deliveries : %6$s\n"
            + "Food Deliveries : %7$s\n" + "Hot Deliveries : %8$s\n"
            + "Cold Deliveries : %9$s\n" + "Heavy Deliveries : %10$s\n"
            + "Bulky Deliveries : %11$s\n" + "Urgent Deliveries : %12$s\n"
            + "Other Deliveries : %13$s\n";

    public final String[] tagList = {"[hot]", "[liquid]", "[heavy]", "[fragile]",
        "[bulky]", "[food]", "[cold]", "[urgent]"};
    public final HashMap<String, Integer> tagMap = new HashMap<>();


    @Override
    public CommandResult execute(Model model) {
        List<Customer> lastShownList = model.getFilteredCustomerList();
        double size = lastShownList.size();
        double tagless = 0;
        double doneCounter = 0;
        int deliveriesDone;
        int deliveriesNotDone;
        double liquidPercentage;
        double heavyPercentage;
        double fragilePercentage;
        double taglessPercentage;
        double donePercentage;
        double hotPercentage;
        double bulkyPercentage;
        double coldPercentage;
        double foodPercentage;
        double urgentPercentage;
        double deliveriesNotdue = 0;
        double deliveriesDue = 0;
        double deliveriesDuePercentage;

        if (size == 0) {
            return new CommandResult(String.format("No Data"));
        }

        for (Customer customer : lastShownList) {
            if (customer.getDone().toString().equals(CHECKMARK)) {
                doneCounter += 1;
            }
        }
        for (Customer customer : lastShownList) {
            Set<Tag> customerTag = customer.getTag();
            if (customerTag.size() == 0) {
                tagless += 1;
            }
            for (Tag tag : customerTag) {
                for (String taglist : tagList) {
                    if (tag.toString().equals(taglist)) {
                        tagMap.put(tag.toString(), tagMap.getOrDefault(tag.toString(), 0) + 1);
                    }
                }
            }
        }

        liquidPercentage = (tagMap.getOrDefault("[liquid]", 0) / size) * 100;
        heavyPercentage = (tagMap.getOrDefault("[heavy]", 0) / size) * 100;
        fragilePercentage = (tagMap.getOrDefault("[fragile]", 0) / size) * 100;
        bulkyPercentage = (tagMap.getOrDefault("[bulky]", 0) / size) * 100;
        coldPercentage = (tagMap.getOrDefault("[cold]", 0) / size) * 100;
        foodPercentage = (tagMap.getOrDefault("[food]", 0) / size) * 100;
        urgentPercentage = (tagMap.getOrDefault("[urgent]", 0) / size) * 100;
        hotPercentage = (tagMap.getOrDefault("[hot]", 0) / size) * 100;

        taglessPercentage = (tagless / size) * 100;
        donePercentage = (doneCounter / size) * 100;
        deliveriesDone = (int) doneCounter;
        deliveriesNotDone = (int) size - deliveriesDone;

        LocalDate now = LocalDate.now();
        for (Customer customer : lastShownList) {
            seedu.timeforwheels.model.customer.Date c = customer.getDate();
            Period period = Period.between(c.getLocalDate(), now);
            if (period.getDays() < 0) {
                deliveriesNotdue += 1;
            }
            if (period.getDays() > 0) {
                if (!customer.getDone().toString().equals("[✓]")) {
                    deliveriesDue += 1;
                }
            }
        }

        deliveriesDuePercentage = (deliveriesDue / size) * 100;

        int liquid = tagMap.getOrDefault("[liquid]", 0);
        int fragile = tagMap.getOrDefault("[fragile]", 0);
        int hot = tagMap.getOrDefault("[hot]", 0);
        int heavy = tagMap.getOrDefault("[heavy]", 0);
        int cold = tagMap.getOrDefault("[cold]", 0);
        int bulky = tagMap.getOrDefault("[bulky]", 0);
        int food = tagMap.getOrDefault("[food]", 0);
        int urgent = tagMap.getOrDefault("[urgent]", 0);
        int deliveryDue = (int) deliveriesDue;
        int deliveryNotDue = (int) size - deliveryDue;
        int notag = (int) tagless;

        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                deliveriesDone + " ( " + String.format("%.2f", donePercentage) + "%" + " )",
                deliveriesNotDone + " ( " + String.format("%.2f", 100 - donePercentage)
                        + "%" + " )",
                deliveryDue + " ( " + String.format("%.2f", deliveriesDuePercentage)
                        + "%" + " )",
                deliveryNotDue - deliveriesDone + " ( " + String.format("%.2f",
                        100 - deliveriesDuePercentage - donePercentage)
                        + "%" + " )",
                fragile + " ( " + String.format("%.2f", fragilePercentage) + "%" + " )",
                liquid + " ( " + String.format("%.2f", liquidPercentage) + "%" + " )",
                food + " ( " + String.format("%.2f", foodPercentage) + "%" + " )",
                hot + " ( " + String.format("%.2f", hotPercentage) + "%" + " )",
                cold + " ( " + String.format("%.2f", coldPercentage) + "%" + " )",
                heavy + " ( " + String.format("%.2f", heavyPercentage) + "%" + " )",
                bulky + " ( " + String.format("%.2f", bulkyPercentage) + "%" + " )",
                urgent + " ( " + String.format("%.2f", urgentPercentage) + "%" + " )",
                notag + " ( " + String.format("%.2f", taglessPercentage) + "%" + " )"

        ));
    }
}
