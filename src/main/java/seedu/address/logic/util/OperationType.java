package seedu.address.logic.util;

import static seedu.address.logic.util.OperationFlag.ADD_FLAG;
import static seedu.address.logic.util.OperationFlag.REMOVE_FLAG;

public enum OperationType {
    ADD(ADD_FLAG),
    REMOVE(REMOVE_FLAG);

    private final String operationType;

    OperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }
}
