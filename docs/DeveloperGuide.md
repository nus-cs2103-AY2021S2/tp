# Developer Guide

## Appendix: Requirements

### Product Scope

**Target User Profile:**

* Students currently in tertiary educational institutions

* Users that prefer desktop applications as opposed to other types of applications

* Users that are reasonably comfortable with command line interfaces

* Users who are able to type fast and hence prefer typing to mouse interaction

* Users who have a hard time organising their wardrobe

  

**Value Proposition:**

* Ability to organise clothing items based on attributes such as colour, size, material, type of clothing

* Maintain outfit schedules to prevent repetitive dressing

* Receive clothing suggestions based on factors such as weather, temperature, or the nature of events the user may be attending

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​ | So that I can…​ |  
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |  
| `* * *`  | new user                                   | have a tutorial to teach me how to use the app| so that I will be able to learn how to use the app quicker                 |  
| `* * *`  | user                                       | add an item of clothing               |        keep a record of clothing items                                                                |  
| `* * *`  | user                                       | edit details of clothing logged in                | correct incorrect details pertaining to items          |  
| `* * *`  | user                                       | find a clothing by name          | locate details of clothing without having to go through the entire list |  
| `* *`    | user                                       | remove an item of clothing from my wardrobe   | so that I can reflect that an item of clothing has been discarded                |  
|  `* *`      | user        | easily plan a schedule for what clothes to wear                                                |  so that i don't have to waste time deciding last minute
|  `* *`      | user        | be reminded on the clothes I have not worn                                                 |  so that I would not be wearing the same clothes all the time
|  `*`      | user        | to see whether certain colours and types of clothing match                                                 |  so that I will be able to ascertain if the clothes match without having to try them on physically

### Use cases
(For all use cases below, the System is the `NuFash` and the Actor is the `user`, unless specified otherwise)

**Use case: Add a clothing item**

**MSS**

1. User requests to add a clothing item with specified attributes
2. NuFash adds the specified clothing item to list of existing clothing

   Use case ends.

**Extensions**
* 1a. The specified attributes are in an invalid format.
    * 1a1. NuFash shows an error message with the valid format.

    * 1a2. User enters new data.
    
        Steps 1a1-1a2 are repeated until data entered is in a valid format.
        
        Use case resumes from step 2.

**Use case: Delete a clothing item**

**MSS**

1. User requests to list all clothing items
2. NuFash shows a list of clothing items
3. User requests to delete a specific clothing item in the list
4. NuFash deletes the specified clothing item 
   
    Use case ends.

**Extensions**
* 2a. The list is empty.
  
  Use case ends.

* 3a. The given index is invalid.
    * 3a1. NuFash shows an error message.
      
        Use case resumes at step 2.

    
*{More to be added}*

