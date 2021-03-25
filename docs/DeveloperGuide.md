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

## **Implementation**

### Find filter Date of birth
Step 1. User will execute find command
Step 2. findcommand will be parsed
Step 3. Access the specific DOB filter to filter the results and list them out on the GUI



--------------------------------------------------------------------------------------------------------------------


### Product scope

**Target user profile**:

* Car salesperson working in a multi-brand dealership 
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: 

* Manage contacts faster than a typical mouse/GUI driven app

* Offers a higher success rate of closing deals as car salesperson can focus on selling cars that fits <br> 
  the customer needs and requirements

* Notify potential clients on promotions and new car launches based on their profile preferences

* Follow-up with ex-customers upon expiry of their COE for new sales opportunity





### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                             |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | add a new customer             | add new customers to my existing customer base                         |
| `* * *`  | user                                       | delete an existing customer     | remove customer entries that I no longer require                       |
| `* * *`  | user                                       | find a customer by name        | locate details of customers without having to go through the entire list |
| `* * *`  | user                                       | list my customers              | have an overview of my customer contacts                               |
| `* * *`  | user                                       | clear all my customers contacts | start afresh with a empty customer database instead of trying to rectify a legacy database riddle with outdated data|
| `* * *`  | user                                       | exit from the app              | safely exit the app, certain that my data is securely saved            |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user with numerous customer contacts       | list customer by car type preference  | locate customers who prefer a specific car type easily          |
| `* * *`  | user with numerous customer contacts       | list customer by car brand preference | locate customers who prefer a specific car brand easily         |
| `* *`    | user with numerous ex-customers            | list customers with expiring COE   |  have a list of potential customers who might purchase a new car   |
| `* *`    | user                                       | hide private contact details   | minimize chance of someone else seeing them by accident                |
| `*`      | user with many customers contacts          | sort customer by name          | locate a customer easily                                                 |
| `*`      | user                                       | send customer holiday greetings| maintain cordial relationship with my customer                         | 
| `*`      | user                                       | track family status of customers| make appropriate recommendations for car type for family              |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Car@Leads` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a customer**

**MSS**

1.  User requests to list customers.
2.  System shows a list of customers.
3.  User requests to add a specific customer in the list.
4.  System adds the specified customer.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case resumes from step 3

* 3a. The given index is invalid.

    * 3a1. System shows an error message.

      Use case resumes at step 2.
    
    
**Use case: Delete a customer**

**MSS**

1.  User requests to list customers
2.  System shows a list of customers
3.  User requests to delete a specific customer in the list
4.  System deletes the customer

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. System shows an error message.

      Use case resumes at step 2.

**Use case: List customers**

**MSS**

1.  User requests to list customers
2.  System shows a list of customers

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 customers without a noticeable sluggishness (response time within 100ms) in 
    performance for 
    typical 
    usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **COE**: Certificate of Entitlement for Singapore Cars
* **GUI**: Graphical user interface for the Car@leads app


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

   1. Test case: `delete John doe`<br>
      Expected: contact with name 'John doe' is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete Invalid@Name`<br>
      Expected: No customer is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
