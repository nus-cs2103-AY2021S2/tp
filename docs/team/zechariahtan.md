---
layout: page
title: Zechariah Tan's Project Portfolio Page
---

## Project: Green Mileage Efforts
Green Mileage Efforts - is a shared desktop application used to make finding groups of people to carpool with easier.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ListPool command feature. (Pull requests [\#227](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/227))
    * What it does: Allows the user to list all existing pools.
    * Justification: As there exists a feature to filter the pool list, the listPool command allows the user to remove all existing filters and view the full list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=zechariah&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ZechariahTan&tabRepo=AY2021S2-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs)

* **Project management**:
  * Managed releases `v1.4` (1 release) on GitHub

* **Enhancements to existing features**:
  * Model:
    * Added TripDay, TripTime, Person, Driver, and Pool classes to the model to allow for further extension of previous versions
    * Refactored existing code to fit new model format
  * Logic:
    * Added listPool command to fix feature bug where pool list cannot be refreshed to show all pools after filtering the list with findPool command
  * UI: 
    * Improved the help window to show a command summary on top of a link to the user guide.
  * Testing:
    * Added test code for TripDay, TripTime, Person, and Driver classes.
    * Added test code for Pool, and Unpool classes, as well as their respective parsers

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Search`[\#72]().
    * Modified examples and standardised the formatting[\#74]().
    * Added iconography formatting and introduction paragraph.
    * Added more terms to glossary.
  * Developer Guide:
    * Modified existing glossary to include terms specified in the user guide as well.
    * Modified existing use cases to include relevant information in headers.
    * Modified user stories to better match the current iteration of the product.
    * Added rationale, sequence diagram, activity diagram, and explanation for FindPool command.
    * Added Extension of Model paragraph to Appendix: Effort section.
    * Added Manual Testing instructions for FindPool command.
  * About Us:
    * Added relevant profile pictures and modified AboutUs.md to include relevant teammembers information

* **Community**:

