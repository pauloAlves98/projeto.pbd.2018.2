package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.border.AbstractBorder;

import br.com.palves.pbd.view.FieldRedondo.RoundedCornerBorder;

public class FieldFormattedRedondo extends JFormattedTextField {
	public static void main(String[] args) {
//		JFrame j = new JFrame ();
//		j.setLayout(new GridLayout(1,1));
//		j.setSize(300,200);
//		FieldRedondo f = new FieldRedondo();
//		j.add(new FieldRedondo());
//		j.setVisible(true);
	}

	@Override protected void paintComponent(Graphics g) {
		if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setPaint(getBackground());
			g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
					0, 0, getWidth() - 1, getHeight() - 1));
			g2.dispose();
		}
		super.paintComponent(g);
	}
	@Override public void updateUI() {
		super.updateUI();
		setOpaque(false);
		setBorder(new RoundedCornerBorder());
	}
	class RoundedCornerBorder extends AbstractBorder {
		private final Color ALPHA_ZERO = new Color(0x0, true);
		@Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Shape border = getBorderShape(x, y, width - 1, height - 1);
			g2.setPaint(ALPHA_ZERO);
			Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
			corner.subtract(new Area(border));
			g2.fill(corner);
			g2.setPaint(Color.GRAY);
			g2.draw(border);
			g2.dispose();
		}
		public Shape getBorderShape(int x, int y, int w, int h) {
			int r = h; //h / 2;
			return new RoundRectangle2D.Double(x, y, w, h, r, r);
		}
		@Override public Insets getBorderInsets(Component c) {
			return new Insets(4, 8, 4, 8);
		}
		@Override public Insets getBorderInsets(Component c, Insets insets) {
			insets.set(4, 8, 4, 8);
			return insets;
		}
	}
}
