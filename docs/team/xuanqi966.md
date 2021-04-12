---
layout: page
title: Xuanqi's Project Portfolio Page
---

## Project: BookCoin

BookCoin is a CLI facility management system which allows users to type in commands to manage bookings and presents users a structured and detailed information on facility availability.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=xuanqi966&tabRepo=AY2021S2-CS2103-W17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added the ability to delete previously added bookings.
    * What it does: allows the user to delete any previous added bookings one at a time.
    * Justification: This feature improves the product by allowing users to delete a booking permanently when the booking is no longer valid.

* **New Feature**: Added multi-step prompting structure for commands and implemented the structure on `add_booking`
    * What it does: allows the user to input information step by step
    * Justification: A lot of information input is needed to create a booking, making the `add_booking` process troublesome and confusing.
      This feature breaks down the input process into several steps, improving the input experience for users.
    * Highlights: This enhancement affects existing and future multi-step commands. It required an in-depth analysis of design alternatives to ensure subsequent implementations for multi-step commands
        can adopt the same structure. The implementation too was challenging as it required complex management of states between different classes while ensuring OOP and design principles are maintained.

* **New Feature**: Added the ability to find bookings by booker, venue, or date.
    * What it does: allows the user to filter any previously added bookings by their attributes.
    * Justification: This feature improves the product significantly because a user can efficiently search for a booking with the specified field.

* **Enhancements to existing features**:
    * Wrote tests for new features (pull request [#142](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/142))


* **Documentation**:
    * User Guide:
        * Wrote command examples
        * Updated command descriptions
    * Developer Guide:
        * Wrote uses cases
        * Wrote user stories

* **Community**:
    * Set up GitHub team org and repo
    * Contributed to UI design mockup
    * Contributed to maintaining of issue tracker and release management
