---
layout: page
title: Jaryl Loh's Project Portfolio Page
---

## Project: BudgetBaby

BudgetBaby is a desktop budget tracking application used to manage finances. The app supports CRUD functions and seeks to improve the financial literacy of University students in Singapore.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added `find-fr` command to that allows users to find financial records based on specified fields.
  * What it does: Allows the user to specify fields such as `DESCRIPTION`, `AMOUNT` and/or `CATEGORY`. The financial records list will be filtered according to these inputs and only the financial records that match all specified fields will be displayed.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement followed from the initial implementation of `category-filter` which only allowed the filtering of financial records by category. With `find-fr`, users are provided the flexibility of finding financial records based on their own preference.

* **New Feature**: Added `remove-filter` command that allows users to view the original list of financial records.
  * What it does: Allows the user to allows users to remove all filters set on the financial records list (e.g. after using `find-fr`)
  * Justification: This feature provides an option to undo the `find-fr` command completely, regardless of how many times the financial records list has been filtered. This is an essential feature that complements `find-fr` after users have completed viewing the filtered list of financial records.

* **Project Management**:
  * Applied critical thinking skills and provided valuable feedback to implemented or to-be implemented features
  * Assisted with task management and equal distribution of workload amongst the team
  * Contributed to the opening of several major issues on the project's Github repository

* **Enhancements to existing features**:
  * Replaced `category-filter` to `find-fr` as a general feature that caters to all fields (Pull request [\#108](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/108))
  * Enhanced the output of `find-fr` on results with no matching records to display an empty list (Pull request [\#201][https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/201])
  * Added maximum character limit for category to prevent overflow (Pull request [\#142](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/142), [\#201](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/201))
  * Wrote additional tests for the following features: `category`, `find-fr`, `edit-fr` and `delete-fr` to increase coverage from 0% to 100% (Pull request [\#206](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/206))

* **Documentation**:
  * User Guide:
    * Added description of the purpose of the User Guide and what BudgetBaby is about (Pull request [\#118](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/118))
    * Added the table of contents and provided links for quick re-direction to specific parts of the document (Pull request [\#122](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/122))
    * Added documentation for the features `find-fr` and `reset-filter` (Pull request [\#108](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/108))
    
  * Developer Guide:
    * Added implementation details of the `find-fr` and `reset-filter` feature. (Pull request [\#108](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/108))
    * Added documentation for storage component (Pull request [\#92](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/92))
    * Added v1.3 and v1.4 full table of user stories
    * Added use cases for all features

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#86](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/86)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/173#issuecomment-783923667), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/227))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/207), [2](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/208), [3](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/215), [4](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/214), [5](https://github.com/AY2021S2-CS2103T-W15-3/tp/issues/212))

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo
