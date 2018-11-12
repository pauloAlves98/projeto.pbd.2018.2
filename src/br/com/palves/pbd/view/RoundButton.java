package br.com.palves.pbd.view;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;

import javax.swing.Action;
import javax.swing.Icon;

public class RoundButton extends RoundedCornerButton {
    protected RoundButton() {
        super();
    }
    protected RoundButton(Icon icon) {
        super(icon);
    }
    protected RoundButton(String text) {
        super(text);
    }
    protected RoundButton(Action a) {
        super(a);
        //setAction(a);
    }
    protected RoundButton(String text, Icon icon) {
        super(text, icon);
        //setModel(new DefaultButtonModel());
        //init(text, icon);
    }
    @Override public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        int s = Math.max(d.width, d.height);
        d.setSize(s, s);
        return d;
    }
    @Override protected void initShape() {
        if (!getBounds().equals(base)) {
            base = getBounds();
            shape = new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
            border = new Ellipse2D.Double(FOCUS_STROKE, FOCUS_STROKE,
                                          getWidth() - 1 - FOCUS_STROKE * 2,
                                          getHeight() - 1 - FOCUS_STROKE * 2);
        }
    }
}

