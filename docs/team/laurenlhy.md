---
layout: page
title: Lauren Lee's Project Portfolio Page
---

## Project: Cheese Inventory Management (CHIM)

Cheese Inventory Management (CHIM) is a desktop app for managing the contacts, inventory, and orders of a home-based
cheese making business. It is an extension from the AddressBook Level 3 project for Software Engineering (SE) students.

Given below are my contributions to the project.

* **Changes to existing code**: Update existing code from AB3 to be more relevant to CHIM.
  * Updated all mentions of "Person" to "Customer" within the code and test cases.
  * Justification: The CHIM app is created for maintaining customers of a business. The code would also
align with the commands we used, e.g. "addcustomer".
  * Highlights: Implementation was slightly challenging as it required changes to a significant amount of existing code.

* **New Feature**: Delete customers, orders and cheeses from the CHIM app.
  * What it does: Customers, orders and cheeses can be deleted individually.
Delete cascading was implemented such that deleting a customer will delete any orders they have
and deleting any orders will delete any cheeses assigned to them.
  * Justification: Deleting customers, orders and cheeses is an essential feature for the user to manage their contacts, inventory and orders.
Delete cascading helps the user delete related information easily. For instance, once a customer is deleted,
all orders and cheeses related to that customer is deleted as well.
  * Highlights: The implementation was challenging and required changes to existing code for
deleting a contact by phone number instead of by list index. New classes and testcases were also added for the commands
to delete cheeses and orders.

* **Enhancements to project**:
  * Wrote additional test cases for Edit Cheese Commands.

* **Documentation**:
  * User Guide:
    * Added documentation for Delete commands (`deletecustomer`, `deletecheese`, `deleteorder`).
    * Reorganised user guide to group commands together by Customer, Cheese and Order, instead of their command words.
    * Added more specific format requirements for the commands.
  * Developer Guide:
    * Added documentation for Logic class, including Logic class diagram and sequence diagram for `deletecheese`.
    * Added documentation for implementation of Delete commands, including sequence diagram for `deletecustomer`.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#125](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/125), [\#127](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/127)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=laurenlhy&tabRepo=AY2021S2-CS2103-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
