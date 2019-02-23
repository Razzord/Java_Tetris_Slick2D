package ui.button;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

/**
 * 
 * Abstract class to give all the function a button has
 * 
 * @author Jeremy
 *
 */
public abstract class GameButton {
	
	/**
	 * Idle image of the button
	 */
	protected Image m_image;
	
	/**
	 * Hover image of the button
	 */
	protected Image m_hover;
	
	/**
	 * Reference to the current drawn image
	 */
	protected Image m_currentImage;
	
	/**
	 * Tells if the button is hovered by the mouse
	 */
	protected boolean m_isHover = false;
	
	/**
	 * X coordinate to draw the button
	 */
	protected float m_x;
	
	/**
	 * Y coordinate to draw the button
	 */
	protected float m_y;
	
	
	/**
	 * Draw the button on the screen
	 */
	public void render() {
		m_currentImage.draw(m_x, m_y);
	}
	
	/**
	 * Update data of this button according the the gamecontainer.
	 * Uses the mouse to update the hover state and if the button was clicked, returns true
	 * @param gc_ GameContainer used to check what the mouse does
	 * @return True if the button was clicked
	 */
	public boolean update(GameContainer gc_) {
		m_isHover = isHovering(gc_.getInput().getMouseX(), gc_.getInput().getMouseY());
		
		if(m_isHover) {
			m_currentImage = m_hover;
			
			if(gc_.getInput().isMouseButtonDown(0)) {
				return true;
			}
		} else {
			m_currentImage = m_image;
		}
		
		return false;
	}
	
	/**
	 * Tells if the button is hovered by the mouse coordinates
	 * @param x_ X coordinate of the mouse
	 * @param y_ Y coordinate of the mouse
	 * @return True if the mouse hovers the button
	 */
	private boolean isHovering(float x_, float y_) {
		if( (m_x <= x_ && x_ <= m_x + m_currentImage.getWidth() ) && (m_y <= y_ && y_ <= m_y + m_currentImage.getHeight()) ){
			return true;
		} else {
			return false;
		}
	}
}
