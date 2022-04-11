# Meeting 12 11/04/22

## Attendees
- Elliott
- Ben
- Joe
- Cameron

## Agenda

Catch up on documentation level

Finalise UI for video

Sort plans for video

Visualisation Algorithm

## Meeting Notes

### Feedback off darran for table

Wants 1st option on git issue #73

Needs to be implemented, In-degree on left, out-degree on right

### Nearly there with manual input

Will be done tonight

### Need issue for checking unused code at end of project

### Setting pin

teacher should set pin when page first opened

### UI for user manual, when will this be done?

Will be finalised this week

### Need an algorithms section in documentation

Adjacency matrix stuff and the force-direction stuff mainly but should be apparent in code

### Ben will do the demo

Joe: let Ben know when data page is done

### Reassign

reassigned SVG watermark to Tom

Need to do adding file name to png, svg, jpeg

### going through Cameron's research on forced directed graph

force-directed could do clusters

```js
// Attracting
for edge
  get nodeA, nodeB
  use Hookes law to get force
  cache force
  if edge is mutual: double force
// Repelling
for nodeA
  for nodeB
    if nodeA == nodeB: continue
    calculate force from Coulombs law
  calculate resultant force
  use F = ma to get acceleration
  apply acceleration using trigonometry

// Notes
// Potentially could run this several times with random positions and apply quality metric to determine best
// Machine learning to figure out best constants? May work if we get a good quality metric
```

### Next mini meet

Need to meet with Barney and Tom to delegate who does this and how much of bug fixing has tom got left





