package rogue.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class HelpScreen implements Screen {

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.clear();
        terminal.writeCenter("Aide et Regle du jeu", 1);
        terminal.write("Explorer, trouver la king's soul, et revenir", 1, 3);
        terminal.write("a la surface pour gagner.", 1, 4);

        int y = 5;
        terminal.write("-- Movement --", 2, y++);
        terminal.write("[k] up, [j] down, [h] left, [l] right", 2, y++);
        terminal.write("[y] up-left, [u] up-right", 2, y++);
        terminal.write("[b] down-left, [n] down-right", 2, y++);
        terminal.write("[<] or [>] up/down etage", 2, y++);
        terminal.write("-- Actions --", 2, y++);
        terminal.write("[q] boire une potion", 2, y++);
        terminal.write("[w] equipement", 2, y++);
        terminal.write("[e] manger, [r] lire", 2, y++);
        terminal.write("[t] jetter, [d] drop", 2, y++);
        terminal.write("[f] fire a ranged weapon", 2, y++);
        terminal.write("[g] or [,] to pick up", 2, y++);
        terminal.write("[;] look around, [m] to look at notifications", 2, y++);
        terminal.write("[x] examine your items", 2, y++);
        terminal.write("[:] help, ['] view your stats", 2, y++);

        terminal.writeCenter("-- press any key to continue --", 22);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        return null;
    }

}
