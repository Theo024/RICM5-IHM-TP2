package ihm;
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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputListener;

/* paint *******************************************************************/

@SuppressWarnings("serial")
class Paint extends JFrame {
    Vector<Shape> shapes = new Vector<>();

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
                 }
                 ellipse.setFrame(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()),
                         Math.abs(e.getX() - o.getX()), Math.abs(e.getY() - o.getY()));
                 panel.repaint();
             }
         } };
    Tool tool;

    JPanel panel;

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
        add(panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(Color.BLACK);
                for (Shape shape : shapes)
                    g2.draw(shape);
            }
        });

        pack();
        setVisible(true);
    }

    /* main *********************************************************************/

    public static void main(String argv[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Paint("paint");
            }
        });
    }
}
