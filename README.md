## Slay the NecromanCEO
This is a project made for Hack K-State 2025. It's a text-based roguelike about going through an office building full of reanimated 9-5 workers to slay the NecromanCEO.

### Running
An executable .jar file can be found in this repo's releases. 

To run, make sure you're in the .jar file's directory, then run `java -jar SlayTheNecromanCEO.jar` in a terminal. Make sure you're using an up-to-date version of JRE.

A small JFrame is used to capture inputs for the game. If your inputs aren't going through, click on the window titled "Office Goblin" to refocus.

### Playing

You are the little green G (G for goblin). Move with WASD.

Any tile that is a period (`.`) is **empty**.

Use the arrow keys to attack adjactent tiles. *All* non-empty tiles can be attacked (even walls!)

Every movement or attack is a "turn." Enemies take their turn immediately after yours, though most only get to take an action once every few turns.

Colored squares represent walls. 
- Blue walls are **windows**, which shatter easily. 
- White walls are **wood**, which are fairly flimsy. 
- Magenta walls are **concrete**, which can take quite a few hits.

Yellow desks are around, and can provide a quick heal or a provide a permanent stat up.
White filing cabinets are common, and can provide weapons to help you fight.

Some letters represent enemies. They will slowly shamble toward the goblin, and will attack if adjacent. All enemies flash white just before taking an action.

- O represents an **Office Skeleton** - Your standard undead worker. Takes an action every 3 turns, 3 DMG, 20 HP.
- Z reprsents a **Zombie** - Lurching temps hired on-the-fly by the NecromanCEO to do his cruel bidding. Takes an action every 3 turns, 3 DMG, 10 HP.
- N represents **The NecromanCEO** - The big boss of this twisted company. Slay this guy to win the game! Summons a Zombie every 5 turns. Takes an action every 3 turns, 8 DMG, 35 HP. His attack strikes all adjacent tiles, including diagonals.

Other letters represent containers full of loot to be found in the office. To rifle through them with your grubby goblin hands, just attack them.

- F represents a **Filing Cabinet**, which sometimes contain enchanted office supplies that can be used as weapons. They're usually empty, though.
- D represents a **Desk**, which usually contains some coffee - great if you're low on caffiene. Sometimes you'll find a more stylish suit in them, too.