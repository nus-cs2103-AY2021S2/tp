package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.timeforwheels.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.tag.Tag;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Lists all customer in the delivery list to the user.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";
    public static final String CHECKMARK = "[✓]";
    public static final String MESSAGE_SUCCESS = "Here are the summary statistics \n"
            + "Deliveries Done : %1$s\n" + "Deliveries Not Done : %2$s\n"
            + "Deliveries Due: %8$s\n" + "Deliveries Not Due: %9$s\n"
            + "Fragile Deliveries : %3$s\n" + "Liquid Deliveries : %4$s\n"
            + "Heavy Deliveries : %5$s\n" + "Hot Deliveries : %6$s\n"
            + "Tagless Deliveries : %7$s\n";
    public double doneCounter;
    public int deliveriesDone;
    public int deliveriesNotDone;
    public double liquidPercentage;
    public double heavyPercentage;
    public double fragilePercentage;
    public double taglessPercentage;
    public double donePercentage;
    public double hotPercentage;
    public double deliveriesNotdue;
    public double deliveriesDue;
    public double deliveriesDuePercentage;

    public final String[] tagList = {"[hot]", "[liquid]", "[heavy]", "[fragile]"};
    public final HashMap<String, Integer> tagMap = new HashMap<>();


    @Override
    public CommandResult execute(Model model) {
        List<Customer> lastShownList = model.getFilteredCustomerList();
        double size = lastShownList.size();
        double tagless = 0;

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
        liquidPercentage = (tagMap.get("[liquid]") / size) * 100;
        heavyPercentage = (tagMap.get("[heavy]") / size) * 100;
        fragilePercentage = (tagMap.get("[fragile]") / size) * 100;
        hotPercentage = (tagMap.get("[hot]") / size) * 100;
        taglessPercentage = (tagless / size) * 100;
        donePercentage = (doneCounter / size) * 100;
        deliveriesDone = (int) doneCounter;
        deliveriesNotDone = (int) size - deliveriesDone;
        doneCounter = 0;

        LocalDate now = LocalDate.now();
        for (Customer customer : lastShownList) {
            seedu.timeforwheels.model.customer.Date c = customer.getDate();
            Period period = Period.between(c.getLocalDate(), now);
            if (period.getDays() < 0) {
                deliveriesNotdue += 1;
            }
            deliveriesDue = size - deliveriesNotdue;
        }

        deliveriesDuePercentage = (deliveriesDue / size) * 100;

        int liquid  = tagMap.get("[liquid]");
        int fragile = tagMap.get("[fragile]");
        int hot = tagMap.get("[hot]");
        int heavy = tagMap.get("[heavy]");
        int deliveryDue = (int) deliveriesDue;
        int deliveryNotDue = (int) size - deliveryDue;
        int notag = (int) tagless;
        deliveriesNotdue = 0;

        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                deliveriesDone + " ( " + String.format("%.2f", donePercentage) + "%" + " )",
                deliveriesNotDone + " ( " + String.format("%.2f", 100 - donePercentage)
                        + "%" + " )",
                fragile + " ( " + String.format("%.2f", fragilePercentage) + "%" + " )",
                liquid + " ( " + String.format("%.2f", liquidPercentage) + "%" + " )",
                heavy + " ( " + String.format("%.2f", heavyPercentage) + "%" + " )",
                hot + " ( " + String.format("%.2f", hotPercentage) + "%" + " )",
                notag + " ( " + String.format("%.2f", taglessPercentage) + "%" + " )",
                deliveryDue + " ( " + String.format("%.2f", deliveriesDuePercentage)
                        + "%" + " )",
                deliveryNotDue + " ( " + String.format("%.2f", 100 - deliveriesDuePercentage)
                        + "%" + " )"
        ));
    }
}
