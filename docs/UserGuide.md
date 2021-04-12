---
layout: page
title: User Guide
---

<p align="center">
  <img src="./images/budget_baby.png" width="200px" />
  <h1 align="center">BudgetBaby :baby: :money_with_wings: </h1>
</p>

## Table of Contents

- [1 Introduction](#1-introduction)
- [2 About](#2-about)
  - [2.1 Strucutre of the User Guide](#21-structure-of-the-user-guide)
  - [2.2 Understanding the User Guide](#22-understanding-the-user-guide)
- [3 Quick Start](#3-quick-start)
- [4 UI Overview](#4-ui-overview)
  - [4.1 Menu Bar](#41-menu-bar)
  - [4.2 Budget Display](#42-budget-display)
  - [4.3 Financial Record List](#43-financial-record-list)
  - [4.4 Command Box](#44-command-box)
  - [4.5 Result Display](#45-result-display)
  - [4.6 Status Bar](#46-status-bar)
- [5 Features](#5-features) TODO
  - [5.1 Viewing help : `help`](#viewing-help--help)
  - [5.2 Adding a financial record : `add-fr`](#adding-a-financial-record--add-fr)
  - [5.3 Deleting a financial record : `delete-fr`](#deleting-a-financial-record--delete-fr)
  - [5.4 Editing a financial record : `edit-fr`](#editing-a-financial-record--edit-fr)
  - [5.5 Setting monthly budget : `set-bg`](#setting-monthly-budget--set-bg)
  - [5.6 Viewing a specific month : `view-month`](#viewing-a-specific-month--view-month)
  - [5.7 Finding financial records : `find-fr`](#finding-financial-records--find-fr)
  - [5.8 Resetting filters on financial records : `reset-filter`](#resetting-filters-on-financial-records--reset-filter)
  - [5.9 Exiting the program : `exit`](#exiting-the-program--exit)
  - [5.10 Viewing top 5 categories spent for the current month](#viewing-top-5-categories-spent-for-the-current-month)
  - [5.11 Viewing budget for the current month](#viewing-budget-for-the-current-month)
  - [5.12 Checking remaining budget for the current month](#checking-remaining-budget-for-the-current-month)
  - [5.13 Saving the data](#saving-the-data)
  - [5.14 Editing the data file](#editing-the-data-file)
- [6 FAQ](#6-faq)
- [7 Command Summary](#7-command-summary)
- [8 Credits](#8-credits)

## 1 Introduction

Thank you for downloading **BudgetBaby**! Do you wonder where all your money went during your school semester? Are you struggling with managing all your financial expenses? Trying to stick to a monthly budget to save money? Fret not, our intuitive and easy to use application will help you track and manage your finances from today.

**BudgetBaby** is a **budget and expenses tracking desktop app for University students and/or those who are looking to better manage their finances**. Our main differentiating features include:

- Simple budget and financial record tracking and management
- A progress bar to track the amount you have spent
- An organized side panel of your financial records
- Update and manage everything using command entry box
- Sorted categorical spending data

We have designed a beautiful user interface (UI) that is pleasing yet engaging. You will be able to quickly learn how to use it because we designed it with the end-user in mind. It is **optimized for use via a Command Line Interface** (CLI) so that you can enter and update your financial records or budget quickly by typing in commands while still having the benefits of a Graphical User Interface (GUI). The GUI provides unique features that help you visualize your spending habits

We look forward to you building financial literacy and positive habits while using our app. If you find any issues with the application or have any new features you would like implement, feel free to contact us on our [GitHub page](https://github.com/AY2021S2-CS2103T-W14-2/tp).

Now what are you waiting for? Head on over to the [quick start](#3-quick-start) section now.

## 2 About

This document aims to guide new users like you in learning how to use BudgetBaby. It provides a reference of all features and their intended usage within our application. More experienced users may wish to head to the [command summary](#7-command-summary)section to refresh their memory of commands.

## 3 Quick start

This section helps you quickly get the application running and started.

1. Ensure that you have Java `11` or above installed on your Computer.

2. You can download the latest `budgetbaby.jar` [here](https://github.com/AY2021S2-CS2103T-W14-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for the BudgetBaby application.

4. Double-click the file to start the app. You should see a GUI similar to the one below appear in a few seconds. Note
   that the app contains some sample data for you when it starts that may differ from what is shown below.<br>

   <img src="images/Ui.png" width="600px">

## 4 UI Overview

<img src="images/Ui_Components.png" width="600px">

The main GUI window consists of UI components that work together to help you manage and track your finances. This section serves to help you understand how each of this UI components work and what they should display when you use our features.

These components include:

1. [Menu Bar](#41-menu-bar)
2. [Budget Display](#42-budget-display)
3. [Financial Record List](#43-financial-record-list)
4. [Command Box](#44-command-box)
5. [Result Display](#45-result-display)
6. [Status Bar](#46-status-bar)

### 4.1 Menu Bar

The `Menu Bar` contains drop-down menus which provides you access to common useful functions.
These functions include:

- [Exit](#exiting-the-program-:-`exit`) - Exits the program
- `Toggling CLI's visibility` - Shows/Hides the [Command Box](#44-command-box) and [Result Display](#45-result-display)
- [Category Statistics](#viewing-top-5-categories-spent-for-the-current-month) - Opens the Category statistics window
- [Month Statistics](#viewing-budget-for-the-current-month) - Opens the Month statistics window
- [Help](#viewing-help-:-`help`) - Opens the Help window

### 4.2 Budget Display

The `Budget Display` interactively display the details of a given month.
You get to see the given month's budget in the form of a text and a progress bar. On top of that, there is a 'All categories' section where all the categories of the given month is being shown in <strong>alphabetical order</strong>.

### 4.3 Financial Record List

The `Financial Record List` interactively display all the financial records of a given month.
Each record in the list contain details like description, amount, timestamp as well as tagged categories.

### 4.4 Command Box

The `Command Box` is where you can supply your command inputs. The application will processs these inputs and make changes to your data when necessary. The available command inputs (format and examples) can be found in the [Features](#features) section.

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:**<br>

- You may press the `UP` and `DOWN` arrow keys to access past command inputs.

</div>

### 4.5 Result Display

The `Result Display` interactively display results of a given command so you can identify its details and check whether the given command was executed successfully.

### 4.6 Status Bar

The `Status Bar` displays the location of the data file being used by the application.
To find out more about how the data file is being managed, please refer to [Saving the data](#saving-the-data) and [Editing the data file](#editing-the-data-file)

## 5 Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are arguments which must be supplied by you when you type in a command.<br>
  For example, in the command `add-fr d/FR_DESCRIPTION a/FR_AMOUNT`, `FR_DESCRIPTION` and `FR_AMOUNT` are arguments
  which must be supplied by you. An example of supplied arguments look like this `add-fr d/Lunch a/10`.

- You may choose to omit items in square brackets as they are optional.<br>
  For example, with the command `add-fr d/FR_DESCRIPTION a/FR_AMOUNT [t/DATE] [c/CATEGORY]…`, you may omit the category and
  use `add-fr d/Lunch a/10 t/01-01-2021 c/food` or `add-fr d/Lunch a/10`.

- For items suffixed with `…`​ you may repeat it multiple times within the command.<br>
  For example, with the command `add-fr d/FR_DESCRIPTION a/FR_AMOUNT [t/DATE] [c/CATEGORY]…`, you may repeat `[c/FR_CATEGORY]…​` multiple
  times with `c/food`, `c/transportation`, `c/shopping` etc.

- You may key in arguments in any order.<br>
  For example, you can use `d/FR_DESCRIPTION a/FR_AMOUNT` or `a/FR_AMOUNT d/FR_DESCRIPTION` as both are acceptable.

- If an argument is expected only once in the command, but you specify multiple arguments, only the last occurrence of
  the argument is used.<br>
  For example, if you specify `d/Lunch d/Dinner`, only `d/Dinner` will be used.

- If you key in extraneous arguments for commands that do not take in arguments (such as `reset-filter`, `help` and `exit`),
  they will be ignored.<br>
  For example, if you specify `reset-filter 123`, it will be interpreted as `reset-filter`.

- If you key in extraneous spaces before or after an argument, these spaces will be trimmed.
  If you key in multiple spaces in the middle of an argument that takes in text (such as `FR_DESCRIPTION` and `CATEGORY`),
  these multiple spaces will be compressed into one space.<br>
  For example, if you specify `add-fr d/ bubble tea a/10.0 c/ food `,
  it will be interpreted as `add-fr d/bubble tea a/10.0 c/food`.

</div>

### 5.1 Viewing help : `help`

If this is your first time opening BudgetBaby, or if you have forgotten what commands you can use, you may utilise the `help`
command to access the help page for a detailed guide on using the application.

<img src="images/helpMessage.png" width="600px">

Format: `help`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:**<br>

You may use the `F5` hotkey to open up the Help window.

</div>

### 5.2 Adding a financial record : `add-fr`

If you just spent some money and want to record it, you may use the `add-fr` command to add this expenditure as a financial record in BudgetBaby.

<img src="images/features/add-fr.png" width="1200px">

Format: `add-fr d/FR_DESCRIPTION a/FR_AMOUNT [t/DATE] [c/CATEGORY]…`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:** <br>

- You must replace `FR_DESCRIPTION` with the description of your financial record. The description should not exceed 100 characters and it should contain special character "/".
- You must replace `FR_AMOUNT` with a **positive number** containing up to **two decimal places**. The upper limit for `FR_AMOUNT`is 1,000,000.
- If you key in additional decimal places for `FR_AMOUNT`, it will be rounded to two decimal places, and the value after rounding
  must be within the valid range (between 0 to 1,000,000). For example, `0.001` will be rejected, as it will be rounded to `0.00`(not positive).
- You may include more than 1 `CATEGORY`, but each of it should not exceed 20 characters
- You may replace `Date` following the format `DD-MM-YYYY` and it must be a date between 01-01-1970 and 31-12-2100. (e.g. 31-12-2020)
- In the above format for the date `DD-MM-YYYY`, `DD` is the numeric value of the date, `MM` is the numeric value of the month
  and `YYYY` is the numeric value of the year. You must use a date number `DD` with 2 digits, a month number `MM` with 2 digits,
  and a year number `YYYY` with 4 digits.

</div>

Examples:

- `add-fr d/Lunch a/10`
- `add-fr d/Movie a/13.50 t/01-03-2021 c/Entertainment c/Friends`

### 5.3 Deleting a financial record : `delete-fr`

If you mistyped a financial record or added a financial record incorrectly, you may use the `delete-fr` command to delete a financial record from BudgetBaby.

<img src="images/features/delete-fr.png" width="1200px">

Format: `delete-fr FR_INDEX [FR_INDEX]`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:**<br>

- You may include more than 1 `FR_INDEX` after the command to delete multiple financial records.
- You must replace `FR_INDEX` with the index of the financial record you want to delete.
- `FR_INDEX` refers to the index number shown in the displayed financial record list.
- `FR_INDEX` must be a **positive integer** (e.g. 1, 2, 3, …​)

</div>

Examples:

- `delete-fr 10` deletes the 10th financial record displaying on the screen

### 5.4 Editing a financial record : `edit-fr`

If you mistyped the details of a financial record, instead of deleting it completely, you may use the `edit-fr` command to edit an existing financial record in BudgetBaby.

<img src="images/features/edit-fr.png" width="1200px">

Format: `edit-fr FR_INDEX [d/FR_DESCRIPTION] [a/FR_AMOUNT] [t/DATE] [c/CATEGORY]...`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:**<br>

- You must replace `FR_INDEX` with the index of the financial record you want to delete.
- `FR_INDEX` refers to the index number shown in the displayed financial record list.
- `FR_INDEX` must be a **positive integer** (e.g. 1, 2, 3, …​)
- Existing values will be overwritten by the input values. For example, if you include `c/` in your `èdit-fr`
  command, all existing categories will be replaced by the new input categories.
- Use `c/` (leave `CATEGORY` blank) to completely remove existing categories of a record.
- For the specifications on `FR_DESCRIPTION`, `FR_AMOUNT`, `DATE` and `CATEGORY`, please refer to `add-fr` command.

</div>

Examples:

- `edit-fr 1 d/new description` changes the description of the first financial record displaying on the screen to `new description`.

### 5.5 Setting monthly budget : `set-bg`

If you would like to change your budget to something different from what it currently is in BudgetBaby, you may use the `set-bg` command to set the budget for the current month and the following 12 months. Note that the budget you have set for previous months cannot be updated and you can only update the current month's budget.

<img src="images/features/set-bg.png" width="1200px">

Format: `set-bg BG_AMOUNT`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:**<br>

- You must replace `BG_AMOUNT` with a **positive number** containing up to **two decimal places**.
- You must replace `BG_AMOUNT` with a **positive number** containing up to **two decimal places**. The upper limit for `BG_AMOUNT`is 1,000,000.
- If you key in additional decimal places for `BG_AMOUNT`, it will be rounded to two decimal places, and the value after rounding
must be within the valid range (between 0 to 1,000,000).
For example, `0.001` will be rejected, as it will be rounded to `0.00`(not positive).
</div>

Examples:

- `set-bg 100`
- `set-bg 1300.50`

### 5.6 Viewing a specific month : `view-month`

If you would like to take a look at how you managed your budget for a particular month, you may use the `view-month` command to display the data associated with a specific month.

Data associated with a month includes:

- The budget set by you for that month
- Your total expense for that month
- The amount you have spent in relation to the total budget for that month (in term of percentage)
- Your list of financial records for that month

<img src="images/features/view-month.png" width="1200px">

Format: `view-month MM-YYYY`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:**<br>

- The month you input must follow the format `MM-YYYY` (e.g. 09-2020)
- `MM` is the numeric value of the month and `YYYY` is the numeric value of the year.
You must use a month number `MM` with 2 digits, and a year number `YYYY` with 4 digits.
</div>

Examples:

- `view-month 01-2021`

### 5.7 Finding financial records : `find-fr`

If you would like to find financial records matching description, amount and/or categories, you may use the `find-fr` command to filter the list of financial records based on specified fields.

<img src="images/features/find-fr.png" width="1200px">

Format: `find-fr [d/FR_DESCRIPTION] [a/FR_AMOUNT] [c/FR_CATEGORY]`

**:bulb: Tips:**

- `d/FR_DESCRIPTION`, `a/FR_AMOUNT` and `c/FR_CATEGORY` are optional fields, but the command expects at least 1 field present.
- `c/FR_CATEGORY` accepts multiple categories (i.e. `c/Food c/Picnic c/Family`)
- The function only filters records that satisfies all the fields provided.
- For the specifications on `FR_DESCRIPTION`, `FR_AMOUNT` and `CATEGORY`, please refer to `add-fr` command.

Examples:

- `find-fr d/Lunch a/10 c/Food c/Family`

### 5.8 Resetting filters on financial records : `reset-filter`

If you currently have a filter set, such as a particular month or category, and you would like to go back to the original view of the application, you may use the `reset-filter` command to reset all filters on financial records.

<img src="images/features/reset-filter.png" width="1200px">

Format: `reset-filter`

### 5.9 Undoing commands : `undo`

<div markdown="block" class="alert alert-info">

**:information_source: Info:**<br>

You can only undo `add-fr`, `delete-fr` and `set-bg` operations.

</div>

If you made a mistake from executing any commands, `undo` allows you to revert multiple latest changes made.

<img src="images/features/undo.png" width="1200px">

Format: `undo`

### 5.10 Redoing commands : `redo`

<div markdown="block" class="alert alert-info">

**:information_source: Info:**<br>

You can only redo undone `add-fr`, `delete-fr` and `set-bg` operations.

</div>

If you made a mistake from executing any commands, `redo` allows you to advance multiple latest changes made.
It works in the opposite way from `undo`,

<img src="images/features/redo.png" width="1200px">

Format: `redo`

### 5.11 Exiting the program : `exit`

After you are done with editing details in BudgetBaby, you may use the `exit` command to exit the program.

Format: `exit`

### 5.12 Viewing top 5 categories spent for the current month

<img src="images/features/category-stats.png" width="1200px">

The UI displays the top 5 categories that were spent on a particular month.

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:**<br>

You may use the `F6` hotkey to open up the Month statistics window.

</div>

### 5.13 Viewing budget for the current month

<img src="images/features/monthly-stats.png" width="1200px">

The UI displays the current monthly budget that has been set automatically.

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:**<br>

You may use the `F7` hotkey to open up the Month statistics window.

</div>

### 5.14 Checking remaining budget for the current month

The UI displays and updates the remaining budget for the current month automatically.

### 5.15 Saving the data

You do not have to worry about saving your data manually. It will be saved to your hard disk automatically after any you
enter any command that updates data.

### 5.16 Editing the data file

Data is saved as an encoded JSON file to the following location `[JAR file location]/data/budgetbaby.json`. You are
<strong>NOT RECOMMENDED</strong> to edit the file in any way.

**:exclamation: Caution:**
If your changes to the data file invalidates its format, BudgetBaby will discard all your data and start with an
empty data file at the next run.

---

## 6 FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**:

1. You can follow the [quick start](#3-quick-start) guide to install the app to your other computer.
2. On your current computer send the data file located at `[JAR file location]/data/budgetbaby.json` to your other
   computer.
3. Overwrite the empty data file on your other computer with the data file you sent over.

---

## 7 Command summary

| Action                        | Format, Examples                                                                                                    |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| **Add a Financial Record**    | `add-fr d/FR_DESCRIPTION a/FR_AMOUNT [t/DATE] [c/CATEGORY]…` <br> e.g., `add-fr d/Lunch a/10`                       |
| **Delete a Financial Record** | `delete-fr FR_INDEX` <br> e.g., `delete-fr 10`                                                                      |
| **Edit a Financial Record**   | `edit-fr FR_INDEX [d/FR_DESCRIPTION] [a/FR_AMOUNT] [t/DATE] [c/CATEGORY]…` <br> e.g., `edit-fr 1 d/new description` |
| **Set Monthly Budget**        | `set-bg BG_AMOUNT​` <br> e.g., `set-bg 100`                                                                      |
| **View a Particular Month**   | `view-month MM-YYYY` <br> e.g., `view-month 01-2021`                                                                |
| **Find a Financial Record**   | `find-fr c/FR_CATEGORY` <br> e.g., `find-fr c/food`                                                                 |
| **Reset Filter**              | `reset-filter`                                                                                                      |
| **Undo**                      | `undo`                                                                                                              |
| **Redo**                      | `redo`                                                                                                              |
| **Help**                      | `help`                                                                                                              |
| **Exit**                      | `exit`                                                                                                              |

## 8 Credits

This user guide format has been adapted
from [AddressBook Level 3 User Guide](https://github.com/nus-cs2103-AY1920S2/addressbook-level3/blob/master/docs/UserGuide.adoc)
