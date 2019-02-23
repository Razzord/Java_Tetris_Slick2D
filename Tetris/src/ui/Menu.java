package ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ui.button.ButtonFactory;
import ui.button.GameButton;

public class Menu extends BasicGameState {
	
	/**
	 * Play button. Clicking on it should start the game
	 */
	private GameButton m_playButton;
	
	/**
	 * Exit button. Clicking on it should end the game
	 */
	private GameButton m_exitButton;
	
	/**
	 * Title image
	 */
	private Image m_title;
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		m_playButton = ButtonFactory.createPlayButton(100.f, 250.f);
		m_exitButton = ButtonFactory.createExitButton(100f,  350f);
		
		try {
			m_title = new Image("sprites/tetris_title.png");
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException {
		m_playButton.render();
		m_exitButton.render();
		m_title.draw(100f, 50f);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(m_playButton.update(gc)) {
			sbg.enterState(StateID.GAME_ID);
		}
		
		if(m_exitButton.update(gc)) {
			System.exit(0);
		}
	}

	@Override
	public int getID() {
		return StateID.MENU_ID;
	}

}
