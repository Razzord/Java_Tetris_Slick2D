package ui.button;

/**
 * 
 * Factory to instantiate buttons
 * 
 * @author Jeremy
 *
 */
public class ButtonFactory {
	/**
	 * Create a new play button at specific coordinates
	 * @param x_ X coordinate
	 * @param y_ Y coordinate
	 * @return A new play button
	 */
	public static GameButton createPlayButton(float x_, float y_) {
		return new PlayButton(x_, y_);
	}
	
	/**
	 * Create a new exit button at specific coordinates
	 * @param x_ X coordinate
	 * @param y_ Y coordinate
	 * @return A new exit button
	 */
	public static GameButton createExitButton(float x_, float y_) {
		return new ExitButton(x_, y_);
	}
}
