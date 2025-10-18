public class Pathfinder {
    /**
     * Shows the closest direction to take to get from one actor to another.
     * @param actor the actor to go from
     * @param otherActor the actor to go to
     * @return an array of the x and y of where to go. Values of -1, 0, or 1.
     */

    // Checks which of the two values (x vs y) are greater, then sets that direction to 1 or -1.
    public final static int[] findPath(Actor actor, Actor otherActor) {

        int x = 0;
        int y = 0;

        if (otherActor.getX() - actor.getX() > otherActor.getY() - actor.getY()) {
            x = (otherActor.getX() - actor.getX()) / Math.abs(otherActor.getX() - actor.getX());
        }
        else {
            y = (otherActor.getY() - actor.getY()) / Math.abs(otherActor.getY() - actor.getY());
        }

        int[] coordinates = {x, y};
        return coordinates;
    }
}