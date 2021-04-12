---
layout: page
title: Nicholas' Project Portfolio Page
---

## Project: PartyPlanet

PartyPlanet is a cross-platform desktop application that helps users plan birthday celebrations.
The user primarily interacts with it using a command-line interface, inside a GUI created with JavaFX.
It is written in Java with around 10k LOC.

A full list of code contribution can be found here:
[RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=nickyfoo&breakdown=true)

Given below are my contributions to the project.

### New features

- Added feature to undo/redo previous commands.
  [\#107](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/107)
  [\#189](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/189)
  * What it does: Allows the user to `undo` previous commands (which change the state of the address book/event book). Prior undo commands can be redone with `redo`
  * Justification: A user could make mistakes when writing/using commands, especially since we condensed the available commands to the essentials. As such, PartyPlanet should provide an easily available way to undo this. Keyboard shortcuts were also implemented, e.g. `CTRL + Z` for undo.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required the creation of a `StateHistory` class to track the `State` of the address and event books at the same time.
  * Credits: Code used is referenced weakly from the [AB4 `undo` and `redo`](https://github.com/se-edu/addressbook-level4/tree/master/src/main/java/seedu/address/logic/commands), but they differ significantly as `StateHistory` has to be customized for PartyPlanet.

- Added event book and feature to add events (`eadd` command)
  commits [4408fd8](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/4408fd8d35d6b7316c1df697fe85b66798235ad8), [858ae4a](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/858ae4a90277abeebc7681eddd9fe0739e2727c5), [551afbd](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/551afbd4839195fde395d29ff86ef197637c0da8), [9394207](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/93942076dd8d51785c33e835dd3ac217c81a6344)
  * What it does: Allows the user to store a book of events in PartyPlanet, and to `eadd` an event to PartyPlanet
  * Justification: A user would want to keep track of upcoming celebrations, or celebrations that need to be planned.
  * Highlights: This enhancement affected the existing `Logic` and `Model`, and required implementation and integration of `EventBook` and `Event` from scratch. Finally, testing stubs had to be updated to reflect changes. Implementation was very difficult, as a lot of content had to be added, and existing content had to be refactored.
  * Credits: Code used for `eadd` is referenced from the `add` command, and `EventBook` and `Event` are based off of `AddressBook` and `Person`. 
 
- Added feature to request help for specific commands (`help COMMAND`):
  [\#72](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/72)
  * What it does: Allows the user to request help for a specific command with an optional parameter `COMMAND`, to be displayed directly in the UI, instead of redirecting to the UG.
  * Justification: Our command syntax is dense, with many optional parameters. `help` gives a brief overview of all available commands with syntax, and the user can specify a command to get detailed help on, directly available in the UI. This speeds up workflow and minimizes cross referencing to resources outside the application.
  * Highlights: This enhancement depends on the existing commands and commands to be added in the future. It relies heavily on `MESSAGE_USAGE_CONCISE` and `MESSAGE_USAGE` in each individual command.
  * Credits: Code used is based on the AB3 `help` command, but then significantly modified after.

### Feature enhancements
- Created a `Date` class and refactored `Birthday` and `EventDate` to inherit from it:
  [\#145](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/145)
- Refactored DETAIL to REMARK and repackaged and reorganized `Name` and `Remark` classes:
  [\#311](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/311)
- Wrote tests for `Undo` and `Redo`:
  [\#317](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/317)

### Documentation

- UserGuide:
  * Added sections for [`help`](https://ay2021s2-cs2103-w16-3.github.io/tp/UserGuide.html#showing-help--help), [`undo`](https://ay2021s2-cs2103-w16-3.github.io/tp/UserGuide.html#undoing-actions--undo) and [`redo`](https://ay2021s2-cs2103-w16-3.github.io/tp/UserGuide.html#redoing-actions--redo)
 
- DeveloperGuide:
  * Added User Stories: [\#36](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/36)
  * Added Use Case UC7 [\#60](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/60)
  * Updated Product Scope [\#69](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/69)
  * Updated UML diagram for `Model` component after refactor [\#311](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/311)
  * Added Appendix: Effort [\#332](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/332)
  * Added sections for [`help`](https://ay2021s2-cs2103-w16-3.github.io/tp/DeveloperGuide.html#feature-help), [`undo` and `redo`](https://ay2021s2-cs2103-w16-3.github.io/tp/DeveloperGuide.html#feature-undoredo), including all UML Diagrams.

### Community

- Team-based tasks:
  * Update README with GitHub Actions build status badge [\#35](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/35)
  * Update site wide settings [\#44](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/44)
 
- Forum posts:
  [\#141](https://github.com/nus-cs2103-AY2021S2/forum/issues/141),
  [\#151](https://github.com/nus-cs2103-AY2021S2/forum/issues/151)
 
- PRs reviewed (with non-trivial comments):
  [\#88](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/88),
  [\#92](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/92),
  [\#309](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/309)
 
