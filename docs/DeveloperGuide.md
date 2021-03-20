---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a customer).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th customer in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new customer. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the customer was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the customer being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Companies that have delivery operations and freelance delivery drivers
* Need to manage many delivery entries and their respective details
* Prefers typing to mouse interactions
* Can type fast
* Is reasonably comfortable using CLI apps
* Looking for a desktop app over other types to better manage their workflow

**Value proposition**: manage delivery workflows and details faster than a typical mouse/GUI driven app for greater efficiency

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                   | I want to …​                                                                                | So that I can…​                                           |
| -------- | --------------------------| ------------------------------------------------------------------------------------------- | --------------------------------------------------------- |
| `* * *`  | delivery driver           | add a delivery entry to the list (product, pickup/drop off time and address, route)         | make the delivery immediately/later                       |
| `* * *`  | delivery driver           | mark a delivery entry in the list as done                                                   | know which deliveries I've done                           |
| `* * *`  | delivery driver           | delete a delivery entry from the list                                                       | remove the delivery entry from the list                   |
| `* * *`  | delivery driver           | delete all delivery entries from the list                                                   | remove all delivery entries from the list                 |
| `* * *`  | delivery driver           | edit a delivery entry in the list                                                           | make necessary changes to the delivery details            |
| `* * *`  | delivery driver           | get the list of delivery entries                                                            | see all the deliveries I have to make                     |
| `* * *`  | delivery driver           | get the list of completed delivery entries                                                  | see all the deliveries I have done                        |
| `* * *`  | delivery driver           | get the list of delivery entries ranked by urgency/timing                                   | see which are the more urgent deliveries to be made first |
| `* * *`  | delivery driver           | find all delivery entries associated with a time (pickup/drop off time)                     | see the deliveries with the pickup/drop off times         |
| `* * *`  | delivery driver           | find all delivery entries associated with a location (pickup/drop off address)              | see the deliveries with the pickup/drop off addresses     |
| `* * *`  | delivery driver           | find all delivery entries associated with a keyword or phrase                               | see the deliveries with the specific keyword/phrase       |
| `* * *`  | delivery driver           | exit the app                                                                                | exit the app                                              |
| `* *`    | delivery driver           | be notified of a duplicate delivery entry                                                   | avoid adding a duplicate delivery entry to the list       |
| `* *`    | delivery driver           | to set reminders for each delivery                                                          | be notified prior to each delivery                        |
| `* *`    | delivery driver           | get a pre-planned route for the delivery                                                    | make the delivery faster and more efficiently             |
| `* *`    | delivery driver           | get details of the delivery good (fragile, etc.)                                            | take necessary preparations/precautions for various goods |
| `*`      | delivery driver           | get basic customer details of the delivery (contact details, delivery frequency, business)  | understand my customers better                            |
| `*`      | delivery driver           | to have an estimated delivery duration for a delivery entry                                 | plan my time better                                       |
| `*`      | new delivery driver       | see samples of deliveries entries                                                           | familiar myself with the app and process                  |
| `*`      | new delivery driver       | receive easier deliveries (shorter distance)                                                | transition into using the app more easily                 |
| `*`      | part-time delivery driver | choose specific delivery timings and receive delivery notifications during specific timings | make deliveries during my free time                       |

*{More to be added}*

### Use cases

**Software System: Delivery App**

**Use case: UC01 - add a delivery entry to the list**

**Actor: User (delivery customer)**

**Guarantees:**

* A new delivery entry will be added to the list of deliveries.

**MSS**

1.  User indicates that they will be adding a delivery entry.
2.  Delivery App requests for details of the delivery entry.
3.  User enters the delivery entry to be added to the list.
4.  Delivery App adds the delivery entry to the list and informs the User.

    Use case ends.

**Extensions**

* 3a. The delivery entry to be added has an invalid format.
    * 3a1. Delivery App requests for a valid delivery entry.
    * 3a2. User enters new delivery entry details.
      
    Steps 3a1-3a2 are repeated until the valid details are entered.

    Use case resumes from step 4.

* 3b. The delivery entry to be added already exists in the list.
    * 3b1. Delivery App informs the User of the duplicate.

    Use case ends.

**Software System: Delivery App**

**Use case: UC02 - mark a delivery entry in the list as done**

**Actor: User (delivery customer)**

**Guarantees:**

* A delivery entry in the list will be marked as done.

**MSS**

1.  User requests to see the list of delivery entries.
2.  Delivery App shows the list.
3.  User indicates which delivery entry to mark as done by entering the entry number.
4.  Delivery App marks that delivery entry as done and informs the User.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

    Use case ends.

* 3a. The number of the delivery entry is invalid.
    * 3a1. Delivery App requests for a valid entry number.
    * 3a2. User enters new entry number.

    Steps 3a1-3a2 are repeated until the valid entry number is entered.

    Use case resumes from step 4.

**Software System: Delivery App**

**Use case: UC03 - delete a delivery entry from the list**

**Actor: User (delivery customer)**

**Guarantees:**

* An existing delivery entry will be deleted from the list of deliveries.

**MSS**

1.  User requests to see the list of delivery entries.
2.  Delivery App shows the list.
3.  User indicates which delivery entry to be deleted by entering the entry number.
4.  Delivery App removes that delivery entry from the list and informs the User.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 3a. The number of the delivery entry is invalid.
    * 3a1. Delivery App requests for a valid entry number.
    * 3a2. User enters new entry number.

  Steps 3a1-3a2 are repeated until the valid entry number is entered.

  Use case resumes from step 4.

**Software System: Delivery App**

**Use case: UC04 - delete all delivery entries from the list**

**Actor: User (delivery customer)**

**Guarantees:**

* All existing delivery entries will be deleted from the list of deliveries.

**MSS**

1.  User indicates to delete all delivery entries from the list.
2.  Delivery App removes all delivery entries from the list and informs the User.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that there are no delivery entries to delete.

  Use case ends.

**Software System: Delivery App**

**Use case: UC05 - edit a delivery entry in the list**

**Actor: User (delivery customer)**

**Guarantees:**

* An existing delivery entry will be updated in the list of deliveries.

**MSS**

1.  User requests to see the list of delivery entries.
2.  Delivery App shows the list.
3.  User indicates which delivery entry to edit by entering the entry number.
4.  Delivery App requests for updated details of the delivery entry.
5.  User enters the updated details.
6.  Delivery App replaces the old delivery entry with the updated one and informs the User.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 3a. The number of the delivery entry is invalid.
    * 3a1. Delivery App requests for a valid entry number.
    * 3a2. User enters new entry number.

  Steps 3a1-3a2 are repeated until the valid entry number is entered.

  Use case resumes from step 4.

* 5a. The updated details have an invalid format.
    * 5a1. Delivery App requests for a valid format.
    * 5a2. User enters new details.

  Steps 3a1-3a2 are repeated until the valid details are entered.

  Use case resumes from step 6.

* 5b. The edited entry already exists in the list.
    * 5b1. Delivery App informs the User of the duplicate.

  Use case ends.

**Software System: Delivery App**

**Use case: UC06 - get the list of delivery entries**

**Actor: User (delivery customer)**

**Guarantees:**

* a list of all existing delivery entries.

**MSS**

1.  User requests to see all delivery entries from the list.
2.  Delivery App lists out all existing delivery entries.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

**Software System: Delivery App**

**Use case: UC07 - get the list of completed delivery entries**

**Actor: User (delivery customer)**

**Guarantees:**

* a list of all completed delivery entries.

**MSS**

1.  User requests to see all completed delivery entries from the list.
2.  Delivery App lists out all delivery entries that were marked as done.

    Use case ends.

**Extensions**

* 1a. The completed list is empty.
    * 1a1. Delivery App informs the User that the completed list is empty.

  Use case ends.

**Software System: Delivery App**

**Use case: UC08 - get the list of delivery entries ranked by urgency**

**Actor: User (delivery customer)**

**Guarantees:**

* a list of existing delivery entries ranked by urgency.

**MSS**

1.  User requests to see all delivery entries from the list ranked by urgency.
2.  Delivery App lists out all existing delivery entries ranked by urgency, starting from most to least urgent.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.
  
  Use case ends.

* 1b. There are more than 1 delivery entry with the same urgency.
    * 1b1. Delivery App ranks them based on timing then alphabetical order.

  Use case ends.

**Software System: Delivery App**

**Use case: UC09 - get the list of delivery entries ranked by timing**

**Actor: User (delivery customer)**

**Guarantees:**

* a list of existing delivery entries ranked by timing.

**MSS**

1.  User requests to see all delivery entries from the list ranked by timing.
2.  Delivery App lists out all existing delivery entries ranked by timing, starting from oldest to latest.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 1b. There are more than 1 delivery entry with the same timing.
    * 1b1. Delivery App ranks them based on urgency then alphabetical order.

  Use case ends.

**Software System: Delivery App**

**Use case: UC10 - find all delivery entries associated with a time (pickup/drop off time)**

**Actor: User (delivery customer)**

**Guarantees:**

* a list of existing delivery entries associated with a time.

**MSS**

1.  User requests to see all delivery entries from the list associated with a pickup/drop off time.
2.  Delivery App lists out all existing delivery entries associated with the pickup/drop off time.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 1b. There are no delivery entries associated with the time.
    * 1b1. Delivery App informs the User that there are no delivery entries associated with that time.

  Use case ends.

**Software System: Delivery App**

**Use case: UC11 - find all delivery entries associated with a location (pickup/drop off address)**

**Actor: User (delivery customer)**

**Guarantees:**

* a list of existing delivery entries associated with an address.

**MSS**

1.  User requests to see all delivery entries from the list associated with a pickup/drop off address.
2.  Delivery App lists out all existing delivery entries associated with the pickup/drop off address.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 1b. There are no delivery entries associated with the address.
    * 1b1. Delivery App informs the User that there are no delivery entries associated with that address.

  Use case ends.

**Software System: Delivery App**

**Use case: UC12 - find all delivery entries associated with a keyword or phrase**

**Actor: User (delivery customer)**

**Guarantees:**

* a list of existing delivery entries with details containing the keyword/phrase.

**MSS**

1.  User requests to see all delivery entries with matching keywords/phrases.
2.  Delivery App lists out all existing delivery entries with details containing the keyword/phrase.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 1b. There are no delivery entries with matching keywords/phrases.
    * 1b1. Delivery App informs the User that there are no delivery entries with details containing the keyword/phrase.

  Use case ends.

**Software System: Delivery App**

**Use case: UC13 - exit the app**

**Actor: User (delivery customer)**

**Guarantees:**

* the app exits.

**MSS**

1.  User indicates to quit the app.
2.  Delivery App exits.

    Use case ends.

**Software System: Delivery App**

**Use case: UC14 - to set reminders for each delivery**

**Actor: User (delivery customer)**

**Guarantees:**

* be notified prior to each delivery.

**MSS**

1.  User requests to see all delivery entries in the list.
2.  Delivery App lists out all existing delivery entries.
3.  User indicates which delivery entry to set a reminder for by entering the entry number.
4.  Delivery App requests for reminder date/time.
5.  User enters the date/time.
6.  Delivery App sets the reminder for that delivery entry.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 3a. The number of the delivery entry is invalid.
    * 3a1. Delivery App requests for a valid entry number.
    * 3a2. User enters new entry number.

  Steps 3a1-3a2 are repeated until the valid entry number is entered.

  Use case resumes from step 4.

* 5a. The reminder date/time have an invalid format.
    * 5a1. Delivery App requests for a valid format.
    * 5a2. User enters new date/time.

  Steps 3a1-3a2 are repeated until the valid date/time are entered.

  Use case resumes from step 6.

* 5b. The delivery entry already has the same reminder.
    * 5b1. Delivery App informs the User of the duplicate reminder.

  Use case ends.

**Software System: Delivery App**

**Use case: UC15 - get a pre-planned route for the delivery**

**Actor: User (delivery customer)**

**Guarantees:**

* to get a delivery route for the delivery.

**MSS**

1.  User requests to see all delivery entries in the list.
2.  Delivery App lists out all existing delivery entries.
3.  User requests to see the delivery route of an entry by entering the entry number.
4.  Delivery App shows the delivery route for the chosen entry.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 3a. The number of the delivery entry is invalid.
    * 3a1. Delivery App requests for a valid entry number.
    * 3a2. User enters new entry number.

  Steps 3a1-3a2 are repeated until the valid entry number is entered.

  Use case resumes from step 4.

**Software System: Delivery App**

**Use case: UC16 - get details of the delivery good**

**Actor: User (delivery customer)**

**Guarantees:**

* to get details of the delivery good.

**MSS**

1.  User requests to see all delivery entries in the list.
2.  Delivery App lists out all existing delivery entries.
3.  User requests to see the details of a delivery entry by entering the entry number.
4.  Delivery App shows the details of the chosen delivery entry.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 3a. The number of the delivery entry is invalid.
    * 3a1. Delivery App requests for a valid entry number.
    * 3a2. User enters new entry number.

  Steps 3a1-3a2 are repeated until the valid entry number is entered.

  Use case resumes from step 4.

**Software System: Delivery App**

**Use case: UC17 - get basic customer details of the delivery**

**Actor: User (delivery customer)**

**Guarantees:**

* to get basic details of the customer of the delivery.

**MSS**

1.  User requests to see all delivery entries in the list.
2.  Delivery App lists out all existing delivery entries.
3.  User requests to see the customer details of a delivery entry by entering the entry number.
4.  Delivery App shows the customer details of the chosen delivery entry.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 3a. The number of the delivery entry is invalid.
    * 3a1. Delivery App requests for a valid entry number.
    * 3a2. User enters new entry number.

  Steps 3a1-3a2 are repeated until the valid entry number is entered.

  Use case resumes from step 4.

**Software System: Delivery App**

**Use case: UC18 - get basic customer details of the delivery**

**Actor: User (delivery customer)**

**Guarantees:**

* to get basic details of the customer of the delivery.

**MSS**

1.  User requests to see all delivery entries in the list.
2.  Delivery App lists out all existing delivery entries.
3.  User requests to see the customer details of a delivery entry by entering the entry number.
4.  Delivery App shows the customer details of the chosen delivery entry.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 3a. The number of the delivery entry is invalid.
    * 3a1. Delivery App requests for a valid entry number.
    * 3a2. User enters new entry number.

  Steps 3a1-3a2 are repeated until the valid entry number is entered.

  Use case resumes from step 4.

**Software System: Delivery App**

**Use case: UC19 - to have an estimated delivery duration for a delivery entry**

**Actor: User (delivery customer)**

**Guarantees:**

* to get an estimated delivery duration of a delivery.

**MSS**

1.  User requests to see all delivery entries in the list.
2.  Delivery App lists out all existing delivery entries.
3.  User requests to get the estimated delivery duration of a delivery entry by entering the entry number.
4.  Delivery App provides the estimated delivery duration of the chosen delivery entry.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. Delivery App informs the User that the list is empty.

  Use case ends.

* 3a. The number of the delivery entry is invalid.
    * 3a1. Delivery App requests for a valid entry number.
    * 3a2. User enters new entry number.

  Steps 3a1-3a2 are repeated until the valid entry number is entered.

  Use case resumes from step 4.

**Software System: Delivery App**

**Use case: UC20 - see samples of deliveries made**

**Actor: New User (new delivery customer)**

**Guarantees:**

* to get samples of delivery entries.

**MSS**

1.  User requests to see samples of delivery entries.
2.  Delivery App shows samples of delivery entries.

    Use case ends.

*{More to be added}*

### Non-Functional Requirements

1. **Usability**:
    - Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
    - A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

2. **Reliability**:
    - Should be able to hold up to 1000 delivery entries and display all data if requested by the user in less than 5 seconds
    - Should be able to detect and inform users of duplicate delivery entries and confirm with user if they wish to proceed in adding them to the list

3. **Security:**
    - Users that request for any delivery entry data to be deleted will have the data permanently erased from memory

4. **Integrity**
    - For any time-related data presented to the user, the date formats will be `DD-MM-YYYY`
    - Delivery entries made by the user will be stored in a _JSON_ file inside the hard disks

5. **Flexibility**
    - TimeforWheels should be able to handle as many date format inputs as possible by the user and convert the date format to `DD-MM-YYYY`

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **JSON:** JavaScript Object Notation

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a customer

1. Deleting a customer while all customers are being shown

   1. Prerequisites: List all customers using the `list` command. Multiple customers in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No customer is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
