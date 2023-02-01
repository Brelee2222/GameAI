package Display.GameDisplay;

import AI.Search.Search;

import javax.swing.*;
import java.awt.event.ItemListener;

public class OptionsSidebar extends JPanel {
    private final JComboBox<Search> searchSelector;
    private final JComboBox<Game> gameSelector;

    public OptionsSidebar(Search[] searches, Game[] games) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        gameSelector = new JComboBox<>(games);
        add(gameSelector);

        searchSelector = new JComboBox<>(searches);
        add(searchSelector);
    }

    public void addSearchChangeListener(ItemListener itemListener) {
        searchSelector.addItemListener(itemListener);
    }

    public void addGameChangeListener(ItemListener itemListener) {
        gameSelector.addItemListener(itemListener);
    }
}
