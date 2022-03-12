# Meeting 7 11/03/22

## Attendees

- Onsite
   - Ben
   - Cammy
   - Darran
- On Discord
   - Joe
   - Tom
   - Barney
   - Elliott

## Agenda

Get feedback from Darran

Make some new goals based on feedback

Hand out work to team based on feedback and development tasks

## Meeting Notes

### Darran's Thoughts

Would be helpful to have node size change to based on how many friends they have, smaller = less vice versa

Lines need to be thicker and arrowheads

Show parallel line for mutual friendship

Move more than one node at once

Size of nodes may need to change, based on if there were around 20 nodes might get crowded

Title down not across on table

Joe is redesigning at the moment, Barney will change the axis orientation

Visually, the settings menu doesn't look amazing

### Need to do sizing soon - better now than later

Font size a bit small, scale the font sizes

### Probably best to get Darran a gantt chart

### What details are the server

All more critical data, office 365 and security systems.

Could sit there?

Ours should work on any webserver

password protected would be helpful

All done locally in browser so privacy not a concern

So, have on site and store the json/csv/images into the sharepoint

Just have files locally

Sharepoint just works in a browser

possibly sharepoint integration?

### Modifying file name - having a name to a class

Use file name in bottom corner of print off, this is so person looking at it knows which class at what school on what year etc

Need a clear title maybe at the top

Both?

### Exporting csv

The export to csv currently doesn't keep friendship preference

### What is the IP on this?

You can currently just download the code off the site like any other website

Don't have to just keep it local, keep it contained

Best way is just password protection, cannot inspect source code because of log in.

so one log in, distribute to whoever wants/needs the app. download the pwa.

### Can we have multi user log in

Not in the current way we have it, Is this wanted in the future?

yes? stretch goal though because what if people leave the organisation

### Error control

Drop down lists for correct spellings (this was already considered)

### We can host this through sharepoint

This means internal management, possibly move away from downloading from a site but just hosting the code.

Maybe keep PWA just host password access through sharepoint

### Can Darran remember the software he used to use?

no, but going to look and send to Cameron

### Communication

How is best way to individual call Darran?

Phone call, text (thats good because can come back to you), emails can take time if he is working away

### Do we want to handle graphs being overwritten?

Just have a question like: *Do you want to save this file before it is lost?* Their fault if its gone.

## Dish out tasks

- Tom
   - Panning and zooming, font wrapping, edit table
- Cammy
   - show error when user tries to insert malformed data, JSON export/import save positions, title on image
- Joe
   - Double click on node to open friends section, maintain line colour when focused, redesign settings page, style table, thicker lines and arrowheads, nodes change size depending on number of edges
- Barney
   - Transpose table, importing file table doesn't get updated
- Elliott
   - Fix CSV friendship hierarchy, double lines for mutual relationship, stop user losing their graph when importing new
- Ben