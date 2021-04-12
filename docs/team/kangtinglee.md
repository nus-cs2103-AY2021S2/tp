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
  ([#31](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/31
  "#31"), [#33](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/33
  "#33"), [#42](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/42 "#42"))
    * What it does: Allows the deletion of different objects in the
      application (contacts, menu items, inventory, orders).
    * Justification: Enable users to remove items that are no longer
      relevant to their operations.

* **New Feature**: Cascading deletion of objects ([#119](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/119 "#119"), [#80](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/80 "#80"))
    * What is does: Whenever an object is deleted, related objects are
      also removed from the application.
    * Justification: Automatically remove objects that are related so
      that the user does not need to manually remove
      outdated/irrelevant objects. For instance, when a contact is
      deleted, all orders made by them are also removed. Another
      example is when a menu item is deleted, all orders that use the
      item are canceled automatically since they cannot be fulfilled
      anymore.
    * Highlights: Added a warning whenever there are pending orders
      that will be affected by this cascading deletion behavior and
      require the user to append a `-f` flag in order for the command
      to go through, acknowledging this intended effect. This was
      quite non-trivial to implement because of the many layers of
      hierarchy within the system. For example, when deleting
      inventory items, then the corresponding menu items that use
      these ingredients need to be accounted for and then finally, the
      orders that depend on the availability of these menu items need
      to be accounted for.

* **New Feature**: Data links for menu item edits ([#104](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/104 "#104"), [#112](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/122 "#112"), [#130](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/130 "#130"), [#134](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/134 "#134"))
    * What is does: Checks that the edits to menu items (in terms of
      the ingredients needed for them) do not result in the user being
      unable to fulfill pending orders.
    * Justification: At the point of order additions, ingredients are
      accounted for and decremented from the stock. Hence, when orders
      are edited, there is also a need to check that as a result of
      this edit operation, there is still sufficient inventory to fulfill
      the pending orders.
    * Highlights: This is non-trivial to do because of the immutable
      nature of objects within the source code. When an edit is
      processed, all orders that utilize the dish needs to be
      collated, their new ingredient requirements calculated and
      checked, and then this new edited `Dish` object needs to be
      propagated throughout the order database. This was also
      non-trivial because of the hierarchical nature of the
      objects. For instance, when `Ingredient` items are edited, this
      edit needs to be propagated to all menu items that use them and
      then all uncompleted orders that depend on these menu items also
      need to be edited.

* **New Feature**: Data link for order add/edit ([#138](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/138 "#138"), [#159](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/159 "#159"), [#245](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/245 "#245"))
    * What it does: Checks that orders can be fulfilled before
      adding/editing to the order list.
    * Justification: In order for the user to always be able to
      fulfill orders given their inventory, there is a need to query
      the inventory for every order that is to be added or edited to
      ensure that there is sufficient inventory of ingredients to
      complete the orders.
    * Highlights: Added new interfaces and methods in `Model` to
      support convenient querying of inventory for ability to fulfill
      the new orders.

* **Enhance Feature**: Added ability to filter menu items so that only items that can be produced are shown [#141](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/141 "#141")
    * What it does: Shows only dishes that can be produced given inventory.
    * Justification: Enables users to know what items they can produce
      currently.
    * Highlights: The default behavior of `menu list` shows all items
      in the menu. With this update, `menu list -a` displays only
      items that can be produced.

* **Documentation**:
    * User Guide:
        * Added working examples and explainations for `customer` related commands [link](https://ay2021s2-cs2103t-w15-3.github.io/tp/UserGuide.html#customer "link").
        [#145](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/145 "#145")
    * Developer Guide:
        * Added segment on data consistency describing the behavior of
          `customer delete` and `customer edit` commands [link](https://ay2021s2-cs2103t-w15-3.github.io/tp/DeveloperGuide.html#deletion-of-person-objects "link")
          ([#81](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/81
          "#81"),
          [#105](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/105/files
          "#105"))
        * Added segment on data consistency describing the behavior of
          `menu delete` and `menu edit` commands
          [link](https://ay2021s2-cs2103t-w15-3.github.io/tp/DeveloperGuide.html#deletion-of-dish-objects
          "link")
        * Added segment on data consistency describing the behavior of
          `inventory delete` command
          [link](https://ay2021s2-cs2103t-w15-3.github.io/tp/DeveloperGuide.html#deletion-of-ingredient-objects
          "link")
        * Added segment on data consistency describing the behavior of
          `order edit` command
          [link](https://ay2021s2-cs2103t-w15-3.github.io/tp/DeveloperGuide.html#editing-of-order-objects
          "link")
        * Contributed to the use cases in DG
          [link](https://ay2021s2-cs2103t-w15-3.github.io/tp/DeveloperGuide.html#use-cases
          "link")

* **Team-Based Task**:
    * Ensured that issues, tags, and milestones were properly set up
      and delegated.

* **Review/mentoring contributions**:
    * Consistently reviewed other team members' PRs for approval.
