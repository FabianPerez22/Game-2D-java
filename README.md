
Features i've made by me way:
=
---
* Debufs:
    - Wet: -2 speed after step on wet floor.
    - Burned: -1 life, if life is 1, none damage but apply his status.
####
* Stamina and Run:
    - The player can run, regen stamina and can't do any action like attack or run without stamina.
####
* The lanter can be consumed by the time.
####
* Debuff's:
  - Weapon's, monster and projectile can apply debuff's like as burned or wet.


Features maybe I'll make:
=
---

* The player can get armor and more stats like defence or immunity to each debuffs.
* The monster can apply debuffs.
* More players with new ability like as teleport, special attacks or anything else.
* New items who can be used for teleport the user.
* Minerals and craft system.
* And another more things.


How to implement new features
=
---- - 

GamePanel:
---
The most important class, on this one is where the FPS is controlled,
it's where the game's paint and control the limits of the word like
how many NPC, Enemy or Objects can be place for each map, so
if u wants to place more monster, objects or interactive tiles or objects
u may to change his limits on his array.

Entity:
---
The Entity class ist the one who share all methods and starts, counter
and attributes like life, damage, defense... so, if the player will
have a new abilities, and you want to share it for a monster o
new player's, it needs to be con class Entity.


Objects:
---
Depends on the kind of the objects, you'll need to put his current 
stats on Entity class, so if u want's to create another object
more like that, they'll share the basic attributes.

Don't forget create the same objects on EntityFactory and implements
his methods to apply the save.

Monster:
--- 
It's only repeat the same logic of another's monster,
and adjust his width and height proportionally.

Monster can use specially abilities if u want's.

Maps:
--- 
You will need to watch's this video and downloading his programs.
https://www.youtube.com/watch?v=y06YDM5Kq9Q&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=51

Draw:
---
If u want's to draw something's like stats or text, u may to use the
UI Class and identify where u want's to put it.

Key Inputs:
---
If u want's to add a new button or change some controls it's on KeyHandler.
Don't forget to add his boolean if it's a new ability, it needs to
have some variables like, (the Key inputs name + pressed),
and don't forget to add its value on false on keyReleased.

Event:
---
On EventHandler, this class manage all events like save, teleport
and the wet floor, if u wants to add a new event's like, fire floor,
it needs to be added on EventHandler and put's his map, Col and Row
position on the map. Don't forget to add his values like the counter
the fired boolean on Entity for players or monsters (if u want's).
