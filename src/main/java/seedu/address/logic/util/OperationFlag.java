package seedu.address.logic.util;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class OperationFlag {

    public static final String ADD_FLAG = "-a";

    public static final String REMOVE_FLAG = "-r";

    public static final String MESSAGE_CONSTRAINTS = "Operation flag should be one of either -a or -r";

    public final OperationType operationType;

    /**
     * Constructs an OperationFlag with the appropriate given type.
     * @throws IllegalArgumentException if the given type is invalid.
     */
    public OperationFlag(String operationFlag) {
        requireNonNull(operationFlag);
        checkArgument(isValidOperationType(operationFlag), OperationFlag.MESSAGE_CONSTRAINTS);
        switch (operationFlag) {
        case ADD_FLAG:
            operationType = OperationType.ADD;
            break;
        case REMOVE_FLAG:
            operationType = OperationType.REMOVE;
            break;
        default:
            throw new IllegalArgumentException(OperationFlag.MESSAGE_CONSTRAINTS);
        }
    }

    public OperationType getOperationType() {
        return operationType;
    }

    /**
     * Checks if a given string represents a valid operation type.
     */
    public static boolean isValidOperationType(String s) {
        return s.equals(ADD_FLAG) || s.equals(REMOVE_FLAG);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OperationFlag
                && operationType.equals(((OperationFlag) other).operationType));
    }
}
