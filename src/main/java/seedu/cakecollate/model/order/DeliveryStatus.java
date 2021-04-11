package seedu.cakecollate.model.order;

import java.util.Arrays;
import java.util.HashMap;

public class DeliveryStatus implements Comparable<DeliveryStatus> {
    public static final String MESSAGE_CONSTRAINTS = "The delivery status should be a valid enum value.";

    public static final String[] STRING_REPRESENTATION = Arrays.stream(Status.values())
            .map(Enum::name)
            .toArray(String[]::new);

    private Status deliveryStatus;
    
    private static final HashMap<Status, Integer> ordering = new HashMap<>();
    
    static {
        ordering.put(Status.UNDELIVERED, 1);
        ordering.put(Status.CANCELLED, 2);
        ordering.put(Status.DELIVERED, 3);
    }

    public DeliveryStatus() {
        this.deliveryStatus = Status.UNDELIVERED;
    }

    public DeliveryStatus(Status status) {
        this.deliveryStatus = status;
    }

    public Status getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Status status) {
        deliveryStatus = status;
    }

    @Override
    public String toString() {
        return STRING_REPRESENTATION[deliveryStatus.ordinal()];
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeliveryStatus
                && deliveryStatus.equals(((DeliveryStatus) other).deliveryStatus));
    }

    @Override
    public int hashCode() {
        return deliveryStatus.hashCode();
    }

    /**
     * Uses the compareTo already defined by the LocalDate class to compare delivery dates.
     * This is needed for sorting order lists according to delivery dates.
     */
    @Override
    public int compareTo(DeliveryStatus d) {
        return ordering.get(this.deliveryStatus).compareTo(ordering.get(d.deliveryStatus));
    }
}