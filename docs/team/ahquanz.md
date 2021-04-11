---
layout: page
title: Li Quan's Project Portfolio Page
---

## Project: Cheese Inventory Management (CHIM)

Cheese Inventory Management (CHIM) is a desktop app for managing the contacts, inventory, and orders of a home-based
cheese making business. It is an extension from the AddressBook Level 3 project for Software Engineering (SE) students.

Given below are my contributions to the project.

* **New Feature**: Design and implementation of the initial model classes to be used for the project in v1.1.
    * What it does: Provides models for team members to use during their implementation of the different features.
    * Justification: This feature helps in the future development process since basic classes are provided and
      the architecture of the project has already been discussed and decided.
    * Highlights: The designing of the initial model classes was slightly challenging because we are starting from scratch,
      team members had different ideas of how the model classes should be designed and implemented.

* **New Feature**: Marking an order as complete in the CHIM app.
    * What it does: It allows the user of the CHIM app to mark an order as complete by a command.
      The CHIM app will update completed date for the order and assign available cheeses to the order
      to provide an indication of what available cheeses the user needs to use to fulfill the order.
    * Justification: Marking an order as complete is an essential feature for the user would have to fulfill
      the order made by his/her customer.
    * Highlights: The implementation was challenging as there was a variety of ideas proposed by team members
      regarding the representation of cheeses as this feature requires the assigning of cheeses to an order for the order to be completed.
      New testcases and modification to existing codes was required during the implementing of this feature.

* **Enhancements to project** : Modified the summary messages for `findcustomer` , `findorder`, `findcheese` , `listcustomers`,
  `listorders`, `listcheeses` to provide more useful insight of the result of the command executed.

* **Enhancements to project** : Wrote additional test cases for Edit order command.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=AhQuanz&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

* **Documentation**:
  * User Guide:
    * Added documentation for features (`done` , `clear`)
  * Developer Guide:
    * Added documentation for Model class, including Model , Customer , Cheese , Order class diagram.
    * Added documentation for the implementation of `done` command , including sequence diagram for `done` command.
    * Added documentation for Appendix section.

* **Community**:
  * PRs reviewed: [#\18](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/18)
    , [#\26](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/26)
    , [#\38](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/38)
    , [#\121](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/121)
    , [#\124](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/124)
