---
layout: page
title: Justin's Project Portfolio Page
---

## Project: PartyPlanet

PartyPlanet is a cross-platform desktop application that helps users plan birthday celebrations.
The user primarily interacts with it using a command-line interface, inside a GUI created with JavaFX.
It is written in Java with around 10k LOC.

A full list of code contribution can be found here:
[RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=pyuxiang&breakdown=true)
<br>Given below are my contributions to the project.

### New features

- Add options to search by partial / exact / all / any matches in `list`:
  [\#116](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/116)
  * What it does:
    Allows the user to expand or limit the search criteria when filtering contacts, by specifying whether a partial match
    is desired, and whether all or only one specified criteria should be fulfilled by each filtered contact.
  * Justification:
    The granularity with which users filter contacts can vary greatly depending on their search preferences and
    use case.
  * Highlights:
    Required defining of individual predicates used for filtering, as well as combining these predicates.
    
- Search for contacts by birthday month:
  [\#183](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/183)
  * What it does:
    Allows the user to find groups of contacts matching a birthday month (or with no birthday specified).
  * Justification:
    A major use case involves users planning birthday celebrations on a monthly-basis.
  * Highlights:
    Implemented code to accept months as case-insensitive short / long forms, or as integers.

### Feature enhancements

- Refactor Date class + Add sort by upcoming birthdays (dates) in `list` (`elist`):
  [\#180](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/180)
  * A noticeably big PR, to minimize duplicate functionality using inheritance, and change internal date types
    to better fit subclassing requirements.
- Add option to sort by birthday month in chronological order, and decoupled ordering option from sorting option:
  [\#126](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/126)
- Add alternative date formats (ISO, DMY, YMD) with and without years:
  [\#119](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/119)
  * Justification for chosen formats is partially detailed [here](https://github.com/AY2021S2-CS2103-W16-3/tp/issues/91#issuecomment-797487476)
- Update email regex to be more permissive:
  [\#286](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/286), issue [\#247](https://github.com/AY2021S2-CS2103-W16-3/tp/issues/247)
- Change prefix syntax to dash form, and add long form alternatives:
  [\#73](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/73),
  [\#111](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/111)
- Improve GUI visuals to accommodate prior font change:
  [\#198](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/198)
  * This is particularly noteworthy since it emphasizes UX design.
- Refactor `theme` command to rely on enumeration, and add theme switching alternative using GUI menu bar:
  [\#184](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/184)
  * Builds upon initial `theme` implementation by `garyljj` in [\#176](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/176)
- Add initial GUI skeleton to display event list:
  commit [`02452dd`](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/02452dd58c89a77c79a7f3b2597eb2580a845a65)
  * Implemented in favor of tag list proposed in [\#99](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/99) due to limited horizontal space.
- Add unit tests:
  `ParserUtil`, `Date` (commit [`7b886b5`](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/7b886b5b6b9e27971cde42b06a325cb14e64a063)),
  `Birthday` (commit [`1f059df`](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/1f059df0e26bac1f2d0629810367bb86b6c38795)),
  `ListCommand` (commit [`bf68113`](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/bf68113f18067d72a8da7b5d7a72966f80e3180b)),
  `ListCommandUtil` (commit [`7ef9fb2`](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/7ef9fb209f4f19cb7cc65e2eb9ad44ee9163114d)),
  `Prefix` (commit [`d2b9a8c`](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/d2b9a8c9372f1f6f43892ff55322e95e7781d1cb))

### Documentation

- UserGuide:
  * Add section on `list` as well as command summary: [\#218](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/218)
  * Format glossary of parameters into table form [\#217](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/217)
- DeveloperGuide:
  * Add user stories: [\#51](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/51)
  * Add use case: [\#57](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/57)
  * Add developer section on `theme` command implementation:
    commit [`977ed91`](https://github.com/AY2021S2-CS2103-W16-3/tp/commit/977ed91ee207e33b4e56353822b76d17e2ba436e)

### Team workflow

- Add workflow to send pull request notifications (open, close, sync) to Telegram
  [\#52](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/52),
  [\#64](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/64)
  * Improves team workflow since our developer channel on Telegram is more frequently monitored compared to email
- Add issue tracker templates:
  [\#75](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/75)
- Organize repo [labels](https://github.com/AY2021S2-CS2103-W16-3/tp/labels)
- Manage opening and closing of GitHub milestones

### Community

- Share [script](https://github.com/nus-cs2103-AY2021S2/forum/issues/29)
  to generate direct links to CS2103 video quizzes (received 10 hoorays :>)
- Notable PR reviews with non-trivial comments:
  [\#128](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/128)
  [\#158](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/158)
  [\#186](https://github.com/AY2021S2-CS2103-W16-3/tp/pull/186)
