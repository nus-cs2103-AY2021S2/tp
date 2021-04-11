---
layout: page
title: Lee Kang Ting Marcus's Project Portfolio Page
---

## Project: JJIMY

JJIMY is a restaurant management app for managing food orders, ingredient inventory, etc.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 21.3 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=kangtinglee&tabRepo=AY2021S2-CS2103T-W15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Delete command for all new components
    * What it does: Allows the deletion of different objects in the
      application (contacts, menu items, inventory, orders).
    * Justification: Enable users to remove items that are no longer
      relevant to their operations.

* **New Feature**: Cascading deletion of customer data
    * What it does: Whenever a customer contact is deleted, all orders
      made by the customer is also deleted automatically.
    * Justification: Ensures that customer information is properly
      deleted throughout the entire application. Without this link,
      there are two consequences. First, there will be orders
      belonging to customers who no longer exist in the contact list,
      which is redundant. Second, the customers' contact information
      still exist attached to `Order` objects, meaning that their
      information isn't really removed, which can be a data privacy
      issue for the user.
    * Highlights: Whenever a customer is to be deleted and has
      pending/uncompleted orders, a warning is displayed to the user
      and requires them to enter the same command with a `-f` flag to
      acknowledge that they are removing orders that are yet to be
      compelted. This ensures that the user never cancels an order
      unknowingly while the customer still expects it to be fulfilled,
      which can lead to customer disatisfaction.

* **New Feature**: Cascading deletion of menu items/dishes [#119](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/119 "#119")
    * What is does: When inventory items are removed, all dishes that
      utilize it are also delted from the menu.
    * Justification: This cascading delete feature enables the user to
      remove all dishes that he/she can no longer prepare since the
      ingredient has been removed from the inventory permanently in
      one command instead of having to locate these dishes and delete
      them seperately.
    * Highlights: Similar to the cascading delete command, a `-f` flag
      is needed if menu items will be deleted. This ensures that the
      user acknowledges the deletion and will not be blindsided by the
      effects of this cascading deletion.

* **New Feature**: Data consistency for edit commands ([#104](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/104 "#104"))
    * What is does: Changes every instance of an object throughout the
      application whenver it is edited.
    * Justification: Since all objects are immutable and each
      component holds an immutable copy of other objects, editing a
      piece of information is not propagated through the
      application. Hence, it is important that all objects that
      describe the same item (contact, inventory, menu item, order,
      etc) are updated whenever it is edited.
    * Highlights: New interfaces and methods added in order to easily
      aggregate/locate all relevant objects that need to be updated.

* **New Feature**: Delete command for all new components [#138](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/138 "#138")
    * What it does: Checks that orders can be fulfilled before adding
      to the order list.
    * Justification: Warns users that they do not have sufficient
      inventory to produce the order they are logging which is
      important so that users can tell straight away whether they can
      fulfil an order or not.
    * Highlights: Added methods and interfaces to enable quering the
      inventory with a list of items and quantities easily.

* **Enhance Feature**: Added ability to filter menu items so that only items that can be produced are shown [#141](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/141 "#141")
    * What it does: Shows only dishes that can be produced given inventory.
    * Justification: Enables users to know what items they can produce
      currently.
    * Highlights: The default behavior of `menu list` shows all items
      in the menu. With this update, `menu list -a` displays only
      items that can be produced.

* **Documentation**:
    * User Guide:
        * Added working examples and explainations for `customer` related commands.
        [#145](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/145 "#145")
    * Developer Guide:
        * Added segment on data consistency during deletions and
          provided sequence diagram for cascading delete command.
          ([#81](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/81 "#81"), [#105](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/105/files "#105"))
        * Added segment on data consistency between orders placed and
          inventory which describes how the two are linked and
          automatically synced.
          ([#81](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/81 "#81"))

* **Team-Based Task**:
    * Ensured that issues and milestones were properly set up and
      delegated.

* **Review/mentoring contributions**:
    * Consistently reviewed other team members' PRs for approval.
