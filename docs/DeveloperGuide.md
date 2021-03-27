# Developer Guide

## Implementation
This section describes some noteworthy details on how certain features are implemented.

### Add

### Remove

### Edit

### [Proposed] List feature

#### Proposed Implementation

The proposed `list` mechanism extends the `list` mechanism of `Addressbook`, which lists out the garments based on 
the order in which they have been input into the system. The extended `list` mechanism sorts the list of garments
based on the date and time that they were last used, and outputs the entries in that order.

This is achieved through the creation of the `LastUse` attribute that is tied to the `Garment` object, that gives the 
it a date and time as to when it was last used.

`ListCommand` is updated to allow the garments to be sorted by the `LastUse` attribute.

The following sequence diagram shows how the list operation works:

The following activity diagram summarizes what happens when a user executes a new command:

#### Design Consideration:

##### Aspect: Listing the garments
* **Alternative 1 (Preferred implementation)**: <br>
  Lists out the garments based on chronological ordering of the `LastUse` attribute.
  * Pros: Garments that have not been used for a longer period of time come up earlier in the list, which would 
    encourage and remind users to wear all their clothes.
  * Cons: Garments may not have been used for a reason, which may result in user taking a longer time to sieve 
    through the list.
* **Alternative 2**: <br>
  Lists out the garments based on reverse chronological ordering of the `LastUse` attribute.
  * Pros: Garments that have been used often could be better suited for the users' needs, and making them come 
    earlier in the list results in the users sieving through the list quicker.
  * Cons: Users may forget about the garments that they have not worn in some time.
* **Alternative 3**: <br>
    Lists out the garments based on chronological or reverse chronological ordering of the `LastUse` attribute.
  * Pros: Allows users to choose which ordering they want, and they can get the respective benefits as above.
  * Cons: Requires more time to implement.


### [Proposed] Find feature

#### Proposed Implementation

The proposed `find` mechanism extends the `find` mechanism of `AddressBook`, which only allows users to find entries based on the "Name" attribute. This extended find mechanism allows users to find entries based on any attribute, namely:
* Name
* Size
* Colour
* DressCode
* Type
* Description

This is achieved through the creation of new Predicates (in addition to the existing NameContainsKeywordsPredicate):
* SizeContainsKeywordsPredicate
* ColourContainsKeywordsPredicate
* etc.

FindCommandParser is updated to detect the prefixes for multiple attributes (i.e. `n/` for Name, `c/` for Colour, etc.) and the respective predicate is hence used to create the FindCommand Object

#### Design Consideration:

##### Aspect: How many attributes Find can account for at a time
* **Alternative 1 (Current implementation)**: <br>
  Finds with only one attribute at a time. <br>
  E.g. `find n/jeans c/blue` will only find entries whose Name attribute contains the keyword "jeans".
  * Pros: Easier to implement.
  * Cons: Limited functionality.
* **Alternative 2**: <br>
  Finds with multiple given attributes. <br>
  E.g. `find n/jeans c/blue` will find entries whose Name attribute contains the keyword "jeans" **and** whose Colour attribute contains the keyword "blue".
  * Pros: More precise results.
  * Cons: Requires a single predicate to account for all combinations of user input.

### [Proposed] Match feature

#### Proposed Implementation

The proposed `match` mechanism matches extends the proposed `find` mechanism of `NuFash`. It 
finds garments that match the colour and dress code of a specified garment, and 
also complements the type of the specified garment.

This is achieved through the creation of three new Predicates (in addition to the existing NameContainsKeywordsPredicate):
* ColourContainsKeywordsPredicate
* DressCodeContainsKeywordsPredicate
* TypeContainsKeywordsPredicate

MatchCommand is updated to use an updated find command
with multiple attributes (i.e. `c/` for Colour, `d/` for dressCode and `t/` for type) and the respective predicate is hence used to create the FindCommand Object

#### Design Consideration:

##### Aspect: Matching based on multiple attributes
* **Alternative 1 (Current implementation)**: <br>
  Matches based on a single garment. <br>
  E.g. `match 1` will find entries that match the colour and dress code of garment at index 1 in the wardrobe,
  and complement its type.
    * Pros: Easier to implement.
    * Cons: Requires multiple match commands to generate a full outfit.
* **Alternative 2**: <br>
  Matches based on multiple garments <br>
  E.g. `match 1 2` will find entries that match the colours and dress codes of the garments at indices 1 and 2
  in the wardrobe, and complements their types.
    * Pros: Can generate a full outfit with one match command.
    * Cons: Difficult to implement.

### [Proposed] Select feature

#### Proposed Implementation
The proposed `select` mechanism is facilitated by the `SelectCommand` class
The mechanism allows for the `LastUse` attribute to be updated in the specified Garment.
`LastUse` is an attribute of `Garment` which implements the `Model` interface.
It implements the following operations:
* `Select()` - Takes the specified object and updates the LastUse attribute

Given below is an example usage scenario and how the Select mechanism behaves at each step.

Step 1. The user launches the application for the first time. The Wardrobe will be initialized with the stored garments.
Each garment has the distinct attributes: Colour, DressCode, LastUse, Name, Size, Type.

Step 2. The user executes add n/NAME s/SIZE c/COLOUR r/DRESSCODE t/TYPE to add a new garment to the existing list.
The LastUse attribute of this garment is instantiated with the local time and date.

Step 3. The user decides that they would like to indicate that a particular garment was worn. They can do this
by viewing the garments by using List, following which they can use the Select Command by specifying the garment's index.

The following sequence diagram shows how the select operation works:

The following activity diagram summarizes what happens when a user executes a new command:

#### Design consideration:

##### Aspect: How select executes

* **Alternative 1 (current choice):** Uses index of garment object to select it
    * Pros: Easy to implement.
    * Cons: The garment indexing is not fixed. The User has to use the List command to use the correct index.

* **Alternative 2:** Use a set of attributes to select it
    * Pros: User can use either Find or List command to view relevant garments.
    * Cons: User will need to key in more information in the GUI.

## Appendix: Requirements

### Product Scope

**Target User Profile:**

* Students currently in tertiary educational institutions

* Users that prefer desktop applications as opposed to other types of applications

* Users that are reasonably comfortable with command line interfaces

* Users who are able to type fast and hence prefer typing to mouse interaction

* Users who have a hard time organising their wardrobe
<br><br>
  

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
|  `* *`      | user        | easily plan a schedule for what clothes to wear                                                |  so that I do not have to waste time deciding at the last minute
|  `* *`      | user        | be reminded on the clothes I have not worn                                                 |  so that I would not be wearing the same clothes all the time
|  `*`      | user        | to see whether certain colours and types of clothing match                                                 |  so that I will be able to ascertain if the clothes match without having to try them on physically
<br>

### Use cases
(For all use cases below, the System is the `nufash` and the Actor is the `user`, unless specified otherwise)

**Use case: Add a clothing item**

**MSS**

1. User requests to add a clothing item with specified attributes
2. nufash adds the specified clothing item to list of existing clothing items

   Use case ends.

**Extensions**
* 1a. The specified attributes are in an invalid format.
    * 1a1. nufash shows an error message with the valid format.

    * 1a2. User enters new data.
    
        Steps 1a1-1a2 are repeated until data entered is in a valid format.
        
        Use case resumes from step 2.

      
**Use case: Delete a clothing item**

**MSS**

1. User requests to list all clothing items
2. nufash shows a list of clothing items
3. User requests to delete a specific clothing item in the list
4. nufash deletes the specified clothing item 
   
    Use case ends.

**Extensions**
* 2a. The list is empty.
  
  Use case ends.

* 3a. The given index is invalid.
    * 3a1. nufash shows an error message.
      
        Use case resumes at step 2.
<br><br>

**Non-Functional Requirements:**
1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2.  Should be able to hold up to 1000 articles of clothing without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
<br><br>
    
**Glossary:**
* **Mainstream OS**: Windows, Linux, Unix, OS-X
