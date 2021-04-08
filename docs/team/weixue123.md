---
layout: page
title: Wei Xue Ng's Project Portfolio Page
---

## Project: Cheese Inventory Management (CHIM)

Cheese Inventory Management (CHIM) is a desktop app for managing the contacts, inventory, and orders of a home-based
cheese making business. It is an extension from the AddressBook Level 3 project for Software Engineering (SE) students.

Given below are my contributions to the project

* **New Feature**: Added the functionality (the `listcheeses` command) for users to list all the cheeses in CHIM.
    * What it does: Shows on the user-interface an indexed list of all the cheeses, along with their properties, in
      CHIM.
    * Justification: The indexes shown on the UI allow the users to refer to specific cheeses when executing
      the `editcheese` and `deletecheese` commands.

* **New Feature**: Added the functionality (the `listorders` command) for users to list all the orders in CHIM.
    * What it does: Shows on the user-interface an indexed list of all the orders, along with their properties, in CHIM.
    * Justification: The indexes shown on the UI allow the users to refer to specific orders when executing
      the `editorder` and `deleteorder` commands.

* **New Feature**: Added the functionality (the `findcheese` command) for users to search for specific cheeses in CHIM.
    * What it does: Lets users filter the cheeses list so that only cheeses with certain specified attributes are shown
      on the UI. For example, a user can run the search command so that only Brie cheeses remain on the UI.
    * Justification: With this feature, users can quickly & conveniently look up what cheeses the inventory holds.

* **New Feature**: Added the functionality (the `findorder` command) for users to search for specific orders in CHIM.
    * What it does: Lets users filter the orders list so that only orders with certain specified attributes are shown on
      the UI. For example, a user can run the search command so that only orders by customers named Bob remain on the
      UI.
    * Justification: With this feature, users can quickly & conveniently look up what completed or outstanding orders he
      or she has.

* **New Feature**: Automatic toggling of list panel on the user-interface.
    * What it does: Whenever a command is executed, the UI's list panel automatically switches between showing the
      customers list, the orders list, and the cheeses list - the list shown depends on the command ran. If the command
      executed was a customer-related command, e.g. `editcustomer`, then the UI switches to showing the customers list.
    * Justification: Without the need to manually toggle between the different views, CHIM becomes more convenient to
      use. We also want to emphasize that the automatic switching was made to be as intuitive as possible. With that
      said, this feature ultimately aims at improving user-experience.

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=weixue123&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=weixue123&tabRepo=AY2021S2-CS2103-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.1` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Improved parsing of emails (Pull request [\#116](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/116))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `listcheeses`, `listorders`, `findcheeses`, `findorders`.
    * Developer Guide:
        * Updated the documentation to reflect the new design of the `Ui` component.
        * Added implementation details for the automatic toggling of UI's list panels.

* **Community**:
    * PRs reviewed: [\#14](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/14)
      , [\#31](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/31)
      , [\#37](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/37)
      , [\#39](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/39)
      , [\#43](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/43)
      , [\#49](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/49)
      , [\#51](https://github.com/AY2021S2-CS2103-W16-2/tp/pull/51)
