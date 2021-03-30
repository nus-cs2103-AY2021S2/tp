--------------------------------------------------------------------------------------------------------------------
## Developer Guide for v1.2

## Table of contents

- Description of Application
- Design
    - Architecture
- Implementation
    - AddOn feature
    - View feature
    - FindAll feature
- Appendix: Requirements
    - Product Scope
    - User Stories
    - Use Cases
    - Non-Functional Requirements
    - Glossary
    - UI Mock-Up
--------------------------------------------------------------------------------------------------------------------

## **Description**

The Food Diary is a **desktop app for managing food diary entries**, optimized with a Command Line Interface (CLI) and packaged with a Graphical User Interface (GUI).

The Food Diary **caters to food-passionate NUS students** who would ideally benefit from keeping records of food options tasted in the vicinity of NUS.

The Food Diary will **allow students to save time and effort** when finding places to eat around the NUS vicinity. The Food Diary especially caters to students chiefly on 3 aspects:
1. The ability for users to log personal food reviews tagged under different categories for future reference;
1. The ability to effortlessly reference food options based on relevant filters in a user-friendly GUI; and
1. The ability to import and export their personal food diary to share with friends.

## **Design**
### Architecture
![Architecture Diagram](images/ArchitectureDiagram.png)

## **Implementation**
### AddOn Feature 
#### Implementation
The AddOn feature allows the user to add multiple reviews to a single entry of a food place. This will be useful 
for users who frequently visit a particular place and would like to enter their reviews every visit
This feature follows the architecture of AB3. 

The following sequence diagram shows how the AddOn feature works:
![AddOn Sequence Diagram](images/AddOn_Sequence_Diagram.png)

The following activity diagram summaries the flow of event when a user executes the addon command:
![AddOn_Activity_Diagram](images/AddOn_Activity_Diagram.png)

### View Feature
#### Implementation
The View feature allows the user to view a specified entry in a new window, allowing the user to carefully look through
all the details of an entry. 

The mechanism works in such a way where after the user enters a command in the UI, the command will be passed into 
`MainWindow#executeCommand()`, in which `Logic#execute()` will be called to parse the user input in 
`FoodDiaryParser#parseCommand()`. The user input will be parsed as a 'View' command and executed to retrieve all the 
details related to the specified entry. The result of this execution will be passed back to the UI and shown in a 
pop up window.

The following sequence diagram shows how the View feature works:
![View Sequence Diagram](images/ViewSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a view command:
![View Activity Diagram](images/ViewActivityDiagram.png)

### FindAll Feature
#### Implementation

The FindAll feature allows a user to find entries that match all the keywords provided by the user.
This enables the user to easily sieve out all the entries that meet every single requirement the user
is looking for, which will be useful when deciding where to eat.

The FindAll feature is similar to the Find feature. The Find feature finds for all entries that meet
at least one of the given keywords, while the FindAll feature only finds for entries that meet all the
given keywords.

One of the alternatives considered was to make the Find command serve the purpose of both the Find & FindAll
commands, as they behave similarly. However, this would require the user to key in additional syntax to
specify which method of find they would like to use. This was deemed to be less user-friendly and more prone
to errors as the command now consists of 3 parts (command word, type of find to use & keywords to find),
instead of 2 (command word & keywords to find). As a result, FindAll was implemented as a separate feature.

The following sequence diagram shows how the FindAll feature works:
![FindAll Sequence Diagram](images/FindAllSequenceDiagram.png)

The following activity diagram summarises the events that take place when a user executes the FindAll
command:
![FindAll Activity Diagram](images/FindAllActivityDiagram.png)

## **Appendix: Requirements**

### Product scope

**Target user profile**:

This product caters to food-fervent NUS students who would ideally benefit from
keeping records of food options tasted in the vicinity of NUS.

* prefer desktop apps over other types

* can type fast and prefers typing to mouse interactions

* is reasonably comfortable using CLI apps
  
* occasionally prefers to use ui for certain tasks


**Value proposition**:

The Food Diary will allow NUS foodies to save time and effort when finding places to eat around the NUS vicinity.
The Food Diary especially caters to students chiefly on 3 aspects
– One, the ability for users to log personal food reviews tagged under different categories for future reference;
Two, the ability to effortlessly reference food options based on relevant filters in a user-friendly GUI;
and Three, the ability to import and export their personal food diary to share with friends.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority| As a …​                                                       | I want to …​                                  | So that I can…​                                             |
| ------- | ---------------------------------------------------------------- | ------------------------------------------------ | ---------------------------------------------------------------------- |
| `* * *` | User wanting to add a review of food experience to a particular restaurant | Add a review of food experience                  | refer back to the particular element that defined my food experience  |
| `* * *` | User with little patience and time                               | Add names of places I have visited               | efficiently add a review to a place I have visited               |
| `* *`   | User who wants to look at the places I have visited              | View the list of experiences I have had          | easily show them to my friends              |
| `* * *` | Student trying to decide where to eat                            | Look at the places i have visited before         | decide where I shall re-visit                                          |
| `* * *` | User who would like to create custom category of food place      | Add the category of the place                    | I can have a specific view of certain places                           |
| `* * *` | User who does not want to visit a place again                    | Remove the place                                 | reduce redundant food places in my list                           |
| `* * *` | User who wants to remember food ratings | Give a rating on the overall food experience | I can gauge/ballpark the satisfaction level I get against other food experiences           |
| `* * *` | User deciding to revisit a place | Expand all the reviews of an entry | Read all the reviews in a glance           |
| `* *`   | User frequently revisiting a place                          | Add multiple reviews to a single place           | Store all my food experiences with the place   |
| `* *`   | User who wants to eat good food at an affordable price           | Search for places that match both the rating and price that I want | visit the best food places without overspending
| `* * *`   | User who made a mistake in an entry           | Perform revisions and updates to the entry | keep accurate and up-to-date information of food places


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Food Diary` and the **Actor** is the `user`, unless specified otherwise)

**UC01: Add a restaurant**

**MSS**

1.  User adds a restaurant
2.  Food Diary adds a new restaurant to the app.
    Use case ends.

**Extensions**

* 1a.  Food Diary detects invalid command from user.

    *   1a1. Food Diary warns user about wrong syntax.

    *	1a2. User enters correct syntax

         Use case resumes from step 2

* 2a. Food Diary detects duplicate restaurant that is already reviewed

    *	2a1. Food Diary warns user about duplicate

    *	2a2. Suggests user to either delete or update review

         Use case ends.

**UC02: List all restaurants**

**MSS**

1.  User requests to list all restaurants.
2.  Food Diary displays all the restaurants.

**Extensions**

* 1a. Food Diary detects invalid command from user.

    * 1a1. Food Diary warns user about wrong syntax.
    * 1a2. User enters correct syntax.

      Use case resumes from step 2.

* 2a. No Restaurants to display.

    * 2a1. Tells users that there are no restaurants.
    * 1a2. User enters correct syntax.

      Use case ends.

**UC04: Add category**

**MSS**

1. User adds a new category
2. Food Diary adds the new category into the app
3. Food Diary displays the new category added in a tag

Use case ends.

**Extensions**

* 1a. Food Diary detects invalid command from user
    * 1a1. Food Diary warns user about wrong syntax
    * 1a2. User enters correct syntax

      Use case resumes from step 2
* 1b. New category already exists
    * 1b1. Food Diary tells user that the category already exists

      Use case ends.

**UC05: Add food experience of Restaurant**

**MSS**

1. User requests to add some information about the food experience with a restaurant
2. Food Diary requests for restaurant details and food experience
3. User keys in the restaurant details and food experience
4. Food Diary adds the food experience to the requested restaurant

**Extensions**:

* 1a. Food Diary detects invalid command from user
    * 1a1. Food Diary warns user about wrong syntax
    * 1a2. User enters correct syntax

      Use case resumes from step 2
* 2a. No restaurant found
    * 2a1. Food Diary tells user that no restaurants found

      Use case ends.

    *	2a2. Suggests user to either delete or update review

         Use case ends.

**UC06: Delete a Restaurant**

**MSS**

1. User deletes a restaurant or food place
2. Food diary removes the restaurant from list

**Extensions**:
* 1a. Food diary detects invalid command from user
    * 1a1. Food Diary warns user about wrong syntax
    * 1a2. User enters correct syntax

* 2a. No restaurants found
    * 2a1. Food Diary tells user that no restaurants found.

      Use case ends.

**UC07: View an Entry**

**MSS**
1. User requests to view a specific entry
2. Food Diary checks requested entry
3. Food diary shows specified entry details

**Extensions**:
* 1a. Food diary detects invalid command from user
    * 1a1. Food Diary warns user about wrong syntax
    * 1a2. User enters correct syntax

* 2a. No entry found
    * 2a1. Food Diary tells user that no entry was found.

      Use case ends.
    
**UC08: Find all specific entries**

**MSS**
1. User enters keywords to specify requirements for entries
2. Food Diary shows all entries matching user requirements (if any)

    Use case ends.

**Extensions**:
* 1a. Food Diary detects invalid command from user
    * 1a1. Food Diary warns user about wrong syntax
    * 1a2. User enters correct syntax
    
    Use case resumes from step 2.

**UC09: Revise an Entry**

**MSS**
1. User requests to revise a specific entry
2. Food Diary checks requested entry
3. Food diary allows user to make revisions to the entry

**Extensions**:
* 1a. Food diary detects invalid command from user
    * 1a1. Food Diary warns user about wrong syntax
    * 1a2. User enters correct syntax

* 2a. No entry found
    * 2a1. Food Diary tells user that no entry was found.

      Use case ends.
    

### Non-Functional Requirements

Non-functional requirements specify the constraints under which the system for The Food Diary is developed and operated.
The Food Diary system is made up of the front-end, which is interchangeably referred to as the User Interface (UI),
and the back-end, which handles data management and operations.

#### Technical requirements:

* The system should be operable on Windows, MacOS and Linux operating systems, with Java 11 or above installed.

#### Performance requirements:

* The system should be loaded up within 2 seconds or less.
* The UI should appear within 2 seconds or less and be responsive to User input.
* The back-end should be responsive to processing User input at the same time the UI appears.
* The back-end should be able to handle 1,000 or more data entries (Food Diary entries) without noticeable sluggishness
  in performance for typical usage.

#### Usability requirements:

* The user should have access to a keyboard, and be proficient with typing alphanumeric English characters for commands
  to accomplish most of the usages of The Food Diary.

#### Project scope:
* The system should mainly comprise the handling of Food Diary-entry addition, deletion, and listing.
* The system would not be responsible for features involving multiple users

#### Scalability requirements:
* The system can handle a larger user base with many more food reviews being added to it
* Program is extendable for future addition of features easily

#### Other Noteworthy Points:

* The system should preserve data keyed in by the user

### Glossary
The glossary serves to ensure that all stakeholders, including users, have a common understanding of the noteworthy terms, and abbreviations.

* **Sluggishness**: The system starts to be slow in displaying user input when typed, and processing user input when entered.

### UI Mockup

![Ui Mock-up](images/Ui.png)
