## Introduction

DieTrack is a **desktop app with a Command-Line Interface (CLI) that allows users to easily track and maintain their meals so that they are able to maintain their ideal body weight.** If you can type fast, you’ll be able to record your meals in this app much faster than other traditional GUI-based diet tracking apps.

## Table of Contents

<!--ts-->
* [Introduction](##Introduction)
* [Quick start](##Quick-start)
* [Features](##Features)
* [Body Mass Index (BMI) Tracker](##1.-Body-Mass-Tracker)
  * [Input weight, height and ideal weight](###1.1-Input-weight,-height-and-ideal-weight-:-`bmi`)
  * [Query weight, height and BMI](###1.3-Update-weight,-height-and-ideal-weight-:-`bmi-update`)
  * [Update weight, height and ideal weight](###1.3-Update-weight,-height-and-ideal-weight-:-`bmi-update`)
* [Diet Plan Selector](##2.-Diet-Plan-Selector)
  * [Get diet recommendations based on current BMI](###2.1-Get-diet-recommendations-based-on-current-BMI-:-`plan-diet-recommend`)
  * [View active diet plan](###2.2-View-active-diet-plan-:-`plan`)
  * [Select active diet plan](###2.3-Select-active-diet-plan-:-`plan-select`)
* [Macronutrients Tracker](##-3.-Macronutrients-Tracker)
  * [Input carbohydrates, fats and protein intake](###3.1-Input-carbohydrates,-fats-and-protein-intake-:-`food`)
  * [List food intake for certain days](###3.2-List-food-intake-for-certain-days-:-`food-query`)
* [Command summary](##Command-summary)
<!--te-->

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `dietrack.jar` from [here](https://github.com/AY2021S2-CS2103T-T12-2/tp/releases).

3. Copy the file to the folder you will be launching DieTrack from on a daily basis.

4. Double-click the file to start the app. The GUI should appear in a few seconds. Note how the app contains some sample data.

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.

--------------------------------------------------------------------------------------------------------------------

## Features


--------------------------------------------------------------------------------------------------------------------

## 1. Body Mass Index (BMI) Tracker

### 1.1 Input weight, height and ideal weight : `bmi`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### 1.2 Query weight, height and BMI : `bmi query`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### 1.3 Update weight, height and ideal weight : `bmi update`

Shows a list of all persons in the address book.

Format: `list`

## 2. Diet Plan Selector

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### 2.1 Get diet recommendations based on current BMI : `plan diet recommend`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### 2.2 View active diet plan : `plan`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### 2.3 Select active diet plan : `plan select`


## 3. Macronutrients Tracker

Clears all entries from the address book.

Format: `clear`

### 3.1 Input carbohydrates, fats and protein intake : `food`

Exits the program.

Format: `exit`

### 3.2 List food intake for certain days : `food query`

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
