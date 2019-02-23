package ui;

import java.lang.Thread.State;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.BlockQueue;
import core.DifficultyManager;
import core.Direction;
import core.EventTimer;
import core.IBlock;
import core.IGameEvent;
import core.Rotation;
import core.ScoreManager;
import core.StaticLevel;

public class Game extends BasicGameState{
	
	/**
	 * Current displayed block
	 */
	private IBlock m_block;
	
	/**
	 * Current level
	 */
	private StaticLevel m_level;
	
	/**
	 * The block queue the player interacts with
	 */
	private BlockQueue m_queue;
	
	/**
	 * Manager to keep track of the player's score
	 */
	private ScoreManager m_score;
	
	/**
	 * Manager to update the difficulty
	 */
	private DifficultyManager m_difficulty;
	
	/**
	 * Play while game is not over
	 */
	private boolean m_isGameOver;
	
	/**
	 * Custom timer to go back to the main menu after game is over
	 */
	private EventTimer m_timer;
	
	/**
	 * X position of the score
	 */
	public static float SCORE_X = 310f;
	
	/**
	 * Y position of the score
	 */
	public static float SCORE_Y = 50f;
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		m_level = new StaticLevel();
		m_queue = new BlockQueue(m_level);
		m_block = m_queue.getCurrentBlock();
		m_difficulty = new DifficultyManager();
		m_score = new ScoreManager();
		m_isGameOver = false;
		m_timer = new EventTimer(false, 1500, new IGameEvent() {

			@Override
			public void executeEvent() {
				sbg.enterState(StateID.MENU_ID);
			}
			
		});
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException {
		if(!m_isGameOver) {
			m_level.render(graphics);
			m_block.render();
			m_queue.render();
			graphics.drawString("Score : " + m_score.getScore(), SCORE_X, SCORE_Y);
		} else {
			graphics.drawString("GAME OVER", m_level.CELL_WIDTH_NUMBER*m_level.CELL_SIZE/2.0f, m_level.CELL_HEIGHT_NUMBER*m_level.CELL_SIZE/2.0f);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(!m_isGameOver) {
			m_score.update(m_level);
			m_queue.update();
			m_block = m_queue.getCurrentBlock();
			m_block.update(delta, m_difficulty.getCurrentSpeed(m_score));

			if(gc.getInput().isKeyPressed(Input.KEY_LEFT)){
				m_block.move(Direction.LEFT);
			}
			if(gc.getInput().isKeyPressed(Input.KEY_RIGHT)){
				m_block.move(Direction.RIGHT);
			}
			if(gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
				m_block.zap(delta, m_difficulty.getCurrentSpeed(m_score));
			}
			if(gc.getInput().isKeyPressed(Input.KEY_R)) {
				m_block.rotate(Rotation.CLOCKWISE);
			}
			if(gc.getInput().isKeyPressed(Input.KEY_E)) {
				m_block.rotate(Rotation.ANTICLOCKWISE);
			}
			if(gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
				m_queue.switchToSaveBlock();
			}
			
			m_level.update(delta);
			
			m_isGameOver = m_level.isLevelFull();
		} else {
			//GAME OVER
			m_timer.update(delta);
		}
	}

	@Override
	public int getID() {
		return StateID.GAME_ID;
	}
	
	public StaticLevel getLevel() {
		return m_level;
	}

}
