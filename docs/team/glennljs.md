---
layout: page
title: Glenn's Project Portfolio Page
---

## Project: PartyPlanet

PartyPlanet is a cross-platform desktop application that helps users plan birthday celebrations.
The user primarily interacts with it using a command-line interface, inside a GUI created with JavaFX.
It is written in Java with around 10k LOC.

A full list of code contribution can be found here:
[RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=ellevy&breakdown=true)

Given below are my contributions to the project.

## New Features:

- Added feature to Autocomplete edit and eEdit commands: [\#186](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/186)
  * What it does: This feature allows the user to quickly autocomplete specific prefixes when editing Persons or Events.
  * Justification: Editing of Persons and Events can be tedious, especially when trying to change fields such as remark which may be very long. Previously, users were required to retype out entire fields for minor changes. With this change, users can simply autocomplete the specified fields, saving large amounts of time and keystrokes.

- Added feature to find Persons (`find` command): [\#86](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/86)
  * What it does: This feature allows the user to find/filter persons stored in PartyPlanet based on a given input.
  * Justification: This feature improves the product significantly because given a large list of people, it will be difficult for the user to quickly and efficiently conduct actions on the intended person.

- Added feature to list/sort all Persons (`list` command): [\#86](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/86)
  * What it does: Allows the user to list out all Persons (after filtering) or sort the Person's list based on given criterias.
  * Justification: This feature improves the product significantly because it allows users to quickly sort the Person's list which would make it easier to find Persons by association.

- Added feature to search or list Tags (`tags` command) (Eventually Deprecated): [\#86](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/86)
  * What it does: Allows the user to  search or list out all Tags associated with user.
  * Justification: This feature improves the product significantly because it allows users to quickly list all tags used by users and find users based on associations.
  * Note: This feature was deprecated as it was eventually merged with the find feature by pyuxiang in PR [\#116](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/116).

### Feature enhancements

- Improved Help Window UI and Help Message: [\#297](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/297)
- Add fresh font and standardize it across themes: [\#150](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/150) & [\#191](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/191).

### Documentation
* User Guide
  * Added User Stories: [\#26](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/26)
  * Added Coming Soon Features: [\#49](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/49)
  * Add Autocomplete Documentation: [\#299](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/299)

* Developer Guide
  * Add Autocomplete Documentation: [\#171](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/171)
  * Assist with Refactoring: [\#220](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/220)

### Community
* PRs Reviewed: [\#143](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/143), [\#189](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/189), [\#198](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/198), [\#218](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/218)
