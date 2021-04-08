---
layout: page
title: Lan Yu Xuan's Project Portfolio Page
---

## Project: WeebLingo

Weeblingo is a desktop application used for managing flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add unique user tags.
    * What it does: Allow the users to add tags to flashcards that are separate from the default and immutable tags that the application provides.
    * Justification: This feature improves the product significantly because the application provides functions that allow for viewing and sorting flashcards by their tags, which complements the Learn mode of the application greatly.
    * Highlights: This enhancement affected one of the key modes, Learn Mode, of the application greatly and became one of the main features of that mode, along with viewing all flashcards.
      Tagging also affected most other sections of the application and thus required deeper insight into how the entire application functioned.
    * Credits: The code took some inspiration from the original Address Book that the project was morphed from.

* **New Feature**: Added the deleteTag command that tied in with the above command. Users could delete tags, but not default ones set by the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/#breakdown=true&search=jellybeano)

* **Project management**:
    * Coordinated and led project meetings.
    * Assisted in managing milestones for the team.

* **Enhancements to existing features**:
    * Updated the GUI color scheme and added visual improvements (Pull request [\#60](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/60))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `learn`, `tag` and `deleteTag` (Pull requests [\#96](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/96), [\#111](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/111))
        * Update the diagrams to match the upstream repo after they were changed (Pull request [\#111](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/111))
    * Developer Guide:
        * Added implementation details of the `learn` feature and updated various UML diagrams. (Pull requests [\#81](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/81), [\#96](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/96))
        * Added implementation details of the tagging feature, along with relevant diagrams. (Pull request [\#81](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/81))

* **Team-Based Tasks**:
    * Assisted with refactoring and morphing Address Book into the current application. (Pull request [\#22](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/22))
    * Changed the icon for the application. (Pull request [\#179](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/179))
    * Helped maintain the issue tracker for the team repository. 
    
## Contributions to the User Guide

### Learn Mode Commands

### Entering learn mode: `learn`

Enters Learn Mode, where all current flashcards are listed out. Answers to current flashcards are shown as well.

Users can utilise this mode to facilitate the learning process,
and look through complete flashcards without having to test themselves.

Tagging related functions are also only available in this mode.

Format: `learn`

Learn Mode:
![learn mode](images/learn_mode.png)

### Tagging a flashcard: `tag`

Reads in a positive index and one or more tags, and adds these tags to the flashcard at the desired index.
At least one tag must be provided, else the user will be prompted to enter a valid command.

Tagging of flashcards can only be done in Weeblingo's Learn Mode.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Default tags are provided by Weeblingo,
which can neither be deleted nor edited.
The purpose of this command is to allow users to add their own tags as well to assist in the learning process.

</div>

Format: `tag INDEX t/TAGS…`

Examples:
* `tag 1 t/difficult`
* `tag 5 t/fire`

After successfully adding a tag:
![tag successful](images/tag_success.png)

### Deleting tags from a flashcard: `deleteTag`

Reads in a positive index and zero or more tags, and removes these tags from the flashcard at the desired index.
If no tags are provided, all user-added tags will be removed from the flashcard.

Tagging of flashcards can only be done in Weeblingo's Learn Mode.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Default tags are provided by Weeblingo, which can neither be deleted nor edited.
The purpose of this command is to allow users to delete tags added by themselves.

</div>

Format: `deleteTag INDEX [t/TAGS…]`

Examples:
* `deleteTag 3`
* `deleteTag 1 t/difficult`

After successfully deleting sampleTag from the flashcard at index 3:
![delete_successful](images/delete_success.png)


## Contributions to the Developer Guide

### [Implemented] Tagging Flashcards

The tagging mechanism allows users to add tags to flashcards of their choice while in the _Learn Mode_
of the WeebLingo application. Each flashcard has a set of default tags which cannot be edited, followed by
any unique user added tags.

![Structure of the Flashcard with tags](images/FlashcardWithTagsObjectDiagram.png)

The following activity diagram summarizes what happens when a user adds a new command:

![NewTagActivityDiagram](images/NewTagActivityDiagram.png)

The tags function ties together with the Start function of the application, as users can choose to start a quiz
containing flashcards that have the same tag only.
