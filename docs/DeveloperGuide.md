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
## **Introduction**
**Tutor Tracker** is a desktop tuition management application meant for secondary students to track their tuition information, such as upcoming tuition appointments and tutor's contact information.
It focuses on the Command Line Interface (CLI) while providing users with a simple and clean Graphical User Interface (GUI).
Thus, the main interaction with **Tutor Tracker** will be done through commands.

Tutor Tracker is an all-in-one tuition management solution for tech-savvy secondary school students.
The features of Tutor Tracker includes:

- Adding, editing, deleting and viewing of tutors' profile
- Filtering tutors by personal preference (i.e. availability, experiences, name, location, price, etc.)
- Adding, editing, deleting note of tutors
- Exporting tutor's details, subject list and notes
- Adding, removing and listing favourite tutors
- Adding, editing, deleting and viewing of all tuition appointments
- Adding, editing, deleting and viewing of all tuition-related schedules
- Adding, editing, deleting and listing grade records
- Adding, editing, deleting and viewing of reminders
- Adding, editing, deleting and viewing of budget

The purpose of this Developer Guide is to help you understand the design and implementation of Tutor Tracker to get started on your contributions to Tutor Tracker.

## **Design**
This section will help you learn more about the design and structure of Tutor Tracker. Each section will describe and explains how each component of the application works.
It's noteworthy to mention that the Tutor Tracker follows an Object-Oriented Programming paradigm.

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

_Figure 1. Architecture Diagram of Tutor Tracker_


The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

The application consists of 6 main components:

| Component                           | Description
| ----------------------------------- | -------------------------------------------------------------------- |
| `Main`                              | **`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/MainApp.java).  It is responsible for: (1) At app launch: Initializes the components in the correct sequence, and connects them up with each other. (2) At shut down: Shuts down the components and invokes cleanup methods where necessary.|
| [**`UI`**](#ui-component)           | The UI of the App.                                                   |
| [**`Logic`**](#logic-component)     | The command executor.                                                |
| [**`Model`**](#model-component)     | Holds the data of the App in memory.                                 |
| [**`Storage`**](#storage-component) | Reads data from, and writes data to, the hard disk.                  |
| [**`Commons`**](#common-classes)    | Represents a collection of classes used by multiple other components.|

For each of `UI`, `Logic`, `Model` and `Storage` component, it
* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete_appointment 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultBarFooter`, `FilterPanel`, `TutorListPanel` etc. 
All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `TutorTrackerParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding an appointment).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete_appointment 1")` API call.

![Interactions Inside the Logic Component for the `delete appointment_1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : `Model.java`

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the Tutor Tracker data.
* exposes an unmodifiable `ObservableList<XYZ>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* In-depth details of each `XYZ` model are shown in each [Implementation](#implementation) section.

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : `Storage.java`

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the Tutor Tracker details in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## Implementation

### Tutor Book
Tutor Tracker's Tutor Book allows users to store a list of tuition tutors.

#### Rationale
As Tutor Tracker is an application to aid users in viewing a tutor's profile, having a list of tuition tutors is core of Tutor Tracker.

The proposed tutor features is to facilitate the user to keep track of his/her list of tutors.
It implements the following operations:
* `Add tutor` - Adds a tutor to the list of tutors.
* `Edit tutor` - Edit an existing tutor from the list of tutors.
* `Delete tutor` - Delete a tutor from the list of tutors.
* `Find tutor` - Find a tutor from the list of tutors by name.
* `View tutor` - View a tutor by index from the list of tutors.
* `List all tutors` - Show an unfiltered list of the tutors.

These operations are exposed in the `Logic` interface by parsing respective `AddCommand` `EditCommand`, `DeleteCommand`, `FindCommand`,
`ViewCommand` and `ListCommand`. 

#### Implementation 
The class and commands are re-used from the `AddressBook Level-3`'s `Person` class.

#### Rationale
As Tutor Tracker is an application to aid users to track their upcoming tuition appointments, allowing user to store a list of his/her tuition appointment is core of Tutor Tracker.

The proposed appointment feature is to facilitate the user to keep track of his/her tuition appointments.
It implements the following operations:
* `Add appointment` - Adds an appointment to the list of appointments.
* `Edit appointment` - Edit an existing appointment from the list of appointments.
* `Delete appointment` - Delete an appointment from the list of appointments.
* `Find appointment` - Find a tutor from the list of appointments by tutor's name.
* `View schedules` - View the list of appointments that is happening on the queried date.
* `List all appointments` - Show an unfiltered list of the appointments.


These operations are exposed in the `Logic` interface by parsing respective `AddAppointmentCommand` `EditAppointmentCommand`, `DeleteAppointmentCommand`, `FindAppointmentCommand`,
`ViewAppointmentCommand` and `ListAppointmentCommand`.
![Class Diagram of Tutor](images/TutorClassDiagram.png)

### Favourite Feature
Tutor Tracker's Favourite feature allows users to create a list of favourites tutors from the entire list of tutors.

#### Rationale
As Tutor Tracker is an application to aid users in viewing a tutor's personal profile, we have also considered that users may also wish to keep a favourite tutor list for easier personal reference instead of performing a search or filtering every time.

#### Implementation
The favourite feature is to facilitate the user to keep track of his/her favourites out of the entire list of tutors. 
It implements the following operations:
* `Favourite tutor` - Add a tutor to the list of favourite tutors.
* `Unfavourite tutor` - Delete the tutor from the list of favourite.
* `List favourites` - Show the list of the favourite tutor.

These operations are exposed in the `Logic` interface by parsing respective `FavouriteCommand`,
`UnfavouriteCommand` and `ListFavouriteCommand`.

When the user enters any of the aforementioned commands,
the user input command undergoes the same command
parsing as described in the [Logic component](#logic-component).

To add the `favourite` attribute to a Tutor, we can simply create an `EditTutorDescriptor` which is a `Favourite` descriptor, and edit the corresponding `Tutor` with this descriptor. 
The following shows an example of how an `FavouriteCommand` is executed.

Steps for the execution of the `FavouriteCommand` (assuming that no errors are encountered during parsing of inputs):
1. When the `execute()` method of the `FavouriteCommand` is called, the `Model`’s `getFilteredTutorList()` method is called.
1. The `get()` method of the `ObservableList` is called returning the tutor at the index specified by the user. 
1. The `isFavourite()` method of the `Tutor`, which was retrieved from the previous step, is called to check if the tutor specified by index is already a favourite.
1. Assuming that the above validation passes, the `FavouriteCommand`'s `createEditedTutor()` method is called to create a `Tutor` object with a `favourite` attribute while retaining existing information from the tutor specified by index.
1. The `Model`'s `setTutor()` method is called, which will then call the `TutorBook`'s `setTutor()` method.
1. The `Ui` component will detect this change and update the GUI with the `favourite` indicator (a star) beside the tutor's name.
1. Assuming that the above steps are all successful, the `FavouriteCommand` will then create a `CommandResult` object and return the result.

The following Sequence Diagram summarises the aforementioned steps.

![Sequence Diagram of Favourite Tutor](images/favourite/FavouriteSequenceDiagram.png)

#### Design Consideration

##### Displaying Favourite Tutors in the GUI

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1 (current choice)** <br> Display tutors with a graphical indicator beside tutor's name. | Allows users to view everything in a single panel. | May be difficult to differentiate non-favourite and favourite tutor. |
| **Option 2** <br> Separate non-favourite and favourite tutors into different tabs. | Clear segregation between non-favourite and favourite tutors. | May impose inconvenience as users have to switch tabs between non-favourite and favourite tutor depending on their needs. |

Reason for choosing option 1:
* We have researched the existing website and applications and concluded that option 1 is the most commonly adopted method.
* There is also the `list_favourites` command in case users may want to only display favourite tutors.

The following activity diagram summarizes what happens when the `favourite` command is executed.

![Activity Diagram of Favourite Activity](images/favourite/FavouriteActivityDiagram.png)

### Note Feature
Tutor Tracker's Notes feature allows users to create notes that are tagged to specific tutors and export them into a text file.

#### Rationale
As Tutor Tracker is an application to aid users to track their upcoming tuition appointments, we have also considered 
that users may also wish to add notes to specific tutors to keep track of miscellaneous information that the user might have. 
We also considered that the user might want to export details and notes of a tutor into a text file for ease of reference and sharing.

#### Implementation
The proposed note feature is to facilitate the user to keep track of his/her own note of different tutors and export them.
The notes feature consists of the following operations that can be performed on tutors:
* `Add note` - Adds a note to a tutor
* `Delete note` - Deletes the note of a tutor
* `Edit note` - Edits an existing note of a tutor
* `List note` - List tutor(s) that has a note
* `Export tutor` - Exports the tutor's details, subject list and attached notes into a text file 

These operations are exposed in the `Logic` interface by parsing respective `AddNoteCommand`,
`DeleteNoteCommand`, `EditNoteCommand`, `ListNoteCommand` and `ExportCommand`.

When the user enters any of the aforementioned commands,
the user input command undergoes the same command
parsing as described in the [Logic component](#logic-component).

To add the `note` attribute to a Tutor, we can simply create an `EditTutorDescriptor` which is a `Note` descriptor, and edit the corresponding `Tutor` with this descriptor.
The following shows an example of how an `AddNoteCommand` is executed.

Steps for the execution of the `AddNoteCommand` (assuming that no errors are encountered during parsing of inputs):
1. When the `execute()` method of the `AddNoteCommand` is called, the `Model`’s `getFilteredTutorList()` method is called.
1. The `get()` method of the `ObservableList` is called returning the tutor at the index specified by the user.
1. The `hasNotes()` method of the `Tutor`, which was retrieved from the previous step, is called to check if the tutor specified by index have an existing note.
1. Assuming that the above validation passes, the `AddNoteCommand`'s `createEditedTutor()` method is called to create a `Tutor` object with a `note` attribute while retaining existing information from the tutor specified by index.
1. The `Model`'s `setTutor()` method is called, which will then call the `TutorBook`'s `setTutor()` method.
1. The `Ui` component will detect this change and update the GUI with the `notes` panel attached beside the tutor's profile.
1. Assuming that the above steps are all successful, the `AddNoteCommand` will then create a `CommandResult` object and return the result.

The following Sequence Diagram summarises the aforementioned steps.

![Sequence Diagram of Add Notes](images/notes/NotesSequenceDiagram.png)

#### Design Consideration

##### Displaying Notes in the GUI

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1 (current choice)** <br> Display tutors with attached notes in the same list view. | Allows users to view everything in a single panel. | The tutor panel might look cluttered when the notes are long.|
| **Option 2** <br> Display notes with a pop up when a tutor is selected. | Prevents the GUI from being too cluttered with information. | May impose inconvenience as users would have to select a tutor in order to view their notes |

Reason for choosing option 1:
* We want the user to be able to see their notes easily and having to type in another command to select and show the notes
  in a separate pop up poses too much of an inconvenience.
* The window to display the notes has been made static, and a scrollbar is added when the notes are too long to fit into the
  static window. This can help make the GUI look less cluttered.
  
The following activity diagram summarizes what happens when the `add_note` command is executed.

![Activity Diagram of Add Note](images/notes/NotesActivityDiagram.png)

To export the details and notes of a `Tutor` into a text file, we use the `export` command. The `export` command would
create a new folder `/export` in the root directory. Details and notes of a `Tutor` would be converted into human-readable
text form and exported into the `/export` folder.

### Grade Book
Tutor tracker's Grade Book is to allow users to keep track their grade records for self reference and future study planning.

#### Rationale
As Tutor Tracker is an application to aid users to manage the tutor and tuition information, we have also considered that users
may wish to keep track and manager their academic records for self reference and future study planning. Examples are keeping track
their grades of different tests and different subjects. With Grade Book, users can now store and manage all tuition-related and academic
related information on the same application instead of using multiple applications.

#### Implementation
A grade is composed of a `subject`, `graded item` and `grade letter`, out of which `subject` and `graded item` are used to identify a grade object uniquely.
![Class Diagram of Grade](images/grade/GradeClassDiagram.png)

All the user's grades are stored internally in the `GradeList`. Grade Book consist of the following operations
that can be performed on the grade:   

* `Add a grade` - Add a grade record to user's `GradeBook`.
* `Delete a grade` - Delete an existing grade record at specified index of the `GradeBook`.
* `Edit a grade` - Edit an existing grade record (changing its attributes' value) at specified index displayed in the `GradeBook`.
* `List all grades` - Display a list of all existing grade records in the `GradeBook`

These operations are exposed in the `Logic` interface by parsing respective `AddGradeCommand`,
`DeleteGradeCommand`, `EditGradeCommand` and `ListGradeCommand`.

When the user enters the `add_grade` command to add a new command, the user input command undergoes
the same command parsing as described in [Logic component](#logic-component).

Steps for the execution of the `AddGradeCommand` (assuming that no errors are encountered):
1. When the `execute()` method of the `LogicManager` is called, the `TutorTracker`'s `parseCommand()` method is called.
2. The `TutorTrackerParser` will then create a `AddGradeCommandParser`.
3. The `AddGradeCommandParser` will then parse the inputs, and creates a `AddGradeCommand`.
4. The `AddGradeCommand` will then validate the parameters and creates a `Grade` object.
5. Assuming that the above steps are all successful, the `LogicManager` will call the `ModelManager`'s `addGrade()`, 
then create a `CommandResult` object and return the result.
6. The `Ui` component will detect this change and update the GUI.
![Sequence Diagram of Add Grade](images/grade/GradeSequenceDiagram_updated.png)

#### Design Consideration

##### Displaying Grades in the GUI

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1** <br> Display grades with budget and reminder in the same list view. | Allows users to view everything in a single panel. | Users may have difficulty to differentiate grades, budget and reminders if a considerable number of them are listed all together.|
| **Option 2 (current choice)** <br> Display grades in a separate tab from budget and reminder in a `Your Information` panel. | Clear segregation between grades, budget and reminder. | May impose inconvenience as users have to switch tabs between grades, budget and reminder depending on their needs |

Reason for choosing option 2:
* As we do not wish to overwhelm the user with too much information to provide a better user experience, we decided that option 2 may be a better option.

The following activity diagram summarizes what happens when the `add_grade` command is executed.

![Activity Diagram of Add Grade](images/grade/GradeActivityDiagram_updated.png)

### Filter Feature

The Filter feature allows users to manage filters and apply them to the list of tutors and appointments. This allows filtering of tutors by attributes such as personal information as well as the subjects they teach, as well as filter appointments by appointment attributes.

#### Rationale

In order to facilitate efficient management of tutors and appointments, there needs to be a way to search through the lists quickly and easiler. While a search function is possible, filtering is a better option as it allows users to add and remove different filters according to their needs.

#### Implementation

The Filter Feature comprises of 4 commands:
* Add a Tutor Filter
* Delete a Tutor Filter
* Add an Appointment Filter
* Delete an Appointment Filter

The activity diagram for the Add Tutor Filter command is shown:

![Activity Diagram of Add Tutor Filter command](images/filter/FilterActivityDiagram.png)

The activity flow for the Add Appointment Filter command is similar. For the Delete commands, the activity flow also follow a similar format, except that instead of checking for duplicate filters, they instead check if the filters input exist in the system.

The Tutor Filters and Appointment Filters are represented by the `TutorFilter` and `AppointmentFilter` classes respectively. Both contain multiple `FilterSet` each of which is a set of filters for each attribute in `Tutor` and `Appointment`, labeled as `XYZFilter` for convenience, where `XYZ` is a placeholder for the attribute name. (eg. a `TutorFilter` would contain a `FilterSet` of `NameFilter`). The class diagram is as shown:

![Class Diagram of Filter Feature](images/filter/FilterClassDiagram.png)

Each `XYZFilter` tests the respective attribute in Tutor or Appointment according to rules specified in the user guide. The filters are combined together in each `FilterSet`, determined by whether it is a `InclusiveFilterSet` or `ExclusiveFilterSet`, with the combined filter returning a boolean value when testing attributes. `TutorFilter` and `AppointmentFilter` use each respective `FilterSet` on all attributes when testing a `Tutor` or `Appointment`, returning a boolean value.

Testing thus follows the following steps:

1. `Tutor` is passed to `TutorFilter` to test
2. Each attribute in `Tutor` is passed to the respective `FilterSet` to test
3. Each `FilterSet` returns true or false according to its combined filters
4. `TutorFilter` combines the results from all `FilterSet`s according to the rules specified in the user guide and returns true or false

Steps are similar for `AppointmentFilter`.

When adding or deleting from each `FilterSet`, the combined filter must be recreated. This process is shown in the following sequence diagram:

![Sequence Diagram of Add Filter](images/filter/FilterSequenceDiagram.png)

The difference for exclusive filters is that an `and` is used instead of `or` to compose the filters. The sequence for deletion is similar, except that filters are removed from the set before composing the filters.

#### Design Consideration

**Filtering or Searching**

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1 (current choice)** <br> Filtering | Users can add and remove multiple filters in order to refine their display of tutors and appointments. | User may have to type multiple commands. |
| **Option 2** <br> Searching. | User can search for tutor or appointment using only one command. | User may have to type long command, and must retype it for every change. |

Option 1 was chosen as it does not require retyping a long command in the event that the user wants to refine their search.

### Appointment Book
Tutor Tracker's Appointment Book allows users to manage and keep track his/her tuition appointments.

The proposed appointment feature is to facilitate the user to keep track of his/her tuition appointments.
It implements the following operations:
* `Add appointment` - Adds an appointment to the list of appointments.
* `Edit appointment` - Edit an appointment from the list of appointments.
* `Delete appointment` - Delete an appointment from the list of appointments.
* `Find appointment` - Find a tutor from the list of appointments by tutor's name.
* `View appointments` - View the list of appointments that is happening on the queried date.
* `List all appointments` - Show an unfiltered list of the appointments.


Diagram below shows the activity diagram of the `add_appointment` command.

![Activity Diagram of Add Appointment](images/appointment/appointmentActivity.png)

Steps for the execution of the `AddAppointmentCommand` (assuming that no errors are
encountered):
1. When the `execute()` method of the `LogicManager` is called, the `TutorTrackerParser`'s `parseCommand()` method is called.
2. The `TutorTrackerParser` will then create a `AddAppointmentCommandParser`.
3. The `AddApppointmentCommandParser` will then parse the inputs, and then validate
   the parameters and create an `Appointment` object.
4. The `AddAppointmentCommandParser` will then create and `AddAppointmentCommand`
   with the created `Appointment` object.
5. Assuming that the above steps are all successful, the `LogicManager` will call
   the `ModelManager`'s `addAppointment()`, then create a `CommandResult` object and
   return the result.

![Sequence Diagram of Add Appointment](images/appointment/appointmentSequence.png)


These operations are exposed in the `Logic` interface by parsing respective `AddAppointmentCommand` `EditAppointmentCommand`, `DeleteAppointmentCommand`, `FindAppointmentCommand`,
`ViewAppointmentCommand` and `ListAppointmentCommand`.

### Event Class

#### Rationale
As Tutor Tracker is an application to aid users to track their upcoming tuition 
appointments, schedules are naturally created, and the event class helps determine
the hierarchy of schedule.


#### Implementation
The class and commands are re-used from the `AddressBook Level-3`'s `Person` class.
The following UML Class Diagram depicts the hirerachy of `Event`, `Appointment` and `Schedule`.
![Class Diagram of Event](images/EventClassDiagram.png)

### Schedule Tracker
Tutor Tracker's Schedule Tracker allows users to create schedules to track their ongoing or upcoming timed-sensitive tasks.

#### Rationale
As Tutor Tracker is an application to aid users to track their upcoming tuition appointments, we have also considered that users may also wish to keep track and manage other time-sensitive tuition-related tasks. 
Examples are allocating time to finish their tuition homework or any other tuition-related tasks.
With Schedule Tracker feature, users can now keep track and manage all tuition-related information on the same application instead of using multiple applications.

#### Implementation

A schedule is composed of a `title`, `description`, `time_from` and `time_to`, which are used to identify a schedule uniquely.

All the user's schedules are stored internally in the `scheduleList`.
Schedule Tracker consist of the following operations that can be performed on schedule:
* `Add a schedule` - Add a new schedule and store it in the user's `scheduleList`.
* `Delete a schedule` - Delete a schedule by the index displayed in the `scheduleList`.
* `Edit a schedule` - Edit a schedule (changing its attributes' value) by the index displayed in the `scheduleList`. 
* `View schedules` - View the list of schedules that is happening on the queried date.
* `List all schedules` - View all the schedules stored in the `scheduleList`.
 
These operations are exposed in the `Logic` interface by parsing respective `AddScheduleCommand`,
`DeleteScheduleCommand`, `EditScheduleCommand` and `ViewScheduleCommand`.

When the user enters any of the aforementioned commands,
the user input command undergoes the same command
parsing as described in the [Logic component](#logic-component).

Steps for the execution of the `AddScheduleCommand` (assuming that no errors are encountered during parsing of inputs):
1. When the `execute()` method of the `AddScheduleCommand` is called, it will perform two-steps of validation. 
    1. The first validation method called is the `Model`’s `hasSchedule()`. 
    1. Assuming the previous step's validation is successful, it will then call `DateTimeValidationUtil`'s `validateDateTime()` method.
1. Assuming that the above validations passes, the `Model`'s `addSchedule()` method is called, which will then call the `TutorTracker`'s `addSchedule()` method.
1. The `Ui` component will detect this change and update the GUI.
1. Assuming that the above steps are all successful, the `AddScheduleCommand` will then create a CommandResult object and return the result.

The following Sequence Diagram summarises the aforementioned steps.

![Sequence Diagram of Add Schedule](images/schedule/ScheduleSequenceDiagram.png)

#### Design Consideration

##### Determine the hierarchy of schedule

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1** <br> Schedule to be a subclass of Appointment. | More ideal in terms of naming convention. | It may cause huge code changes and refactor required due to the Appointment class have already completed. |
| **Option 2 (current choice)** <br> Create an additional parent class to be inherited by Appointment and Schedule. | Less change in codes as the Appointment class was completed (v1.2) before this idea extension proposed in v1.3. | May confuse users with the naming convention if the definitions and examples are not clearly stated. |

Reason for choosing option 2:
* We estimated the number of code changes required, and we deem it to be quite substantial. Hence, we have decided to go for option 2, by creating an abstract parent class called `Event`.

##### Naming of the datetime attribute

After choosing option 2 from the previous consideration, another issue arose due to the separation of classes.

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1** <br> Rename `AppointmentDateTime` to `EventDateTime` . | More ideal in terms of naming convention. | It may cause huge code changes and refactor required due to the Appointment class have already completed. At the same time, we have other features such as the Appointment Filter, which is heavily build using the `AppointmentDateTime` variable. |
| **Option 2 (current choice)** <br> Retain the `AppointmentDateTime` attribute. | Less change in codes as the Appointment class was completed (v1.2) before this idea extension proposed in v1.3. | May confuse developers with the naming convention if the definitions and examples are not clearly stated. |

Reason for choosing option 2:
* We estimated the number of code changes required, and we deem it to be quite substantial. Hence, we have decided to go for option 2 by retaining the name `AppointmentDateTime` under the `Event` class. We have put comments in the class to explain the rationale behind it.

##### Displaying Schedule in the GUI

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1** <br> Display schedules with appointments in the same list view. | Allows users to view everything in a single panel. | Users may have difficulty to differentiate appointments and schedule if not looked properly. |
| **Option 2 (current choice)** <br> Display schedules in a separate tab from appointment. | Clear segregation between appointments and schedules. | May impose inconvenience as users have to switch tabs between appointments and schedules depending on their needs. |

Reason for choosing option 2:
* As we do not wish to overwhelm the user with too much information to provide a better user experience, we decided that option 2 may be a better option.
* Viewing appointments and schedules in the same panel is later proposed as a different solution in the Timeable Feature.

The following activity diagram summarizes what happens when the `add_schedule` command is executed.

![Activity Diagram of Add Schedule](images/schedule/ScheduleActivityDiagram.png)

### Reminder Tracker
Tutor Tracker's Reminder Tracker allows users to create reminders to help them track their ongoing or upcoming todos or non-time constraint tasks.

#### Rationale
As Tutor Tracker is an application to aid users to track their upcoming tuition appointments, we have also considered that users may also wish to keep track and manage tuition-related reminders.
Reminders are date but not time-sensitive, users can add as many reminders as they wish on a certain day.

#### Implementation
A reminder is composed of `description` and `date`, which are used to identify a reminder uniquely.

![Class Diagram of Reminder](images/reminder/ReminderClassDiagram.png)

All the user's reminders are stored internally in the `reminderList`.
Reminder Tracker consist of the following operations that can be performed on reminders:
* `Add a reminder` - Add a new reminder and store it in the user's `reminderList`.
* `Delete a reminder` - Delete a reminder by the index displayed in the `reminderList`.
* `Edit a reminder` - Edit a reminder (changing its attributes' value) by the index displayed in the `reminderList`.
* `List all reminders` - View all the reminders stored in the `reminderList`.

These operations are exposed in the `Logic` interface by parsing respective `AddReminderCommand`,
`DeleteReminderCommand` and `EditReminderCommand`.

When the user enters any of the aforementioned commands,
the user input command undergoes the same command
parsing as described in the [Logic component](#logic-component).

Steps for the execution of the `AddReminderCommand` (assuming that no errors are encountered during parsing of inputs):
1. When the `execute()` method of the `AddReminderCommand` is called, it will perform two-steps of validation.
    1. The first validation method called is the `AddReminderCommand`’s `Reminder`'s object `isBeforeToday()`.
    1. Assuming the previous step's validation is successful, it will then call `Model`'s `hasReminder()` method.
1. Assuming that the above validations passes, the `Model`'s `addReminder()` method is called, which will then call the `ReminderTracker`'s `addReminder()` method.
1. The `Ui` component will detect this change and update the GUI.
1. Assuming that the above steps are all successful, the `AddReminderCommand` will then create a `CommandResult` object and return the result.

The following Sequence Diagram summarises the aforementioned steps.

![Sequence Diagram of Add Reminder](images/reminder/ReminderSequenceDiagram.png)

**Displaying Reminders in the GUI**

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1** <br> A pop-up notification for reminder of the day. | User are notified of reminders without having specifically request for it. | Multiple pop-up notifications from the reminders could cause annoyance for the user. |
| **Option 2 (current choice)** <br> Display reminders at the side of the main window. | User could see clearly the duration left for each task at a glance. | Reminders can be missed out if users are not aware of it. |

Reason for choosing option 2:
* Pop-up notifications can be intrusive and may cause latency when using the application.

The following activity diagram summarizes what happens when the `add_reminder` command is executed.

![Activity Diagram of Add Reminder](images/reminder/ReminderActivityDiagram.png)

### Timetable Window
Tutor Tracker's Timetable GUI allows users to view their appointments and schedules of a particular week in a graphical representation.

#### Rationale
As Tutor Tracker is an application to aid users in tracking their upcoming tuition appointments and schedules, our target users are secondary students.
Similar to their school's timetable, we wish to present their personal tuition appointments and schedules as a timetable.

#### Implementation
The current implementation of the timetable view retrieve the list of appointments and schedules from the `ModelManager`. 
The timetable view will display appointments and schedules timeslots from Monday to Sunday using JavaFx's GridPane. 
Each row would consist of all appointments & schedules of a specific day.
The duration of an appointment or schedule would correspond to the number of columns. 
The date of an appointment will be indicated using the first column of the grid.

When the user enters the `timetable` command to open the timetable window, the user input command undergoes the same command parsing as described in
[Logic component](#logic-component).

**Displaying TimeTable in the GUI**

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1** <br> A embedded timetable at the side of main window. | Users can view their timetable without having specifically request for it. | UI may be too cluttered due to amount of information on screen. |
| **Option 2 (current choice)** <br> Displaying timetable as a separate window. | Visually cleaner and clearer as it is isolated from the other Tutor Tracker's Main Window. | Lower user experience as commands are required to open the timetable window. |

Reason for choosing option 2:
* To avoid overwhelming users with a huge amount of information, we strongly believe that opening a separate window is ideal.

The following activity diagram summarizes what happens when the `timetable` command is executed.

![Activity Diagram of View TimeTable](images/timetable/TimetableWindowActivityDiagram.png)

### Budget Feature
#### Implementation
The proposed budget feature is to facilitate the user to keep track of the total
cost of all of his own appointments and whether it is within the budget he has set for
himself. The budget is an optional feature, and can easily be added through
CLI.
It supports the following operations:
#### `command` - example usage
* `add_budget budget_size` - add_budget b/500
* `edit_budget budget_size` - edit_budget b/600
* `delete_budget` - delete_budget
* `view_budget` - view_budget

The following activity diagram summarizes what happens when the `add_budget` command is 
executed.

![Activity Diagram of Add Budget](images/budget/budgetActivity.png)

Steps for the execution of the `AddBudgetCommand` (assuming that no errors are 
encountered):
1. When the `execute()` method of the `LogicManager` is called, the `TutorTrackerParser`'s `parseCommand()` method is called.
2. The `TutorTrackerParser` will then create a `AddBudgetCommandParser`.
3. The `AddBudgetCommandParser` will then parse the inputs, validate the parameter, and 
   create a new `Budget` object with the given budget value.
4. The `AddBudgetCommandParser` will then create a 
   new `AddBudgetCommand` with the `Budget` object created.
5. Assuming that the above steps are all successful, the `LogicManager` will call 
   the `ModelManager`'s `addBudget()`, then create a `CommandResult` object and return 
   the result.


![Sequence Diagram for Add Budget](images/budget/budgetSequence.png)


Given below is example usage scenarios and how the reminder features behave.

_{More to be added}_

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

Tech-savvy secondary school students in Singapore who to need to search for tutors, manage their tuition appointments
and academic records, and prefer CLI over GUI.

**Value proposition**:

The demand for tuition in Singapore is escalating, especially among secondary school students. A large amount of time and money has been invested in finding tutors and managing ever growing lists of tuition appointments. Currently, there are limited number of apps and websites that cater to this need, particularly in a streamlined typing oriented CLI. Therefore, this app aims to assist secondary school students in streamlining the process of searching for tutors and managing their tuition appointments. These students can search for an ideal tutor based on their personal preferences (such as subjects, expertise, years of experience, cost, availability etc.), and cut down on the time taken tracking their favoured tutors and tuition appointments.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`


| Priority| As a …​                                 | I want to …​                 | So that I can…​                                                    |
| --------| ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *` | User | Add new tutor details                              | Keep track of a new tutor that I have heard about               |
| `* * *` | User | Delete tutor details                               | Remove tutor that I no longer need the details of              |
| `* * *` | User | List all tutor(s)                                  | See all known tutor(s)                                            |
| `* * *` | User | View details of a tutor (subject, background, age) | Determine whether I should choose this tutor                    |
| `* * *` | User | Add tuition appointment                            | Keep track of appointments I have made                          |
| `* * *` | User | View my tuition appointment(s)                       | Keep track of appointment(s)                                      |
| `* * *` | User | Delete a tuition appointment                       | Remove canceled appointment                                    |
| `* * *` | User | Check my own tuition appointments list             | Know the timing of ALL my appointments in order                 |
| `* *` | User | Filter tutors by their name | Find a tutor by name |
| `* *` | User | Filter tutors by their gender | Find a tutor of my preferred gender |
| `* *` | User | Filter tutors by their contact details | Find a tutor by known contact details |
| `* *` | User | Filter tutors by their location | Find tutors nearby |
| `* *` | User | Filter tutors by the subjects they teach | Find a tutor that caters to my academic needs |
| `* *` | User | Filter tutor by cost | Find a tutor that fits my budget |
| `* *` | User | Filter tutors by their years of experience | Find a tutor with experience within the range of my expectation |
| `* *` | User | Filter tutors by their qualifications | Find a tutor with qualifications within the range of my expectation |
| `* *` | User | Filter my tuition appointments by tutor's name | Keep track of all the tuition appointments under a particular tutor |
| `* *` | User | Filter my tuition appointments by subjects | Keep track of all the tuition appointments of a particular subject |
| `* *` | User | Filter my tuition appointments by date and time | Keep track of all the tuition appointments in a time period |
| `* *` | User | Filter my tuition appointments by location | Keep track of all the tuition appointments in a given location |
| `* *`   | Careless user  | Edit tutor details                       | Fix typos or add in details that I forgot to enter of the tutor |
| `* * `  | Tech-savvy user| Export tutor's details into a text file  | Share the tutor's details with others                           |
| `* *`   | User | Keep track of the details of my favourite tutors   | Contact them and set up an appointment with them                |
| `* *`   | User | Unfavourite a tutor                                | Remove the tutor from my list of favourites                     |
| `* *`   | User | List all the tutor(s) that were added to favourites  | Access the details of the favourite tutor(s) fast               |
| `* *`   | Meticulous user| Add note to tutor                        | Remind myself of some additional details of the tutor           |
| `* *`   | User | Delete note from tutor                             | Remove note from tutor that are no longer relevant              |
| `* *`   | User | List tutors with note                              | Keep track which tutor has note added                           |
| `* *`   | Tech-savvy User | Add grade records to a digital GradeBook | Keep track of grades I obtained for reference and future study plan          |
| `* *`   | Careless user   | Edit grade record details               | Correct typos or update the details of my academic records            |
| `* *`   | User  | Delete outdated grade records                     | Remove past grades if they are no longer needed                    |
| `* *`   | User  | List all grades                                   | See all existing academic records in my GradeBook      
| `* *`   | User  | Add a budget                                      | Add a personal budget to keep track of
| `*`     | User  | Delete a budget                                   | Delete an existing budget that I have and no longer need
| `* *`   | User  | Edit a budget                                     | Change an existing budget that I have   
| `* *`   | User  | View a budget                                     | View an existing budget and cost of total appointments of user
| `* *`   | User | Add a schedule                                         | Keep track of tuition-related schedules                          |
| `* *`   | User | View my schedule(s) on a particular date               | Know what schedule(s) do I have on a particular day                                      |
| `* *`   | User | Delete a schedule                                    | Remove canceled schedules                                    |
| `* *`   | User | Check my own schedule list                           | Know the timing of all my schedule(s) in order                 |
| `* *`   | Careless user  | Edit schedule details                       | Fix typos or add in details that I forgot to enter of the schedule |
| `* *`   | Meticulous user  | View my timetable that consist of both appointment(s) and schedule(s)                       | Keep track of appointment(s) and schedule(s) happening on a particular week |
| `* *`   | User | Add a reminder                                         | Keep track and manage personal reminder(s)                          |
| `* *`   | User | Delete a reminder                                    | Remove unnecessary reminder                                    |
| `* *`   | User | Check my own reminder list                           | Know the dates of all my reminders in order                 |
| `* *`   | Careless user  | Edit reminder details                       | Fix typos or add in details that I forgot to enter of the reminder |

### Use cases

_For all use cases below, the **System** is the `TutorTracker` and the **Actor** is the `user`, unless specified otherwise._

<hr>

**Use Case UC0001: Add new tutor details**

**MSS**

1. User inputs tutor details.
2. TutorTracker confirms that tutor details have been added to list.
   
   Use case ends.

**Extensions**
* 1a. Details are not keyed in the correct format as specified in user guide.
    * 1a1. TutorTracker shows an error message

      Use case resumes at step 1.
    
* 2a. Tutor details already exists in list.
    * 2a1. TutorTracker shows an error message 

      Use case ends.

<hr/>

**Use Case UC0002: List tutor(s)**

**MSS**
1. User requests to list tutor(s).
2. TutorTracker shows a list of tutor(s).

    Use case ends.

**Extension**
* 1a. The list is empty. 
    
  Use case ends.
  
<hr/>

**Use Case UC0003: Edit tutor**

**MSS**
1. User __requests to list tutors (UC0002)__.
2. User inputs new tutor details of specific tutor they want to edit.
3. TutorTracker confirms that tutor details have been edited.

   Use case ends.

**Extensions**
* 2a. Details are not keyed in the correct format as specified in user guide.
    * 2a1. TutorTracker shows an error message

      Use case resumes at step 2.

* 2b. The tutor specified is invalid.
    * 2b1. TutorTracker shows an error message.

      Use case resumes at step 2.

<hr/>

**Use Case UC0004: Delete a tutor**

**MSS**

1.  User __requests to list tutors (UC0002)__.
2.  TutorTracker shows a list of tutors.
3.  User requests to delete a tutor in the list by index.
4.  TutorTracker deletes the tutor.

    Use case ends.

**Extensions**

* 1a. The list is empty.   
  Use case ends.

* 3a. The index is invalid.
    * 3a1. TutorTracker shows an error message.
      
      Use case resumes at step 2.

<hr/>

**Use Case UC0005: Favourite a tutor**

**MSS**

1.  User __requests to list tutors (UC0002)__.
2.  TutorTracker shows a list of tutors.
3.  User requests to favourite a tutor in the list by index.
4.  Tutor added as favourite.
    
    Use case ends.

**Extensions**

* 1a. The list is empty.
  
   Use case ends.

* 3a. The index is invalid.
    * 3a1. TutorTracker shows an error message.
      
      Use case resumes at step 2.

* 3b. The tutor is already a favourite.
   * 3b1. TutorTracker shows an error message.

     Use case ends.   

<hr/>

**Use Case UC0006: List favourite(s)**

**MSS**

1.  User requests to list tutor(s) who were added as favourite.
2.  TutorTracker shows a list of favourite tutor(s).

    Use case ends.

**Extension**

* 1a. The list is empty.
      
     Use case ends.
<hr/>

**Use Case UC0007: Unfavourite a tutor**

**MSS**

1.  User __requests to list tutors (UC0002)__.
2.  TutorTracker shows a list of tutors.
3.  User requests to unfavourite a tutor in the list by index.
4.  Tutor removed from favourite.

    Use case ends.

**Extensions**

* 1a. The list is empty.  
      Use case ends.

* 3a. The index is invalid.
    * 3a1. TutorTracker shows an error message.
      
      Use case resumes at step 2.

* 3b. The tutor is not a favourite so cannot be unfavourited.
    * 3b1. TutorTracker shows an error message.

      Use case ends.

<hr/>

**Use Case UC0008: Export tutor's details**

**MSS**

1.  User __requests to list tutors (UC0002)__.
2.  TutorTracker shows a list of tutors.
3.  User requests to export a tutor's details,subject list and notes in the list, by index of that tutor.
4.  Text file containing tutor's details,subject list and notes created.
    
    Use case ends.

**Extensions**

* 1a. The list is empty.   
Use case ends.

* 3a. The index is invalid.
    * 3a1. TutorTracker shows an error message.   
      Use case resumes at step 2.

<hr/>

**Use Case UC0009: Add new tuition appointment**

**MSS**

1.  User requests to add an appointment.
2.  TutorTracker adds the appointment and displays the new appointment.

    Use case ends.

**Extensions**
* 1a. The tutor name, date of appointment or start and end time is empty.
    * 1a1. TutorTracker shows an error message.

      Use case ends.

* 1b. The given date or start and end time is invalid.
    * 1b1. TutorTracker shows an error message.

        Use case ends.

<hr/>

**Use Case UC0010: List all tuition appointments**

**MSS**

1. User requests to view the list of tuition appointments.
2. TutorTracker displays the list of tuition appointments to the user.

    Use case ends.

**Extension**

* 1a. The list is empty.   
  Use case ends.

<hr/>

**Use Case UC0011: View tuition appointments**

**MSS**

1.  User requests to view the list of tuition appointments.
2.  TutorTracker displays the list of tuition appointments to the user.
3.  User requests to view an appointment by date.
2.  TutorTracker displays the appointment.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. TutorTracker shows a message that there are no appointments.

    Use case ends.

* 3a. The date is invalid.
    * 3a1. TutorTracker shows an error message.
      
    Use case resumes at step 2.

<hr/>

**Use Case UC0012: Find tuition appointment**

**MSS**

1.  User requests to view the list of tuition appointments.
2.  TutorTracker displays the list of tuition appointments to the user.
3.  User requests to find appointments by tutor's name.
2.  TutorTracker displays the appointment that match the search value.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. TutorTracker shows a message that there are no appointments.

  Use case ends.

* 3a. No appointment matches the search value.
    * 3a1. TutorTracker displays an empty list.
      
    Use case ends.

<hr/>

**Use Case UC0013: Delete a tuition appointment**

**MSS**

1.  User requests to list appointments.
2.  TutorTracker shows a list of appointments.
3.  User requests to delete a specific appointment in the list.
4.  TutorTracker deletes that specific appointment.   
    Use case ends.

<hr/>

**Use Case UC0014: Add a new grade**

**MSS**

1. User inputs grade details.
2. TutorTracker adds the grade and displays the new grade.    
   Use case ends.
   
**Extensions**
* 1a. Grade details are invalid or empty.
    * 1a1. TutorTracker shows an error message.   
    
    Use case ends.   
 

* 2a. Grade details already exist in list.
    * 2a1. TutorTracker shows an error message.
      
    Use case ends.

<hr/>

**Use Case UC0015: List grade(s)**

**MSS**
1. User requests to list grade(s).
2. TutorTracker shows a list of grade(s).    
   Use case ends.

<hr/>

**Use Case UC0016: Delete a grade**

**MSS**

1.  User requests to list grades.
2.  TutorTracker shows a list of grades.
3.  User requests to delete a grade at specified index.
4.  TutorTracker deletes that specific grade.    
    Use case ends.

**Extension**
* 3a. Index is invalid.
    * 3a1. TutorTracker shows an error message.

  Use case ends.

<hr/>

**Use Case UC0017: Edit a grade**

**MSS**

1.  User requests to list grades.
2.  TutorTracker shows a list of grades.
3.  User requests to edit a grade at specified index.
4.  TutorTracker edits that specific grade.   
Use case ends.

**Extensions**
* 3a. Index is invalid.
    * 3a1. TutorTracker shows an error message.
      
  Use case ends.


* 3b. Grade details are invalid or empty.
    * 3b1. TutorTracker shows an error message.

  Use case ends.


* 3c. Grade details already exist in list.
    * 3c1. TutorTracker shows an error message

  Use case ends.

<hr/>

**Use Case UC0018: Add a budget**

**MSS**

1. User wants to add a personal budget to keep track of.
2. User keys in command to add budget of an amount he wants.   
   Use case ends.

**Extensions**
* 2a. A budget is already present.
   * 2a1. TutorTracker flags out an error.

     Use case ends.

* 2b. Invalid budget amount is added, for example a negative amount.
   * 2b1. TutorTracker flags out the error.

     Use case ends.

<hr/>

**Use Case UC0019: Edit a budget**

**MSS**

1. User wants to change the existing budget.
2. User requests to change the budget to an amount he wants.

   Use case ends.

**Extensions**
* 2a. A budget is already present.
   * 2a1. TutorTracker flags out an error.

     Use case ends.

<hr/>

**Use Case UC0020: Delete a budget**

**MSS**

1. User does not want to do with a budget.
2. User requests to delete budget.

   Use case ends.

**Extensions**
* 2a. There is no existing budget.
   * 2a1. TutorTracker flags out an error.

     Use case ends.

<hr/>

**Use Case UC0021: View a budget**

**MSS**

1. User does not want view an existing budget.
2. User requests to view budget.

   Use case ends.

**Extension**
* 2a. There is no existing budget.
   * 2a1. TutorTracker flags out an error.

     Use case ends.
<hr/>

**Use Case UC0022: Add new schedule**

**MSS**

1.  User requests to add a schedule.
2.  TutorTracker adds the schedule and displays the new schedule.

    Use case ends.

**Extensions**
* 1a. The title, description, date of schedule or start and end time is empty.
    * 1a1. TutorTracker shows an error message.

      Use case ends.

* 1b. The given date or start and end time is invalid.
    * 1b1. TutorTracker shows an error message.

      Use case ends.

<hr/>

**Use Case UC0023: List all schedules**

**MSS**

1. User requests to view the list of schedules.
2. TutorTracker displays the list of schedules to the user.

   Use case ends.

<hr/>

**Use Case UC0024: View schedule**

**MSS**

1.  User requests to view the list of schedules.
2.  TutorTracker displays the list of schedules to the user.
3.  User requests to view schedules on a particular date.
4.  TutorTracker displays the schedules happening on a particular date the user have requested for.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. TutorTracker shows a message that there are no schedules.

  Use case ends.

* 3a. The date is invalid.
    * 3a1. TutorTracker shows an error message.

  Use case resumes at step 2.

<hr/>

**Use Case UC0025: Delete a schedule**

**MSS**

1.  User requests to view the list of schedules.
2.  TutorTracker displays the list of schedules to the user.
3.  User requests to delete a specific schedule in the list by indicating the index shown.
4.  TutorTracker deletes that specific schedule.

**Extensions**

* 3a. The index is invalid.
    * 3a1. TutorTracker shows an error message.
      Use case resumes at step 2.

<hr/>

**Use Case UC0026: Add a new reminder**

**MSS**

1.  User requests to add a reminder.
2.  TutorTracker adds the reminder and displays the new reminder.

    Use case ends.

**Extensions**
* 1a. The description or date of reminder is empty.
    * 1a1. TutorTracker shows an error message.

      Use case ends.

* 1b. The given date is invalid.
    * 1b1. TutorTracker shows an error message.

      Use case ends.

<hr/>

**Use Case UC0027: List all reminders**

**MSS**

1. User requests to view the list of reminders.
2. TutorTracker displays the list of reminders to the user.

   Use case ends.

<hr/>

**Use Case UC0028: Delete a reminder**

**MSS**

1. User requests to view the list of reminders.
2. TutorTracker displays the list of reminders to the user.
3. User requests to delete a specific reminder in the list by indicating the index shown.
4. TutorTracker deletes that specific reminder.

**Extensions**

* 3a. The index is invalid.
    * 3a1. TutorTracker shows an error message.
      Use case resumes at step 2.


<hr/>

**Use Case UC0029: Open timetable window**

**MSS**

1. User requests to view timetable of a particular week by entering a date.
2. TutorTracker open and displays the timetable window to user.

**Extensions**

* 1a. The date is empty.
    * 1a1. TutorTracker set the default date is today.

    Use case resumes at step 2.

* 1b. The given date is invalid.
    * 1b1. TutorTracker shows an error message.
      
    Use case ends.

<hr/>

**Use Case UC0030: Add Tutor Filter**

**MSS**
1. User inputs details of new tutor filter(s).
2. TutorTracker confirms that tutor filters have been added to list of tutor filters.
3. TutorTracker displays tutors according to the updated list of tutor filters.

   Use case ends.

**Extensions**
* 1a. Details are not keyed in the correct format as specified in user guide.
    * 1a1. TutorTracker shows an error message

      Use case resumes at step 1.

* 1b. Duplicate tutor filter exists in TutorTracker.
    * 1b1. TutorTracker shows an error message.

      Use case resumes at step 1.

<hr/>

**Use Case UC0031: Delete Tutor Filter**

**MSS**
1. User inputs details of tutor filter(s) to delete.
2. TutorTracker confirms that tutor filters have been deleted from list of tutor filters.
3. TutorTracker displays tutors according to the updated list of tutor filters.

   Use case ends.

**Extensions**
* 1a. Details are not keyed in the correct format as specified in user guide.
    * 1a1. TutorTracker shows an error message

      Use case resumes at step 1.

* 1b. Tutor filter(s) do not exist in TutorTracker.
    * 1b1. TutorTracker shows an error message.

      Use case resumes at step 1.

<hr/>

**Use Case UC0032: Add Appointment Filter**

**MSS**
1. User inputs details of new appointment filter(s).
2. TutorTracker confirms that appointment filters have been added to list of appointment filters.
3. TutorTracker displays appointments according to the updated list of appointment filters.

   Use case ends.

**Extensions**
* 1a. Details are not keyed in the correct format as specified in user guide.
    * 1a1. TutorTracker shows an error message

      Use case resumes at step 1.

* 1b. Duplicate appointment filter exists in TutorTracker.
    * 1b1. TutorTracker shows an error message.

      Use case resumes at step 1.

<hr/>

**Use Case UC0033: Delete Appointment Filter**

**MSS**
1. User inputs details of appointment filter(s) to delete.
2. TutorTracker confirms that appointment filters have been deleted from list of appointment filters.
3. TutorTracker displays appointments according to the updated list of appointment filters.

   Use case ends.

**Extensions**
* 1a. Details are not keyed in the correct format as specified in user guide.
    * 1a1. TutorTracker shows an error message

      Use case resumes at step 1.

* 1b. Appointment filter(s) do not exist in TutorTracker.
    * 1b1. TutorTracker shows an error message.

      Use case resumes at step 1.

<hr/>


### Non-Functional Requirements
**Technical Requirements**:
* Application should be able to launch in any operating
  systems (Linux, Max, Windows) with JDK 11 installed on computer.
* Should be able to
  run on both 32-bit and 64-bit systems.

**Performance Requirements**
* Response to user command (add, delete, update, retrieve)
  should be visible within 2 seconds.
* Should be able to hold at least 10000 persons
  and appointments without any noticeable decrease in loading time.

**Quality Requirements**
* Interface can be used by a user with no programming
  experience, i.e., user should not be expected to key in complicated commands or
  logical statements to get a desired output.

**Process Requirements**
* Project to be updated with one new feature/improvement from
  each member each week.
* Updates will be pushed to each teammates' individual branches,
  where PRS are made to the master branch.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Event**: Tuition-related event that is happening on a particular day that has a start time and end time.
* **Appointment**: An event that refers to a tuition session with a particular tutor. Information stored including, the tutor's name, date of appointment, start and end time and location.
* **Schedule**: An event that is closely related to tuition, such as allocating time to do tuition homework or assessments.
* **Education Level**: The level of education offered by a tutor for a specific subject, e.g, "O level".
* **Years of Experience**: Years of experience of tutoring a specific subject.
* **Qualifications**: Official certificates of successful completion of an education programme, e.g, Bachelor of Science.
* **Index**: Index number shown in the displayed list. The index must be a positive integer 1, 2, 3, …​
* **Unfavourite**: Reverse the action of adding as a favourite
* **Singapore-GCE O'Level grading system**: The alphanumeric grade A (1,2), B (3,4), C (5,6), D7, E8, and F9. (Reference: [Singapore-GCE O'Level grading system](https://en.wikipedia.org/wiki/Singapore-Cambridge_GCE_Ordinary_Level#Grades).)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample tutors. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a Tutor

1. Adding a tutor
    1. Prerequisites: Arguments are valid and compulsory parameters are provided.
    1. Test Case: `add_tutor n/John Doe g/Male p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/English r/50 l/Secondary 3 y/5 q/A-Level s/Mathematics r/60 l/Secondary 4 y/6 q/A-Level` <br>
       Expected: Adds a tutor by the name `John Doe`, who can teach `English` at `Secondary 3` level. <br><br>
    1. Test Case: `add_tutor n/John Doe g/Male p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/English r/50 l/Secondary 3 y/5 q/A-Level s/Mathematics r/60 l/Secondary 4 y/6 q/A-Level notes/Patient` <br>
      Expected: Adds a tutor by the name `John Doe`, who can teach `English` at `Secondary 3` level. A note: `Patient` is attached to the tutor as well. <br><br>
    1. Test Case: `add_tutor n/John Doe g/Male p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/English r/50 l/Secondary 3 y/5 q/A-Level s/Mathematics r/60 l/Secondary 4 y/6 q/A-Level notes/Patient` <br>
       Expected: The tutor is not added. An error message saying that the tutor already exists (assuming you did the first
       test case) is shown <br><br>
    1. Test Case: `add_tutor n/John!!!!! g/Male p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/English r/50 l/Secondary 3 y/5 q/A-Level s/Mathematics r/60 l/Secondary 4 y/6 q/A-Level` <br>
       Expected: The tutor is not added. An error message saying that the name should be in alphanumeric is shown <br><br>
    1. Test Case: `add_tutor n/John Doe g/Dinosaur p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/English r/50 l/Secondary 3 y/5 q/A-Level s/Mathematics r/60 l/Secondary 4 y/6 q/A-Level` <br>
       Expected: The tutor is not added. An error message saying that the gender is invalid is shown <br><br>
    1. Test Case: `add_tutor n/John Doe g/Male p/995 e/johnd@example.com a/John street, block 123, #01-01 s/English r/50 l/Secondary 3 y/5 q/A-Level s/Mathematics r/60 l/Secondary 4 y/6 q/A-Level` <br>
      Expected: The tutor is not added. An error message saying that the phone number length is invalid is shown <br><br>
    1. Test Case: `add_tutor n/John Doe g/Male p/98765432 e/johexample.com a/John street, block 123, #01-01 s/English r/50 l/Secondary 3 y/5 q/A-Level s/Mathematics r/60 l/Secondary 4 y/6 q/A-Level` <br>
       Expected: The tutor is not added. An error message saying that the email format is invalid is shown <br><br>
    1 . Test Case: `add_tutor n/John Doe g/Male p/98765432 e/johnd@example.com a/ s/English r/50 l/Secondary 3 y/5 q/A-Level s/Mathematics r/60 l/Secondary 4 y/6 q/A-Level` <br>
       Expected: The tutor is not added. An error message saying that the address should not be blank is shown <br><br>
        
### Deleting a tutor

1. Deleting a tutor while all tutor(s) are being shown

    1. Prerequisites: 
       1. List all tutor(s) using the `list_tutors` command. Multiple tutors in the list.
       2. The tutor to be deleted must exist.
       3. Index must be a positive integer.

    1. Test case: `delete_tutor 1`<br>
       Expected: First tutor is deleted from the list. Details of the deleted tutor shown in the status message.

    1. Test case: `delete_tutor 0`<br>
       Expected: No tutor is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete_tutor`, `delete_tutor x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.
       
### Viewing a tutor

1. Viewing a tutor 

   1. Prerequisites: 
      1. List all tutor(s) using the `list_tutors` command. Multiple tutors in the list.
      2. Arguments are valid and compulsory parameters are provided.
      3. The index provided must be a positive integer must be smaller or equal to the largest index seen on the current window.
    
   2. Test Case: `view_tutor 1` <br>
      Expected: The Tutor List Panel updates and displays the tutor at index 1.
      
   3. Test Case: `view_tutor -1` <br>
      Expected: The Tutor List Panel does not update. An error message of invalid command format is shown.
      
### Listing all tutors

1. List all tutors
    1. Test Case: `list_tutors` <br>
       Expected: The Tutor List Panel displays the all the existing tutors. <br><br>
    2. Test Case: `list_tutors abcdefg` <br>
       Expected: The Tutor List Panel displays the all the existing tutors. <br><br>
       
### Adding a Note

1. Adding a Note
    1. Prerequisites:
        1. List all tutor(s) using the `list` command. Multiple tutors in the list
        1. Arguments are valid and compulsory parameters are provided.
    1. Test Case: `add_note 1 patient tutor` <br>
       Expected: Adds a note `patient tutor` to the first tutor from the list. <br><br>
    1. Test Case: `add_note 0 patient tutor` <br>
      Expected: No note is added. Error details shown in the status message. <br><br>
    1. Test Case: `add_note 1 patient tutor` <br>
      Expected: No note is added. An error message saying that the tutor already has a note (assuming you did the first
      test case) is shown <br><br>

### Editing a Note

1. Editing a Note
    1. Prerequisites: Arguments are valid and compulsory parameters are provided.
    1. Test Case: `edit_note 1 good tutor` <br>
       Expected: The note of the first tutor from the list changed to `good tutor`. <br><br>
    1. Test Case: `edit_note 0 patient tutor` <br>
       Expected: No note is updated. Error details shown in the status message. <br><br>

### Listing All Tutor(s) with Note

1. List all tutor(s) with note
    1. Test Case: `list_note` <br>
       Expected: The Tutor List Panel displays the all the tutor with notes. <br><br>
    2. Test Case: `list_note abcdefg` <br>
       Expected: The Tutor List Panel displays the all the tutor with notes. <br><br>

### Deleting a Note

1. Deleting a note while all note(s) are being shown

    1. Prerequisites: 
       1. List all tutor(s) using the `list_tutors` command. Multiple tutors in the list.
       2. The note to be deleted must exist.
       3. Index must be a positive integer.

    1. Test case: `delete_note 1`<br>
       Expected: Note of the first tutor is deleted. Details of the deleted note shown in the status message.

    1. Test case: `delete_note 0`<br>
       Expected: No note is deleted. Error details shown in the status message.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Export Tutor Details

1. Export Tutor Details
    1. Prerequisites: 
       1. List all tutor(s) using the `list_tutors` command. Multiple tutors in the list.
       2. The tutor to be exported must exist.
       3. Index must be a positive integer.
     1. Test Case: `export 1` <br>
       Expected: The details of the first tutor is exported. A `txt` file named after the tutor is saved at the `export` folder of the `jar` file location. <br><br>
    1. Test Case: `export 0` <br>
       Expected: No details is exported. Error details shown in the status message. <br><br>

### Favourite Tutor

1. Favourite Tutor
    1. Prerequisites: 
       1. List all tutor(s) using the `list_tutors` command. Multiple tutors in the list.
       2. The tutor to be favourite must exist.
       3. Index must be a positive integer.
     1. Test Case: `favourite 1` <br>
       Expected: The first tutor from the list is favourite. A favourite indicator (star) shown beside the tutor's name. <br><br>
    1. Test Case: `export 0` <br>
       Expected: No tutor is favourite. Error details shown in the status message. <br><br>

### Unfavourite Tutor

1. Unfavourite Tutor
    1. Prerequisites: 
       1. List all tutor(s) using the `list_tutors` command. Multiple tutors in the list.
       2. The tutor to be unfavourite must exist.
       3. Index must be a positive integer.
     1. Test Case: `favourite 1` <br>
       Expected: The first tutor from the list is unfavourite. A favourite indicator (star) is removed from beside the tutor's name. <br><br>
    1. Test Case: `export 0` <br>
       Expected: No tutor is unfavourite. Error details shown in the status message. <br><br>

### Listing All Favourite Tutor(s)

1. List all favourite tutor(s)
    1. Test Case: `list_favourites` <br>
       Expected: The Tutor List Panel displays the all the favourite tutors. <br><br>
    2. Test Case: `list_favourites abcdefg` <br>
       Expected: The Tutor List Panel displays the all the favourite tutors. <br><br>

### Viewing Appointments

1. Viewing appointments
    1. Prerequisites:
        1. Arguments are valid and compulsory parameters are provided.
        2. The date must be in the form `yyyy-mm-dd`.<br><br>
    2. Test Case: `view_appointment 2021-4-20` <br>
       Expected: The Appointment List Panel displays the appointments happening on Apr 20 2021. <br><br>
    3. Test Case: `view_appointment 20/4/2021` <br>
       Expected: The Appointment List Panel not updated. An error message saying that the date is in the wrong format is shown. <br><br>

### Listing All Appointments

1. List all appointments
    1. Test Case: `list_appointments` <br>
       Expected: The Appointment List Panel displays the all the appointments in ascending (the earliest appointment date to the latest appointment date) order. <br><br>
    2. Test Case: `list_appointments abcdefg` <br>
       Expected: The Appointment List Panel displays the all the appointments in ascending (the earliest appointment date to the latest appointment date) order. <br><br>

### Adding a Schedule

1. Adding a schedule
    1. Prerequisites:
        1. Arguments are valid and compulsory parameters are provided.
        1. The date must be in the form `yyyy-mm-dd`. 
        1. The time must be in the form ` hh:mm a`.
        1. The new schedule date and time must be a future datetime.  
        1. `TIME_FROM` and `TIME_TO` must be a valid time range (`TIME_FROM` must be before `TIME_TO`).
        1. The earliest possible `TIME_FROM` is **06:00 AM** and latest possible `TIME_TO` is **11:00 PM**.
        1. The shortest possible schedule is **1 hour**, and the longest possible schedule is **8 hours**
        1. The schedule's timeslot must be in blocks of **30 minutes** or **1 hour**.
        1. The schedule's timeslot must not clash with existing appointments & schedules. <br><br>
    2. Test Case: `add_schedule t/Maths Tuition Homework d/2021-6-2 fr/5:00pm to/7:00pm ds/Calculus Topic` <br>
       Expected: Adds a schedule by the title `Maths Tuition Homework`, happening from `Jun 02 2021 05:00 PM to Jun 02 2021 07:00 PM` <br><br>
    3. Test Case: `add_schedule t/Science Tuition Homework d/2021-6-31 fr/6:00pm to/7:00pm ds/Chapter 5 to 6` <br>
       Expected: Adds a schedule by the title `Science Tuition Homework`, happening from `Jun 30 2021 06:00 PM to Jun 30 2021 07:00 PM` <br><br>
    4. Test Case: `add_schedule t/Maths Tuition Homework d/2021-6-2 fr/5:00pm to/7:00pm ds/Calculus Topic` <br>
       Expected: The schedule is not added. An error message saying that the schedule already exists (assuming you did the first
       test case) is shown <br><br>
    5. Test Case: `add_schedule t/Maths Tuition Homework d/2/5/2021 fr/5:00pm to/7:00pm ds/Calculus Topic` <br>
       Expected: The schedule is not added. An error message saying that the date is in the wrong format is shown <br><br>
    6. Test Case: `add_schedule t/Maths Tuition Homework d/2021-6-10 fr/15:00pm to/7:00pm ds/Calculus Topic` <br>
      Expected: The schedule is not added. An error message saying that the time is in the wrong format is shown <br><br>
    7. Test Case: `add_schedule t/Maths Tuition Homework d/2021-6-10 fr/7:00pm to/5:00pm ds/Calculus Topic` <br>
      Expected: The schedule is not added. An error message saying that the time range is invalid is shown <br><br>
    8. Test Case: `add_schedule t/Maths Tuition Homework d/2021-6-10 fr/5:00am to/10:00am ds/Calculus Topic` <br>
      Expected: The schedule is not added. An error message saying that the start time is invalid is shown <br><br>
    9. Test Case: `add_schedule t/Maths Tuition Homework d/2021-6-10 fr/10:00pm to/1:00am ds/Calculus Topic` <br>
      Expected: The schedule is not added. An error message saying that the end time is invalid is shown <br><br>
    10. Test Case: `add_schedule t/Maths Tuition Homework d/2021-6-10 fr/5:31pm to/8:46pm ds/Calculus Topic` <br>
      Expected: The schedule is not added. An error message saying that the time minutes are not in blocks of 30 or 60 minutes is shown <br><br>
    11. Test Case: `add_schedule t/English Tuition Homework d/2021-6-2 fr/4:00pm to/8:00pm ds/Calculus Topic` <br>
      Expected: The schedule is not added. An error message saying that the schedule clashes with another appointment or schedule (assuming you did the first
      test case) is shown <br><br>

### Editing a Schedule

1. Editing a schedule
    1. Prerequisites:
        1. Arguments are valid and compulsory parameters are provided.
        2. The schedule to be edited must not be in the past.
        3. The date must be in the form `yyyy-mm-dd`.
        4. The time must be in the form ` hh:mm a`.
        5. The edited schedule date and time must be a future datetime.
        6. If any of the following parameter: `DATE`, `TIME_FROM` or `TIME_TO` are edited, all three parameters **must be** provided.
        7. TIME_FROM` and `TIME_TO` must be a valid time range (`TIME_FROM` must be before `TIME_TO`).
        8. The earliest possible `TIME_FROM` is **06:00 AM** and latest possible `TIME_TO` is **11:00 PM**.
        9. The shortest possible schedule is **1 hour**, and the longest possible schedule is **8 hours**
        10. The schedule's timeslot must be in blocks of **30 minutes** or **1 hour**.
        11. The schedule's timeslot must not clash with existing appointments & schedules.
        12. The index provided must be a task index seen on the current window.<br><br>
    2. Test Case: `edit_schedule 1 t/New Schedule Name` <br>
       Expected: The schedule's title changes to `New Schedule Name` <br><br>
    3. Test Case: `edit_schedule 1 ds/New Schedule Description` <br>
       Expected: The schedule's description changes to `New Schedule Description` <br><br>
    4. Test Case: `edit_schedule 1 d/2021-6-2 fr/5:00pm to/7:00pm` <br>
       Expected: The schedule's time_from changes to `Jun 02 2021 05:00 PM` and time_to changes to `Jun 02 2021 07:00 PM`. <br><br>
    5. Test Case: `edit_schedule 1 fr/5:00pm to/7:00pm` <br>
       Assuming the schedule to be edited is in the past,  
       Expected: The schedule is not updated. An error message saying that past schedules cannot be edited. <br><br>
    6. Test Case: `edit_schedule 1 fr/5:00pm to/7:00pm` <br>
       Expected: The schedule is not updated. An error message saying that all three date and time parameters must be present. <br><br>
    7. Test Case: `edit_schedule 1 to/7:00pm` <br>
      Expected: The schedule is not updated. An error message saying that all three date and time parameters must be present. <br><br>
    8. Test Case: `edit_schedule 1 d/2021-6-2` <br>
      Expected: The schedule is not updated. An error message saying that all three date and time parameters must be present. <br><br>
    9. Test Case: `edit_schedule 1 d/2/5/2021 fr/5:00pm to/7:00pm` <br>
       Expected: The schedule is not updated. An error message saying that the date is in the wrong format is shown <br><br>
    10. Test Case: `edit_schedule 1 d/2021-6-10 fr/15:00pm to/7:00pm` <br>
       Expected: The schedule is not updated. An error message saying that the time is in the wrong format is shown <br><br>
    11. Test Case: `edit_schedule 1 d/2021-6-10 fr/7:00pm to/5:00pm` <br>
       Expected: The schedule is not updated. An error message saying that the time range is invalid is shown <br><br>
    12. Test Case: `edit_schedule 1 d/2021-6-10 fr/5:00am to/10:00am` <br>
       Expected: The schedule is not updated. An error message saying that the start time is invalid is shown <br><br>
    13. Test Case: `edit_schedule 1 d/2021-6-10 fr/10:00pm to/1:00am` <br>
       Expected: The schedule is not updated. An error message saying that the end time is invalid is shown <br><br>
    14. Test Case: `edit_schedule 1 d/2021-6-10 fr/5:31pm to/8:46pm` <br>
        Expected: The schedule is not updated. An error message saying that the time minutes are not in blocks of 30 or 60 minutes is shown <br><br>
    15. Test Case: `edit_schedule 1 d/2021-6-2 fr/4:00pm to/8:00pm` <br>
        Expected: The schedule is not updated. An error message saying that the schedule clashes with another appointment or schedule (assuming you did the first
        test case) is shown <br><br>
    16. Test Case: `edit_schedule -1 t/New Schedule Name` <br>
        Expected: The schedule is not updated. An error message about the invalid index is shown. <br><br>

### Viewing Schedules

1. Viewing schedules
    1. Prerequisites:
        1. Arguments are valid and compulsory parameters are provided.
        2. The date must be in the form `yyyy-mm-dd`.<br><br>
    2. Test Case: `view_schedule 2021-4-20` <br>
       Expected: The Schedule List Panel displays the schedules happening on Apr 20 2021. <br><br>
    3. Test Case: `view_schedule 20/4/2021` <br>
      Expected: The Schedule List Panel not updated. An error message saying that the date is in the wrong format is shown. <br><br>

### Listing All Schedules

1. List all schedules
    1. Test Case: `list_schedules` <br>
       Expected: The Schedule List Panel displays the all the schedules in ascending (the earliest appointment date to the latest appointment date) order. <br><br>
    2. Test Case: `list_schedules abcdefg` <br>
      Expected: The Schedule List Panel displays the all the schedules in ascending (the earliest appointment date to the latest appointment date) order. <br><br>

### Deleting a Schedule

1. Deleting a Schedule
    1. Prerequisites: 
        1. List all schedule(s) using the `list_schedules` command. Multiple schedules in the list. 
        2. The schedule to be deleted must exist.
        3. Index must be a positive integer.
    2. Test Case: `delete_schedule 1` <br>
       Expected: The first schedule displayed in the list is deleted. <br><br>
    3. Test Case: `delete_schedule` <br>
       Expected: An error message about the invalid command format is shown <br><br>
    4. Test Case: `delete_schedule -1` <br>
      Expected: An error message about the invalid command format is shown <br><br>

### Adding a Reminder

1. Adding a reminder
    1. Prerequisites:
        1. Arguments are valid and compulsory parameters are provided
        2. The date must be in the form `yyyy-mm-dd`.
        3. The new reminder date must be a future date. <br><br>
    2. Test Case: `add_reminder ds/Science Tuition Payment Due d/2021-6-2` <br>
       Expected: Adds a reminder by the name `Science Tuition Payment Due`, to be reminded on `Jun 02 2021` <br><br>
    3. Test Case: `add_reminder ds/Maths Tuition Payment Due d/2021-6-21` <br>
       Expected: Adds a schedule by the name `Maths Tuition Payment Due`, to be reminded on `Jun 21 2021` <br><br>
    4. Test Case: `add_reminder ds/Science Tuition Payment Due d/2021-6-2` <br>
       Expected: The reminder is not added. An error message saying that the schedule already exists (assuming you did the first
       test case) is shown <br><br>
    5. Test Case: `add_reminder ds/Science Tuition Payment Due d/2/6/2021` <br>
       Expected: The reminder is not added. An error message saying that the date is in the wrong format is shown <br><br>

### Editing a Reminder

1. Editing a reminder
    1. Prerequisites:
        1. Arguments are valid and compulsory parameters are provided.
        2. The reminder to be edited must not be in the past.
        3. The date must be in the form `yyyy-mm-dd`.
        4. The edited reminder date must be a future date.
        5. The index provided must be a positive integer index seen on the current window. <br><br>
    2. Test Case: `edit_reminder 1 ds/New Reminder Description` <br>
       Expected: The reminder's description changes to `New Reminder Description` <br><br>
    3. Test Case: `edit_reminder 1 d/2021-6-5` <br>
       Expected: The schedule's date changes to `Jun 05 2021` <br><br>
    4. Test Case: `edit_schedule 1 d/2021-1-5` <br>
       Assuming the reminder to be edited is in the past,  
       Expected: The reminder is not updated. An error message saying that past reminders cannot be edited. <br><br>
    5. Test Case: `edit_reminder -1 ds/New Reminder Description` <br>
       Expected: The reminder is not updated. An error message about the invalid index is shown. <br><br>

### Listing All Reminders

1. List all schedules
    1. Test Case: `list_reminders` <br>
       Expected: The Reminder List Panel displays the all the reminders in ascending (the earliest appointment date to the latest appointment date) order. <br><br>
    2. Test Case: `list_reminders abcdefg` <br>
       Expected: The Reminder List Panel displays the all the reminders in ascending (the earliest appointment date to the latest appointment date) order. <br><br>

### Deleting a Reminder

1. Deleting a Reminder
    1. Prerequisites:
        1. List all reminder(s) using the `list_reminders` command. Multiple reminders in the list.
        2. The reminder to be deleted must exist.
        3. The index provided must be a positive integer index seen on the current window. <br><br>
    2. Test Case: `delete_reminder 1` <br>
       Expected: The first reminder displayed in the list is deleted. <br><br>
    3. Test Case: `delete_reminder` <br>
       Expected: An error message about the invalid command format is shown. <br><br>
    4. Test Case: `delete_reminder -1` <br>
       Expected: An error message about the invalid command format is shown. <br><br>

### Adding a Grade

1. Adding a grade
    1. Prerequisites:
        1. Arguments are valid and compulsory parameters are provided.
        2. There should not be any duplicate grade (same `subject` and same `graded item`) existing in the list.
        3. `grade letter` must follow the [Singapore-GCE O'Level grading system](#Glossary).
    2. Test Case: `add_grade s/Geography gi/CA1 gr/A1` <br>
       Expected: Adds a grade with subject `Geography`, graded item `CA1` and a grade `A1` <br><br>
    3. Test Case: `add_grade s/Geography gi/CA1 gr/A1`  <br>
       Expected: The grade is not added. An error message saying that the grade already exists (assuming you did the previous
       test case) is shown <br><br>
    4. Test Case: `add_grade s/Geography gi/CA1 gr/A2`  <br>
       Expected: The grade is not added. An error message saying that the grade already exists (assuming you did the first
       test case) is shown <br><br>
    5. Test Case: `add_grade s/Geography gi/Final Exam gr/A3` <br>
       Expected: The grade is not added. An error message saying that the grade letter is in the wrong format is shown <br><br>
   5. Test Case: `add_grade s/Secondary 4 Geography gi/Final Exam gr/B3` <br>
      Expected: The grade is not added. An error message saying that the graded item is in the wrong format is shown <br><br>
      
### Deleting a Grade

1. Deleting a grade
    1. Prerequisites:
        1. List all grade(s) using the `list_grades` command. Multiple grades in the list.
        2. The grade to be deleted must exist.
        3. Index must be a positive integer and must be smaller or equal to the largest index seen on the current window.
    2. Test Case: `delete_grade 1` <br>
       Expected: The first grade displayed in the list is deleted. <br><br>
    3. Test Case: `delete_grade` <br>
       Expected: An error message about the invalid command format is shown. <br><br>
    4. Test Case: `delete_grade -1` <br>
       Expected: An error message about the invalid command format is shown. <br><br>

### Editing a Grade

1. Editing a grade
    1. Prerequisites:
        1. Arguments are valid and compulsory parameters are provided.
        2. The grade to be edited must not be in the past.
        3. The index provided must be a positive integer must be smaller or equal to the largest index seen on the current window.
        4. There should not be any duplicate grade (same `subject` and same `graded item`) existing in the list.
        5. `grade letter` must follow the [Singapore-GCE O'Level grading system](#Glossary).  <br><br>
    2. Test Case: `edit_grade 1 s/New Subject` <br>
       Expected: The grade's subject changes to `New Subject` <br><br>
    3. Test Case: `edit_grade 1 gi/New Graded Item` <br>
       Expected: The grade's graded item changes to `New Graded Item` <br><br>
    4. Test Case: `edit_grade 1 gr/C5` <br>
       Expected: The grade's grade letter changes to `C5` <br><br>
    5. Test Case: `edit_grade 2 s/New Subject gi/New Graded Item` <br>
       Assuming there are at least 2 grades in the list and you did the previous two test cases.
       Expected: The grade is not updated. An error message saying that duplicate grade exists in the list. <br><br>
    6. Test case: `edit_grade -1 s/New Subject` <br>
       Expected: The grade is not updated. An error message of invalid command format is shown. <br><br>

### Listing All Grades

1. List all grades
    1. Test Case: `list_grades` <br>
       Expected: The Grade List Panel displays the all the existing grades. <br><br>
    2. Test Case: `list_grades abcdefg` <br>
       Expected: The Grade List Panel displays the all the existing grades. <br><br>
       
### Adding a Tutor Filter

1. Adding a Tutor Filter
    1. Prerequisites for each test case:
        1. List of Tutor Filters must be empty
        2. There must be at least 2 Tutors in the tutor list.
        3. The first tutor must have the name Alex Yeoh and have at least 1 subject with experience of 5 years
        4. The second tutor must have the name Bernice Yu and have at least 1 subject with experience of 4 years
    2. Test Case: `add_tutor_filter n/Alex` <br>
       Expected: A Tutor Filter `Name: alex` is added and the tutor Bernice Yu is no longer displayed.   <br><br>
    3. Test Case: `add_tutor_filter y/<5` <br>
       Expected: A Tutor Filter `Subject Experience: < 5` is added and the Alex Yeoh is no longer displayed. <br><br>
    4. Test Case: `add_tutor_filter` <br>
       Expected: An error message is shown saying at least 1 filter must be provided. <br><br>

### Deleting a Tutor Filter
1. Adding a Tutor Filter
    1. Prerequisites for each test case:
        1. Test case is run directly after the previous test case specified
    2. Test Case (Follows `Adding a Tutor Filter` test case 1.2): `delete_tutor_filter n/Alex` <br>
       Expected: Tutor Filter `Name: alex` is deleted and both Alex Yeoh and Bernice Yu are shown.   <br><br>
    3. Test Case (Follows `Adding a Tutor Filter` test case 1.3): `delete_tutor_filter y/<5` <br>
       Expected: Tutor Filter `Subject Experience: < 5` is deleted and both Alex Yeoh and Bernice Yu are shown. <br><br>
    4. Test Case: `delete_tutor_filter` <br>
       Expected: An error message is shown saying at least 1 filter must be provided. <br><br>

### Adding an Appointment Filter

1. Adding an Appointment Filter
    1. Prerequisites for each test case:
        1. List of Appointment Filters must be empty
        2. There must be at least 2 Appointment in the appointment list.
        3. The first appointment must have the tutor name Alex Yeoh and subject name Mathematics
        4. The second appointment must have the tutor name Bernice Yu and subject name English
    2. Test Case: `add_appointment_filter n/Alex` <br>
       Expected: An Appointment Filter `Name: alex` is added and the appointment with Bernice Yu is no longer displayed.   <br><br>
    3. Test Case: `add_appointment_filter s/English` <br>
       Expected: An Appointment Filter `Subject Name: English` is added and the appointment with Alex Yeoh is no longer displayed. <br><br>
    4. Test Case: `add_appointment_filter` <br>
       Expected: An error message is shown saying at least 1 filter must be provided. <br><br>

### Deleting an Appointment Filter

1. Deleting an Appointment Filter
    1. Prerequisites for each test case:
        1. Test case is run directly after the previous test case specified
    2. Test Case (Follows `Adding an Appointment Filter` test case 1.2): `delete_appointment_filter n/Alex` <br>
       Expected: Appointment Filter `Name: alex` is deleted and both appointments with Alex Yeoh and Bernice Yu are displayed.   <br><br>
    3. Test Case (Follows `Adding an Appointment Filter` test case 1.3): `delete_appointment_filter s/English` <br>
       Expected: Appointment Filter `Subject Name: English` is deleted and both appointments with Alex Yeoh and Bernice Yu are displayed. <br><br>
    4. Test Case: `delete_appointment_filter` <br>
       Expected: An error message is shown saying at least 1 filter must be provided. <br><br>
       
