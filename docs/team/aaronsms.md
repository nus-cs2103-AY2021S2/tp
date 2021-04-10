---
layout: page
title: Aaron Saw's Project Portfolio Page
---

## Project: Cheese Inventory Management (CHIM)

Cheese Inventory Management (CHIM) is a desktop app for managing the contacts, inventory, and orders of a home-based
cheese making business. It is an extension from the AddressBook Level 3 project for Software Engineering (SE) students.

Given below are my contributions to the project.

* **New Feature**: Add customers, orders and cheeses from the CHIM app.
  * What it does: Customers, orders and cheeses can be added into the app.
Customers added into the system are identified by their phone number. Checks are done to ensure that no two customers
can share the same phone numbers. Invalid inputs by users are carefully handled accordingly. For example, the manufacture
date of a cheese should be valid and be earlier than its maturity date.
  * Justification: Adding customers, orders and cheeses is a core feature for the user to manage their contacts, inventory and orders.
Phone number is used as the primary key constraint instead of the name because we believe the business owner would want to account for
cases where two customers share the same name. Out of all other fields, phone number is the next best choice as we assume different
customers should own different phone numbers.
  * Highlights: The implementation was challenging and required changes to existing code for
deleting a contact by phone number instead of by list index. New classes and testcases were also added for the commands
to delete cheeses and orders.

* **New Feature**: Edit customers, orders and cheeses from the CHIM app.
  * What it does: Existing customers, orders and cheeses can be edited.
Edit commands were implemented to respect the exiting constraints exiting in the app. For example, no two customers
can share the same phone numbers. Orders that are already marked as completed are protected from modifications.
Cheeses that are already assigned to certain orders are kept as archieve only and are not modifiable.
  * Justification: Editing customers, orders and cheeses is an essential feature to improve the user experience of using the app.
Edit commands help the users to rectify and make changes to the existing data. Otherwise, the users would have to do separate
delete and add commands in order to fix errors.
  * Highlights: The implementation was challenging and required changes to existing code for
editing a contact to conform to the phone number constraint rather than the name field.
New classes and testcases were also added for the commands to edit cheeses and orders.
More work has done to ensure the validity of relationship between the cheeses and orders.

* **Enhancements to project**: Wrote extensive test cases for Add commands and EditCustomer command.

* **Documentation**:
  * User Guide:
    * Added documentation for Add commands (`addcustomer`, `addcheese`, `addorder`).
    * Added documentation for Edit commands (`editcustomer`, `editcheese`, `editorder`).
  * Developer Guide:
    * Added documentation for architectural design, with an updated Architecture Diagram.
    * Added documentation for implementation of Edit commands, including Sequence Diagram for `editorder`.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=aaronsms&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=undefined&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=aaronsms&tabRepo=AY2021S2-CS2103-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
