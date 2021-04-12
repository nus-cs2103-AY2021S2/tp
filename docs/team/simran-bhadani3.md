---
layout: page
title: Bhadani Simran's Project Portfolio Page
---

## Project: CakeCollate

CakeCollate is a desktop application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added an IndexList class
  * What it does: Allows parsing of multiple Indexes input by the user.
  * Justification: This allows users to key in multiple indexes for any command which requires an Index to be input.
  * Highlights: This feature was challenging to implement as it required quite a few changes to existing commands and tests.

* **New Feature**: Added an OrderItems model
  * What it does: An `OrderItems` model allows the creation of `OrderItem` objects and also multiple operations to be performed on them.
  * Justification: An `OrderItems` model was needed for the OrderItems table which relies on a `UniqueOrderItemList` which was added in the model.
  * Highlights: This model is a crucial part of CakeCollate and was challenging to implement as a lot of classes had to be created and tested properly.
  * Credit: Based on the CakeCollate model.

* **New Feature**: Added the `deleteItem` and `addItem` commands.
  * What it does: These commands allow the users to interact with the `OrderItems` model by enabling them to add their own order items to the order items table and also delete orders.
  * Justification: The user needs to be able to add their own order items to the order items table since they might want to predefine order items.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=simran-bhadani3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=simran-bhadani3&tabRepo=AY2021S2-CS2103T-T11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Maintained the issue tracker
    * Managed jar file creation for two releases

* **Enhancements to existing features**:
    * Modified delete command to accept multiple indexes
        * Involved creating the `IndexList` class

* **Documentation**:
    * User Guide:
        * Added documentation for the `delete`, `addItem` and `deleteItem` features. [\#122](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/112/)
        * Edited Glossary, FAQ and checked for grammatical errors. [\#248](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/248)

    * Developer Guide:
        * Added implementation details for the `addItem` feature.
        * Added implementation details for the `deleteItem` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#125](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/125), [\#232](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/232)

