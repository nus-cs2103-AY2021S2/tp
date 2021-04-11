---
layout: page
title: How Yu Hin Chester's Project Portfolio Page
---

## Project: FriendDex

FriendDex is a relationship management tool built to help offload the tedious mental task of
manually keeping track of relationships in our heads. It is a Java application where users interact
with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add/delete special dates to a person
  * Justification: This feature allows users to keep a record of special dates which recur annually
  in FriendDex. Users will be reminded of their special dates on the Upcoming Events tab.

* **New Feature**: Added the Details Panel UI component
  * What it does: It is a panel on the right of the UI, which is used for displaying additional
  information. It displays a "tab" and the tab can be toggled to display different kinds of
  information.
  * Justification: This feature allows the displaying of non-critical data on the screen only when
  necessary. It currently supports 3 tabs - Upcoming Events, Streaks and Person Details.

* **New Feature**: Added the ability to toggle the "tab" on the Details Panel via commands
  * What it does: Users can now toggle the toggle the tab displayed on the Details Panel via the use
  of commands.
  * Justification: This enhances the previous feature by allowing developers to easily create
  additional tabs in the future. They simply have to create a new UI component for their tab. And to
  toggle to their new tab they either extend the the existing `view` command or create a new a
  command to do so.
  * Highlights: Initially the Details Panel was just a fixed panel displaying the upcoming events,
  only later did we decide to use the panel to display different kinds of information. The challenge
  was then making it easy for developers to display a new kind of data in the future. That was how I
  eventually came up with the idea of tabs, as it was one that was easy to extend and follows the
  Open-Closed principle.

* **New Feature**: Added the Upcoming Events tab
  * What it does: Displays a list of upcoming events on the Details Panel.
  * Justification: This feature allows users to see at a glance what events are coming up, such as
  birthdays or special dates, giving them an early heads up to prepare for the event.

* **New Feature**: Added the Person Details tab
  * What it does: Displays the full details of a person on the Details Panel.
  * Justification: Due to the amount of details a person can have, not all details are shown on the
  list of people. Hence, this feature allows users to see the full details of a person.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=chesterhow&tabRepo=AY2021S2-CS2103T-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Team Tasks**:
  * Set up the GitHub organisation and tp repo
  * Created the logo and favicon for FriendDex [\#248](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/248)
  * Maintained the issue tracker by adding appropriate labels/milestones and closing issues when appropriate
  * Maintained and updated screenshots in the documentation

* **Documentation**:
  * User Guide:
    * Added documentation for the `add-date`, `del-date`, `details` and `view` commands.
    * Added the Details Panel section
    * Rewrote the Quick Start section
    * Maintained quality of grammar and language

  * Developer Guide:
    * Added implementation details for Details Panel tab switching
    * Updated UI class diagram
    * Added instructions for manual testing for the commands: `add-date`, `del-date`, `details`, `edit`, and `view`

* **Review contributions**:
  * PRs reviewed (with non-trivial review comments): [\#41](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/41),  [\#61](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/61), [\#108](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/108), [\#207](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/207), [\#220](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/220)
