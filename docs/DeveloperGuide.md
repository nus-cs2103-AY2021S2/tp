## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

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
    
## Description 

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                             |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * * ` | User wanting to add a food experience to a particular restaurant | Add a food experience | I can refer back to the particular element that defined my food experience  |
| `* * *`  | user with little patience                  | easily add names of places I have visited        | I can efficiently add a review to a place I have visited               |
| `* * *`  | Student trying to decide where to eat      | look at the places i have visited before         | Decide where I shall re-visit                                          |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**UC01: Add a restaurant**

**MSS**

1.  User adds a restaurant
2.  FoodDiary adds a new restaurant to the app.
    Use case ends.

**Extensions**

* 1a.  FoodDiary detects invalid command from user.

    *   1a1. FoodDiary warns user about wrong syntax.

    *	1a2. User enters correct syntax

         Use case resumes from step 2

* 2a. FoodDiary detects duplicate restaurant that is already reviewed

    *	2a1. FoodDiary warns user about duplicate

    *	2a2. Suggests user to either delete or update review

         Use case ends.

**UC02: List all restaurants**

**MSS**

1.  User requests to list all restaurants.
2.  FoodDiary displays all the restaurants.

**Extensions**

* 1a. FoodDiary detects invalid command from user.

    * 1a1. FoodDiary warns user about wrong syntax.
    * 1a2. User enters correct syntax.

      Use case resumes from step 2.

* 2a. No Restaurants to display.

    * 2a1. Tells users that there are no restaurants.
    * 1a2. User enters correct syntax.

      Use case ends.

**UC05: Add food experience of Restaurant**

**MSS**

1. User requests to add some information about the food experience with a restaurant
2. FoodDiary requests for restaurant details and food experience
3. User keys in the restaurant details and food experience
4. FoodDiary adds the food experience to the requested restaurant

**Extensions**:

* 1a. FoodDiary detects invalid command from user
    * 1a1. FoodDiary warns user about wrong syntax
    * 1a2. User enters correct syntax
      Use case resumes from step 2
* 2a. No restaurant found
    * 2a1. FoodDiary tells user that no restaurants found

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

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
