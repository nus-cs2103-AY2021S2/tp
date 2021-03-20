--------------------------------------------------------------------------------------------------------------------
## Developer Guide for v1.2

## Table of contents

- Description of Application
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

## **Appendix: Requirements**

### Product scope

**Target user profile**:

This product caters to food-fervent NUS students who would ideally benefit from
keeping records of food options tasted in the vicinity of NUS.

* prefer desktop apps over other types

* can type fast and prefers typing to mouse interactions

* is reasonably comfortable using CLI apps


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
| `* * *` | As a user deciding to revisit a place | Expand all the reviews of an entry | Read all the reviews in a glance           |

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
