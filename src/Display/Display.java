package Display;

import AI.Search.Search;
import Display.GameDisplay.Game;
import Display.GameDisplay.OptionsSidebar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class Display extends JFrame {
    private static Search search;
    private static Game game;
    public final static JPanel displayPanel = new JPanel();

    public Display(Search[] searches, Game[] games) {
        setLayout(new BorderLayout());

        OptionsSidebar optionsSidebar = new OptionsSidebar(searches, games);

        optionsSidebar.addGameChangeListener(this::onGameChanged);
        optionsSidebar.addSearchChangeListener(this::onSearchChanged);
        add(optionsSidebar, BorderLayout.WEST);

        add(displayPanel, BorderLayout.EAST);
    }

    public void onGameChanged(ItemEvent e) {
        game = (Game) e.getItem();
    }

    public void onSearchChanged(ItemEvent e) {
        search = (Search) e.getItem();
    }
}
