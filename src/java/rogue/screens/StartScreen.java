package rogue.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class StartScreen implements Screen {
    
    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("UVSQ - Roguelike", 1, 1);
        terminal.write("Developed by Ali, Leila, Lylia and Soufiane", 1, 2);
        terminal.writeCenter("-- press [enter] to start --", 22);
    }
    
    @Override
    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new ClassScreen() : this;
    }
    
}
