# Meeting 6 04/03/22

## Attendees

- Joe
- Barney
- Tom
- Elliott
- Ben
- Cammy - Discord

## Agenda

Progress check

Decide on meeting day for Darran

Redistribute tasks if needed

## Notes from meeting

Need to do UI:
	- Table is done - just need to add into html
	- menu done
	- Need to do: Importing Exporting Bonds, Colouring (down to svg)

Are we moving to SVG or staying with canvas?
- Tom suggests svg because zooming in and looking around is easier
- rendering doesn't require a full re-render
- export straight to svg
- will have to change object to not hold coords
- array of nodes may become array of DOM elements from xml in svg
	- currently uses a bit of both
	- probably best to do rendering first, then when anything programitcaly happends, we can go back to primitive DOM elements
- If we use SVG, will need to basically bin everything we have done
- currently have two arrows on top of eachother other than bi-directional

We will move to svg:
	- Tom and Elliott will sit in call saturday to move to adjacency

Ben will write something about moving from canvas from SVG

Cameron working on additional visualisation and finishing testing design

Tom Merge SVG and create new child branch

Next meeting: see Darran and make next plan - 2 hours of Darran's time hour later have a 1 hour meeting to discuss next steps

Joe emails Darran to get feedback on design of UI.

Darran meeting: Friday 11th - 2pm-4pm

Our meeting: ~5pm - ~6pm