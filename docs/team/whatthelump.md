---
layout: page
title: Li Gang's Project Portfolio Page
---

## Project: Residence Tracker

ResidenceTracker (RT) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, RT can get your residence management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* Morphed Person class to Booking class.
  * What it does: Instead of having Person(s) associated with a Residence, Residences would have a list of Bookings.
  * Justification: Considering ResidenceTracker's target users would have high turnover rates for their rental properties, they would find it more useful to edit and view bookings rather than track the people making the reservations.
  * Highlights: This change meant that existing commands, many of which referenced Person, had to be updated accordingly. It required a careful weighing of which features of Person were relevant, which could be discarded, and which new features should be added. The morphing also affected various existing tests, test utilities, and necessitated new tests for the Booking class itself.

* Morphed Logic and Storage classes to reflect changes from AB3 to ResidenceTracker.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=whatthelump&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=whatthelump&tabRepo=AY2021S2-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Opening and assigning of Github issues
  * Practice of forking workflow

* **Enhancements to existing features**:
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#81](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/81), [\#108](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/108/))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addb` and `deleteb` [\#139](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/139)
    * Updated documentation for `delete` feature [\#19](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/19)
    * Removed traces of AB3 [\#56](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/56)
  * Developer Guide:
    * Updated product scope
    * Added user stories
    * Added implementation details of the `Booking` class

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#172](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/172)
  * Reported bugs and suggestions for other teams in the class ([Link](https://github.com/whatthelump/ped/issues))

