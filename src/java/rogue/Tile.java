package rogue;

import java.awt.Color;

import asciiPanel.AsciiPanel;

/**
 * The Tile class contains all of the necessary information to do with tiles
 */
public enum Tile {
    /** The tile that represents the floor **/
    FLOOR('.', AsciiPanel.yellow, "A dirt and rock cave floor.", 0),
    /** The tile that represents a wall **/
    WALL('#', AsciiPanel.yellow, "A dirt and rock cave wall", 0),
    /** The tile that represents stairs going downward **/
    STAIRS_DOWN('>', AsciiPanel.white, "A stone staircase that goes down.", 0),
    /** The tile that represents stairs going upward **/
    STAIRS_UP('<', AsciiPanel.white, "A stone staircase that goes up.", 0),
    HOLE('.', AsciiPanel.black, "A hole that leads deeper into the Catacombs.", 0),
    DOOR('+', AsciiPanel.brightRed, "A door.", 0),
    DEEP_WATER('~', AsciiPanel.brightBlue, "Deep water.", -1),
    /** The tile that represents an unknown part of the world **/
    UNKNOWN(' ', AsciiPanel.white, "(unknown)", 0),
    /** The tile that represents space beyond the edge of the map **/
    BOUNDS('x', AsciiPanel.brightBlack, "Beyond the edge of the world.", 0);
    
    // TODO: Add new tiles
    // � Tile to represent "bloodwort stalk" (color red/magenta)
    // * Tile to represent "bloodwort pod" (color red/magenta, disappears & heals when stepped on, spawns only next to "bloodwort stalk"
    // � Tile to represent exit (aka stairs up on top floor, color bright yellow)

    
    /** The glyph that represents the tile **/
    private char glyph;
    /** The color of the glyph that represents the tile **/
    private Color color;
    /** The details of the tile **/
    private String details;
    private int stealthModifier;
    
    /**
     * @return returns the Tile's glyph
     */
    public char glyph() {
        return glyph;
    }
    
    /**
     * @return returns the Tile's color
     */
    public Color color() {
        return color;
    }

    /**
     * Method used to determine whether or not a tile is ground
     * 
     * @return returns true if the tile is not a wall and is not out of bounds
     */
    public boolean isGround() {
        return this != WALL && this != BOUNDS && this != HOLE && this != DEEP_WATER;
    }
    
    public boolean isWater(){
        return (this == DEEP_WATER);
    }
    
    public boolean blocksVision(){
        return (this == WALL || this == DOOR);
    }
    
    /**
     * @return returns a tile's details
     */
    public String details() {
        return details;
    }

    /**
     * The general constructor for Tiles
     * 
     * @param glyph
     *        - the glyph that represents the tile
     * @param color
     *        - the color of the glyph that represents the tile
     * @param details
     *        - the details of the tile
     */
    Tile(char glyph, Color color, String details, int stealthModifier) {
        this.glyph = glyph;
        this.color = color;
        this.details = details;
        this.stealthModifier = stealthModifier;
    }
}
