package seedu.cakecollate.model.order;

import java.util.Arrays;

public class DeliveryStatus {
    public static final String MESSAGE_CONSTRAINTS = "The delivery status should be a valid enum value.";

    public static final String[] STRING_REPRESENTATION = Arrays.stream(Status.values())
            .map(Enum::name)
            .toArray(String[]::new);

    private Status deliveryStatus;

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
}
