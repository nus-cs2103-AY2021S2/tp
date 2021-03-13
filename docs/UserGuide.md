---
layout: page
title: User Guide
---

Chesse Inventory Management (CHIM) is a desktop app for managing the contacts, inventory, and orders of a home-based
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

Format: `addorder t/CHEESE_TYPE q/QUANTITY p/PHONE_NUMBER`
* The specified `QUANTITY` must be a positive integer.
* The specified `PHONE_NUMBER` must belong to an existing user.

Example: `addorder t/Parmesan q/2 p/65555555`

### Adding a cheese: `addcheese`

Adds a cheese to CHIM.

Format: `addcheese t/CHEESE_TYPE q/QUANTITY`
* The specified `QUANTITY` must be a positive integer.

Example: `addcheese t/Parmesan q/2`

### Adding a customer: `addcustomer`

Adds a customer to CHIM.

Format: `addcustomer n/CUSTOMER_NAME p/PHONE_NUMBER a/ADDRESS`

Example: `addcustomer n/John Doe p/65555555 a/Blk 436 Serangoon Gardens St 26 #01-01`

### Remove an order: `removeorder`

Removes a specific order from CHIM, identified by its index.

Format: `removeorder ORDER_INDEX`
* Removes the order at the specified `ORDER_INDEX`. The index must be a positive integer.

Example: `removeorder 2`
* Removes the second order in the list of orders.

### Remove cheese from inventory: `removecheese`

Removes some specified quantity of a specified cheese type from the inventory.

Format: `removecheese CHEESE_INDEX`
* Removes the cheese at the specified `CHEESE_INDEX`. The index must be a positive integer.

Example: `removecheese 2`

### Remove customer: `removecustomer`

Removes a customer from the list of customers, using their phone number as identifier.

Format: `removecustomer p/PHONE_NUMBER`

Example: `removecustomer p/65555555`


### Listing orders: `listorders`

Displays all incomplete orders in CHIM.

Format: `listorders`

### Listing cheese: `listcheeses`

Displays the current inventory count. If a cheese type is specified, then display the current inventory count for that particular cheese.

Format: `listcheeses [t/CHEESE_TYPE]`

Example: `listcheeses t/Mozzarella`

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

### Searching for customers: `search`

Searches for a customer in CHIM.

Format: `search n/NAME`
* Search is case-insensitive, e.g. Betty will match betty.

Examples:
* `search n/Betty`
* `search n/Robin Lee`

### Exiting the program: `exit`

Exits the program.

Format: `exit`


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Order** | `addorder t/CHEESE_TYPE q/QUANTITY p/PHONE_NUMBER` <br> e.g. `addorder t/Parmesan q/2 p/65555555`
**Add Cheese** | `addcheese t/CHEESE_TYPE q/QUANTITY` <br> e.g. `addcheese t/CHEESE_TYPE q/QUANTITY`
**Add Customer** | `addcustomer n/CUSTOMER_NAME p/PHONE_NUMBER a/ADDRESS` <br> e.g. `addcustomer n/John Doe p/65555555 a/Blk 436 Serangoon Gardens St 26 #01-01`
**Remove Order** | `removeorder ORDER_INDEX` <br> e.g. `removeorder 2`
**Remove Cheese** | `removecheese CHEESE_INDEX` <br> Example: `removecheese 5`
**Remove Customer**| `removecustomer p/PHONE_NUMBER` <br> Example: `removecustomer p/65555555`
**List Orders** | `listorders`
**List Cheeses** | `listcheeses [t/CHEESE_TYPE]` <br> e.g. `listcheeses t/Mozzarella`
**List Customers** | `listcustomers`
**Mark As Done** | `done INDEX` <br> e.g. `done 2`
**Search** | `search n/NAME` <br> e.g. `search n/Betty`
**Exit** | `exit`
