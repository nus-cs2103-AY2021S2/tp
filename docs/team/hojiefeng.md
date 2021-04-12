---
layout: page
title: Jie Feng's Project Portfolio Page
---

## Project: BookCoin

BookCoin is a CLI facility management system which allows users to manage bookings through a CLI interface and presents users a structured and detailed information on facility availability.

Given below are my contributions to the project.

* **New Feature**: Implemented the classes Booking, Venue which forms the basis of this entire project

* **New Feature**: Refactored existing code in the AB3 so that we can add booking specific code onto it

* **New Feature**: Implemented `filter_booking_by_tag` command (Functionality now merged into find_booking command)
  * What it does: allows the user to find venues in the system through a multi step command with optional fields such as capacity, description, tags.

* **New Feature**: Implemented the ability to display the correct list(Person/Venue/Booking) in the UI
  * What it does: switches the display list to correctly show what the user has requested.
  * Justification: Our app manages three different lists, and the user needs to see them, so there is a need to show the user the correct one, for example, the venue list when making a booking and entering the booking name
    * Highlights: This enhancement affects existing and future multi-step commands. It significantly improves the user-friendliness of the system, as the switch is done automatically

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=hojiefeng&tabRepo=AY2021S2-CS2103-W17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Helped with git issues along the way
    * Advised on how to make the project more coherent in terms of code along the way

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage
    * Managed the classes to fit the naming of our project better
    * Implemented a better UI on top of the existing one

* **Documentation**:
    * User Guide: Fixing the user guide for more readability and effectiveness
    * Code documentation: refactored existing AB3 documentation, added, checked and edited existing documentation by other members
    * Developer Guide: Removed traces of AB3 in the code and modified the documentation and diagrams to fit our application

* **Others**:
    * Conducted active bug testing and fixed bugs

* **Community**:

* **Tools**:

