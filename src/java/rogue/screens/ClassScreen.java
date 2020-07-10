package rogue.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class ClassScreen implements Screen {

    private static String className;

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("-- Select ur class --", 1);
        int y = 8;
        terminal.write(" [G]  Guerier", 5, y++);
        terminal.write(" [P]  Paladin", 5, y++);
        terminal.write(" [S]  Sorcier", 5, y++);
        terminal.write(" [V]  Voleur", 5, y++);
        terminal.write(" [C]  Chasseur", 5, y++);
        terminal.write(" [M]  Mendiant", 5, y);
        terminal.writeCenter("-- choose the specific letter to continue --", 22);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_G) className = "knight";
        if (key.getKeyCode() == KeyEvent.VK_P) className = "paladin";
        if (key.getKeyCode() == KeyEvent.VK_S) className = "sorcier";
        if (key.getKeyCode() == KeyEvent.VK_V) className = "rogue";
        if (key.getKeyCode() == KeyEvent.VK_C) className = "ranger";
        if (key.getKeyCode() == KeyEvent.VK_M) className = "deprived";
        return (key.getKeyCode() == KeyEvent.VK_G || key.getKeyCode() == KeyEvent.VK_P
                || key.getKeyCode() == KeyEvent.VK_S || key.getKeyCode() == KeyEvent.VK_V
                || key.getKeyCode() == KeyEvent.VK_C || key.getKeyCode() == KeyEvent.VK_M) ? new NameScreen() : this;
    }

    public static String className() {
        return className;
    }

}
