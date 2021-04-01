---
layout: page
title: Developer Guide
---

<p align="center">
  <img src="images/dietlah-slim.png" alt="DietLAH!">
</p>

# Developer Guide

## Table of Contents
<!--ts-->
* [Design](#design)
* [Implementation](#implementation)
  * [User Object](#user-object)
  * [Food Object](#food-object)
    * [Add food item feature](#add-food-item-feature)
    * [Update food item feature](#update-food-item-feature)
    * [List food item feature](#list-food-item-feature)
    * [Delete food item feature](#delete-food-item-feature)
  * [FoodIntake Object](#foodintake-object)
  * [Progress Report feature](#progress-report-feature)
  * [Mifflin-St Joer Formula](#mifflin-st-joer-formula)
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
* [References](#references)
<!--te-->

## **Introduction**
DietLAH! is a desktop app with a Command-Line Interface (CLI) that allows users to easily track and maintain their meals so that they are able to maintain their ideal body weight. The application also stores all the application data in a JSON (JavaScript Object Notation) storage file so that the user's progress and records will remain when they re-open the application.

This developer guide serves as a documentation and manual of how the existing system was designed, and provides information on how certain important features were implemented.

## Understanding the Developer Guide
To make the Developer Guide more comprehensible, certain labelling and highlights are used in the guide. Familiarising yourself with these syntaxes may help you get the most out of the Developer Guide.
Legend | Description
--------|------------------
`Inline code` | Highlights Objects, Classes and Code segments
[Tips] | Useful tips
[Important] | Important information to take note of

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

### User Object

The User object is where the majority of the user's information and parameters are stored.

The 'User' object contains the following components:
1. `age`: Represents the Integer value holding the age of the user
2. `bmi`: Represents the Bmi object, which holds the height and weight values of the user (in double)
3. `gender`: Represents the String value holding the gender of the user
4. `IdealWeight`: Represents the Double value providing the ideal weight of the user

Some of the actions that can be performed with the User component are:
1. Set and retrieve the user's chosen diet plan (Active Diet Plan)
2. Update and retrieve the list of Food items that the user has stored


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
Diagram flow to be inserted here

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

### FoodIntake Object

The FoodIntake class stores a `LocalDate` and `Food` object representing the date and food associated with a particular FoodIntake.

1. `date` : Represents the date that the FoodIntake was recorded
2. `food` : Represents the `Food` object associated with the FoodIntake record

The `Food` object associated with each `FoodIntake` object is independent of the `UniqueFoodList` and editing a `Food` in the `UniqueFoodList` will not affect old FoodIntake values, and vice versa.

There are two constructors for the creation of a FoodIntake object.

1. `public FoodIntake(LocalDate date, Food temporaryFood)` : Creates a `FoodIntake` object given the `LocalDate` and `Food` object - used in the general FoodIntakeCommand when there is no need to alter the `Food` name e.g. appending the numerical duplicate count.
2. `public FoodIntake(LocalDate date, String name, double carbos, double fats, double proteins)` : Creates a FoodIntake object given the `LocalDate` and individual food name and nutrient values - used when loading to file and saving duplicate `FoodIntake` Food names.

#### Design consideration

The `FoodIntake` class makes use of a `Food` object as it can be directly retrieved from the `UniqueFoodList` which stores a list of `Food` objects.

Two constructors were used to get-around the restrictions by the `Food` name field. By default, the `Food` name can only contain **alphabets and spaces**, however, when adding a new `FoodIntake` item to the `FoodIntakeList`, duplicate `Food` names are appended with a **numerical duplicate count** (e.g. Chicken rice 2 symbolises that it is the second 'Chicken rice' added on the specific date). As such, the second constructor allows for `Food` names with numerical values and is used when loading the `FoodIntakeList` from file, and when adding a `FoodIntake` with a duplicate `Food` name.

### FoodIntakeList Class

[Work in progress]

### Progress Report feature

The progress report gives the user the ability to track the progress of his/her food intake against the requirements of the diet plan selected by the user.
Each progress report provides the following information:

* The general details of the active diet plan, such as the name, description and its macronutrient composition.
* The required daily macronutrient intake based on the user's Body Mass Index (BMI)
* The foods consumed by the user, grouped in the day it was consumed, and their macronutrient compositions
* How much the daily intake has adhered to the diet plan's macronutrient requirements
* How much the user has adhered to the diet plan's macronutrient requirements in total

Example: `progress`

#### Implementation:
Before the `progress` command can be successfully run, the user needs to have selected an active diet plan to follow. Otherwise, the user will be prompted to select a diet plan first.
A progress report will then be generated whenever the `progress` command is entered.

Below is an example of a usage scenario:

Step 1: The user launches the application and executes `plan_set p/1` to set an active diet plan.

Step 2: The user adds the food consumed using the `food_intake_add` command (refer to the Add food intake feature for more details)

Step 3: The user executes `progress` to view a progress report based on the active diet plan and his/her food intake.

#### ProgressCalculator class

The `ProgressCalulator` class is a static class containing the `calculateProgress` method which accepts a `user` input parameter in the form of a `User` class object.
This `user` object will contain the necessary information required to calculate the precentage of adherence to the diet plan. These information are:

1. The `DietPlan` class object which contains the macronutrients requirements for the plan
2. The `FoodIntakeList` class object which contains the list of foods consumed by the user on which day

The progress calculator will first calculate the required calories and macronutrients to fit the goal of the user's active diet plan. Documented below are the steps involved in deciding the daily amount of calories and macronutrients needed for the user to adhere to the diet plan:

1. The user's weight maintenance calories are calculated based on the **Mifflin-St Joer Formula**.
2. Depending on the type of diet plan (weight gain, weight loss or weight maintenance), the amount of calories required is calculated:
   * For weight gain plans, the daily amount of calories required is **maintenance calories + 400**
   * For weight loss plans, the daily amount of calories required is **maintenance calories - 500**
   * For weight maintenance plans, the daily amount of calories required is the **maintenance calories**
3. The *macronutrients' percentages* for the diet plan is applied to the calculated calories to determine how much of each macronutrient is required (in grams).

The progress calculator then uses the *macronutrients' percentages* to decide whether the user has fulfiled the diet plan's requirements. This information is displayed via 3 main sections of the report:
* Active diet plan details
* Daily report
* Total report

The following sections will explain how each of these information is collected by the `ProgressCalculator` class.

#### Active diet plan details

This section of the progress report details information pertaining to the active diet plan. These information is retrieved from the respective `DietPlan` class of the active diet plan selected by the user.
This includes the name of the diet plan, a brief description of the diet plan and the macronutrient requirements (in percentages).
<br/>

Additionally, this section also shows the daily macronutrients requirements the user needs to follow in order to fulfil the plan's requirements. This includes the daily calorie intake, daily carbohydrate intake, daily protein intake and daily fats intake, in grams.

#### Daily report

This section of the progress report details the foods consumed within each day as well as whether the daily total of each macronutrient adheres to the plan's requirements. <br/>

The total of each macronutrient intake is calculated by summing up the macronutrients *(carbohydrates, proteins and fats)* in each food consumed within that particular day.
These totals are divided by their respective daily macronutrient requirements to get a percentage of how much the user has adhered to the plan's requirements. This *daily adherence percentage* indicates whether the user has exceeded, is under or is within the diet plan's requirements for that day.

A leeway value of **5%** is allowed should the user's *daily adherence percentage* fall just above or below the required amount.

#### Total report

This section of the progress report details the user's total adherence to the diet plan's requirements. This is calculated by taking the average of all *daily adherence percentages* of each day.
This *total adherence percentage* is then reported to the user.

Similar to the *daily adherence percentage*, the *total adherence percentage* has a leeway value of **10%** should it fall just above or below the required amount.

#### Design consideration:

The are certain design considerations made when adding the progress calculator feature. The first of which is the use of the Mifflin-St Joer Formula to calculate the BMR as opposed to other formulas.
Frankenfield (2005) studied 4 equations that were commonly used in calculating BMR. These equations were the Mifflin-St Joer equation, the Harris-Benedict equation, the Owen equation and the WHO/FAO/UNU equation.
The study found that the Mifflin-St Joer formula was most accurate in calculating the BMR, being within 10% of the actual value measured.
As such, the DietLAH! team has decided to use the Mifflin-St Joer formula as the basis for calculating BMR of users.
<br/>

Secondly, there are leeways given for the *daily adherence percentage* and the *total adherence percentage* to provide more flexibility to the application. This is made in consideration of human errors, such as inaccurate estimation of macronutrients, as well as inaccuracies in nutrition labels.


### Mifflin-St Joer Formula

The Mifflin-St Joer Formula is used to calculate the Basal Metabolic Rate (BMR), which is the rate at which calories are burned daily when the individual is not performing any activity.
The formula takes into account the individual's weight, height, age and sex.
<br/>

For men, the formula is as follows: **(10 * weight(kg)) + (6.25 x height(cm)) – (5 x age(years)) + 5**
<br/>
For women, the formula is as follows: **(10 * weight(kg)) + (6.25 x height(cm)) – (5 x age(years)) - 161**


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


### References

Frankenfield, D., Roth-Yousey L. & Compher C. (2005). Comparison of predictive equations for resting metabolic rate in healthy nonobese and obese adults: a systematic review. *Journal of the American Dietetic Association*, 105(5), 775-89. doi: 10.1016/j.jada.2005.02.005.
