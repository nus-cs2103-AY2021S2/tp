---
layout: page
title: Gary's Project Portfolio Page
---

## Project: PartyPlanet

PartyPlanet is a cross-platform desktop application that helps users plan birthday celebrations.
The user primarily interacts with it using a command-line interface, inside a GUI created with JavaFX.
It is written in Java with around 10k LOC.

A full list of code contribution can be found here:
[RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=garyljj&breakdown=true)

### New features

- Add deletion of contacts with specified tags [\#58](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/58),[\#152](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/152)
  * What it does: Allows user to delete multiple contacts with specified tags.
  * Justification: User might want to delete a group of contacts with the same tag. For example, the welfare IC may want to remove all contacts from the graduated batch "batch2020", he can simply use the command `delete -t batch2020`.
  * Was further improved in PR152 to support `--any` flag, which allow contact to be deleted as long as it matches any of the specified tags.

- Add InputHistory: [\#92](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/92)
  * What it does: This feature allows the user to retrieve previous inputs.<br>
  `UP` and `DOWN` arrow key cycles through the input history of the user.
  * Justification: Trying to carry out multiple similar commands (eg. Adding multiple people with similar details) can be troublesome. With this feature, users are able to get the previous input and make minor modification to it.

- Add EDoneCommand: [\#131](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/131)
  * What it does: Allows the user to mark an `Event` as done. Done `Event` will have a tick beside its title.
  * Justification: User may want to keep track of which events are completed, and have an easy way to identify them.

- Add ToggleThemeCommand: [\#176](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/176)
  * What it does: Allows user to toggle between dark theme and pastel theme.
  * Justification: Allow the user to customize the PartyPlanet based on which theme they prefer visually.
  * Was futher improve by pyuxiang in [\#184](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/184) to add theme to userpref so that chosen theme persists through sessions.

### Feature enhancements

- Improve delete command to allow deletion of multiple indexes at one go: [\#82](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/82)
  * Justification: Make it easier for user to delete multiple contacts with a single command.
- Shifted functionality of `clear` to `delete` [\#113](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/113)
  * Justification: Simplify the set of commands available to the user
- Improve PersonCard in Ui to only accomodate optional fields (vary size according to number of fields): [\#117](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/117)
- Improve `delete`, `edelete`, `edone` to handle invalid input better: [\#154](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/154)
  * When both valid and invalid indexes are provided, carrys out operation on valid indexes and inform user of invalid indexes.
- Add testcases:
  [\#301](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/301),
  [\#302](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/302),
  [\#305](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/305),
  [\#306](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/306),
  [\#314](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/314),
  [\#315](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/315),
  [\#316](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/316)

### Documentation

- User Guide
  - Add InputHistory documentation [\#96](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/96)
  - Add list of parameters section [\#204](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/204)
- Developer Guide
  - Add User Stories [\#40](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/40)[\#167](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/167)
  - Add Use Cases [\#62](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/62)[\#70](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/70)
  - Add EDone implementation details [\#166](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/166)

### Community

- PRs reviewed (with non-trivial comments):
  [\#86](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/86),
  [\#106](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/106),
  [\#130](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/130),
  [\#159](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/159),
  [\#194](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/194),
  [\#196](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/196),
  [\#286](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/286)
