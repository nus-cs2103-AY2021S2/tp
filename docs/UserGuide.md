---
layout: page
title: User Guide
---

# BudgetBaby User Guide :baby: :money_with_wings:

**BudgetBaby** is a **desktop app for setting monthly budgets and tracking expenses**. It is **optimized for use via a Command Line Interface** (CLI) so that entering and editing financial records and budgets can be done faster by typing in commands while still having the benefits of a Graphical User Interface (GUI).

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `budgetbaby.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your BudgetBaby.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   <img src="images/Ui.png" width="500px">

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`add-fr`** `d/Lunch a/10` : Add a financial record for `lunch` which cost `10` dollars.
    
   - **`delete-fr`** `3` : Deletes the 3rd financial record shown in the current list.

   - **`set-bg`** `500` : Sets the current month's budget at `500` dollars.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add-fr d/FR_DESCRIPTION a/FR_AMOUNT`, `FR_DESCRIPTION` and `FR_AMOUNT` are
  a parameters which can be used as `add-fr n/Lunch a/10`.

- Items in square brackets are optional.<br>
  e.g `add-fr d/FR_DESCRIPTION a/FR_AMOUNT [t/TAG]…` can be used as `add-fr n/Lunch a/10 t/food` or as `add-fr n/Lunch a/10`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/food`, `t/transportation`, `t/shopping` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `d/FR_DESCRIPTION a/FR_AMOUNT`, `a/FR_AMOUNT d/FR_DESCRIPTION` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/Lunch d/Dinner`, only `d/Dinner` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `view-bg` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` [coming in v1.3]

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a financial record: `add-fr`

Adds a financial record to the budget tracker.

Format: `add-fr d/FR_DESCRIPTION a/FR_AMOUNT​`

<div markdown="span" class="alert alert-primary"></div>

**:bulb: Tips:**

- Adds a financial record with the description FR_DESCRIPTION.
- The FR_AMOUNT must be a positive double (e.g. 10, 13.50).

Examples:

- `add-fr d/Lunch a/10`
- `add-fr d/Dinner a/13.50`

### Listing a month's financial records : `list` [coming in v1.3, subject to changes]

Lists a month's financial records.

Format: `list MM-YYYY`

<div markdown="span" class="alert alert-primary"></div>

**:bulb: Tips:**

- The input month must follow the format of MM-YYYY (e.g. 09-2020)
- Month number must take up 2 digits and the year number 4 digits

Examples:

- `list 02-2021`

### Viewing a financial record: `view-fr` [coming in v1.3, subject to changes]

Displays a financial record in the budget tracker.

Format: `view-fr FR_INDEX`

<div markdown="span" class="alert alert-primary"></div>

**:bulb: Tips:**

- Views the financial record at the specified `FR_INDEX`.
- The index refers to the index number shown in the displayed financial record list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list 02-2021` followed by `view-fr 10` views the 10th financial record of Feb 2021

### Deleting a financial record : `delete-fr`

Deletes a financial record from the budget tracker.

Format: `delete-fr FR_INDEX`

<div markdown="span" class="alert alert-primary"></div>

**:bulb: Tips:**

- Deletes the financial record at the specified `FR_INDEX`.
- The index refers to the index number shown in the displayed financial record list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list 02-2021` followed by `delete-fr 10` deletes the 10th financial record of Feb 2021

### Setting monthly budget : `set-bg`

Sets the budget for the current month and the following twelve months.

Format: `set-bg BG_AMOUNT`

<div markdown="span" class="alert alert-primary"></div>

**:bulb: Tips:**

- Budget amount must be a positive double (e.g. 100, 1300.50)

Examples:

- `set-bg 100`
- `set-bg 1300.50`

### Viewing budget for the current month :

The UI displays the current monthly budget that has been set automatically.

### Checking remaining budget for the current month :

The UI displays and updates the remaining budget for the current month automatically.

### Viewing a specific month : [coming in v1.3 subject to changes]

Displays data associated with a specific month.

Data associated include:
- Budget set
- Remaining budget amount
- Financial records

Format: `view MM-YYYY`

<div markdown="span" class="alert alert-primary"></div>

**:bulb: Tips:**

- The input month must follow the format of MM-YYYY (e.g. 09-2020)
- Month number must take up 2 digits and the year number 4 digits

Examples:

- `view 01-2021`

### Filtering financial records by category :

Filters financial records based on specified category.

Data associated include:
- Category to filter
- Financial records

Format: `category-filter c/FR_CATEGORY`

<div markdown="span" class="alert alert-primary"></div>

Examples:

- `category-filter c/Food`

### Resetting filters on financial records :

Resets all filters on financial records.

Data associated include:
- Financial records

Format: `reset-filter`

<div markdown="span" class="alert alert-primary"></div>

Examples:

- `reset-filter`

### Exiting the program : `exit` [coming in v1.3]

Exits the program.

Format: `exit`

### Saving the data

BudgetBaby data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

BudgetBaby data are saved as a JSON file `[JAR file location]/data/budgetbaby.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning"></div>

**:exclamation: Caution:**
If your changes to the data file makes its format invalid, BudgetBaby will discard all data and start with an empty data file at the next run.

</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BudgetBaby home folder.

---

## Command summary

| Action                              | Format, Examples                                                           |
| ----------------------------------- | -----------------------------------------------------------------------    |
| **Add a Financial Record**          | `add-fr d/FR_DESCRIPTION a/FR_AMOUNT​` <br> e.g., `add-fr d/Lunch a/10` |
| **List a Month's Financial Record** | `list MM-YYYY​` <br> e.g., `list 02-2021`                               |
| **View a Financial Record**         | `view-fr FR_INDEX` <br> e.g., `view-fr 10`                                 |
| **Delete a Financial Record**       | `delete-fr FR_INDEX` <br> e.g., `delete-fr 10`                             |
| **Set Monthly Budget**              | `set-bg BG_AMOUNT​` <br> e.g., `set-bg 100`                             |
| **View Monthly Budget**             | `view-bg​`                                                              |
| **View a Month**                    | `view MM-YYYY` <br> e.g., `view 01-2021`                                   |
| **Help**                            | `help`                                                                     |
| **Exit**                            | `exit`                                                                     |

## Credits

This user guide format has been adapted from [addressbook level 3 User Guide](https://github.com/nus-cs2103-AY1920S2/addressbook-level3/blob/master/docs/UserGuide.adoc)
