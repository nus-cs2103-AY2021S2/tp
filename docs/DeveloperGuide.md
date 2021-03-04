### Use Cases

**Use case: Delete a contact**

**MSS**

1. User requests to list all contacts.
2. Teaching Assistant shows a list of all contacts.
3. User requests to delete a specific contact in this list.
4. Teaching Assistant deletes the contact.
   
   Use case ends.

**Extensions**

* 2a. The list is empty.
  
    Use case ends.
    

* 3a. The given index is empty.
 
    * 3a1. Teaching Assistant shows an error message.

        Use case resumes at step 2.
      

**Use case: Add a schedule**

**MSS**

1. User requests to add a schedule.
2. AddressBook adds the schedule into the list. 
   
    Use case ends.
   
**Extensions**
* 2a. The given date(s) are invalid.

  * 1a1. Teaching Assistant shows an error message.

    Use case ends.
    
**Use case: Delete a schedule**

**MSS**

1. User request to list schedules.
2. Teaching Assistant shows a list of schedules.
3. User requests to delete a specific schedule in the list.
4. Teaching Assistant deletes the schedule.

    Use case ends.

**Extensions**

* 2a. The list is empty.

    Use case ends.

* 3a. The given schedule name is invalid.

  * 3a1. Teaching Assistant shows an error message.

    Use case ends.

### Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3. The system should be usable by a novice who has never used virtual management applications.
4. The user interface should be intuitive enough for users who are not IT-savvy.
5. The product is offered as an open source software.
