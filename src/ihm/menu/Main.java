package ihm.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

    // Ceci est le point d'entrée du programme :)
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<MenuLabel> items = new ArrayList<>();
        items.add(new MenuLabel("1", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("2", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("3", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("4", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("5", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("6", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("7", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("8", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("9", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("10", new Color(255, 0, 0), new Color(0, 255, 0), 100));
        items.add(new MenuLabel("11", new Color(255, 0, 0), new Color(0, 255, 0), 100));

        mainFrame.add(new CircularMenu(600, 600, 200, 50, items));

        mainFrame.setSize(1000, 1000);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

}
