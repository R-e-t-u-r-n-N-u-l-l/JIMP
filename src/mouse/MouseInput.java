package mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
	private MouseState currentMouseState;
	
	public MouseInput(MouseState mouseState) {
		this.currentMouseState = mouseState;
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		if(currentMouseState != null)
			currentMouseState.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		if(currentMouseState != null)
			currentMouseState.mouseReleased(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		if(currentMouseState != null)
			currentMouseState.mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		if(currentMouseState != null)
			currentMouseState.mouseMoved(e);
	}
	
	public MouseState getCurrentMouseState() {
		return currentMouseState;
	}
	
	public void setCurrentMouseState(MouseState currentMouseState) {
		this.currentMouseState = currentMouseState;
	}
}
