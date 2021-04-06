---
layout: page
title: Yoong Yi En's Project Portfolio Page
---

## Project: Vax@NUS

Vax@NUS is a one stop management app to efficiently track and schedule COVID-19 vaccinations for NUS students. It is a desktop app optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to view statistics of vaccinations.
  * What it does: allows the user to view percentage of students vaccinated for a faculty/school residence or whole of NUS.
  * Justification: This feature is important to the product as the user would like to know the status and progress of the vaccination program. The higher the percentage, the safer the campus.  
  * Highlights: This enhancement encompassed many smaller commands and strong understanding of inheritance was required to implement it properly. The implementation too was challenging as it required working with many other classes and fields.

* **New Feature**: Added the ability to view statistics of vaccination appointments.
  * What it does: allows the user to view number of upcoming and past appointments within 1 week from the current day (E.g. If today is Monday, number of upcoming appointments until Sunday will be shown.)
  * Justification: This feature is important to the product as it enables the user to have a clear view of the progress and rate of vaccinations.
  * Highlights: This enhancement made use of the current time. Making the tests proved challenging as the testcases had to make use of the current time and change accordingly.

* **New Feature**: Added the ability to delete appointments.
  * What it does: allows the user to delete a student's existing appointment by the student's matriculation number.
  * Justification: This feature improves the product significantly because a student could cancel their appointment and the app should provide a convenient way for the user to remove the student's appointment.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=yienyoong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&tabOpen=true&tabType=authorship&since=2021-01-11&checkedFileTypes=docs~functional-code~test-code~other&zFR=false&tabAuthor=yienyoong&tabRepo=AY2021S2-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Modified delete student command to delete student by matriculation number. (Pull requests [\#91](https://github.com/AY2021S2-CS2103T-W10-4/tp/pull/91))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete`, `stats`, `deleteAppt` and `statsAppt`.
    * Added all screenshots on the User Guide.
  * Developer Guide:
    * Added implementation details of the `delete` feature. [\#126](https://github.com/AY2021S2-CS2103T-W10-4/tp/pull/126)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#139](https://github.com/AY2021S2-CS2103T-W10-4/tp/pull/139)
  
  
