package seedu.address.logic.filters.combinator;

import java.util.function.BinaryOperator;

/**
 * Encapsulates a logical operator, which could be either unary or binary.
 */
public enum LogicalOperator {
    AND(true, (x, y) -> x && y),
    OR(true, (x, y) -> x || y),
    NOT(false, (x, y) -> !x);

    private final boolean isBinaryOperator;
    private final BinaryOperator<Boolean> binaryOperator;

    LogicalOperator(boolean isBinaryOperator, BinaryOperator<Boolean> binaryOperator) {
        this.isBinaryOperator = isBinaryOperator;
        this.binaryOperator = binaryOperator;
    }

    /**
     * Checks if the operator is binary.
     *
     * @return - true if its a binary operator, false otherwise
     */
    public boolean isBinaryOperator() {
        return isBinaryOperator;
    }

    /**
     * Checks if the operator is unary.
     *
     * @return - true if its a unary operator, false otherwise
     */
    public boolean isUnaryOperator() {
        return !isBinaryOperator;
    }

    /**
     * Applies the given operator to a pair of operands. Note that the operator must be of binary type.
     *
     * @param b1 - the first operand
     * @param b2 - the second operand
     * @return - the result of the operator acting on the two operands
     * @throws IllegalArgumentException - if this is called on an Unary Operator
     */
    public boolean apply(boolean b1, boolean b2) {
        if (isUnaryOperator()) {
            throw new IllegalArgumentException("Two parameters passed for a unary operation");
        }

        return binaryOperator.apply(b1, b2);
    }

    /**
     * Applies the given operator to a pair of operands. Note that the operator must be of unary type.
     *
     * @param b1 - the operand
     * @return - the result of the operator acting on the operand
     * @throws IllegalArgumentException - if this is called on an binary Operator
     */
    public boolean apply(boolean b1) {
        if (isBinaryOperator) {
            throw new IllegalArgumentException("One parameter passed for a unary operation");
        }

        return binaryOperator.apply(b1, b1);
    }
}
