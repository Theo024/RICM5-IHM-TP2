package ihm.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuLabel extends JPanel {

    private int    size;
    private Color  color;
    private Color  activeColor;
    private Color  idleColor;
    private String texte;

    public MenuLabel(String text, Color idleColor, Color activeColor, int size) {
        color = idleColor;
        this.idleColor = idleColor;
        this.activeColor = activeColor;
        texte = text;
        this.size = size;
        this.setSize(new Dimension(size + 10, size + 10));
        setOpaque(false);
        ajouterListener();
    }

    private void ajouterListener() {
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                color = idleColor;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                color = activeColor;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                repaint();

            }

        });
    }

    @Override
    public void paint(Graphics arg0) {
        super.paint(arg0);

        Graphics2D g2d = (Graphics2D) arg0;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(3));

        GradientPaint gradient = new GradientPaint(size / 2, 4, color, size / 2, 4 + size,
                new Color(255, 255, 255, 200));

        g2d.setPaint(gradient);
        g2d.fillRect(4, 4, size, size);

        gradient = new GradientPaint(size / 2, 4, Color.white, size / 2, 4 + size / 2,
                new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));

        g2d.setPaint(gradient);
        g2d.fillRect(4 + size / 5, 4, 5 * size / 8, size / 3);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Calibri", Font.BOLD, 14));

        FontMetrics fm = g2d.getFontMetrics();

        int x = (getWidth() - fm.stringWidth(texte)) / 2;
        int y = fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2;

        g2d.drawString(texte, x, y);
    }
}
