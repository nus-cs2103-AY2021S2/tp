### [Feature] Marking `Event` as Done

This feature allows `Event` to be marked as done. Helps user to easily keep track of what events have been completed.

In the display, done `Event` will include a tick to represent completion.

#### Implementation
* Syntax for EDoneCommand: `edone INDEX [INDEX]...`
* Modification to `Event` class
    * New attribute `isDone` to represent a done and not done event.
    * A `setDone()` method to return a new `Event` object that is done.
    * A `getStatus()` method that returns a tick if the `Event` is done (for UI display).

Given below is an example usage scenario and how `edone` will work.

1. The user executes `edone 1 2 3` command to mark event at index 1, 2 and 3 as done.

2. `LogicManager` calls `parseCommand("edone 1 2 3")` of `AddressBookParser` to parse the input.

3. `AddressBookParser` detects command word `edone` and creates an `EDoneCommandParser`.

4. `AddressBookParser` calls `parse("1 2 3")` of `EDoneCommandParser`.

5. `EDoneCommandParser` processes the input and compiles the valid indexes into a list `List<Index>`.

6. `EDoneCommandParser` creates an `EDoneCommand(List<Index>)` and returns it to `LogicManager`.

7. `LogicManger` executes the `EDoneCommand`.

8. `EDoneCommand` loops through the list of index, and set the events, at the given index, as a done event.

9. `EDoneCommand` creates a `CommandResult` containing the output message and returns it to `LogicManager`.

Given below is the full Sequence Diagram for interactions for the `execute("edone 1 2 3")` API call.

![Sequence Diagram for EDone](images/EDoneSequenceDiagram.png)
