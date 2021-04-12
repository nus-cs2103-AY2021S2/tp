---
layout: page
title: Soorya's Project Portfolio Page
---

## Project: Residence Tracker

ResidenceTracker (RT) is a **desktop app for managing residences, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are a busy landlord renting out multiple residences and can type fast, RT can get your residence management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Adding Bookings to a Residence
  * What it does: Allows users to add booking details to an existing residence.
  * Justification: This feature is essential to the product since tracking bookings is a necessary feature of residence management that will determine when the residence needs to be cleaned or if it is available for personal use and so on.
    By allowing the user to add details of bookings such as dates, tenant name and contact details, it improves the completeness of the product for the user in managing residences.
    
* **New Feature**: Automatic Sorting of Residences
  * What it does: Allows users to view residences that need to be cleaned before residences that were already clean.
  * Justification: This feature enhances the product by allowing the users to view the residences that need the most attention first since the unclean residences would need to be cleaned.
  * Highlights: This feature was tricky to implement as the sorting of residences affects other commands that access the residences based on the index in the display list. 
    Specifically, the `status` command edited the clean status of multiple residences one after the other, so the sorting could not happen until a user command execution was completed. 
    It required some thought to ensure that the implementation did not add more dependencies while also sorting when necessary.

* **New Feature**: Reminder to View Residences with Upcoming Bookings
  * What it does: Allows users to view only the upcoming residences that have bookings in the next 7 days.
  * Justification: This feature enhances the product by allowing the users to view the residences that need the most attention and take necessary actions to ensure they are kept clean for upcoming bookings.
    Although this feature is similar to the above, this was added to give greater importance to residences with upcoming bookings as other unclean residences need not have to be cleaned as much as these do in order to be ready for tenants.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=vrsoorya&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=VRSoorya&tabRepo=AY2021S2-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.3` - `v1.4` (3 releases) on GitHub
  * Practice of forking and feature branch workflow with issues, milestones and CI/CD.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `remind` and `addb` [\#173](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/173/files)
    * Did cosmetic tweaks to existing documentation of features `add`, `edit`, `status`
  * Developer Guide:
    * Added implementation details of the `remind` feature: [\#174](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/174/files), [\#240](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/240/files)
    * Added user stories and made cosmetic tweaks to adapt the document to the new application. [\#237](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/237/files)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#146](https://github.com/AY2021S2-CS2103-T16-3/tp/pull/146)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/110))
  * Reported bugs and suggestions for other teams in the class (2 teams: [1](https://github.com/vrsoorya/ped/issues), [2](https://github.com/vrsoorya/pe/issues))
