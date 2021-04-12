package seedu.address.logic.filters.combinator;

/**
 * This enum represents the two types of node in the {@code FilterCombinator} - either EVALUATOR OR COMBINATOR.
 */
enum NodeType {
    /**
     * This type of node is used to represent a logical combinator like 'and', 'or' or 'not'. These are essentially
     * operators and can be unary or binary.
     */
    COMBINATOR,
    /**
     * This type of node basically means that there will be no children for this type, and the value is directly
     * derived from a {@code Filter}.
     */
    EVALUATOR
}
