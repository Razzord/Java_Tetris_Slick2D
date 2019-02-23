package ui.button;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

class PlayButton extends GameButton {

	/**
	 * Constructor
	 * @param x_ X coordinate to draw the button
	 * @param y_ Y coordinate to draw the button
	 */
	public PlayButton(float x_, float y_) {
		m_x = x_;
		m_y = y_;
		
		try {
			m_image = new Image("sprites/button_play.png");
			m_hover = new Image("sprites/button_play_hover.png");
			m_currentImage = m_image;
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
}
