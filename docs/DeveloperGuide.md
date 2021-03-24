---
layout: page 
title: Developer Guide
---

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of
each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/AY2021S2-CS2103T-W10-1/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.

</div>

**`Main`** has two classes
called [`Main`](https://github.com/AY2021S2-CS2103T-W10-1/tp/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2021S2-CS2103T-W10-1/tp/tree/master/src/main/java/seedu/address/MainApp.java). It
is responsible for,

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
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and
exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-W10-1/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PassengerListPanel`
, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are
in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2021S2-CS2103T-W10-1/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-W10-1/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-W10-1/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a passenger).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying
   help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API
call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**
API** : [`Model.java`](https://github.com/AY2021S2-CS2103T-W10-1/tp/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Passenger>` that can be 'observed' e.g. the UI can be bound to this list so
  that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Passenger` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Passenger` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**
API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-W10-1/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,

* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

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

- has a need to find a driver/passenger to travel between workspace and home
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**: Eliminate the need for human interaction such as requiring HR personnel to manage to maintain
social distancing

--------------------------------------------------------------------------------------------------------------------

### User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

|Priority|As a …​                    |I want to …​                                                     |So that                                                                                         |
|--------|---------------------------|-----------------------------------------------------------------|------------------------------------------------------------------------------------------------|
|* * *   |Driver                     |select passengers to be picked up                                |I can carpool with my colleagues                                                                |
|* * *   |Driver                     |search for specific type of passengers                           |I can see if any passengers fulfil my criteria and view their carpool details                   |
|* * *   |Driver                     |list all passengers                                              |I can see all the passengers available                                                                                                |
|* * *   |Passenger                  |create my profile                                                |I can find carpooling drivers and be contacted by the driver if needed                                                                                                |
|* * *   |Passenger                  |delete my profile                                                |my data will not be stored when I have stopped the service                                      |
|* *     |Driver                     |filter passengers' destination and pickup point based on location|I don't have to spend too long to pick up or drop off passengers and minimise my travelling time|
|* *     |Female Passenger           |find only female drivers                                         |I can be comfortable                                                                            |
|*       |User concerned with privacy|limit the information I want to disclose                         |my private information exposed is limited                                                       |
|*       |Driver                     |Indicate one-off trips                                           |my passengers would understand this is not a recurring trip                                                                                                |
|* *     |Passenger                  |edit drop off location                                           |change the destination if needed and be more flexible                                           |
|*       |Passenger                  |indicate the price willing to pay                                |I can incentivise the drivers to more likely to choose me and pick me up on time                |

### Use Cases

**Use case: Select a passenger to be picked up**

1. Search or list out passengers available to be picked up
2. GME shows a list of passengers
3. Driver choose and view the details of the specific passenger
4. Driver requests to add the specific passenger to the driver's carpooling group 
   
    Use case ends.

***Extensions***

* 1a. The list is empty.

    Use case ends.

--------------------------------------------------------------------------------------------------------------------

**Use case: Search for specific type of passengers**

1. Driver chooses the criteria that the passengers need to fulfil in order to be picked up
2. Driver initiates the search
3. GME shows a list of passengers that fulfils the criteria
   
   Use case ends.

***Extensions***

* 3a. No passenger fulfils the criteria

  Use case ends.

--------------------------------------------------------------------------------------------------------------------
**Use case: Creates a passenger profile**

1. Passenger fills out their name, contact number and pickup address
2. GME verifies that all the required fields are not empty
3. GME asks the passenger to confirm all data input is correct

   Use case ends

***Extensions***

* 1a. Any required field is missing 
  * 1a1. GME warns the user to input the data missing
    
    Use case ends.

* 1b. User indicates to cancel
  
    Use case ends.

  

--------------------------------------------------------------------------------------------------------------------
**Use case: Delete a passenger** **profile**

1. Passenger indicates they would like to delete their profile
2. GME warns the passenger that the action is irreversible and data cannot be recovered
3. GME verifies that the passenger wish to continue with the action
4. GME deletes the specific passenger's profile

***Extensions***

* 1a. User indicates to cancel

  Use case ends.

--------------------------------------------------------------------------------------------------------------------
**Use case:** **Find only female drivers**

1. Passenger creates the carpooling event.
2. Passenger choose the criteria with `Driver: female only`
3. Only female driver will be able to search the passenger that indicated the preference

***Extensions***

- No female driver searches for passenger

  The passenger will not be shown on any list to be chosen to be picked up

  Use case ends

--------------------------------------------------------------------------------------------------------------------

### Non-Functional Requirements

1. **Usability**:
    - GME shall work on any *mainstream OS* as long as it has Java `11` or above installed.
    - GME's interface shall be user-friendly and easy to use by using mouse when not using commands, meaning all buttons
      and interaction should be self-explanatory and intuitive which can be used by people without training.
    - A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should
      be able to accomplish most of the tasks faster using commands than using the mouse.
2. **Reliability**:
    - GME shall allow drivers to pick passengers throughout the week ant any time during the day, up to 6 passengers
      depending on car size.
    - GME Should be able to hold up to 1000 persons (drivers and passengers included) without a noticeable sluggishness
      in performance for typical usage.
3. **Security:**
    - Only users that creates their own profile can view their private information and change the privacy settings
4. **Integrity**
    - All monetary amounts (including passenger's tips) must be accurate to two decimal places and in SGD.
    - All geolocation coordinates must be accurate to six decimal places.
    - All email and phone numbers provided by users must pass through format check.
    - All time-related data that is presented to user must be accurate to minutes, and use `DD-MM-YYYY` format
    - All timestamps for any events occurred recorded by the GME shall be in UTC (Universal Time Coordinated) when
      placed into permanent storage.
5. **Flexibility**
    - GME shall be able to process different date formats input by user and converts to `DD-MM-YYYY`

--------------------------------------------------------------------------------------------------------------------

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, MacOS
- **Green cars**: Electric or hybrid passenger cars

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

### Deleting a passenger

1. Deleting a passenger while all passengers are being shown

    1. Prerequisites: List all passengers using the `list` command. Multiple passengers in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No passenger is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
