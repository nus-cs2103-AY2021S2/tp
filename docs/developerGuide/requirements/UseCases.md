### Use cases

(For all use cases below, the **System** is the `PartyPlanet` application while the **Actor** is the `User`,
unless specified otherwise)
<pre>
Use case: UC1 - Add a contact
MSS:
  1. User requests to add a new contact.
  2. PartyPlanet displays the new list of contacts with the added contact.
     Use case ends.
Extensions:
  1a. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC2 - Delete a contact
MSS:
  1. User requests for a contact/contacts to be deleted.
  2. PartyPlanet displays the list of contacts without the deleted contact.
     Use case ends.
Extensions:
  1a. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC3 - Edit a contact
MSS:
  1. User request for a contact/contacts to be edited.
  2. PartyPlanet displays the updated details.
     Use case ends.
Extensions:
  1a. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC4 - List contacts
MSS:
  1. User requests to list out all contacts.
  2. PartyPlanet displays a list of all contacts.
     Use case ends.
Extensions:
  1a. User chooses a sort order from a list of possible sort orders.
      1a1. PartyPlanet displays the list of all contacts in the given sort order.
           Use case ends.
  1b. User chooses a searching criteria from a list of possible criteria.
      1b1. PartyPlanet displays the list of all contacts meeting the given criteria.
           Use case ends.
  1c. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC5 - Add an event
MSS:
  1. User requests to add a new event.
  2. PartyPlanet displays the new list of events with the added event.
     Use case ends.
Extensions:
  1a. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC6 - Delete an event
MSS:
  1. User requests for an event/events to be deleted.
  2. PartyPlanet displays the list of events without the deleted event.
     Use case ends.
Extensions:
  1a. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC7 - Edit an event
MSS:
  1. User requests for an event to be edited.
  2. PartyPlanet displays the updated details.
     Use case ends.
Extensions:
  1a. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC8 - Mark an event as done
MSS:
  1. User requests for an event to be marked as done.
  2. PartyPlanet displays the updated status.
     Use case ends.
Extensions:
  1a. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC9 - List events
MSS:
  1. User requests to list out all events.
  2. PartyPlanet displays a list of all events.
     Use case ends.
Extensions:
  1a. User chooses a sort order from a list of possible sort orders.
      1a1. PartyPlanet displays the list of all events in the given sort order.
           Use case ends.
  1b. User chooses a searching criteria from a list of possible criteria.
      1b1. PartyPlanet displays the list of all events meeting the given criteria.
           Use case ends.
  1c. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC10 - Get Help
MSS:
  1. User requests for help.
  2. PartyPlanet displays all available commands.
     Use case ends.
Extensions:
  1a. User supplied a specific command as a parameter.
      1a1. PartyPlanet displays help for the specific command supplied.
           Use case ends.
  1b. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC11 - Erroneous input
MSS:
  1. PartyPlanet detects erroneous input.
  2. PartyPlanet displays error message.
     Use case ends.



Use case: UC12 - Exit PartyPlanet
MSS:
  1. User requests to exit.
  2. PartyPlanet exits and closes the window.
     Use case ends.



Use case: UC13 - Undo an action
MSS:
  1. User requests to undo an action.
  2. PartyPlanet displays the details of the action that was undone and the list of contacts/events after the action is undone.
     Use case ends.
Extensions:
  1a. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.



Use case: UC14 - Redo an action
MSS:
  1. User requests to redo an action.
  2. PartyPlanet displays the details of the action that was redone and the list of contacts/events after the action is redone.
     Use case ends.
Extensions:
  1a. PartyPlanet <ins>detects an erroneous input (UC11)</ins>.
      Use case ends.
</pre>
