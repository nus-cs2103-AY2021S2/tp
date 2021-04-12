---
layout: page
title: Weiming's Project Portfolio Page
---

## Project: BookCoin

BookCoin is a CLI facility management system which allows users to manage bookings through a CLI interface and presents users a structured and detailed information on facility availability.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=ming-00&tabRepo=AY2021S2-CS2103-W17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Implemented `add_venue` command and refactored it during iterations to include new fields and features.
    * What it does: allows the user to add venues one at a time into the system through a multi step command with optional fields such as capacity, description, tags.
    * Justification: implementation of `add_venue` as a multi-step command enhances usability and makes it more convenient for users to input a new venue because of the number of fields needed, which can be tedious to type in one go and also difficult to remember. 
  
* **New Feature**: Implemented and managed storage function for Venue class
  * What it does: automatically updates and saves changes upon addition, edit and deletion of a venue

* **Project management**:
    * Tested release `v1.3` to ensure that it works as intended

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage from various Venue, Parser, Tag, Booking and Storage classes (e.g. [#48](https://github.com/AY2021S2-CS2103-W17-3/tp/commit/7ea8136296ad5dbc60de9440a44b3cc3490e179f), [#238](https://github.com/AY2021S2-CS2103-W17-3/tp/commit/ce330cf23a7f7abbed1b86c0272b7ab48b2626ca))
    * Made initial modifications to layout of Storage class for BookCoin classes
    * Managed modifications in implementing tag class to better suit BookCoin's purposes
    * Refactored other Venue commands when modifications are added into Venue class (e.g. [#133](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/133/files))
    * Conducted active bug fixing for Venue class (e.g. [#252](https://github.com/AY2021S2-CS2103-W17-3/tp/commit/9eab70b8ffe26d8f87a118b8a1e04e69db5539fd))

* **Documentation**:
    * User Guide: formatted and polished user guide for better readability, added app constraints and design justifications, wrote and revised commands listed in user guide alongside testing of the app.
    * Code documentation: refactored existing AB3 documentation, added, checked and edited existing documentation by other members
    * Developer Guide: 

* **Others**:
    * Designed UI mockup
    * Conducted active bug testing and fixed bugs for implementation by other team members

* **Community**:
    * [Reported bugs](https://github.com/ming-00/ped/issues) during PE dry run
  
