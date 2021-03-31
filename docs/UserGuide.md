---
layout: page
title: User Guide
---

Cheese Inventory Management (CHIM) is a desktop app for managing the contacts, inventory, and orders of a home-based
cheese making business.

The app is optimized for use via a Command Line Interface (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, CHIM can get your management tasks done
faster than traditional GUI apps.

* Table of Content
  - [Quick start](#quick-start)
  - [Features](#features)
  - [FAQ](#faq)
  - [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addcustomer n/CUSTOMER_NAME`, `CUSTOMER_NAME` is a parameter that is to be used
  as `addcustomer n/John Doe ...`.

* Items in square brackets are optional.<br>
  e.g. `listcheeses [t/CHEESE_TYPE]` can be used as `listcheeses` or as `listcheeses t/Brie`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/CUSTOMER_NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/CUSTOMER_NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listcustomers` and `exit`) will be
  ignored.<br>
  e.g. if the command specifies `listcustomers 123`, it will be interpreted as `listcustomers`.

</div>

### Adding an order: `addorder`

Adds an order to CHIM.

Format: `addorder t/CHEESE_TYPE q/QUANTITY p/PHONE_NUMBER [d/ORDER_DATE]`
* The specified `QUANTITY` must be a positive integer.
* The specified `PHONE_NUMBER` must belong to an existing user.

Example: `addorder t/Parmesan q/2 p/65555555`

### Adding a cheese: `addcheese`

Adds a cheese to CHIM.

Format: `addcheese t/CHEESE_TYPE q/QUANTITY [d/MANUFACTURE_DATE] [m/MATURITY_DATE] [d/EXPIRY_DATE]`
* The specified `QUANTITY` must be a positive integer.

Example: `addcheese t/Parmesan q/2`

### Adding a customer: `addcustomer`

Adds a customer to CHIM.

Format: `addcustomer n/CUSTOMER_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/tags...]`

Example: `addcustomer n/John Doe p/65555555 e/johndoe@gmail.com a/Blk 436 Serangoon Gardens St 26 #01-01`

### Editing an order: `editorder`

Edits an existing uncompleted order in CHIM.

Format: `editorder INDEX [t/CHEESE_TYPE] [q/QUANTITY] [p/PHONE_NUMBER] [d/ORDER_DATE]`
* The specified `INDEX` and `QUANTITY` must be a positive integer.
* The specified `PHONE_NUMBER` must belong to an existing user.

Example: `editorder 2 t/Parmesan q/2 p/65555555`

### Editing a cheese: `editcheese`

Edits an existing unassigned cheese in CHIM.

Format: `editcheese INDEX [t/CHEESE_TYPE] [d/MANUFACTURE_DATE] [m/MATURITY_DATE] [d/EXPIRY_DATE]`
* The specified `INDEX` must be a positive integer.

Example: `editcheese 1 t/Parmesan d/2021-03-12`

### Editing a customer: `editcustomer`

Edits an existing customer in CHIM.

Format: `editcustomer [n/CUSTOMER_NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/tags...]`

Example: `addcustomer 4 n/Jane Lim p/65558888`

### Delete an order: `deleteorder`

Deletes a specific order from CHIM, identified by its index.

If the order is complete and has cheeses assigned to it, those cheeses will be deleted by CHIM.

Format: `deleteorder ORDER_INDEX`
* Deletes the order at the specified `ORDER_INDEX`. The index must be a positive integer.

Example: `deleteorder 2`
* Deletes the second order in the list of orders.

### Delete cheese from inventory: `deletecheese`

Deletes a specified cheese from the inventory.

The cheese must be unassigned.

Format: `deletecheese CHEESE_INDEX`
* Deletes the cheese at the specified `CHEESE_INDEX`. The index must be a positive integer.

Example: `deletecheese 2`

### Delete customer: `deletecustomer`

Deletes a customer from the list of customers, using their phone number as identifier.

If the customer has orders, their orders will be deleted by CHIM.

Format: `deletecustomer p/PHONE_NUMBER`

Example: `deletecustomer p/65555555`


### Listing orders: `listorders`

Displays all orders in CHIM.

Format: `listorders`

### Listing cheese: `listcheeses`

Displays all cheeses in CHIM.

Format: `listcheeses`


### Listing customers: `listcustomers`

Displays the customers in CHIM.

Format: `listcustomers`

### Marking an order as complete: `done`

Marks an incomplete order as complete from CHIM, identified by its index.

Format: `done ORDER_INDEX`
* Marks the order at the specified `ORDER_INDEX`.
* The `ORDER_INDEX` must be a positive integer.

Example: `done 2`
* Mark the second order in the list of orders as complete.

### Finding customers: `findcustomer`

Searches for a customer in CHIM.

Format: `findcustomer [n/NAME_KEYWORDS...] [p/PHONE_KEYWORDS...] [e/EMAIL_KEYWORDS...] [a/ADDRESS_KEYWORDS...] `
* Search is case-insensitive, e.g. Betty will match betty.
* Search will search by given keywords as prefix, e.g. Bet will match Betty.

Examples:
* `findcustomer n/Betty`
* `findcustomer a/Clementi Road`

### Finding cheeses: `findcheese`

Searches for particular cheeses in CHIM.

Format: `findcheese [t/CHEESE_TYPE_KEYWORDS...] [s/ASSIGNMENT_STATUS]`
* Search is case-insensitive, e.g. Brie will match brie.
* Assignment status parameter must be either 'assigned' or 'unassigned'.

Examples:
* `findcheese t/Brie Feta`
* `findcheese s/unassigned`
* `findcheese t/Gouda s/assigned`

### Finding orders: `findorder`

Searches for particular orders in CHIM.

Format: `findorder [t/CHEESE_TYPE_KEYWORDS...] [n/CUSTOMER_NAME_KEYWORDS] [p/CUSTOMER_PHONE_KEYWORDS] [s/COMPLETION_STATUS]`
* Search is case-insensitive, e.g. Brie will match brie.
* Assignment status parameter must be either 'complete' or 'incomplete'.

Examples:
* `findorder t/Brie Feta s/incomplete`
* `findorder n/Alice s/incomplete`
* `findorder p/92280919`

### Exiting the program: `exit`

Exits the program.

Format: `exit`


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CHIM home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Order** | `addorder t/CHEESE_TYPE q/QUANTITY p/PHONE_NUMBER` <br> e.g. `addorder t/Parmesan q/2 p/65555555`
**Add Cheese** | `addcheese t/CHEESE_TYPE q/QUANTITY` <br> e.g. `addcheese t/CHEESE_TYPE q/QUANTITY`
**Add Customer** | `addcustomer n/CUSTOMER_NAME p/PHONE_NUMBER a/ADDRESS` <br> e.g. `addcustomer n/John Doe p/65555555 a/Blk 436 Serangoon Gardens St 26 #01-01`
**Delete Order** | `deleteorder ORDER_INDEX` <br> e.g. `deleteorder 2`
**Delete Cheese** | `deletecheese CHEESE_INDEX` <br> Example: `deletecheese 5`
**Delete Customer**| `deletecustomer p/PHONE_NUMBER` <br> Example: `deletecustomer p/65555555`
**List Orders** | `listorder`
**List Cheese** | `listcheese` <br> e.g. `listcheese`
**List Customer** | `listcustomer`
**Mark As Done** | `done INDEX` <br> e.g. `done 2`
**Find Customer** | `findcustomer [n/NAME_KEYWORDS...] [p/PHONE_KEYWORDS...] [e/EMAIL_KEYWORDS...] [a/ADDRESS_KEYWORDS...]` <br> e.g. `findcustomer n/Betty`
**Find Cheese** | `findcheese [t/CHEESE_TYPE_KEYWORDS...] [s/ASSIGNMENT_STATUS]` <br> e.g. `findcheese t/Brie Feta s/unassigned`
**Find Order** | `findorder [t/CHEESE_TYPE_KEYWORDS...] [n/CUSTOMER_NAME_KEYWORDS] [p/CUSTOMER_PHONE_KEYWORDS] [s/COMPLETION_STATUS]`  <br> e.g. `findorder t/gouda s/complete
**Exit** | `exit`
