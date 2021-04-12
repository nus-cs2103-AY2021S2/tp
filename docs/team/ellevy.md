---
layout: page
title: Yvelle's Project Portfolio Page
---

## Project: PartyPlanet

PartyPlanet is a cross-platform desktop application that helps users plan birthday celebrations.
The user primarily interacts with it using a command-line interface, inside a GUI created with JavaFX.
It is written in Java with around 10k LOC.

A full list of code contribution can be found here:
[RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=ellevy&breakdown=true)

Given below are my contributions to the project.

### New features

- Added feature to delete event (`edelete` command):
  [\#122](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/122)
  * What it does: Allows the user to delete events that they do not want to store. Users are able to delete multiple
    events in a single command.
  * Justification: This feature improves the product significantly because a user might want to clear the clutter
    on the app and remove some events that he no longer wants to store.
  * Credits: Code used is referenced from the `delete` command [Gary's PR: \#82](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/82)
 
- Added storage for event:
  [\#123](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/123)
  * What it does: Allows the user to read from existing events stored in the data file and store all events back
    into the data file.
  * Justification: This feature improves the product significantly because a user will want to be able to store and
    update all changes to the events done in PartyPlanet in a data file and be able to read in the file the next
    time they open PartyPlanet. 
  * Credits: Code used is referenced from the AddressBook Storage from AB3

- Added feature to search for event by name and detail (`elist` command):
  [\#156](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/156)
  * What it does: Allows the user to search for events in the event list by the event name and detail. The user is
    able to do partial search (default) or exact search, and all search (default) or any search. Partial search
    finds partial matches, exact search finds exact matches. All search requires all specified criteria to be met
    while any search requires at least 1 specified criteria to be met.
  * Justification: This feature improves the product significantly because a user might have many events stored in
    the event list and might want to filter out events by event name or by details of the event.
  * Credits: Code used is referenced from the `list` command [Justin's PR: \#116](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/116)

### Feature enhancements

- Changed compulsory fields to optional fields for `add` command:
  [\#80](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/80)
- Added optional `remark` field for contacts:
  [\#110](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/110)
- Wrote tests for event storage:
  [\#148](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/148)
- More detailed result message for `elist` and `list`:
  [\#196](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/196)
- Fixed bug (throw error) in `list` and `elist` commands when flags `--any` or `--exact` are specified without any
  other predicates to filter the list by:
  [\#201](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/201)
- Fixed bug for `undo` and `redo`:
  [\#203](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/203) (Credits: Code used is taken from [Gary](https://github.com/garyljj))

### Documentation

- UserGuide:
  * Changed introduction and application name to suit PartyPlanet: [\#95](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/95)
 
- DeveloperGuide:
  * Added NFRs: [\#38](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/38)
  * Changed DeveloperGuide from AddressBook to PartyPlanet: [\#95](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/95)
  * Updated UML diagrams for `Logic` and `Model` components to match with PartyPlanet implementation [\#146](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/146)
  * Added sequence diagrams for `add` command [\#146](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/146)

### Community

- PRs reviewed (with non-trivial comments):
  [\#116](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/116),
  [\#128](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/128),
  [\#190](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/190)
 
