public class Pathfinder {
    /**
     * Shows the closest direction to take to get from one tile to another.
     * @param tile the tile to go from
     * @param otherTile the tile to go to
     * @return an array of the x and y of where to go. Values of -1, 0, or 1.
     */

    // Checks which of the two values (x vs y) are greater, then sets that direction to 1 or -1.
    public final static int[] findPath(Tile tile, Tile otherTile) {

        int x = 0;
        int y = 0;

        if (otherTile.getX() - tile.getX() > otherTile.getY() - tile.getY()) {
            x = (otherTile.getX() - tile.getX()) / Math.abs(otherTile.getX() - tile.getX());
        }
        else {
            y = (otherTile.getY() - tile.getY()) / Math.abs(otherTile.getY() - tile.getY());
        }

        int[] coordinates = {x, y};
        return coordinates;
    }
}