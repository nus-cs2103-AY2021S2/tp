---
layout: page
title: Daniel Lau's Project Portfolio Page
---

## Project: Cheese Inventory Management (CHIM)

Cheese Inventory Management (CHIM) is a desktop app for managing the contacts, inventory, and orders of a home-based
cheese making business. It is an extension from the AddressBook Level 3 project for Software Engineering (SE) students.

Given below are my contributions to the project.

* **Changes from existing code**: Update to New Model Diagram
    * What was done: Restructure the models to incorporate both `Cheese` and `Order` classes.
    * Justification: Ensures that CHIM will be able to store `Cheese` and `Order` objects.
    * Highlights: The enhancement required a lot of consideration on the model architecture, for example
      whether the data should be normalized (by using IDs) when storing relations.

* **Changes from existing code**: Update to `find` Command
    * What was done: Find commands was modified to include filtering by more parameters.
    * Justification: Ensures that CHIM will be able to handle more robust queries to the database of items.
    * Highlights: The functionality to make general queries were added into the system, which allows for
      future extension of the `find` command for different types of objects such as `Cheese`.

* **Enhancements to existing features**:
    * Added model validation to ensure all objects in CHIM are in a valid state [\#39]()

* **Documentation**:
    * User Guide:
        * Added documentation for the features `list`, `find` and `exit` [\#4]()
    * Developer Guide:
        * Added implementation details of the `find` command feature. [\#51]()

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#20](), [\#28](), [\#41]()

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=w16-2&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=daniellau88&tabRepo=AY2021S2-CS2103-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

[Coming Soon]
