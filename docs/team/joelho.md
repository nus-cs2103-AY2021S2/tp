---
layout: page
title: Joel Ho's Project Portfolio Page
---

## Project: GreenMileageEfforts

GreenMileageEfforts (GME) is a platform that helps drivers and passengers of any IT company quickly arrange carpooling in order to lower their carbon footprint. The platform follows that of a command-line interface (CLI) such that power users that are familiar can efficiently navigate the program.

Given below are my contributions to the project.

* **New Feature**: Added the ability to drive passengers.
  * What it does: allows the user to select passengers to be driven by a driver.
  * Justification: This is a core feature of the product whereby we assign drivers to passengers

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=JoelHo&tabRepo=AY2021S2-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Augmented Person and Index to make them more testable (PR [\#57](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/57))
  * Wrote additional tests for existing features to increase coverage by 1.63% (PR [\#57](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/57)), 2.56% (PR [\#78](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/78))
  * Refactored code using Java Optionals (PR [\#225](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/225))
  * Prevent Passengers from being deleted if they are being referenced by a Pool (PR [\#132](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/132))
  * Refactored the serialisation of Drivers and Pools such that they are stored as proper JSON (PR [\#125](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/125), [\#117](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/117))
  * Refactor toModelType of Passenger to better fit SLAP (PR [\#117](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/117))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `drive` (PR [\#62](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/62) [\#32](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/32))
    * Did cosmetic tweaks to existing documentation examples: (PR [\#62](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/62))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#16](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/16),

* **Tools**:
  * Patched security vulnerabilities in nokogiri and kramdown versions
  * Added Codacy analyser to repo
  * Setup Codecov check to PRs to ensure we try and maintain testing
