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

## **Design** [In Progress]

### Architecture

### UI component

### Logic component

### Model component

### Storage component

### Common classes

## **Implementation** [In Progress]

## **Documentation, logging, testing, configuration, dev-ops** [In Progress]

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:
* Freelance cheesemaker
* Runs home-based business
* Prefers desktop apps over other types
* Can type fast

**Value proposition**:
1. Specific properties of each cheese
2. Track order status of each cheese (either by batch or by individual)


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                      | I want to …​                     | So that I can …​                                                        |
| -------- | ----------------------------| ------------------------------- | ---------------------------------------------------------------------- |
| `* * *`  | New user                    | Input a new order |  |
| `* * *`  | New user                    | Remove an existing order |  |
| `* * *`  | New user                    | Add cheese entries |  |
| `* * *`  | New user                    | Mark a sample order as delivered |  |
| `* * *`  | New user                    | Search for a customer | Find the customer’s contact information |
| `* *`    | New user                    | Save the data input | Retrieve the same information later |
| `* *`    | User with some familiarity  | View a summary of my inventory | See if there is a need to increase production |
| `* *`    | User                        | See the introduction message |  |
| `*`      | User with some familiarity  | Search up orders of a specific customer | Efficiently find the order status |

[More to be added]

### Use cases

(For all use cases below, the **System** is the `CHIM` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Input a Customer

**MSS**

1. User adds a customer by specifying name, phone number and address.
2. CHIM creates the new customer and shows details of the new customer.  
   Use case ends.

**Extensions**
* 1a. The given phone number is invalid.
  * 1a1. CHIM displays an error message.   
    Use case resumes at step 1.
* 1b. The given phone number is a duplicated customer.
  * 2a1. CHIM displays an error message.  
    Use case resumes at step 1.
    
#### Use case: Remove an Order

**MSS**

1. User enters an order number to remove.
2. CHIM removes the order from the list of orders. 
   Use case ends.

**Extensions**
* 1a. No such order with the specified order number exists.
  * 1a1. CHIM displays an error message.   
    Use case resumes at step 1.

#### Use case: List customers

**MSS**

1. User enters the command to list all the customers recorded in CHIM.
1. CHIM displays a list with the customers’ summary details.

   Use case ends.

**Extensions**
* 1a. CHIM does not have any customers added.
  * 1a1. CHIM informs the user that there are no customers recorded in the application.

    Use case resumes at step 1.


#### Use case: Marks order as complete

**MSS**
1. User enters the index of the order to be marked as complete.
1. CHIM marks the order as complete.

**Extensions**
* 1a. User provides an index which does not exist.
  * 1a1. CHIM displays an error message.

    Use case resumes at step 1.


#### Use case: Search for a customer

**MSS**
1. User enters a request to search for a customer by a particular name.
1. CHIM shows the customer’s details.

   Use case ends.

**Extensions**
* 1a. More than one customer has the input name.
  * 1a1. CHIM shows a list of customers with the matching name.

    Use case ends.
* 1b. There are no existing customers with the input name.
  * 1b1. CHIM responds that there are no existing customers with the input name.

    Use case resumes at step 1.


#### Use case: Exit the application

**MSS**
1. User enters the command to exit the application.
1. CHIM saves customers, orders and cheese data into data files.

   Use case ends.


### Non-Functional Requirements

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**


### Launch and shutdown

