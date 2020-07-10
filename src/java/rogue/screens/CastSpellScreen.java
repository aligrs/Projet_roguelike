package rogue.screens;

import rogue.Creature;
import rogue.Spell;

/**
 *
 */
public class CastSpellScreen extends TargetBasedScreen {

    /****/
    private Spell spell;

    /**
     * @param player  Joueur
     * @param caption legende
     * @param sx      cible x
     * @param sy      cible y
     * @param spell   sort
     */
    CastSpellScreen(Creature player, String caption, int sx, int sy, Spell spell) {
        super(player, caption, sx, sy);
        this.spell = spell;
    }

    public void selectWorldCoordinate(int x, int y, int screenX, int screenY) {
        player.castSpell(spell, x, y);
    }

}
