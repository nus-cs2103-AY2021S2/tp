---
layout: page
title: Ruoxin's Project Portfolio Page
---

## Project: PartyPlanet

PartyPlanet is a cross-platform desktop application that helps users plan birthday celebrations.
The user primarily interacts with it using a command-line interface, inside a GUI created with JavaFX.
It is written in Java with around 10k LOC.

A full list of code contribution can be found here:
[RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=zhengruoxin&breakdown=true)

Given below are my contributions to the project.

### New features

- Added feature to optionally store birthdays of contacts:
  [\#88](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/88)
  * What it does: Allows users to record birthdays of contacts.
  * Justification: This feature improves the product significantly because a welfare IC will need to keep track of members' birthdays to plan celebrations for them.
    It is made optional so that users can also store vendor information whose birthday is irrelevant.
  * Credits: Code used is referenced from the AddressBook Phone from AB3

- Added feature to remove tags from person contacts (`edit` command, `--remove` flag):
  [\#128](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/128)
  * What it does: Allows the user to mass remove tags from all contacts. Users are able to delete multiple tags in a single command.
  * Justification: Manually removing irrelevant tags from all affected contacts can be tedious.
    Previously, users need to edit each target contact by re-entering their remaining tags without the one to be removed.
    With this feature, they can simply enter the target tags in a single command to remove it from all contacts, saving large amounts of time and keystrokes.
  * Credits: Code used is referenced from the `delete` command [Gary's PR: \#82](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/82)

- Added feature to edit events (`eedit` command):
  [\#153](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/153)
  * What it does: Allows the user to make changes to a stored event's name, date and remark.
  * Justification: This feature improves the product significantly because a user will want to be able to
    correct mistakes and update previously entered events without needing to delete and add a new one every time.
  * Credits: Code used is referenced from the AddressBook Edit from AB3

- Added feature to incorporate tags display on list:
  [\#156](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/156)
  * What it does: Reflects all the tags and number of contacts associated with each tag in the unfiltered and filtered lists.
  * Justification: This feature improves the product significantly because it gives the users a quick summary view of all tags which they can use for subsequent searching.
  * Credits: Code used is referenced from the deprecated `tags` command [Glenn's PR: \#86](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/86)

### Feature enhancements

- Added pastel pink party theme and party hat logo for GUI:
  [\#155](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/155)
- Block edit action when both index and remove flag are present:
  [\#188](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/188)
- Restrict characters limit for names and tags:
  [\#303](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/303)
- Wrote tests for edit event:
  [\#320](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/320)
- Wrote tests for removing tags:
  [\#326](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/326)
- Fix bug in display reverting to main list view after successful edit action:
  [\#294](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/294)

### Documentation

- UserGuide:
  * Replace AB3's commands and syntax with PartyPlanet's [\#20](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/20)
  * Added documentation for events commands and undo. Updated edit and list documentations with new feature enhancements [\#178](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/178)
  * Added image and libraries credits [\#195](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/195)
  * Reorganised documentation of list [\#319](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/319)

- DeveloperGuide:
  * Added Edit Remove documentation with sequence diagram [\#179](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/179),
    [\#219](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/42https://github.com/AY2021S2-CS2103-W16-3/tp/pull/219)
  * Assisted with refactoring: [commit 73947a7](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/73947a7f2a5db30bb91a469cb3ac3f10ac258164)

### Community

- PRs reviewed (with non-trivial comments):
  [\#119](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/119),
  [\#149](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/149),
  [\#156](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/156)
