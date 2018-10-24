package ihm.circularmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

        CircularMenu menu = new CircularMenu(1000, 1000, 125, 50, items);
        menu.setVisible(false);
        mainFrame.add(menu);

        mainFrame.setSize(1000, 1000);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setLayout(null);
        mainFrame.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                menu.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                menu.setVisible(true);
                menu.setLocation(e.getX() - 1000 / 2, e.getY() - 1000 / 2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

        });
    }

}
