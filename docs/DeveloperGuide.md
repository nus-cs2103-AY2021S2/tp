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
1. The command execution can affect the `Model` (e.g. adding a person).
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

### Design enhancements

#### Model update

The model has been updated to contain new classes for the `menu`, `inventory`, and `order` components (`Dish`, `Ingredient`, and `Order` respectively), in addition to the original `Person` class for the `contact` component. Each component has its own `Book` class, which has its functionality exposed through the `ModelManager` class (Facade pattern).

#### Storage update

The storage has been updated to handle the new classes and their relevant `Book` classes. Sample data has also been added for each book. JSON serializability of each class is ensured via the use of `Jackson` annotations.

#### Component Parser

The `ComponentParser` mechanism is facilitated by `JJIMYParser` with a general command input format of

    [component] [command] [arguments]

`JJIMYParser` will read in the first word of the input which is the `[component]` and pass the command on to the respective component parser (one of `CustomerParser`, `MenuParser`, `OrderParser` and `InventoryParser`) that implements `ComponentParser`. The component word is stripped by `JJIMYParser`, so the relevant `ComponentParser` receives an input format of

    [command] [arguments]

Finally, the respective `ComponentParser` will read in the `[command]` and return their respective `ComponentCommand` to be executed by `LogicManager`.

The following sequence diagram shows how a `CustomerAddCommand` operation is parsed and executed.
![Sequence diagram for a CustomerAddCommand](images/JJIMYParserSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command.
![Activity diagram for a new command](images/JJIMYParserActivityDiagram.png)

#### Data consistency

To ensure data consistency, some calls of the `delete` function have cascading effects. 

##### Deletion of Person objects

When a `Person` is deleted from the model, all `Order`s related to that `Person` should also be deleted, since that `Person` no longer exists. This is illustrated in the following sequence diagram:

![Diagram showing example of cascading deletion](images/CascadingDeletionCustomers.png)

As seen from the above sequence diagram, when `deletePerson` is called on `ModelManager`, it first deletes the `Person`
from `PersonBook`. Then, it retrieves the entire order list from `OrderBook` and checks each individual `Order`. If the
`Order` is associated with the `Person`, then the `Order` is removed by `ModelManger` via the `deleteOrder` method. This
check is done via `Order::isFromCustomer` which returns `true` if the `Order` is associated with the `Customer` and
`false` otherwise.

##### Deletion of Ingredient objects

Another key instance of data consistency occurs between the `Ingredient` and `Dish` classes. The deletion of an Ingredient also affects all the dishes that use that ingredient and hence, those `Dish`es will also be removed.

When an `Ingredient` is being attempted to be deleted, a check is first done to see if any `Dish` uses that `Ingredient`. If no `Dish` uses the `Ingredient`, then it is deleted immediately.

However, in the event that there are `Dish`es that use the `Ingredient` in question, then a warning will be displayed and users will be required to re-enter their command but with a `-f` flag to confirm that they want to also delete all `Dish`es associated with the `Ingredient`.

##### Logging of Order object

Data consistency extends beyond deletion. When `Order` objects are created, the `Ingredient`s and their quantities are tabulated from the `Dish`es and their respective quantities. 
The quantity of each `Ingredient` is then decremented by the corresponding amount. 
This automated data link ensures that the restaurant owner will be notified when they are attempting to place orders for dishes that have insufficient stock to produce.

#### Concurrent list display

To increase the efficiency of adding food orders, the GUI has been improved to display two lists at the same time. The customer list will always be shown on the left column whereas the right column will display one of the other components.
 
Which component list is shown on the right will depend on the component of the last command input. For example, using a `menu add` command will cause the right side to display the menu list, whereas `order add` will show the right side to display the order list. However, using a command on the `customer` component will only update the left list and not affect the right list.

#### \[Proposed\] Data archiving

It is proposed that the general use case for removing `Order` objects from the currently displayed list will become not to delete them, but to *archive* them for future reference (e.g. accounting purposes). This will be achieved with a `completed` field within each Order object, which will determine whether they are displayed in the currently active order list or in the archived order list.

### Command enhancements

#### Add command

The `add` command is implemented for all four components and can be called from the CLI input with the general form

	[component] add [arguments...]
	
The arguments differ depending on what component the `add` command is being called on. (For more details, see the description of individual `add` commands in the [User Guide](https://ay2021s2-cs2103t-w15-3.github.io/tp/UserGuide.html).) 

For details on how the command is parsed, refer to the explanation in the [Component Parser description](#component-parser). After the command is successfully parsed into an add `Command` object (e.g. `MenuAddCommand`), the `Command` object is executed by the `LogicManager`; the `add` commands' `execute` methods include validation routines to ensure the item to be added is both valid and not a duplicate of an item in the list.

Finally, the `ModelManager` is called to add the item to the relevant `Book`, and a `CommandResult` object is returned, which causes the `MainWindow` to update to display the result. The following sequence diagram shows how the `MainWindow` is updated after a `menu add` command is called by the user. Note that, as detailed in the [concurrent list display description](#concurrent-list-display), the right-hand side of the `MainWindow` will be updated to show the new state of the menu.

![Sequence diagram showing GUI update caused by a MenuAddCommand](images/MenuAddGUI.png)

#### Delete command

The `delete` command is implemented for all four components and can be called from the CLI input with the form

	[component] delete [arguments...]

The argument for the `delete` command is always the (1-indexed) index of the item to be deleted, *as shown in the currently displayed list* .

For details on how the command is parsed, refer to the explanation in the [Component Parser description](#component-parser). After the command is successfully parsed into an delete `Command` object (e.g. `MenuDeleteCommand`), the `Command` object is executed by the `LogicManager`; the `delete` commands' `execute` methods include validation routines to ensure the index selected is a valid index.

Finally, the `ModelManager` is called to delete the item from the relevant `Book`, and a `CommandResult` object is returned, which causes the `MainWindow` to update to display the result.

In some cases, use of the `delete` command may trigger cascading `delete`s on other lists to maintain data consistency. For more information, see the [data consistency section](#data-consistency) of this Developer Guide.

After execution, the GUI's displayed list is updated in a similar fashion to the GUI update caused by the [add command](#add-command).

#### List command

The `list` command is implemented for all four components and can be called from the CLI input with the form

	[component] list

There are no arguments for the `list` command. 

Unlike the other commands, the `list` command has no specific parsers beyond the base component parsers (e.g. `MenuParser`; there is **no** `MenuListParser`), since there are no further arguments to parse. Therefore, the `Command` object is created directly by the base component parser and returned to be executed into a `CommandResult` object. The `CommandResult` is used to update the GUI, as explained in the [concurrent list display description](#concurrent-list-display).

The following sequence diagram shows how the GUI is updated from `MainWindow` after a `menu list` command is called by the user.
![Sequence diagram showing GUI update caused by a MenuListCommand](images/MenuListGUI.png)

#### \[Proposed\] Find command

The `find` command will be implemented for all four components and can be called from the CLI input with the general form

	[component] find [arguments...]

The arguments of the `find` command will always be the keyword(s) to be searched for.

The `find` command will be parsed in a similar way to other commands (see the [Component Parser description](#component-parser)). The `find` command will update the `FilteredList` object to only contain items that match the keywords and return a `CommandResult` object to update the GUI, in a similar fashion to the GUI update caused by the [add command](#add-command).

#### \[Proposed\] Edit command

The `edit` command will be implemented for all four components and can be called from the CLI input with the general form

	[component] edit [arguments...]

Similar to the implementation of the `add` command, the arguments will differ depending on what component the `edit` command is being called on.

The `edit` command will be parsed in a similar way to other commands (see the [Component Parser description](#component-parser)). The `edit` command will select an object from the *currently displayed list* via its (1-indexed) index and create a new object with the same parameters, except for the parameters given as arguments to be updated. 

This new object will replace an object in the current book and return a `CommandResult` object to update the GUI from `MainWindow`, in a similar fashion to the GUI update caused by the [add command](#add-command).

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts, orders, menu items and inventory
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts, orders, menu items and inventory faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a...                        | I want to...                                               | So that I can...                                            |
| -------- | ------------------------------ | ---------------------------------------------------------- | ----------------------------------------------------------- |
| `* * *`  | new user                       | see usage instructions                                     | refer to instructions when I forget how to use the App      |
| `* * *`  | fast typer                     | be able to input by CLI                                    | key in commands faster                                      |
| `* * *`  | restaurant owner               | add a customer's contact                                   | keep track of each customer's details                       |
| `* * *`  | restaurant owner               | remove a customer's contact                                | remove customers who no longer patronize the restaurant     |
| `* * *`  | restaurant owner               | add dishes to the menu                                     | keep track of dishes being offered                          |
| `* * *`  | restaurant owner               | remove dishes from the menu                                | remove dishes that are not being offered anymore            |
| `* * *`  | restaurant owner               | add food orders to the order list                          | keep track of the food I need to prepare                    |
| `* * *`  | restaurant owner               | remove food orders from the order list                     | remove the order if my customers changed their minds        |
| `* * *`  | restaurant owner               | add the ingredients that I have restocked to the inventory | know which ingredients I have in stock                      |
| `* * *`  | restaurant owner               | remove ingredients from the food inventory                 | remove an ingredient I have just used                       |
| `* * *`  | restaurant owner               | view a list of ingredients from the food inventory         | so I know which ingredients I have in stock                 |
| `* * *`  | restaurant owner               | add tasks to my shopping list        			 | so I can remember which items to restock                    |
| `* * *`  | restaurant owner               | remove tasks from my shopping list    		         | so I can remove tasks I don't need anymore                  |
| `* * *`  | restaurant owner               | view all tasks to my shopping list                         | so I can view which items to restock                        |
| `* * *`  | restaurant owner               | add dishes to the menu list                                | so I can keep track of the dishes being offered             |
| `* * *`  | restaurant owner               | remove dishes to the menu list                             | so I can remove dishes that are not being offered anymore   |
| `* * *`  | restaurant owner               | view all dishes to the menu list                           | so I can view all the dishes being offered                  |
| `* * *`  | restaurant owner               | view the list of food orders                               | so I know which dishes to prepare                           |
| `* *`    | restaurant owner               | edit a customer's contact                                  | rectify typos for customer errors                           |
| `* *`    | user with many contacts        | find a customer's contact                                  | quickly locate the contact details of a particular customer |
| `* *`    | owner with a large menu        | find a dish on the menu                                    | quickly locate details of a dish on the menu                |
| `* *`    | owner of a busy restaurant     | find a food order from the order list                      | quickly locate the details of an order I'm working on       |
| `* *`    | owner with a complex inventory | find the quantity of an ingredient in the food inventory   | quickly check how much of a certain ingredient I have left  |

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)


**Use case: Request help**

**MSS**

1.  User requests help
2.  JJIMY displays a list of commands

    Use case ends.

**Use case: Exit**

**MSS**

1.  User requests to exit
2.  JJIMY exits

    Use case ends.

**Use case: Add a contact**

**MSS**

1.  User requests to add a contact
2.  JJIMY adds the contact

    Use case ends.

**Extensions**

* 1a. JIMMY detects duplicate

	* 1a1. JIMMY shows an error message

    Use case ends.

**Use case: List all contacts**

**MSS**

1.  User requests to list contacts
2.  JJIMY shows a list of contacts

    Use case ends.

**Use case: Delete a contact**

**MSS**

1.  User requests to list contacts
2.  JJIMY shows a list of contacts
3.  User requests to delete a specific contact in the list
4.  JJIMY deletes the contact

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. JJIMY shows an error message.

      Use case resumes at step 2.

**Use case: Find a contact**

**MSS**

1. User requests to list contacts
2. JJIMY shows a list of contacts
3. User requests to find contacts based on keywords.
4. JJIMY returns a list of matching contacts for the keywords.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given keywords do not match any contacts.

    * 3a1. JJIMY shows an error message.

      Use case resumes at step 2.

**Use case: Add a menu item**

**MSS**

1.  User requests to add a menu item
2.  JJIMY adds the menu item

    Use case ends.

**Extensions**

* 1a. JIMMY detects duplicate

	* 1a1. JIMMY shows an error message

    Use case ends.

**Use case: List all menu items**

**MSS**

1.  User requests to list menu items
2.  JJIMY shows a list of menu items

    Use case ends.

**Use case: Delete a menu item from the menu**

**MSS**

1.  User requests to list menu items
2.  JJIMY shows a list of menu items
3.  User requests to delete a specific menu item in the list
4.  JJIMY deletes the menu item

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. JJIMY shows an error message.

      Use case resumes at step 2.

**Use case: Find a menu item**

**MSS**

1. User requests to list menu items
2. JJIMY shows a list of menu items
3. User requests to find menu items based on keywords.
4. JJIMY returns a list of matching menu items for the keywords.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given keywords do not match any menu item.

    * 3a1. JJIMY shows an error message.

      Use case resumes at step 2.

**Use case: Add an order**

**MSS**

1.  User requests to add an order
2.  JJIMY adds the order

    Use case ends.

**Extensions**

* 1a. JIMMY detects duplicate

	* 1a1. JIMMY shows an error message

    Use case ends.

**Use case: List all orders**

**MSS**

1.  User requests to list orders
2.  JJIMY shows a list of orders

    Use case ends.

**Use case: Delete an order**

**MSS**

1.  User requests to list orders
2.  JJIMY shows a list of orders
3.  User requests to delete a specific order in the list
4.  JJIMY deletes the order

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. JJIMY shows an error message.

      Use case resumes at step 2.



**Use case: Find an order**

**MSS**

1. User requests to list orders
2. JJIMY shows a list of orders
3. User requests to find orders based on keywords.
4. JJIMY returns a list of matching orders for the keywords.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given keywords do not match any order.

    * 3a1. JJIMY shows an error message.

      Use case resumes at step 2.

**Use case: Add an inventory item**

**MSS**

1.  User requests to add an inventory item
2.  If the quantity is 0, JJIMY adds a new ingredient, otherwise it increments the quantity

    Use case ends.

**Use case: List all inventory items**

**MSS**

1.  User requests to list all inventory items
2.  JJIMY shows a list of all inventory items

    Use case ends.

**Use case: Delete an inventory item**

**MSS**

1.  User requests to list all inventory items
2.  JJIMY shows a list of all inventory items
3.  User requests to delete a specific inventory item in the list
4.  JJIMY deletes the inventory item

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. JJIMY shows an error message.

      Use case resumes at step 2.

**Use case: Decrease the quantity of an inventory item**

**MSS**

1. User requests to list all inventory items
2.  JJIMY shows a list of all inventory items
3.  User requests to decrease the quantity of a specific inventory item in the list
4.  JJIMY decreases the quantity inventory item

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. JJIMY shows an error message.

      Use case resumes at step 2.


**Use case: Find a inventory item**

**MSS**

1. User requests to list all inventory items
2. JJIMY shows a list of all inventory items
3. User requests to find inventory items based on keywords.
4. JJIMY returns a list of matching inventory items for the keywords.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given keywords do not match any inventory item.

    * 3a1. JJIMY shows an error message.

      Use case resumes at step 2.


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 2000 total items (contacts, menu items, inventory stock) without a noticeable sluggishness in performance for typical usage.
3.  Should be able to complete any single request within 200ms.
4.  Should work entirely client-side, without involving a remote server.
5.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **Inventory**: A list of necessary food ingredients and their associated stock quantities
* **Mainstream OS**: Windows, Linux, Unix, OS X
* **Private contact detail**: A contact detail that is not meant to be shared with others
