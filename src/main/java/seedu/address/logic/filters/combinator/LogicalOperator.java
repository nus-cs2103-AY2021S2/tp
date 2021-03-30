package seedu.address.logic.filters.combinator;

import java.util.function.BinaryOperator;

public enum LogicalOperator {
    AND(true, (x, y) -> x && y),
    OR(true, (x, y) -> x || y),
    NOT(false, (x, y) -> !x);

    private boolean isBinaryOperator;
    private BinaryOperator<Boolean> binaryOperator;

    LogicalOperator(boolean isBinaryOperator, BinaryOperator<Boolean> binaryOperator) {
        this.isBinaryOperator = isBinaryOperator;
        this.binaryOperator = binaryOperator;
    }

    public boolean isBinaryOperator() {
        return isBinaryOperator;
    }

    public boolean isUnaryOperator() {
        return !isBinaryOperator;
    }

    /**
     * @param b1
     * @param b2
     * @return
     */
    public boolean apply(boolean b1, boolean b2) {
        if (isBinaryOperator) {
            return binaryOperator.apply(b1, b2);
        } else {
            throw new IllegalArgumentException("Two parameters passed for a unary operation");
        }
    }

    /**
     * @param b1
     * @return
     */
    public boolean apply(boolean b1) {
        if (!isBinaryOperator) {
            return binaryOperator.apply(b1, b1);
        } else {
            throw new IllegalArgumentException("One parameter passed for a unary operation");
        }
    }
}
