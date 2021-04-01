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

![Architecture Diagram of StoreMando](images/ArchitectureDiagram.png)

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of
each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/AY2021S2-CS2103T-W10-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.

</div>

**`Main`** has two classes
called [`Main`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/Main.java)
and [`MainApp`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/MainApp.java).
It is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component) The UI of the App.
* [**`Logic`**](#logic-component) The command executor.
* [**`Model`**](#model-component) Holds the data of the App in memory.
* [**`Storage`**](#storage-component) Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding
  API `interface` mentioned in the previous point).

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and
exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

![Sequence Diagram of the Architecture](images/ArchitectureSequenceDiagram.png)

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are
in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/logic/Logic.java)

1. `Logic` uses the `StoreMandoParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding an item).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying
   help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 5")` API
call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** :
[`Model.java`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the StoreMando data.
* exposes an unmodifiable `ObservableList<Item>` that can be 'observed' e.g. the UI can be bound to this list so that
  the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** :
[`Storage.java`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/storage/Storage.java)

The `Storage` component,

* can save `UserPref` objects in json format and read it back.
* can save the StoreMando data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.storemando.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Delete Item `Delete`

#### Actual Implementation

The `delete` feature allows users to delete an item in their Inventory by the item's index in the list.

The following sequence diagram shows how the delete operation works:

![DeleteSequenceDiagram](images/DeleteSequenceDiagram.png)

Given below is an example usage scenario and how delete mechanism behaves at each step.

Step 1. User executes `delete 5` to delete the 5th item in the list. `StoreMandoParser` takes in the user input and
determines the command word (delete) and argument (5) respectively.

Step 2. An instance of `DeleteCommandParser` will be created, followed by a call on its `parse` method, taking in the
argument stated in step 1 (5).

Step 3. The `parse` method will check for the validity of the index. If valid, a new `DeleteCommand` instance will be
created and returned to `LogicManager` class via `StoreMandoParser` class.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** If the index is determined to be invalid, a parseException will be thrown to notify the
user of the error.

</div>

Step 4. The overridden `execute` method will be called, deleting the item from the list. An instance of `CommandResult`
will be created, generating the result of the execution. The `LogicManager` class will receive the result of the
execution. The item is deleted from `StoreMando`.

The following activity diagram summarizes what happens when a user executes a new command:

![DeleteActivityDiagram](images/DeleteActivityDiagram.png)

#### Design consideration:

##### Aspect: How delete executes

* **Alternative 1 (current choice):** Delete item by an index.
    * Pros: Easy to implement.
    * Cons: Requires user to scroll through the list to find the item and specify the index.

* **Alternative 2:** Delete item by item name.
    * Pros: Will be easier for the user especially when the list is huge.
    * Cons: There are items with the same name but in different location, will cause confusion.

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

* household resident
* has a lot of items at home and cannot constantly keep track of their respective locations
* has a lot of perishable items with various expiry dates that are difficult to remember
* prefers desktop applications over other types
* fast typist
* prefers typing to using mouse
* comfortable using CLI applications

**Value proposition**: Every info of every item you have at home - all in one place. One command is all you have to key
in to add, delete or find for an item. StoreMando keeps track of everything you need so that you don't have to
physically search for an item to obtain information on it. Get everything you need from StoreMando - locations,
quantities and expiry dates.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​ | So that I can…​ | 
| -------- | ---------- | --------------- | ------------------ | 
| `* * *` | user | add an item to the inventory | keep track of it’s location, quantity and expiry date |
| `* * *` | user who discards items often | delete an item from the inventory | remove it when it is expired or used up |
| `* * *` | user who stocks up items regularly | update my items’ details | change the items' expiry dates and/or quantities accordingly | 
| `* * *` | user with many items | list down all items | know all the items I have in one glance |
| `* *` | user who has many items in my room | see all items in my room | keep track of exactly what I have | 
| `* *` | user who tags my items meaningfully | see all items with the same tag | see what items I have with that particular tag |
| `* * *` | forgetful user with many items | search for an item quickly | locate them easily |
| `* *` | forgetful user | be aware of my expiring items | replace them before it is expired or discard them if they have expired |
| `* *` | grocery buyer of the household | sort my items in terms of quantity | stock up items that are running low on quantity |
| `* *` | user who does not like to waste food | sort my food in terms of expiry date | consume food that is expiring first |
| `*` | user who discards large number of items at once | clear all the items in the inventory | start the list afresh without having waste time deleting each item manually |
| `*` | user who does room cleaning during CNY | clear all the items in a certain location | add them back to different places easily |

_**(more to be added)**_

### Use cases

(For all use cases below, the **System** is the `StoreMando` and the **Actor** is the `user`, unless specified
otherwise)

**Use case: UC1 - Add an item**

**MSS**

1. User requests to add a specific item into the inventory list.
2. StoreMando adds the item into the inventory list and displays the updated list of items.

   Use case ends.

**Extensions**

* 1a. User input has invalid syntax.

    * 1a1. StoreMando shows an error message.

      Use case resumes at step 1.

* 1b. Duplicate item exists in the inventory.

    * 1b1. StoreMando shows an error message.

      Use case resumes at step 1.

**Use case: UC2 - Delete an item in a specific location**

**MSS**

1. User requests to <u> list all items in a specific location (UC3). </u>
2. StoreMando displays all items in the location.
3. User requests to delete a specific item in the list.
4. StoreMando deletes the specified item from the list and displays the updated list of items.

   Use case ends.

**Extensions**

* 2a. There are no items in the specified location.

  Use case ends.

* 3a. The index keyed in by the user does not exist in the displayed list.

    * 3a1. StoreMando shows an error message.

      Use case resumes at step 3.

**Use case: UC3 - List all items in a specific location**

**MSS**

1. User requests to display all items in a specific location.
2. StoreMando displays all items in that specific location.

   Use case ends.

**Extensions**

* 1a. User input has invalid syntax.

    * 1a1. StoreMando shows an error message.

      Use case resumes at step 1.

**Use case: UC4 - Find an item**

**MSS**

1. User requests to find an item with a particular name.
2. StoreMando returns a list of all items whose name contains the name specified by the user.

   Use case ends.

**Use case: UC5 - Edit an item**

**MSS**

1. User requests to edit an existing item's details.
2. StoreMando edits the specified item and displays the updated list of items.

   Use case ends.

**Extensions**

* 1a. The command keyed in by the user has an invalid syntax.

    * 1a1. StoreMando shows an error message.

      Use case resumes at step 1.

* 1b. The new details keyed in by the user is the same as the existing details of the item.

    * 1b1. StoreMando shows an error message.

      Use case resumes at step 1.

**Use case: UC6 - Check for expiring items**

**MSS**

1. User wants to search for items that are expiring within the next 7 days.
2. StoreMando displays a list of items that have either expired or are expiring within the next 7 days.

   Use case ends.

**Extensions**

* 1a. User inputs a negative number.

    * 1a1. StoreMando shows an error message.

      Use case resumes at step 1.

* 1a. Time unit input is neither day(s) or week(s)

    * 1a1. StoreMando shows an error message.

      Use case resumes at step 1.

**Use case: UC7 - List all items**

**MSS**

1. User requests to display all items in the inventory.
2. StoreMando displays all items in the inventory.

   Use case ends.

**Use case: UC8 - List all items with a specific tag**

**MSS**

1. User requests to display all items with a specific tag.
2. StoreMando displays all items with the specific tag.

   Use case ends.

**Use case: UC9 - Sort items in ascending order of quantity**

**MSS**

1. User requests to sort the items in the displayed list in increasing order of quantity.
2. StoreMando displays the list of items in increasing order of quantity.

   Use case ends.

**Use case: UC10 - Sort items in descending order of quantity**

**MSS**

1. User requests to sort the items in the displayed list in decreasing order of quantity.
2. StoreMando displays the list of items in decreasing order of quantity.

   Use case ends.

**Use case: UC11 - Sort items by expiry date**

**MSS**

1. User requests to sort the items in the displayed list by expiry date.
2. StoreMando displays a sorted list of the items in chronological order of their expiry date.

   Use case ends.

**Use case: UC12 - Delete all items in a specific location**

**MSS**

1. User requests to delete all items in a specific location.
2. StoreMando deletes all items in that location.

Use case ends.

**Extensions**

* 1a. The location keyed in by the user does not exist in the inventory.

    * 1a1. StoreMando shows an error message.

      Use case resumes at step 1.

*{More to be added}*

### Non-Functional Requirements

1. **Performance**
    * Result should appear within 0.3 seconds after user keys in a command.
2. **Reliability**
    * Should be able to hold up to 1000 line items in the house without any sluggish performance for typical usage.
3. **Portability**
    * Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
4. **Usability**
    * A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should
      be able to accomplish most of the tasks faster by typing rather than using the mouse.
    * StoreMando should work with or without Internet connection.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **StoreMando**: Name of the application
* **CLI**: Command Line Interface
* **GUI**: Graphical User Interface
* **Inventory**: List of all items stored in StoreMando

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting an item

1. Deleting an item while all items are being shown

    1. Prerequisites: List all household items using the `list` command. Multiple items in the list.

    1. Test case: `delete 1`<br>
       Expected: First item is deleted from the list. Details of the deleted item shown in the status message.

    1. Test case: `delete 0`<br>
       Expected: No item is deleted. Error details shown in the status message.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Adding an item

1. Adding an item to StoreMando

    1. Prerequisites: Arguments are valid and compulsory parameters are provided. No duplicate item or similar item
       exists in the list.

    1. Test case: `add n/Banana q/1 l/kitchen e/2020-10-10 `<br>
       Expected: Item is added into the displayed list. Details of the added item shown in the status message.

    1. Test case: `add `<br>
       Expected: No item is added. Error details shown in the status message.

    1. Other incorrect add commands to try: `add n/`, `add l/kitchen`, `...` (where compulsory fields are not
       specified)<br>
       Expected: Similar to previous.

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
