package rogue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * La classe WorldBuilder est responsable de la génération de tous les terrains aléatoires.
 */

/* Warrings inutiles */
@SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
public class WorldBuilder {

    /**
     * The desired width of the world to be generated
     **/
    private int width;
    /**
     * The desired height of the world to be generated
     **/
    private int height;
    /**
     * The desired depth of the world to be generated
     **/
    private int depth;
    /**
     * A 3D Tile array containing information on what the tile is at any given
     * coordinate
     **/
    private Tile[][][] tiles;
    /**
     * A 3D int array containing information on the region numbers for any given
     * area
     **/
    private int[][][] regions;
    /**
     * An int representing the next region number to be used
     **/
    private int nextRegion;

    private ArrayList<ArrayList<Room>> worldRooms;

    /**
     * The general constructor for the WorldBuilder class
     *
     * @param width  - the desired width of the world
     * @param height - the desired height of the world
     * @param depth  - the desired depth of the world
     */
    public WorldBuilder(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.tiles = new Tile[width][height][depth];
        this.regions = new int[width][height][depth];
        this.nextRegion = 1;
        worldRooms = new ArrayList<>();
    }

    /**
     * @return returns a World instance using the tiles from this WorldBuilder
     * object
     */
    public World build() {
        return new World(tiles);
    }

    /**
     * Method that looks at every space in the world. If it is not a wall and it
     * does not have a region assigned, then that empty space, and all connected
     * empty spaces will be given a new region number.
     * <br>
     * If the region is too small it gets removed.
     *
     * @return returns a WorldBuilder object with these modifications
     */
    private WorldBuilder createRegions() {
        regions = new int[width][height][depth];

        for (int z = 0; z < depth; z++) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if ((tiles[x][y][z] != Tile.WALL) && regions[x][y][z] == 0) {
                        fillRegion(nextRegion++, x, y, z);
                    }
                }
            }
        }
        return this;
    }

    /**
     * Method that does a flood-fill starting with an open tile.
     * <br>
     * It, and any open tile it's connected to, gets assigned the same region
     * number.
     * <br>
     * This is repeated until there are no unassigned empty neighboring tiles.
     *
     * @param region - the region number to assign
     * @param x      - the x coordinate of the starting tile
     * @param y      - the y coordinate of the starting tile
     * @param z      - the z coordinate of the starting tile
     */
    private void fillRegion(int region, int x, int y, int z) {
        ArrayList<Point> open = new ArrayList<>();
        open.add(new Point(x, y, z));
        regions[x][y][z] = region;

        while (!open.isEmpty()) {
            Point p = open.remove(0);

            for (Point neighbor : p.neighbors8()) {
                if (neighbor.x < 0 || neighbor.y < 0 || neighbor.x >= width || neighbor.y >= height) continue;
                if (regions[neighbor.x][neighbor.y][neighbor.z] > 0
                        || tiles[neighbor.x][neighbor.y][neighbor.z] == Tile.WALL)
                    continue;

                regions[neighbor.x][neighbor.y][neighbor.z] = region;
                open.add(neighbor);
            }
        }
    }

    /**
     * Method to connect all the regions with stairs.
     * <br>
     * Starting at the top, and connecting them one layer at a time.
     *
     * @return returns a WorldBuilder with updated regions
     */
    private WorldBuilder connectRegions() {
        for (int z = 0; z < depth - 1; z++) {
            connectRegionsDown(z);
        }
        return this;
    }

    /**
     * Method connects two adjacent layers. Looks at each region that sits above
     * another region.
     * <br>
     * If they haven't been connected then we connect them.
     *
     * @param z - the starting region level
     */
    private void connectRegionsDown(int z) {
        List<String> connected = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                String region = regions[x][y][z] + "," + regions[x][y][z + 1];
                if (tiles[x][y][z] == Tile.FLOOR && tiles[x][y][z + 1] == Tile.FLOOR && !connected.contains(region)) {
                    connected.add(region);
                    connectRegionsDown(z, regions[x][y][z], regions[x][y][z + 1]);
                }
            }
        }
    }

    /**
     * To connect two regions, it gets a list of all of the locations where one
     * is directly above one another.
     * <br>
     * Then, based on how much area overlaps, it connects them with stairs going
     * up and down.
     *
     * @param z  - the starting region level
     * @param r1 - the first region to connect
     * @param r2 - the second region to connect
     */
    private void connectRegionsDown(int z, int r1, int r2) {
        List<Point> candidates = findRegionOverlaps(z, r1, r2);

        int stairs = 0;
        do {
            Point p = candidates.remove(0);
            tiles[p.x][p.y][z] = Tile.STAIRS_DOWN;
            tiles[p.x][p.y][z + 1] = Tile.STAIRS_UP;
            stairs++;
        } while (candidates.size() / stairs > 250);
    }

    /**
     * Method to find whether or not a region overlaps
     *
     * @param z  - the z level of the region
     * @param r1 - the first region to check
     * @param r2 - the second region to check
     * @return returns a List of Point objects that overlap
     */
    private List<Point> findRegionOverlaps(int z, int r1, int r2) {
        ArrayList<Point> candidates = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y][z] == Tile.FLOOR && tiles[x][y][z + 1] == Tile.FLOOR && regions[x][y][z] == r1
                        && regions[x][y][z + 1] == r2) {
                    candidates.add(new Point(x, y, z));
                }
            }
        }

        Collections.shuffle(candidates);
        return candidates;
    }

    /**
     * @return returns a WorldBuilder object with stairs having been added
     */
    private WorldBuilder addExitStairs() {
        int x;
        int y;

        do {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
        } while (tiles[x][y][0] != Tile.FLOOR);

        tiles[x][y][0] = Tile.STAIRS_UP;
        return this;
    }

    private WorldBuilder fillTiles() {
        for (int z = 0; z < depth; z++) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    tiles[x][y][z] = Tile.WALL;
                }
            }
        }
        return this;
    }

    private WorldBuilder placeRooms() {
        for (int z = 0; z < depth; z++) {

            ArrayList<Room> newRooms = new ArrayList<>();

            Random rand = new Random();

            for (int r = 0; r < 28; r++) {
                int minRoomSize = 8;
                int maxRoomSize = 14;
                int roomWidth = minRoomSize + rand.nextInt(maxRoomSize - minRoomSize + 1);
                int roomHeight = minRoomSize + rand.nextInt(maxRoomSize - minRoomSize + 1);
                int x = rand.nextInt(width) + 1;
                int y = rand.nextInt(height) + 1;

                while (!(((x + roomWidth) < width) && ((y + roomHeight) < height))) {
                    roomWidth = minRoomSize + rand.nextInt(maxRoomSize - minRoomSize + 1);
                    roomHeight = minRoomSize + rand.nextInt(maxRoomSize - minRoomSize + 1);
                    x = rand.nextInt(width) + 1;
                    y = rand.nextInt(height) + 1;
                }

                Room newRoom = new Room(x, y, z, roomWidth, roomHeight);

                boolean failed = false;
                for (Room otherRoom : newRooms) {
                    if (newRoom.intersects(otherRoom)) {
                        failed = true;
                        break;
                    }
                }
                if (!failed) {
                    createRoom(newRoom, z);

                    Point newCenter = newRoom.center;

                    if (newRooms.size() != 0) {
                        Point prevCenter = newRooms.get(newRooms.size() - 1).center;

                        if (rand.nextInt(2) == 1) {
                            hCorridor(prevCenter.x, newCenter.x, prevCenter.y, z);
                            vCorridor(newCenter.x, prevCenter.y, newCenter.y, z);
                        } else {
                            hCorridor(prevCenter.x, newCenter.x, newCenter.y, z);
                            vCorridor(prevCenter.x, prevCenter.y, newCenter.y, z);
                        }
                    }
                }
                newRooms.add(newRoom);
            }
            worldRooms.add(newRooms);
        }
        return this;
    }

    private void createRoom(Room room, int z) {
        for (int i = room.x1; i < room.x2; i++) {
            for (int j = room.y1; j < room.y2; j++) {
                if (i < width && j < height && i >= 0 && j >= 0) tiles[i][j][z] = Tile.FLOOR;
            }
        }
    }

    private void hCorridor(int x1, int x2, int y, int z) {
        int x = Math.min(x1, x2);
        while (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)) {
            if (x < width && y < height && y >= 0) {
                tiles[x][y][z] = Tile.FLOOR;
                x++;
            } else {
                x++;
            }
        }
    }

    private void vCorridor(int x, int y1, int y2, int z) {
        int y = Math.min(y1, y2);
        while (y >= Math.min(y1, y2) && y <= Math.max(y1, y2)) {
            if (x < width && x >= 0 && y < height) {
                tiles[x][y][z] = Tile.FLOOR;
                y++;
            } else {
                y++;
            }
        }
    }

    /**
     * @return returns a WorldBuilder instance with randomized and (x8)-smoothed
     * tiles
     */
    public WorldBuilder makeCaves() {
        return fillTiles().placeRooms().createRegions().connectRegions().addExitStairs();
    }
}
