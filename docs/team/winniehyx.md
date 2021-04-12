---
layout: page
title: Winnie Ho Yi Xuan's Project Portfolio Page
---

Hello, I am Winnie, a Year 2 Computer Science student. I like to spot fashion trends and operate an online shop that buy and sell clothing.

## Project: Tutor Tracker
**Tutor Tracker** is a desktop tuition management application meant for secondary students to track their tuition information, such as upcoming tuition appointments and tutor's contact information.
It focuses on the Command Line Interface (CLI) while providing users with a simple and clean Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**:Added the ability to add tutor
  * What it does: Allows the user to add a new tutor and enter their basic details as well as an optional note.
  * Justification: This feature is essential for the product because it adds the details of the tutor in so that the user
    can keep track of the tutor's details.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives.
  * Credits: Code is adapted from `add` in AddressBook 3. </br>
    

* **New Feature**:Added the ability to delete tutor
  * What it does: Delete a tutor by index. Deletion cannot occur if there is at least one existing appointment booked with the tutor in question.
  * Justification: This feature is essential for the product because it deletes the details of the tutor that the user no longer wants in the app.
  * Highlights: This enhancement required an in-depth analysis of design alternatives as I have to consider the case whereby the tutor is booked.
  * Credits: Code is adapted from `delete` in AddressBook 3. </br>
  
* **New Feature**:Added the ability to favourite tutor
  * What it does: Allows users to track of their favourite tutors.
  * Justification: This feature enhances the product by allowing users to be able to add tutors to favourites to find the tutor's details easily in the future.
  * Highlights: This feature requires the ability to work with UI to show the star. </br>

* **New Feature**:Added the ability to unfavourite tutor
  * What it does: Removes favourite status of a particular tutor that had been previously added as a favourite by using the index.
  * Justification: This feature enhances the product by allowing users to be able to remove tutor from favourite as the user no longer prefers the tutor.
  * Highlights: This feature requires the ability to work using UI to remove the star. </br>
  
* **New Feature**:Added the ability to list tutor that were added to favourite
  * What it does: View list of tutor(s) that had been added as favourite.
  * Justification: Allow user to quickly access the list of favourite tutor(s)
  * Highlights: This feature requires filtering of the tutor to list which are added as favourite. </br>
  
* **New Feature**:Added the ability to add note 
  * What it does: Shortcut for adding note to tutor at a particular index. One tutor can only have one note added.
  * Justification: Allow user who are meticulous to add some additional notes about the tutor to personalise this app.
  * Highlights: This feature requires the ability to work with UI to show the note and to have a scrollplane if note is too long. </br>
  
* **New Feature**:Added the ability to edit note  
  * What it does: Shortcut for editing note to tutor at a particular index. The tutor must have a note in order to edit.
  * Justification: Allow user who wish to make changes to their notes to do so.
  * Highlights: This feature requires the ability to work with UI to show the note. </br>
  
* **New Feature**:Added the ability to delete note
  * What it does: Deletes solely the note to tutor at a particular index that was added previously.
  * Justification: Allow user to remove irrelevant note.
  * Highlights: This feature allows only the note to be deleted, without touching the tutor's existing details
  
* **New Feature**:Added the ability to export tutor's details and the notes, if added.
  * What it does: Export the tutor details of that index together with the notes and subject list into a text file in the directory you saved the
    TutorTracker jar under the export folder, with the tutor's name as file name.
  * Justification: Allow user to export the details to send to friends or save it.
  * Highlights: This feature allows a text file to be formed.
  
* **Code contributed**: [RepoSense link]()
* **Project management**:
  * Refactored addressbook to tutorbook and person to tutor (#192) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/38fb0ed715165d4f54f4ca43985315072b05f7b6)
  * Helped in the screenshotting of the features of the app for the demo
  
* **Enhancements to existing features**:
  * Update the GUI for the note feature
  * Update the GUI for the favourite feature
  
* **Documentation**:
    * User Guide:
      * Added documentation for the features `view_appointment`,`delete_appointment`(#10) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/6eda3a0af419a6d2ae0d994a6419ce089d41be66)
      * Added documentation for the features `add_tutor`, `delete_tutor`(#88)
      * Added documentation for the feature `exit`(#187) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/649c703d14d6b1cecec37a2ce287cb2ee2f96196)
      * Added documentation for the feature `export`(#173)(https://github.com/AY2021S2-CS2103-T14-3/tp/commit/c63a9256f24e97b35d98206bdba050786d7f7e42)
      * Added documentation for the feature `add_note`,`edit_note`,`delete_note`,`list_note` (#173) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/34bb4520edab92c1fc8064664020d6f093f07e45)
      * Edited documentation for the feature `list_tutors` (#211) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/ca7693142a8ff273e1ffd4f4edda52970f2cd064)
    * Developer Guide:
      * Added use cases for the features `view_appointment`,`delete_appointment`(#11) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/8010d2093edb6b0c96e8a2f26ca3572816b3bdf9)
      * Added model and storage class diagram (#162) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/15bc6e2c66eab91c8b7489e40b94ac117e0869c7)
      * Added brief description for `favourite`, `unfavourite`, `list_favourite`,`add_note`,`edit_note`,`list_note` (#130) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/b73a97ba1c4dba8ed5b7de0416c5b6282777f3bc)
      * Added description for `export` (#187) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/61093220dd295f8491e0f08529fc2883dece661a)
      * Added description, sequence diagram and activity diagram for notes feature (#325) (https://github.com/AY2021S2-CS2103-T14-3/tp/commit/f997864d6cfcc2e90cca2ff59825bb410a41009d)
    
* **Community**:
  * Constantly approved PRs of group members despite not being their reviewer.
  * Reported bugs and suggestions for other teams in the class
  

