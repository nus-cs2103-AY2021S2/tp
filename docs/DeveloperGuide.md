---
layout: page
title: Developer Guide
---

## Table of Contents
<!--ts-->
* [Design](#design)
* [Implementation](#implementation)
  * [Food Object](#food-object)
    * [Add food item feature](#add-food-item-feature)
    * [Update food item feature](#update-food-item-feature)
    * [List food item feature](#list-food-item-feature)
    * [Delete food item feature](#delete-food-item-feature)
* [Product Scope](#product-scope)
* [User Stories](#user-stories)
* [Use Cases](#use-cases)
  * [Calculate BMI](#use-case-calculate-bmi)
  * [Query height and weight](#use-case-query-height-and-weight)
  * [Update height and weight](#use-case-update-height-and-weight)
  * [Add food item as consumption](#use-case-add-food-item-as-consumption)
  * [Get diet recommendation](#use-case-get-diet-recommendation)
  * [View diet plan](#use-case-view-diet-plan)
  * [Add food item as an intake](#use-case-add-food-item-as-an-intake)
  * [Display current food intake for the day](#use-case-display-current-food-intake-for-the-day)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary) 
<!--te-->

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary"></div>

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Food Object

The food object stores the name of the food and its nutrient values (Carbohydrates, Fats and Proteins).

The 'Food' contains the following components:
1. `name`: Represents the name of the food item stored in the food component
2. `carbos`: Represents the carbo values that is associated with the food item stored in the food component
3. `fats`: Represents the fat values that is associated with the food item stored in the food component
4. `proteins`: Represents the proteins values that is associated with the food item stored in the food component
5. `kilocalories`: Represents the kilocalories values that is associated with the food item stored in the food component

There are some actions that can be performed with the Food component. 
1. Update respective nutrients' values.
2. Calculate total kilocalories' values. 

Below is the Sequence Flow Diagram when a Food gets added to the UniqueFoodList through the Add-Command: to-do

#### Design consideration:

##### Aspect: How the components within `Food` are added or changed
* Current Choice: 
  * The food components are not immutable and its nutrients value will update each time an update command is passed. 
* Pros:
  * Faster as food objects do not have to be created everytime when a change is done
  * Flexible to changes since only an update command is called to change the value
* Cons:
  * More prone to bugs as the components can be changed freely
* Alternative 1: Make Food components immutable.
  * Pros:
    * Less prone to bugs
  * Cons:
    * More overhead to update items as a new object is created everytime

### Add food item feature

#### Description:
This command adds a valid food item into the unique food list. Users are able to add a food item in with the valid input to the command below. If a food item with a similar name is added, this command will not allow it and an error will be shown to ask the user if they want to update the value instead.

Example: `food_add n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

#### Implementation: 
Once the user types in the command to add food, the parser will check for all the required prefixes. If all required prefixes are present and the input values are valid, `AddFoodItemCommand` object is created. `AddFoodItemCommand` is a class that extends `Command` abstract class. `AddFoodItemCommand` implements the `execute()` method from the `Command` abstract class. Upon execution, the command will check with the food list whether it has a food item that has a similar name. If there is, it will prompt an error that the food item exist and suggest updating the food item value instead. Otherwise, a new food item object will be created and added into the food list.

Below is an example of a usage scenario:

Step 1: The user launches the application and executes `food_add n/chocolate c/100 f/100 p/100` to create the food item. 

Step 2: The food item is added to the food list.

The following sequence diagram shows how the add operation works:
<INSERT DIAGRAM FLOW HERE>

### Update food item feature

#### Description:
This command updates a valid food item with the new value(s) specified in the unique food list. Food item has to exist in the food list and nutrient values specified has to be different from original before an update is permitted. 

Example: `food_update n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

#### Implementation: 
Once the user types in the command to update food, the parser will check for the presence of the name prefix and the presence of at least one of the nutrient prefix. If the required prefixes and valid value(s) are present, the `UpdateFoodItemCommand` object is created and a temporary food item object is created with the new values. `UpdateFoodItemCommand` is a class that extends `Command` abstract class. `UpdateFoodItemCommand` implements the `execute()` method from the `Command` abstract class. Upon execution, the command will check with the food list whether it has a food item that has a similar name. If there is, it will check for any difference of the original values with the new value(s). If there is at least 1 difference, the food item in the food list will be updated to the new value(s). Otherwise, it will prompt for the user to modify at least 1 of the food item's value to be different from original.    

Below is an example of a usage scenario:

Step 1: The user launches the application and executes `food_update n/chocolate c/200 f/200 p/200` to update the specified food item. 

Step 2: The food item specified will have its value(s) updated to the new value(s) in the food list.

The following sequence diagram shows how the update operation works:
<INSERT DIAGRAM FLOW HERE>

### List food item feature

#### Description:
This command lists all the food item(s) in the food list.

Example: `food_list`

#### Implementation: 
Once the user types in the command, the list of food items in the food list will be displayed.`ListFoodItemCommand` is a class that extends `Command` abstract class. `ListFoodItemCommand` implements the `execute()` method from the `Command` abstract class. Upon execution, the command will list all the food items stored in the food list.

Below is an example of a usage scenario:

Step 1: The user launches the application and executes `food_list`. 

Step 2: The food item(s) in the food list will be displayed.

The following sequence diagram shows how the delete operation works:
<INSERT DIAGRAM FLOW HERE>
 
### Delete food item feature

#### Description:
This command deletes a valid food item from the unique food list. Food item has to exist in the food list before the deletion can be carried out.

Example: `food_delete n/FOOD_NAME`

#### Implementation: 
Once the user types in the command to delete food, the parser will check for the required name prefix. If the name prefix is present, the `DeleteFoodItemCommand` object is created with the food name captured from the parser. `DeleteFoodItemCommand` is a class that extends `Command` abstract class. `DeleteFoodItemCommand` implements the `execute()` method from the `Command` abstract class. Upon execution, the command will check with the food list whether it has a food item that has a similar name. If there is, it will delete the food item from the list. Otherwise, it will prompt an error that the food item is not found.

Below is an example of a usage scenario:

Step 1: The user launches the application and executes `food_delete n/chocolate`. 

Step 2: The food item specified will be deleted from the food list.

The following sequence diagram shows how the delete operation works:
<INSERT DIAGRAM FLOW HERE>

### Product Scope

**Target user profile**
  * want to start a diet
  * track their progress against a diet plan
  * track the macronutrients of the food consumed
  * is comfortable with command-line interface

**Value proposition**: quickly input daily food intake and calculate their macronutrients to check if diet plan is progressing as planned

### User Stories
Priorities: High (must have) - `***`, Medium (nice to have) - `**`, Low (unlikely to have) - `*`

|Priority|   As a ...   |   I want to ...  |   So that I can​ ...   |
|------------|------------------|----------------------|---------------------------|
|`***`|User|Set a deadline for my diet plan|Stay on track of when the diet finishes|
|`***`|User|Add a new diet plan|Start a new diet plan|
|`***`|User|Delete my diet plan|Give up on the current diet plan|
|`***`|User|Track my weight|See if the diet is working|
|`***`|User|View a list of recommended diets|find out what to diet on as I am not sure|
|`***`|First-time User|Skim through some example templates|Know how the application works|
|`**`|Long-time User|See a history of my past diet|I can backtrack what diets I have been on|
|`**`|Frequent user|Store information on the popular foods that I eat|Quickly input my intake for the day|
|`**`|User|Customize my diet plan|Adjust my diet plan to fit my needs|
|`**`|User with dietary requirements|Filter the list of diets to fit my dietary requirements|Choose diets that are suited to my dietary needs|
|`*`|Social Users|See/Give reviews on diets|Know which diets are more effective for others|
|`*`|Social Users|Connect with peers to see their dietary plans and progress|Stay up-to-date with my peers and possibly motivate myself|

*{More to be added}*

### Use Cases

(For all use cases below, the **Body Mass Index (BMI) Tracker** is the `BMITracker`, **Diet Plan Selector** is the `DietSelector`, **Macronutrients Tracker** is the `MacroTracker` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Calculate BMI

**MSS**

1.  User keys in the weight, height and ideal weight
2.  BMITracker calculates BMI from the user input
3.  BMITracker updates the height, weight and BMI of the user

    Use case ends.

#### Use case: Query height and weight

**MSS**

1.  User queries for height and weight
2.  BMITracker displays information for height and weight

    Use case ends.

#### Use case: Update height and weight

**MSS**

1.  User queries for height and weight
2.  BMITracker displays information for height and weight
3.  User updates the height and weight if they are different
4.  BMITracker updates the height, weight and BMI of the user

    Use case ends.

#### Use case: Add food item as consumption

**MSS**

1.  User keys in the date and food item with information such as its name, fats, carbos, proteins
2.  MacroTracker keeps track of that and calculates its kilocalories
3.  MacroTracker computes the total kilocalories for the day

    Use case ends.

#### Use case: Get diet recommendation

**MSS**

1.  User requests to get a list of diet recommendation
2.  DietSelector displays a list of diet recommendation based on user's BMI

    Use case ends.

#### Use case: View diet plan

**MSS**

1.  User requests for a certain diet type
2.  DietSelector displays a detailed requirement for that certain diet type

    Use case ends.

#### Use case: Add food item as an intake

**MSS**

1.  User adds a particular food item
2.  MacroTracker tracks the food and computes the total kilo calories consumption for the day

    Use case ends.

**Extensions**
*  1a. The food item exists.

   * 1a1. MacroTracker updates food item details in the list of food.

*  1b. The food item does not exists.

   * 1b1. MacroTracker adds the food item into the list of food.

   Use case resumes at step 2.

#### Use case: Display current food intake for the day

**MSS**

1.  User queries a particular day intake.
2.  MacroTracker displays the summary intake for that day.

    Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold diet plans for up to the past two years (730 days) without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should help users as they start the application for the first time with sample data.
5.  Interface should not hinder the users' usage of the application in order to input the data they need and get the desired response.
6.  The data files should be easily modifiable by a user with a basic understanding of the JavaScript Object Notation (JSON).
7.  Errors should display vividly and differently from the rest of the normal input such that users are aware something has gone wrong.
8.  Should be easily deployable to all systems running any _mainstream OS_ once compiled executable is distributed via a release.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
*{More to be added}*
