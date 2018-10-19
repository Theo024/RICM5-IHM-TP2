package ihm.menu;

import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CircularMenu extends JPanel {

    private List<MenuLabel> items;
    private int             size;
    private int             radius;

    public CircularMenu(int width, int height, int radius, int size, List<MenuLabel> items) {
        this.items = items;
        this.size = size;
        this.radius = radius;
        setSize(width, height);
        setLayout(null);
        build();
    }

    // Calcul du placement
    private void build() {
        if (items != null && items.size() > 0) {
            int x = getWidth() / 2;
            int y = getHeight() / 2;

            double angle = 0;
            int shift = 1;
            int step = Math.min(items.size(), 8);

            for (int i = 0; i < items.size(); i++) {

                if (i <= 8) { // Cercle
                    items.get(i).setBounds((int) (x - size / 2 + radius * Math.cos(angle)),
                            (int) (y - size / 2 + radius * Math.sin(angle)), size, size);
                    angle += 2 * Math.PI / step;
                } else { // En bas du cercle
                    items.get(i).setBounds(x - size / 2, y - size / 2 + radius + 2 * size * shift, size, size);
                    shift++;
                }

                add(items.get(i));
            }
        }
    }

}
