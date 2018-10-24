package ihm.circularmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import ihm.paint.Paint;

public class Main {

    // Ceci est le point d'entrée du programme :)
    public static void main(String[] args) {

        JFrame paint = new Paint("paint");

        paint.getContentPane().setLayout(new BorderLayout());
        paint.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CircularMenu menu = new CircularMenu(1000, 1000, 125, 50);

        // AJout de nos labels, notre menu supporte un nombre modulaire de label avec
        // répartition équitable -> voir méthode build() du menu
        List<MenuLabel> items = new ArrayList<>();
        items.add(new MenuLabel("1", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("2", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("3", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("4", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("5", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("6", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("7", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("8", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("9", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("10", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));
        items.add(new MenuLabel("11", new Color(255, 0, 0), new Color(0, 255, 0), 100, menu));

        menu.setItems(items);

        menu.setVisible(false);
        paint.add(menu);

        paint.setSize(1000, 1000);
        paint.setVisible(true);
        paint.setLayout(null);
        paint.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("teub");
                    menu.setVisible(true);
                    menu.setLocation(e.getX() - 1000 / 2, e.getY() - 1000 / 2);
                }
            }

        });
    }

}
