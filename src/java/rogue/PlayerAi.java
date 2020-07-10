package rogue;

import java.util.List;

/**
 * La classe PlayerAi contrôle les comportements spécifiques propres au personnage du jeu
 */
public class PlayerAi extends CreatureAi {
    
    /**
     * A List of String objects that represent the messages to pass to the
     * player on-screen
     **/
    private List<String> messages;
    
    /**
     * A FieldOfView object responsible for handling the player's fov, and how
     * it displays on-screen
     **/
    private FieldOfView fov;
    
    /**
     * The constructor for the PlayerAi class
     * <br>
     * Inherits from: {@linkplain CreatureAi#CreatureAi(Creature)}
     * 
     * @param creature
     *        - La créature qui héritera du joueur
     * @param messages
     *        - les messages qui seront envoyés au joueur
     * @param fov
     *        - Champ de vision du joueur
     */
    PlayerAi(Creature creature, List<String> messages, FieldOfView fov) {
        super(creature);
        this.messages = messages;
        this.fov = fov;
        creature.aquatic(false);
        creature.canSwim(true);
    }
    
    @Override
    public boolean canSee(int wx, int wy, int wz) {
        return fov.isVisible(wx, wy, wz);
    }
    
    public void onEnter(int x, int y, int z, Tile tile) {
        if (tile.isGround() || tile.isWater()) {
            creature.x = x;
            creature.y = y;
            creature.z = z;
        }
    }
    
    public Tile rememberedTile(int wx, int wy, int wz) {
        return fov.tile(wx, wy, wz);
    }
    
    public void onGainLevel() {
    }
    
    public void onNotify(String message) {
        messages.add(message);
    }
}
