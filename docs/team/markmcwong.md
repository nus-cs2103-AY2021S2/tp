---
layout: page
title: Wong Man Chun's Project Portfolio Page
---

## Project: Green Mileage Efforts

Green Mileage Efforts (GME) is an efficient carpooling management solution designed to help corporations reduce their 
carbon footprint. The GME system allows for the simple creation and management of weekly carpooling groups of employees 
looking to carpool to and from their office. These pools of employees can then carpool from the office 
regularly on the specified days and times every week.

Given below are my contributions to the project.

* **New Feature**: Added the findPool command feature (Pull requests [\#126](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/126), [\#216](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/216))
  * What it does: allows the user to find the pools in the GME system by the names of passengers in the pool
  * Justification: This feature improves the product significantly because a user can easily find the pools they desired to join, leave or see the details in combination with other commands
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=markmcwong&tabRepo=AY2021S2-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * GUI [\#73](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/73), [\#107](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/107), [\#284](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/284):
     * Updated the GUI to be responsive with split panel
     * Updated the GUI with new color scheme and card design 
  * Wrote additional tests for existing features, classes and predicates to increase coverage:
  [\#218: +2.78% for coverage](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/218), [\#107: +1.04%](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/107), etc)
  * Refactored magic literals used in tests: [\#266](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/266)
  
* **Documentation**:
  * User Guide:
    * Added documentation for the feature `findPool`
    * Fixed errors and inconsistencies found during PE-D ([\#217](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/217))
  * Developer Guide:
    * Drafted initial version of User Stories
    * Added Use Cases and Non-Functional Requirements
    * Updated UML Class Diagrams for `Ui`, `Model` and `Storage`
    * Added UML Sequence Diagram for the feature `listPool` 
    * Created issues and corrected inconsistencies such as different font case and wording ([\#267](https://github.com/AY2021S2-CS2103T-W10-1/tp/issues/267), [\#246](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/246))

* **Community**:
  * PRs reviewed (with non-trivial review comments): ([\#248](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/248),
  [\#234](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/234))
  * Reported [12 Bugs](https://github.com/markmcwong/ped/issues) for team AY2021S2-CS2103T-W13-3.

* **Tools**:
  * Integrated a third party library (testfx) to the project for GUI-testing
  * Configured the project to be able to run in headless-mode for GUI-testing on Github with Travis-CIs
