---
layout: page
title: User Guide
---

# BudgetBaby User Guide :baby: :money_with_wings:

**BudgetBaby** is a **budget and expenses tracking desktop app for University students and/or those who are looking to better manage their finances**. It is **optimized for use via a Command Line Interface** (CLI) so that entering and editing financial records and budgets can be done faster by typing in commands while still having the benefits of a Graphical User Interface (GUI).

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help : `help`](#viewing-help)
  - [Adding a financial record : `add-fr`](#adding-a-financial-record)
  - [Listing a month's financial records : `list`](#listing-a-month's-financial-records)
  - [Viewing a financial record : `view-fr`](#viewing-a-financial-record)
  - [Deleting a financial record : `delete-fr`](#deleting-a-financial-record)
  - [Setting monthly budget : `set-bg`](#setting-monthly-budget)
  - [Viewing budget for the current month](#viewing-budget-for-the-current-month)
  - [Checking remaining budget for the current month](#checking-remaining-budget-for-the-current-month)
  - [Viewing a specific month : `view-month`](#viewing-a-specific-month)
  - [Finding a financial record : `find-fr`](#finding-a-financial record)
  - [Resetting filters on financial records : `reset-filter`](#resetting-filters-on-financial-records)
  - [Exiting the program : `exit`](#exiting-the-program)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
  - [Archiving data files](#archiving-data-files)
- [FAQ](#faq)
- [Command Summary](#command-summary)
- [Credits](#credits)

## Quick start

1. Ensure that you have Java `11` or above installed on your Computer.

2. You can download the latest `budgetbaby.jar` [here](https://github.com/AY2021S2-CS2103T-W14-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for the BudgetBaby application.

4. Double-click the file to start the app. You should see a GUI similar to the one below appear in a few seconds. Note that the app contains some sample data for you when it starts that may differ from what is shown below.<br>

   <img src="images/Ui.png" width="600px">

5. You can type a command in the command box and press the enter key on your keyboard to execute it. For example, typing the **`help`** command and pressing the enter key will open the help window.<br>
   Some example commands you can try:

   - **`add-fr`** `d/Lunch a/10` : Add a financial record for `lunch` which cost `10` dollars.

   - **`delete-fr`** `3` : Deletes the 3rd financial record shown in the current list.

   - **`set-bg`** `500` : Sets the current month's budget at `500` dollars.

   - **`view-month`** `01-2021` : Displays the financial records and budget of the month `January 2021`.

   - **`exit`** : Exits the app.

6. You may refer to the [features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are arguments which must be supplied by you when you type in a command.<br>
  For example, in the command `add-fr d/FR_DESCRIPTION a/FR_AMOUNT`, `FR_DESCRIPTION` and `FR_AMOUNT` are arguments which must be supplied by you. An example of supplied arguments look like this `add-fr n/Lunch a/10`.

- You may choose to omit items in square brackets as they are optional.<br>
  For example, with the command `add-fr d/FR_DESCRIPTION a/FR_AMOUNT [t/TAG]…`, you may omit the tag and use `add-fr n/Lunch a/10 t/food` or `add-fr n/Lunch a/10`.

- For items suffixed with `…`​ you may repeat it multiple times within the command.<br>
  For example, with the command `add-fr d/FR_DESCRIPTION a/FR_AMOUNT [t/TAG]…`, you may repeat `[t/TAG]…​` multiple times with `t/food`, `t/transportation`, `t/shopping` etc.

- You may key in arguments in any order.<br>
  For example, you can use `d/FR_DESCRIPTION a/FR_AMOUNT` or `a/FR_AMOUNT d/FR_DESCRIPTION` as both are acceptable.

- If an argument is expected only once in the command but you specify multiple arguments, only the last occurrence of the argument is used.<br>
  For example, if you specify `d/Lunch d/Dinner`, only `d/Dinner` will be used.

- If you key in extraneous arguments for commands that do not take in arguments (such as `help`, `view-bg` and `exit`), they will be ignored.<br>
  For example, if you specify `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` [coming in v1.3]

You can use this command to access the help page for detailed information on using the UG.

<img src="images/helpMessage.png" width="600px">

Format: `help`

### Adding a financial record: `add-fr`

You can use this command to add a financial record to the budget tracker.

Format: `add-fr d/FR_DESCRIPTION a/FR_AMOUNT`

<div markdown="span" class="alert alert-primary">

**:bulb: Tips:**

- You must replace `FR_DESCRIPTION` with the description of your financial record.
- You must replace `FR_AMOUNT` with a **positive integer** up to **two decimal places**.

</div>

Examples:

- `add-fr d/Lunch a/10`
- `add-fr d/Dinner a/13.50`

### Deleting a financial record : `delete-fr`

You can use this command to delete a financial record from the budget tracker.

Format: `delete-fr FR_INDEX`

<div markdown="span" class="alert alert-primary">

**:bulb: Tips:**

- You must replace `FR_INDEX` with the index of the finacial record you want to delete.
- `FR_INDEX` refers to the index number shown in the displayed financial record list.
- `FR_INDEX` **must be a positive integer** (e.g. 1, 2, 3, …​)

</div>

Examples:

- `list 02-2021` followed by `delete-fr 10` deletes the 10th financial record of Feb 2021

### Setting monthly budget : `set-bg`

You can use this command to set the budget for the current month and the following twelve months.

Format: `set-bg BG_AMOUNT`

<div markdown="span" class="alert alert-primary">

**:bulb: Tips:**

- You must replace `BG_AMOUNT` with a **positive integer** up to **two decimal places**.

</div>

Examples:

- `set-bg 100`
- `set-bg 1300.50`

### Viewing budget for the current month :

The UI will display the current monthly budget set by you automatically. If you have not set a budget it displays a default budget of $1000.

### Checking remaining budget for the current month :

The UI will display and update your remaining budget for the current month automatically whenever you update your list of financial records.

### Viewing a specific month : [coming in v1.3 subject to changes]

You can use this command to display the data associated with a specific month.

Data associated with a month include:

- The budget set by you for the month
- Your remaining budget for the month
- Your list of finacial records for the month

Format: `view MM-YYYY`

<div markdown="span" class="alert alert-primary">

**:bulb: Tips:**

- The month you input must follow the format `MM-YYYY` (e.g. 09-2020)
- `MM` is the month number and `YYYY` is the year number
- You must use a month number with 2 digits and the year number with 4 digits

</div>

Examples:

- `view 01-2021`

### Filtering financial records by a category :

You can use this command to filter the list of financial records based on a specified category.

Data associated include:

- Category to filter
- Financial records

Format: `category-filter c/FR_CATEGORY`

Examples:

- `category-filter c/Food`

### Resetting filters on financial records :

You can use this command to reset all filters on financial records.

Data associated include:

- Financial records

Format: `reset-filter`

Examples:

- `reset-filter`

### Exiting the program : `exit` [coming in v1.3]

You can use this command to exit the program.

Format: `exit`

### Saving the data

You do not have to worry about saving your data manually. It will be saved to your hard disk automatically after any you enter any command that updates data.

### Editing the data file

Data is saved as a JSON file to the following location `[JAR file location]/data/budgetbaby.json`.
If you are an advanced user, feel free to update data directly by editing that file.

**:exclamation: Caution:**
If your changes to the data file makes invalidates its format, BudgetBaby will discard all your data and start with an empty data file at the next run.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**:

1. You can follow the [quick start](#quick-start) guide to install the app to your other computer.
2. On your current computer send the data file located at `[JAR file location]/data/budgetbaby.json` to your other computer.
3. Overwrite the empty data file on your other computer with the data file you sent over.

---

## Command summary

| Action                              | Format, Examples                                                        |
| ----------------------------------- | ----------------------------------------------------------------------- |
| **Add a Financial Record**          | `add-fr d/FR_DESCRIPTION a/FR_AMOUNT​` <br> e.g., `add-fr d/Lunch a/10` |
| **List a Month's Financial Record** | `list MM-YYYY​` <br> e.g., `list 02-2021`                               |
| **View a Financial Record**         | `view-fr FR_INDEX` <br> e.g., `view-fr 10`                              |
| **Delete a Financial Record**       | `delete-fr FR_INDEX` <br> e.g., `delete-fr 10`                          |
| **Set Monthly Budget**              | `set-bg BG_AMOUNT​` <br> e.g., `set-bg 100`                             |
| **View Monthly Budget**             | `view-bg​`                                                              |
| **View a Month**                    | `view MM-YYYY` <br> e.g., `view 01-2021`                                |
| **Help**                            | `help`                                                                  |
| **Exit**                            | `exit`                                                                  |

## Credits

This user guide format has been adapted from [AddressBook Level 3 User Guide](https://github.com/nus-cs2103-AY1920S2/addressbook-level3/blob/master/docs/UserGuide.adoc)
