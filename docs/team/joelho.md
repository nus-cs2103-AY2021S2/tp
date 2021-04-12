---
layout: page
title: Joel Ho's Project Portfolio Page
---

# Project: GreenMileageEfforts

GreenMileageEfforts (GME) is a platform that helps drivers and passengers of any IT company quickly arrange carpooling in order to lower their carbon footprint. The platform follows that of a command-line interface (CLI) such that power users that are familiar can efficiently navigate the program.

Given below are my contributions to the project.

* **New Feature**: Added the ability to `drive` passengers.
  * What it does: allows the user to select passengers to be driven by a driver.
  * Justification: This is a core feature of the product whereby we assign drivers to passengers
  * This feature has since been refactored to the `pool` command.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=JoelHo&tabRepo=AY2021S2-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Setup team repo and organisation
  * Designed icon and branding for product
  * Ensured PRs and issues are linked to the correct author and milestone for proper tracking
  * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Augmented Person and Index to make them more testable (PR [\#57](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/57))
  * Wrote additional tests for existing features to increase coverage by 1.63% (PR [\#57](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/57)), 2.56% (PR [\#78](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/78)),
    2.81% (PR [\#286](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/286))
  * Refactored code using Java Optionals (PR [\#225](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/225))
  * Prevent Passengers from being deleted if they are being referenced by a Pool (PR [\#132](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/132))
  * Fix Index being parsed incorrectly, resulting in wrong error message (PR [\#269](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/269))
  * Refactored the serialisation of Drivers and Pools such that they are stored as proper JSON (PR [\#125](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/125), [\#117](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/117))
  * Refactor toModelType of Passenger to better fit SLAP (PR [\#117](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/117))
  * Add sanity checking for JSON read to ensure manually edited files have no errors (PR [\#256](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/256), [\#298](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/298))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#16](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/16), [\#133](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/133)
  * Substantial number of code related PRs merged, with code quality comments taken offline (and sadly not on github)

* **Tools**:
  * Patched security vulnerabilities in nokogiri and kramdown versions
  * Added [Codacy](https://app.codacy.com/gh/AY2021S2-CS2103T-W10-1/tp/dashboard) static analysis to repo
  * Setup Codecov check to PRs to ensure we attempt to maintain coverage
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `drive` (PR [\#62](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/62) [\#32](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/32))
    * Did cosmetic tweaks to existing documentation examples: (PR [\#62](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/62))
    * Change layout to use Github pages functionality (PR [\#32](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/32))
    * General cosmetic and formatting issues
  

## **Excerpts**


### User Guide

--------------------------------------------------------------------------------------------------------------------

### 3.1.1 User Interface

The various sections of the User Interface are described as in the picture below.

![Ui_labelled](../images/Ui_labelled.png)

--------------------------------------------------------------------------------------------------------------------

### 3.1.6 Editing the data file

GME data is saved as a JSON file `[JAR file location]/data/GMEdata.json`. Advanced users are welcome to update data directly by editing that data file.

* Ensure that the following constraints are met if you decide to edit the file:
  * There are no duplicate `Passengers`
  * There are no duplicate `Pools`
  * Only one `Pool` can reference a `Passenger`
  * The `Passenger` referenced in `Pool` can be found in the `Passenger` object
  * The `Passenger` in a `Pool` must have the same Trip Day as the `Pool`

<div markdown="block" class="alert alert-warning">

**:warning: GME will replace the JSON file with a new one if it cannot read the file**<br>
* Make a backup before any changes
* Edit at your own risk

</div>

--------------------------------------------------------------------------------------------------------------------
**Format:** <code>delete INDEX [<a title="These extra parameters are optional.">INDEX INDEX...</a>]</code>

**Format:** <code>pool n/DRIVER_NAME p/DRIVER_PHONE d/TRIPDAY t/TRIPTIME c/INDEX [<a title="These extra parameters are optional.">c/INDEX c/INDEX ...</a>] [tag/TAG]</code>

--------------------------------------------------------------------------------------------------------------------

## Developer Guide