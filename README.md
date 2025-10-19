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

Yellow loot boxes are around, and can provide a quick heal or a provide a permanent stat up.

Letters that aren't G represent enemies. They will slowly lurch toward the goblin, and will attack if adjacent. All enemies flash white just before taking an action.

- O represents an **Office Skeleton**. They take an action every 3 turns. They deal 3 damage and have 20 HP.