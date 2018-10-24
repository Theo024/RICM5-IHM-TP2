package ihm.paint;
//////////////////////////////////////////////////////////////////////////////

// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputListener;

import ihm.circularmenu.CircularMenu;
import ihm.circularmenu.MenuLabel;

/* paint *******************************************************************/

@SuppressWarnings("serial")
public class Paint extends JFrame {
    Vector<Shape> shapes        = new Vector<>();
    Vector<Color> colors        = new Vector<>();
    Color         colorSelected = Color.BLACK;

    class ColorButton extends JButton implements MouseListener {
        String name;
        Color  color;

        public ColorButton(String name, int r, int g, int b) {
            this.name = name;
            color = new Color(r, g, b);
            setBackground(color);
            setText("    ");
            addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("color selected - " + color);
            colorSelected = color;
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    ColorButton[] colorButtons = { new ColorButton("black", 0, 0, 0), new ColorButton("red", 255, 0, 0),
            new ColorButton("green", 0, 255, 0), new ColorButton("blue", 0, 0, 255) };

    class Tool extends AbstractAction implements MouseInputListener {
        Point o;
        Shape shape;

        public Tool(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("using tool " + this);
            panel.removeMouseListener(tool);
            panel.removeMouseMotionListener(tool);
            tool = this;
            panel.addMouseListener(tool);
            panel.addMouseMotionListener(tool);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                System.out.println("teub");

                menu.setVisible(true);
                menu.setLocation(e.getX() - 1000 / 2, e.getY() - 1000 / 2);
                menu.repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            o = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            shape = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

    Tool tools[] = { new Tool("pen") {
             @Override
             public void mouseDragged(MouseEvent e) {
                 Path2D.Double path = (Path2D.Double) shape;
                 if (path == null) {
                     path = new Path2D.Double();
                     path.moveTo(o.getX(), o.getY());
                     shapes.add(shape = path);
                     colors.add(colorSelected);
                 }
                 path.lineTo(e.getX(), e.getY());
                 panel.repaint();
             }
         }, new Tool("rect") {
             @Override
             public void mouseDragged(MouseEvent e) {
                 Rectangle2D.Double rect = (Rectangle2D.Double) shape;
                 if (rect == null) {
                     rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
                     shapes.add(shape = rect);
                     colors.add(colorSelected);
                 }
                 rect.setRect(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()), Math.abs(e.getX() - o.getX()),
                         Math.abs(e.getY() - o.getY()));
                 panel.repaint();
             }
         }, new Tool("ellipse") {
             @Override
             public void mouseDragged(MouseEvent e) {
                 Ellipse2D.Double ellipse = (Ellipse2D.Double) shape;
                 if (ellipse == null) {
                     ellipse = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
                     shapes.add(shape = ellipse);
                     colors.add(colorSelected);
                 }
                 ellipse.setFrame(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()),
                         Math.abs(e.getX() - o.getX()), Math.abs(e.getY() - o.getY()));
                 panel.repaint();
             }
         } };
    Tool tool;

    JPanel panel;

    CircularMenu menu;

    public Paint(String title) {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        add(new JToolBar() {
            {
                for (AbstractAction tool : tools)
                    add(tool);
            }
        }, BorderLayout.NORTH);

        add(new JToolBar(SwingConstants.VERTICAL) {
            {
                for (JButton button : colorButtons)
                    add(button);
            }
        }, BorderLayout.WEST);

        add(panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());

                for (int i = 0; i < shapes.size(); i++) {
                    g2.setColor(colors.get(i));
                    g2.draw(shapes.get(i));
                }
            }
        });

        pack();
        setVisible(true);

        menu = new CircularMenu(1000, 1000, 125, 50);

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
        this.add(menu);

        this.setSize(1000, 1000);
        setVisible(true);
        setLayout(null);
        addMouseListener(new MouseListener() {

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

    /* main *********************************************************************/

    public static void main(String[] args) {

        JFrame paint = new Paint("paint");

        paint.getContentPane().setLayout(new BorderLayout());
        paint.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
