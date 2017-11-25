package mouse;

import java.awt.event.MouseEvent;

public abstract class MouseState {
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseMoved(MouseEvent e);
}
